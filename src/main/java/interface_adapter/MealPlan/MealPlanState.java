package interface_adapter.MealPlan;

import org.json.JSONObject;

public class MealPlanState {
    private JSONObject[] recipes;

    public JSONObject[] getRecipes() {
        return recipes;
    }

    public void setRecipes(JSONObject[] recipes) {
        this.recipes = recipes;
    }
}
