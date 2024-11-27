package use_case.GetCalories;

import data_access.FileUserDataAccessObject;
import org.json.JSONArray;
import org.json.JSONObject;

import data_access.ApiDataAccessObject;

/**
 * An interactor class that implements the logic for the Get Calories use case.
 * This class makes API calls to retrieve recipe calorie information and
 * manages save operations.
 */
public class GetCaloriesInteractor implements GetCaloriesInputBoundary {
    final GetCaloriesOutputBoundary getCaloriesPresenter;
    final ApiDataAccessObject apiDataAccessObject;
    final FileUserDataAccessObject fileUserDataAccessObject;

    /**
     * Constructs a new GetCaloriesInteractor.
     * @param apiDataAccessObject For making API calls to retrieve the data
     * @param getCaloriesOutputBoundary For presenting the results
     * @param fileUserDataAccessObject For saving recipe data
     */
    public GetCaloriesInteractor(ApiDataAccessObject apiDataAccessObject,
                                 GetCaloriesOutputBoundary getCaloriesOutputBoundary,
                                 FileUserDataAccessObject fileUserDataAccessObject) {
        this.apiDataAccessObject = apiDataAccessObject;
        this.getCaloriesPresenter = getCaloriesOutputBoundary;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
    }

    /**
     * Executes the primary Get Calories use case logic.
     * Retrieves the recipe information from the API and prepares the
     * output through the presenter.
     * @param getCaloriesInputData Input data containing recipe name and user information
     */
    @Override
    public void execute(GetCaloriesInputData getCaloriesInputData) {
        try {
            JSONArray hits = apiDataAccessObject.getRecipebyName(getCaloriesInputData.getRecipeName());
            if (hits.length() > 0) {
                JSONObject firstRecipe = hits.getJSONObject(0).getJSONObject("recipe");
                String actualRecipeName = firstRecipe.getString("label");
                int calories = firstRecipe.getInt("calories");

                GetCaloriesOutputData getCaloriesOutputData =
                        new GetCaloriesOutputData(actualRecipeName, calories, firstRecipe, getCaloriesInputData.getUsername());
                getCaloriesPresenter.prepareSuccessView(getCaloriesOutputData);
            }
        } catch (Exception e) {
            getCaloriesPresenter.prepareFailView(e.getMessage());
        }
    }

    /**
     * Saves a recipe for a specific user.
     * @param recipe The recipe data to save
     * @param username The user who wants to save the recipe
     */
    @Override
    public void saveRecipe(JSONObject recipe, String username) {
        fileUserDataAccessObject.saveRecipe(recipe, username);
    }

    /**
     * Navigates back to the homepage view.
     */
    @Override
    public void backToHome() {
        getCaloriesPresenter.prepareHomeView();
    }

    /**
     * Retrieves the calories of a recipe for a specific user.
     * @param username The username of the user getting the calorie information
     */
    @Override
    public void getCalories(String username) {
        getCaloriesPresenter.prepareGetCaloriesView(username);
    }
}