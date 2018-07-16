package com.example.android.vlad.baking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.vlad.baking.model.Recipe;

public class MainActivity extends AppCompatActivity implements MasterListRecipeFragment.onRecipeClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onRecipeClick(Recipe recipe) {
        final Intent intent = new Intent(this, RecipeSteps.class);
        intent.putExtra(getString(R.string.recipe_key), recipe);
        startActivity(intent);

    }
}
