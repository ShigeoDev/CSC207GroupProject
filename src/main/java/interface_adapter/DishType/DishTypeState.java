package interface_adapter.DishType;

import org.json.JSONArray;

/**
 * The DishTypeState class is responsible for managing the state related to dish type search results.
 * It stores the current dish type, any error messages, and the recipe data.
 */
public class DishTypeState {

    private String DishType = null;
    private String error;
    private JSONArray recipes = null;

    /**
     * Get the current dish type.
     *
     * @return the current dish type.
     */
    public String getDishType() {
        return DishType;
    }

    /**
     * Sets the current dish type.
     *
     * @param currentDishType the dish type to set.
     */
    public void setCurrentDishType(String currentDishType) {
        this.DishType = currentDishType;
    }

    /**
     * Sets an error message to indicate a failure in the dish type operation.
     *
     * @param error the error message to set.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Retrieves the error message.
     *
     * @return the error message, or null if there is no error.
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the recipes data associated with the dish type search.
     *
     * @param recipes the recipes data to set.
     */
    public void setRecipes(JSONArray recipes) {
        this.recipes = recipes;
    }

    /**
     * Retrieves the recipes data associated with the dish type search.
     *
     * @return the recipes data, or null if no recipes are available.
     */
    public JSONArray getRecipes(){
        return recipes;
    }
}
