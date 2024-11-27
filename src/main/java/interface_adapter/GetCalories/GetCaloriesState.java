package interface_adapter.GetCalories;

import org.json.JSONObject;

/**
 * State class that holds the current state of the calorie information.
 * Maintains the data that is displayed in the calorie view.
 */
public class GetCaloriesState {
    private String recipeName = "";
    private int calories = 0;
    private String caloriesError = null;
    private JSONObject caloriesData = null;
    private String username;

    /**
     * Constructs a new GetCalorieState by copying existing data.
     * @param copy The state to copy from
     */
    public GetCaloriesState(GetCaloriesState copy) {
        recipeName = copy.recipeName;
        calories = copy.calories;
        caloriesError = copy.caloriesError;
    }

    /**
     * Constructs a new empty GetCaloriesState.
     */
    public GetCaloriesState() {}

    /**
     * Getter and setter for recipe name.
     * @return The recipe name
     */
    public String getRecipeName() { return recipeName; }
    public void setRecipeName(String recipeName) { this.recipeName = recipeName; }

    /**
     * Getter and setter for the calorie count.
     * @return THe amount of calories
     */
    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }

    /**
     * Getter and setter for the error message.
     * @return Error message
     */
    public String getCaloriesError() { return caloriesError; }
    public void setCaloriesError(String error) { this.caloriesError = error; }

    /**
     * Getter and setter for recipe object containing the required recipe information.
     * @return Recipe JSONObject
     */
    public JSONObject getRecipeObject() { return caloriesData; }
    public void setRecipeObject(JSONObject recipeObject) {this.caloriesData = recipeObject; }

    /**
     * Getter and setter for username of current user.
     * @return The username
     */
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

}
