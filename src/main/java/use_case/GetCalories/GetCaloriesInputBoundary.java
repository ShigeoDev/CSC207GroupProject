package use_case.GetCalories;

import org.json.JSONObject;

/**
 * Input boundary for the GetCalories use case.
 * This defines the methods that must be performed in the use case.
 */
public interface GetCaloriesInputBoundary {

    /**
     * Executes the get calories use case.
     * @param getCaloriesInputData Input data of the use case
     */
    void execute(GetCaloriesInputData getCaloriesInputData);

    /**
     * Saves a recipe for a user.
     * @param recipe Name of recipe
     * @param username Username of user
     */
    void saveRecipe(JSONObject recipe, String username);

    /**
     * Returns to the homepage view.
     */
    void backToHome();

    /**
     * Initiates the get calories method.
     * @param username User requesting the information
     */
    void getCalories(String username);
}