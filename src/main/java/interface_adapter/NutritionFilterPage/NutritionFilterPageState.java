package interface_adapter.NutritionFilterPage;

import java.util.List;

/**
 * The State for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageState {
    private List<String> selectedNutrients;
    private String selectionError;
    private List<String> recipeDetails;
    private String searchError;

    // Default constructor
    public NutritionFilterPageState() {
    }

    // Copy constructor
    public NutritionFilterPageState(NutritionFilterPageState copy) {
        this.selectedNutrients = copy.selectedNutrients;
        this.selectionError = copy.selectionError;
        this.recipeDetails = copy.recipeDetails;
        this.searchError = copy.searchError;
    }

    // Getters and Setters
    public List<String> getSelectedNutrients() {
        return selectedNutrients;
    }

    public void setSelectedNutrients(List<String> selectedNutrients) {
        this.selectedNutrients = selectedNutrients;
    }

    public String getSelectionError() {
        return selectionError;
    }

    public void setSelectionError(String selectionError) {
        this.selectionError = selectionError;
    }

    public List<String> getRecipeDetails() {
        return recipeDetails;
    }

    public void setRecipeDetails(List<String> recipeDetails) {
        this.recipeDetails = recipeDetails;
    }

    public String getSearchError() {
        return searchError;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }
}
