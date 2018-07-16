package com.example.android.vlad.baking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad.
 */

public class Recipe implements Parcelable {

    public Recipe(){}

    public Recipe(Parcel parcel){
        name = parcel.readString();
        id = parcel.readInt();
        ingredients = new ArrayList<>();
        parcel.readList(ingredients, Ingredient.class.getClassLoader());
        steps = new ArrayList<>();
        parcel.readList(steps, Step.class.getClassLoader());
    }

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    private List<Ingredient> ingredients;
    private List<Step> steps;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void addStep(Step step){
        this.steps.add(step);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(id);
        parcel.writeList(ingredients);
        parcel.writeList(steps);
    }

    /* Parcel creator */
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {

        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
