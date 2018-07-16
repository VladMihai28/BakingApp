package com.example.android.vlad.baking;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.vlad.baking.model.Recipe;
import com.example.android.vlad.baking.utils.RecipeJsonParser;


import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Vlad
 * */

public class MasterListRecipeFragment extends Fragment{

    private RequestQueue requestQueue;
    List<Recipe> recipes;
    private RecyclerView recipeRecyclerView;
    private RecipeAdapter recipeAdapter;

    onRecipeClickListener callback;

    public MasterListRecipeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_recipes_master_list, container, false);

        getJsonData();

        GridLayoutManager layoutManager = null;
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if (isTablet){
            layoutManager = new GridLayoutManager(this.getContext(), 3);
        }
        else {
            layoutManager = new GridLayoutManager(this.getContext(), 1);
        }

        recipeRecyclerView = rootView.findViewById(R.id.recyclerview_recipe);
        recipeRecyclerView.setLayoutManager(layoutManager);
        recipeRecyclerView.setHasFixedSize(true);

        recipeAdapter = new RecipeAdapter(this.getContext(), callback);
        recipeRecyclerView.setAdapter(recipeAdapter);

        return rootView;
    }

    private void getJsonData(){
        requestQueue = Volley.newRequestQueue(this.getContext());
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, getString(R.string.recipe_list), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseJsonResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        requestQueue.add(req);
    }

    private void parseJsonResponse(JSONArray jsonArray){
        RecipeJsonParser recipeJsonParser = new RecipeJsonParser();
        recipes = recipeJsonParser.parseRecipes(jsonArray);
        recipeAdapter.setRecipeData(recipes);
    }

    public interface onRecipeClickListener{
        void onRecipeClick(Recipe recipe);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            callback = (onRecipeClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
            + " must implement onRecipeClickListener");
        }
    }

}
