package com.example.android.vlad.baking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.vlad.baking.model.Recipe;

import com.example.android.vlad.baking.MasterListRecipeFragment.onRecipeClickListener;

import java.util.List;

/**
 * Created by Vlad
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder>{

    public RecipeAdapter(Context context, onRecipeClickListener clickHandler){
        this.clickHandler = clickHandler;
        this.context = context;
    }

    private final onRecipeClickListener clickHandler;
    private Context context;



    private List<Recipe> recipeList;

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView recipeTextView;
        public RecipeAdapterViewHolder(View view){
            super(view);
            recipeTextView = view.findViewById(R.id.recipe_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Recipe currentRecipe = recipeList.get(adapterPosition);
            clickHandler.onRecipeClick(currentRecipe);
        }
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.fragment_recipe;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, int position) {

        Recipe currentRecipe = recipeList.get(position);

        holder.recipeTextView.setText(currentRecipe.getName());
    }

    @Override
    public int getItemCount() {
        if (null == recipeList) return 0;
        return recipeList.size();
    }

    public void setRecipeData(List<Recipe> newRecipeList){
        this.recipeList = newRecipeList;
        notifyDataSetChanged();
    }
}
