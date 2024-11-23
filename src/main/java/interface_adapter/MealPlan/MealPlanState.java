package interface_adapter.MealPlan;

import org.json.JSONArray;

public class MealPlanState {
    private JSONArray recipes;

    public JSONArray getRecipes() {
        return recipes;
    }

    public void setRecipes(JSONArray recipes) {
        this.recipes = recipes;
    }
}
