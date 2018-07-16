package com.example.android.vlad.baking.utils;

import com.example.android.vlad.baking.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad
 */

public class RecipeJsonParser {

    public RecipeJsonParser(){}

    private Gson gson;

    public List<Recipe> parseRecipes(JSONArray recipes){
        List<Recipe> recipeList = new ArrayList<>();
        initializeGson();
        for(int i = 0; i < recipes.length(); i++){
            try {
                JSONObject currentRecipeJson = recipes.getJSONObject(i);
                Recipe recipe = gson.fromJson(currentRecipeJson.toString(), Recipe.class);
                recipeList.add(recipe);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return recipeList;
    }

    private void initializeGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
    }
}
