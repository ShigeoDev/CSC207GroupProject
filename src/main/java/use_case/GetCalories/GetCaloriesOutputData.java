package use_case.GetCalories;

import org.json.JSONObject;

/**
 * Contains the results of the Get Calories use case.
 * Includes the retrieved recipe information needed to display.
 */
public class GetCaloriesOutputData {
    private final int calories;
    private final String recipeName;
    private final JSONObject caloriesJSON;
    private final String username;

    /**
     * Constructor for the output data.
     * @param recipeName Name of recipe
     * @param calories Amount of calories
     * @param caloriesJSON Complete recipe data in JSON format
     * @param username Name of user
     */
    public GetCaloriesOutputData(String recipeName, int calories, JSONObject caloriesJSON, String username) {
        this.recipeName = recipeName;
        this.calories = calories;
        this.caloriesJSON = caloriesJSON;
        this.username = username;
    }

    /**
     * Getter for the calories in the recipe.
     * @return Amount of calories
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Getter for the recipe name.
     * @return The name of the recipe
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Getter for the complete recipe data.
     * @return The recipe data as a JSONObject
     */
    public JSONObject getRecipeObject() {
        return caloriesJSON;
    }

    /**
     * Getter for the username.
     * @return Name of user
     */
    public String getUsername() {
        return username;
    }
}