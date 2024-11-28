package use_case.searchByDishType;

import org.json.JSONArray;

/**
 * The DishTypeInputBoundary interface defines the contract for searching recipes or items by their dish type.
 * It provides methods for executing a search operation and switching the current mode to dish type filtering.
 */
public interface DishTypeInputBoundary {

    /**
     * Executes a search based on the provided input data, such as dish type.
     *
     * @param dishTypeInputData the input data containing search criteria for the dish type.
     * @return a JSONArray containing the search results matching the specified dish type.
     */
    JSONArray execute(DishTypeInputData dishTypeInputData);

    /**
     * Switches the context or system mode to focus on dish type operations,
     * such as filtering or categorizing items by dish type.
     */
    void switchToDishType(String username);

    void switchToHomepage(String username);
}
