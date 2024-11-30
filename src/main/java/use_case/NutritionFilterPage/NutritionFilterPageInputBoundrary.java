package use_case.NutritionFilterPage;

import org.json.JSONArray;

/**
 * The input boundary for the Filter Based on Nutrition Use Case.
 */
public interface NutritionFilterPageInputBoundrary {

    /**
     * Execute the Filter Based on Nutrition Use Case.
     *
     * @param nutritionFilterPageInputData the input data for this use case
     * @return a {@code JSONArray} of recipes matching the selected nutrients.
     */
    JSONArray execute(NutritionFilterPageInputData nutritionFilterPageInputData);

    /**
     * Switches the context or system mode to focus on nutrition filter operations,
     * such as filtering or categorizing items by nutrients.
     */
    void switchToHomepage(String username);

    void switchToNutritionFilterPage(String username);
}
