package use_case.MealPlan;

import org.json.JSONObject;

public interface MealPlanInputBoundary {

    public void goHome();

    public void execute(MealPlanInputData mealPlanInputData);
}
