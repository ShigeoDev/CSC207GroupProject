package use_case.MealPlan;

import org.json.JSONObject;

public interface MealPlanOutputBoundary {

    void goHome();

    void prepareSuccessView(MealPlanOutputData mealPlanOutputData);
}
