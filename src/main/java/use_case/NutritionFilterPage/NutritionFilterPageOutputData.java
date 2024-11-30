package use_case.NutritionFilterPage;

import org.json.JSONArray;

/**
 * Output Data for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageOutputData {

    private final JSONArray recipes;
    private final boolean useCaseFailed;

    /**
     * Constructs a new {@code NutritionFilterPageOutputData} object with the specified list of recipes and status flag.
     * <p>
     * This constructor initializes the output data for the Nutrition Filter Page use case.
     * It contains the list of recipes resulting from the filtering operation and a flag indicating
     * whether the use case execution failed.
     * </p>
     *
     * @param recipes       the {@code JSONArray} containing the recipes resulting from the nutrient filter operation
     * @param useCaseFailed a {@code boolean} flag indicating whether the use case execution resulted in failure;
     *                      {@code true} if the use case failed, {@code false} otherwise
     */
    public NutritionFilterPageOutputData(JSONArray recipes, boolean useCaseFailed) {
        this.recipes = recipes;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Retrieves the list of recipe names resulting from the nutrient filter operation.
     * @return a {@code JSONArray} containing the names of the recipes; may be empty but not {@code null}
     */
    public JSONArray getRecipes() {
        return recipes;
    }

    /**
     * Indicates whether the use case execution resulted in a failure.
     * @return {@code true} if the use case failed; {@code false} otherwise
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
