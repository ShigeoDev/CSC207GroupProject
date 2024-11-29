package use_case.MealPlan;

import entity.Recipe;
import entity.User;
import org.json.JSONObject;

public interface MealPlanDataAccessInterface {

    public JSONObject getRecipebyMeal(String mealName);

}
