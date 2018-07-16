package com.example.android.vlad.baking;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;


/**
 * Created by Vlad
 */

public class BakingWidgetProvider  extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, String ingredientList, int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_baking);
        views.setTextViewText(R.id.widget_recipe_ingredients, ingredientList);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_baking);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // Perform any action when one or more AppWidget instances have been deleted
    }

    @Override
    public void onEnabled(Context context) {
        // Perform any action when an AppWidget for this provider is instantiated
    }

    @Override
    public void onDisabled(Context context) {
        // Perform any action when the last AppWidget instance for this provider is deleted
    }

    public static void updateBakingWidgets(Context context, AppWidgetManager appWidgetManager,
                                               int[] appWidgetIds){
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public static void updateBakingWidgets(Context context, AppWidgetManager appWidgetManager,
                                           String ingredientList, int[] appWidgetIds){
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, ingredientList, appWidgetId);
        }
    }
}
