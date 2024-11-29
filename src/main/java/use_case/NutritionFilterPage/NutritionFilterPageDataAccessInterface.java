package use_case.NutritionFilterPage;

import org.json.JSONArray;

import java.util.List;

/**
 * The interface of the DAO for the Filter Based on Nutrition Use Case.
 */
public interface NutritionFilterPageDataAccessInterface {

    /**
     * Retrieves a list of recipes that are high in the specified nutrients.
     * <p>
     * This method accepts a list of nutrient names selected by the user and returns a {@code JSONArray}
     * containing recipes that meet the nutrient criteria. Each recipe in the array includes details
     * such as the recipe name, ingredients, and nutrient information. The method may throw a
     * {@code RuntimeException} if an error occurs during data retrieval or processing.
     *
     * @param selectedNutrients a list of nutrient names selected by the user for filtering recipes
     * @return a {@code JSONArray} containing the recipes that match the nutrient criteria
     * @throws RuntimeException if an error occurs during the API call or data parsing
     */
    JSONArray getRecipesByNutrients(List<String> selectedNutrients);
}
