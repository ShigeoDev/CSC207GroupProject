package use_case.MealPlan;

import org.json.JSONObject;

public class MealPlanOutputData {
    private JSONObject[] recipes;
    private String username;

    public MealPlanOutputData(JSONObject[] recipes, String username) {
        this.recipes = recipes;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public JSONObject[] getRecipes() {
        return recipes;
    }
}

