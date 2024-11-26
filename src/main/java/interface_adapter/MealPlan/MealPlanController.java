package interface_adapter.MealPlan;

import org.json.JSONObject;
import use_case.MealPlan.MealPlanInputBoundary;
import use_case.MealPlan.MealPlanInputData;

public class MealPlanController {

    MealPlanInputBoundary mealPlanInputBoundary;

    public MealPlanController(MealPlanInputBoundary userMealPlanInteractor) {
        this.mealPlanInputBoundary = userMealPlanInteractor;
    }

    public void execute(String username) {
        final MealPlanInputData mealPlanInputData = new MealPlanInputData(username);
        mealPlanInputBoundary.execute(mealPlanInputData);
    }

    public void goHome() {
        mealPlanInputBoundary.goHome();
    }

}
