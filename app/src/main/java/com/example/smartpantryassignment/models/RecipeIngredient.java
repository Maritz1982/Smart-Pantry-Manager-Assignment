package com.example.smartpantryassignment.models;


    public class RecipeIngredient{
        private int recipeId;
        private String ingredient;
        private double quantity;
        private String unitMeasure;
        public RecipeIngredient(){
        }
        public RecipeIngredient(int recipeId,
                                String ingredient,
                                double quantity,
                                String unitMeasure){
            this.recipeId = recipeId;
            this.ingredient = ingredient;
            this.quantity = quantity;
            this.unitMeasure = unitMeasure;
        }
        public int getRecipeId(){
            return recipeId;
        }
        public void setRecipeId(int recipeId){
            this.recipeId = recipeId;
        }
        public String getIngredient() {
           return  ingredient;
        }
            public void setIngredient(String ingredient){
            this.ingredient = ingredient;
        }
        public double getQuantity(){
            return quantity;
        }
        public void setQuantity(double quantity){
            this.quantity = quantity;
        }
        public String getUnitMeasure(){
            return unitMeasure;
        }
        public void setUnitMeasure(String unitMeasure){
            this.unitMeasure = unitMeasure;
        }

    }

