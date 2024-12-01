package interface_adapter.store_recipe;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Represents the state for storing recipes, including the username and recipe list.
 */
public class StoreRecipeState {

    // The username associated with the stored recipes.
    private String username;
    // List of recipes.
    private ArrayList<JSONObject> recipes;

    /**
     * Gets the list of stored recipes.
     * @return The list of recipes.
     */
    public ArrayList<JSONObject> getRecipes() {
        return recipes;
    }

    /**
     * Gets the username associated with the stored recipes.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the stored recipes.
     * @param username The username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the list of stored recipes.
     * @param recipes The list of recipes.
     */
    public void setRecipes(ArrayList<JSONObject> recipes) {
        this.recipes = recipes;
    }
}
