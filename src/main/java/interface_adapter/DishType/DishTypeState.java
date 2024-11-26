package interface_adapter.DishType;

import org.json.JSONArray;

public class DishTypeState {
    private String DishType = null;
    private String error;
    private JSONArray recipes = null;

    public String getDishType() {
        return DishType;
    }

    public void setCurrentDishType(String currentDishType) {
        this.DishType = currentDishType;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setRecipes(JSONArray recipes) {
        this.recipes = recipes;
    }

    public JSONArray getRecipes(){
        return recipes;
    }
}