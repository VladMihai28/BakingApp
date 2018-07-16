package com.example.android.vlad.baking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vlad
 */

public class Ingredient implements Parcelable {

    public Ingredient(){}

    public Ingredient(Parcel parcel){
        quantity = parcel.readString();
        measure = parcel.readString();
        ingredientDescription = parcel.readString();
    }

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("measure")
    private String measure;

    @SerializedName("ingredient")
    private String ingredientDescription;

    public String getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setIngredientDescription(String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredientDescription);
    }

    /* Parcel creator */
    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {

        @Override
        public Ingredient createFromParcel(Parcel parcel) {
            return new Ingredient(parcel);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
