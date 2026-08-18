package com.example.smartpantryassignment.models;

public class PantryItem {
    private int pantryId;
    private String ingredient;
    private double quantity;
    private String unitMeasure;
    private String expireDate;
    public PantryItem() {
    }
    public PantryItem(int pantryId, String ingredient, double quantity,String unitMeasure, String expireDate){
        this.pantryId = pantryId;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unitMeasure = unitMeasure;
        this.expireDate = expireDate;
    }
    public int getPantryId(){
        return pantryId;
    }
        public void setPantryId(int pantryId){
            this.pantryId = pantryId;
    }
    public String getIngredient(){
        return ingredient;
    }
    public void setIngredient(String ingredient){
        this.ingredient = ingredient;
    }
    public double getQuantity() {
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
    public String getExpireDate(){
        return expireDate;
    }
    public void setExpireDate(String expireDate){
        this.expireDate = expireDate;
    }

}
