package use_case.NutritionFilterPage;

/**
 * The input boundary for the Filter Based on Nutrition Use Case.
 */
public interface NutritionFilterPageInputBoundrary {

    /**
     * Execute the Filter Based on Nutrition Use Case.
     * @param nutritionFilterPageInputData the input data for this use case
     */
    void execute(NutritionFilterPageInputData nutritionFilterPageInputData);

    void switchToHomepage(String username);

    void switchToNutritionFilterPage(String username);
}
