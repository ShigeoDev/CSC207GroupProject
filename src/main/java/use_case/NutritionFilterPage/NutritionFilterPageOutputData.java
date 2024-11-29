package use_case.NutritionFilterPage;

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

    /**
     * Retrieves the list of recipe names resulting from the nutrient filter operation.
     * @return a {@code List<String>} containing the names of the recipes; may be empty but not {@code null}
     */
    public List<String> getRecipeNames() {
        return recipeNames;
    }

    /**
     * Indicates whether the use case execution resulted in a failure.
     * @return {@code true} if the use case failed; {@code false} otherwise
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
