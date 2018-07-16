package com.example.android.vlad.baking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.vlad.baking.model.Step;

/**
 * Created by Vlad
 */

public class StepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Step step = null;
        Intent intent = getIntent();
        if (intent != null){
            step = intent.getParcelableExtra(getString(R.string.step_key));
        }

        StepFragment stepFragment = new StepFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        stepFragment.setStep(step);

        fragmentManager.beginTransaction()
                .add(R.id.step_container, stepFragment)
                .commit();
    }

}
