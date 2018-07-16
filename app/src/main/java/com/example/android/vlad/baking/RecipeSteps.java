package com.example.android.vlad.baking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.vlad.baking.model.Step;

/**
 * Created by Vlad
 */

public class RecipeSteps extends AppCompatActivity implements MasterListRecipeStepsFragment.onRecipeStepClickListener {

    boolean isTablet;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        isTablet = getResources().getBoolean(R.bool.isTablet);
        if (isTablet){

            StepFragment stepFragment = new StepFragment();

            fragmentManager = getSupportFragmentManager();
            Step step = new Step();
            step.setShortDescription("Please select a step to get more information");
            step.setVideoURL("");
            step.setThumbnailURL("");
            stepFragment.setStep(step);

            fragmentManager.beginTransaction()
                    .add(R.id.step_container, stepFragment)
                    .commit();

        }
    }

    @Override
    public void onRecipeStepClick(Step step) {

        if (isTablet){
            StepFragment stepFragment = new StepFragment();

            stepFragment.setStep(step);

            fragmentManager.beginTransaction()
                    .replace(R.id.step_container, stepFragment)
                    .commit();
        }
        else {
            final Intent intent = new Intent(this, StepActivity.class);
            intent.putExtra(getString(R.string.step_key), step);
            startActivity(intent);
        }
    }
}
