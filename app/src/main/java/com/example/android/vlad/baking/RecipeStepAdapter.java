package com.example.android.vlad.baking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.vlad.baking.model.Step;
import com.example.android.vlad.baking.MasterListRecipeStepsFragment.onRecipeStepClickListener;

import java.util.List;

/**
 * Created by Vlad
 */

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepAdapterViewHolder> {

    public RecipeStepAdapter(Context context, onRecipeStepClickListener clickHandler){
        this.clickHandler = clickHandler;
        this.context = context;
    }

    private final onRecipeStepClickListener clickHandler;
    private Context context;



    private List<Step> recipeSteps;

    public class RecipeStepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView recipeStepTextView;
        public RecipeStepAdapterViewHolder(View view){
            super(view);
            recipeStepTextView = view.findViewById(R.id.recipe_step_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Step currentStep = recipeSteps.get(adapterPosition);
            clickHandler.onRecipeStepClick(currentStep);
        }
    }

    @Override
    public RecipeStepAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.fragment_recipe_step;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RecipeStepAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeStepAdapterViewHolder holder, int position) {

        Step currentRecipeStep = recipeSteps.get(position);

        if (currentRecipeStep!= null) {
            holder.recipeStepTextView.setText(currentRecipeStep.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        if (null == recipeSteps) return 0;
        return recipeSteps.size();
    }

    public void setRecipeStepData(List<Step> newStepList){
        this.recipeSteps = newStepList;
        notifyDataSetChanged();
    }

}
