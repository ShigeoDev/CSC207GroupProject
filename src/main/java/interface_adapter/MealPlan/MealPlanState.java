package interface_adapter.MealPlan;

import org.json.JSONObject;

/**
 * Represents the state of a meal plan, including recipes and the user.
 */
public class MealPlanState {
    // Array of recipes in the meal plan.
    private JSONObject[] recipes;
    // Username associated with the meal plan.
    private String user;

    /**
     * Gets the recipes in the meal plan.
     * @return Array of recipes.
     */
    public JSONObject[] getRecipes() {
        return recipes;
    }

    /**
     * Sets the recipes in the meal plan.
     * @param recipes Array of recipes.
     */
    public void setRecipes(JSONObject[] recipes) {
        this.recipes = recipes;
    }

    /**
     * Sets the username associated with the meal plan.
     * @param user The username.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the username associated with the meal plan.
     * @return The username.
     */
    public String getUser() {
        return user;
    }
}
