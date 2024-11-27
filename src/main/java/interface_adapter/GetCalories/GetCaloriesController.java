package interface_adapter.GetCalories;

import org.json.JSONObject;
import use_case.GetCalories.GetCaloriesInputBoundary;
import use_case.GetCalories.GetCaloriesInputData;
import view.RecipeSavePanel;

/**
 * A controller class that handles user interactions related to calorie information. Controls the flow of data for
 * the Get Calories use case. Handles user input to the interactor.
 */
public class GetCaloriesController {
    final GetCaloriesInputBoundary getCaloriesUseCaseInteractor;

    /**
     * Constructs a new GetCaloriesController with the specific use case interactor.
     * @param getCaloriesUseCaseInteractor The use case interactor to handle calorie retrieval
     */
    public GetCaloriesController(GetCaloriesInputBoundary getCaloriesUseCaseInteractor) {
        this.getCaloriesUseCaseInteractor = getCaloriesUseCaseInteractor;
    }

    /**
     * This method executes a recipe calorie lookup request for a specific recipe and user.
     * @param recipeName The name of the recipe to get calories for
     * @param username The username of the current user
     */
    public void execute(String recipeName, String username) {
        GetCaloriesInputData getCaloriesInputData = new GetCaloriesInputData(recipeName, username);
        getCaloriesUseCaseInteractor.execute(getCaloriesInputData);
    }

    /**
     * Naviaates and returns back to the homepage view
     */
    public void backToHome() {
        getCaloriesUseCaseInteractor.backToHome();
    }

    /**
     * Finds the calorie infomration for a specific user.
     * @param username The username of the user to get calorie information for
     */
    public void getCalories(String username) {
        getCaloriesUseCaseInteractor.getCalories(username);
    }
}

