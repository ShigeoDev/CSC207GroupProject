package interface_adapter.NutritionFilterPage;

import entity.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * The State for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageState {
    private List<String> selectedNutrients = new ArrayList<>();
    private String selectionError;
    private List<Recipe> recipeDetails = new ArrayList<>();
    private String searchError;

    public NutritionFilterPageState() {
    }

    public NutritionFilterPageState(NutritionFilterPageState copy) {
        this.selectedNutrients = copy.selectedNutrients;
        this.selectionError = copy.selectionError;
        this.recipeDetails = copy.recipeDetails;
        this.searchError = copy.searchError;
    }

    public List<String> getSelectedNutrients() {
        return selectedNutrients;
    }

    public void setSelectedNutrients(List<String> selectedNutrients) {
        this.selectedNutrients.clear();
        if (selectedNutrients != null) {
            this.selectedNutrients.addAll(selectedNutrients);
        }
    }

    public String getSelectionError() {
        return selectionError;
    }

    public void setSelectionError(String selectionError) {
        this.selectionError = selectionError;
    }

    public List<Recipe> getRecipeDetails() {
        return recipeDetails;
    }

    public void setRecipeDetails(List<Recipe> recipeDetails) {
        this.recipeDetails.clear();
        if (recipeDetails != null) {
            this.recipeDetails.addAll(recipeDetails);
        }
    }

    public String getSearchError() {
        return searchError;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }
}
