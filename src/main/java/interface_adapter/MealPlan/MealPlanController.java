package interface_adapter.MealPlan;

import org.json.JSONObject;
import use_case.MealPlan.MealPlanInputBoundary;

public class MealPlanController {

    MealPlanInputBoundary mealPlanInputBoundary;

    public MealPlanController(MealPlanInputBoundary userMealPlanInteractor) {
        this.mealPlanInputBoundary = userMealPlanInteractor;
    }

    public void goHome() {
        mealPlanInputBoundary.goHome();
    }

    public void saveRecipe(JSONObject recipe, String username) {
        mealPlanInputBoundary.saveRecipe(recipe, username);
    }
}
