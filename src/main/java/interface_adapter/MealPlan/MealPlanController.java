package interface_adapter.MealPlan;

import org.json.JSONObject;
import use_case.MealPlan.MealPlanInputBoundary;
import use_case.MealPlan.MealPlanInputData;

/**
 * The MealPlanController class serves as the controller.
 */
public class MealPlanController {

    // An input boundary interface that defines the operations for meal plan use cases.
    private MealPlanInputBoundary mealPlanInputBoundary;

    /**
     * Constructor for MealPlanController.
     *
     * @param userMealPlanInteractor The interactor implementing the MealPlanInputBoundary interface.
     *                               This is used to process meal plan.
     */
    public MealPlanController(MealPlanInputBoundary userMealPlanInteractor) {
        this.mealPlanInputBoundary = userMealPlanInteractor;
    }

    /**
     * Executes the meal plan operation for a given user.
     *
     * @param username The username for which the meal plan is being generated or managed.
     *                 This is passed to the interactor for further processing.
     */
    public void execute(String username) {
        // Create an input data object encapsulating the username.
        final MealPlanInputData mealPlanInputData = new MealPlanInputData(username);

        // Delegate the execution of the meal plan use case to the input boundary.
        mealPlanInputBoundary.execute(mealPlanInputData);
    }
}

