package interface_adapter.NutritionFilterPage;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * The State for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageState {
    private ArrayList<String> selectedNutrients = new ArrayList<>();
    private String selectionError;
    private JSONArray recipeDetails = null;
    private String searchError;
    private String username;

    public NutritionFilterPageState(NutritionFilterPageState copy) {
        this.selectedNutrients = copy.selectedNutrients;
        this.selectionError = copy.selectionError;
        this.recipeDetails = copy.recipeDetails;
        this.searchError = copy.searchError;
        this.username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public NutritionFilterPageState() {
    }

    /**
     * Retrieves the list of recipe details for the current state.
     * @return a {@code JSONArray} containing recipe details; may be empty but not {@code null}
     */
    public JSONArray getRecipeDetails() {
        return recipeDetails;
    }

    /**
     * Sets the list of recipe details for the current state.
     * <p>
     * This method replaces the existing list of recipe details with the provided list.
     * It first clears the current list and then adds all elements from the provided list.
     * If the provided list is {@code null}, the current list will be cleared.
     * @param recipeDetails the list of recipe details to set; may be {@code null} to clear the list
     */
    public void setRecipeDetails(JSONArray recipeDetails) {
        this.recipeDetails = recipeDetails;
    }

    /**
     * Retrieves the error message related to the recipe search, if any.
     * @return a {@code String} containing the search error message, or {@code null} if there is no error
     */
    public String getSearchError() {
        return searchError;
    }

    /**
     * Sets the search error message for the current state.
     * @param searchError the error message to set; may be {@code null} to clear the error
     */
    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
