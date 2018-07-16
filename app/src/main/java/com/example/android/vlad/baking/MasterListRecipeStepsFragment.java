package com.example.android.vlad.baking;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.vlad.baking.model.Ingredient;
import com.example.android.vlad.baking.model.Recipe;
import com.example.android.vlad.baking.model.Step;

import org.w3c.dom.Text;


/**
 * Created by Vlad
 */

public class MasterListRecipeStepsFragment extends Fragment {

    private RecyclerView recipeRecyclerView;
    private TextView textView;
    private RecipeStepAdapter recipeStepAdapter;
    private Recipe recipe;

    onRecipeStepClickListener callback;


    public MasterListRecipeStepsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_steps_master_list, container, false);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 1);
        textView = rootView.findViewById(R.id.ingredient_tv);
        recipeRecyclerView = rootView.findViewById(R.id.recyclerview_recipe_steps);

        recipeRecyclerView.setLayoutManager(layoutManager);
        recipeRecyclerView.setHasFixedSize(true);

        recipeStepAdapter = new RecipeStepAdapter(this.getContext(), callback);
        recipeRecyclerView.setAdapter(recipeStepAdapter);

        recipe = null;
        Intent intent = getActivity().getIntent();
        if (intent != null){
            recipe = intent.getParcelableExtra(getString(R.string.recipe_key));
        }

        if (savedInstanceState != null){
            recipe = savedInstanceState.getParcelable(getString(R.string.recipe_key));
        }

        StringBuilder ingredientList = createIngredientList(recipe);
        textView.setText(ingredientList);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this.getContext(), BakingWidgetProvider.class));
        BakingWidgetProvider.updateBakingWidgets(this.getContext(), appWidgetManager, ingredientList.toString(), appWidgetIds);

        recipeStepAdapter.setRecipeStepData(recipe.getSteps());


        return rootView;

    }

    private StringBuilder createIngredientList(Recipe recipe){
        StringBuilder ingredientsList = new StringBuilder();
        ingredientsList.append(recipe.getName());
        ingredientsList.append(System.lineSeparator());
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredientsList.append(ingredient.getIngredientDescription());
            ingredientsList.append(" ");
            ingredientsList.append(ingredient.getQuantity());
            ingredientsList.append(" ");
            ingredientsList.append(ingredient.getMeasure());
            ingredientsList.append(System.lineSeparator());
        }

        return ingredientsList;
    }

    public interface onRecipeStepClickListener{
        void onRecipeStepClick(Step step);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            callback = (onRecipeStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onRecipeStepClickListener");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(getString(R.string.recipe_key), recipe);
        super.onSaveInstanceState(outState);

    }

}