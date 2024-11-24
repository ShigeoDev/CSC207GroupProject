package interface_adapter.MealPlan;

import use_case.MealPlan.MealPlanInputBoundary;

public class MealPlanController {

    MealPlanInputBoundary mealPlanInputBoundary;

    public MealPlanController(MealPlanInputBoundary userMealPlanInteractor) {
        this.mealPlanInputBoundary = userMealPlanInteractor;
    }

    public void goHome() {
        mealPlanInputBoundary.goHome();
    }
}
