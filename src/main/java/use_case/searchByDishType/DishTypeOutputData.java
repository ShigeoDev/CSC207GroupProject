package use_case.searchByDishType;

import org.json.JSONArray;

/**
 * The DishTypeOutputData class represents the data that is returned as output from
 * the search by dish type use case.
 */
public class DishTypeOutputData {

    private JSONArray recipe;  // The list of recipes returned by the use case.
    private final boolean useCaseFailed;  // A flag indicating whether the use case failed.

    /**
     * Constructs a new instance of DishTypeOutputData.
     *
     * @param recipe a JSONArray containing the list of recipes related to the dish type.
     * @param useCaseFailed a boolean flag indicating if the use case has failed (true) or succeeded (false).
     */
    public DishTypeOutputData(JSONArray recipe, boolean useCaseFailed) {
        this.recipe = recipe;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the list of recipes retrieved by the use case.
     *
     * @return a JSONArray containing the list of recipes.
     */
    public JSONArray getRecipe() {
        return recipe;
    }

    /**
     * Checks if the use case has failed.
     *
     * @return true if the use case failed, otherwise false.
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

