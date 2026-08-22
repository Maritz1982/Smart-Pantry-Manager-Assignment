package com.example.smartpantryassignment.models;

public class Recipe {
    private int recipeId;
    private String recipeProcess;
    private String recipeName;
    public Recipe(){

    }
    public Recipe(int recipeId,
                  String recipeName,
                  String recipeProcess) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeProcess = recipeProcess;
    }
        public int getRecipeId(){
            return recipeId;
        }
        public void setRecipeId(int recipeId){
            this.recipeId = recipeId;
        }
        public String getRecipeName() {
        return recipeName;
        }
        public void setRecipeName(String recipeName){
        this.recipeName = recipeName;
        }
        public String getRecipeProcess(){
        return recipeProcess;
        }
        public void setRecipeProcess(String recipeProcess){
        this.recipeProcess = recipeProcess;
        }
}
