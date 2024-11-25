package use_case.MealPlan;

import org.json.JSONObject;

public interface MealPlanDataAccessInterface {
    public void saveRecipe(JSONObject recipe, String username);
}
