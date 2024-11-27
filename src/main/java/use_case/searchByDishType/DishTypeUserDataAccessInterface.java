package use_case.searchByDishType;

import org.json.JSONArray;

/**
 * The DishTypeUserDataAccessInterface defines the contract for accessing
 * recipe JSONARRAY based on a specific dish type. Implementations of this interface
 * will handle the retrieval of recipes from a data source.
 */
public interface DishTypeUserDataAccessInterface {

    /**
     * Retrieves a list of recipes based on the given dish type.
     *
     * @param dishType the type of dish (e.g., "Vegetarian", "Dessert") for which recipes should be retrieved.
     * @return a JSONArray containing the list of recipes corresponding to the given dish type.
     */
    JSONArray getRecipeByDishType(String dishType);
}
