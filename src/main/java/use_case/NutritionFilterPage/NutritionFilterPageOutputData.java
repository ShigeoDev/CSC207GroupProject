package use_case.NutritionFilterPage;

import entity.Recipe;

import java.util.List;

/**
 * Output Data for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageOutputData {

    private final List<Recipe> filteredRecipes;
    private final boolean useCaseFailed;

    public NutritionFilterPageOutputData(List<Recipe> filteredRecipes, boolean useCaseFailed) {
        this.filteredRecipes = filteredRecipes;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Recipe> getFilteredRecipes() {
        return filteredRecipes;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
