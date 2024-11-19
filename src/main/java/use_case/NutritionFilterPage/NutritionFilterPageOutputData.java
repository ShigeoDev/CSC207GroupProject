package use_case.NutritionFilterPage;

import entity.Recipe;

import java.util.List;

/**
 * Output Data for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageOutputData {

    private final List<String> recipeNames;
    private final boolean useCaseFailed;

    public NutritionFilterPageOutputData(List<String> recipeNames, boolean useCaseFailed) {
        this.recipeNames = recipeNames;
        this.useCaseFailed = useCaseFailed;
    }

    public List<String> getRecipeNames() {
        return recipeNames;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
