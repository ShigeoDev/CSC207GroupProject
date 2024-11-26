package use_case.MealPlan;

import org.json.JSONObject;

public interface MealPlanDataAccessInterface {

    public JSONObject getRecipebyMeal(String mealName);

}
