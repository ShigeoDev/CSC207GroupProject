package use_case.searchByDishType;

/**
 * The DishTypeInputData represents the input data required for a search by dish type.
 * It encapsulates the dish type as a string, which serves as the search criterion.
 */
public class DishTypeInputData {

    // The dish type to be used as the search criterion.
    private String dishType;

    /**
     * Constructs a new DishTypeInputData instance with the specified dish type.
     *
     * @param dishType the type of dish to be used for searching (e.g., "desserts", "main course").
     */
    public DishTypeInputData(String dishType) {
        this.dishType = dishType;
    }

    /**
     * Returns the dish type encapsulated in this input data.
     *
     * @return a string representing the dish type.
     */
    public String getDishType() {
        return dishType;
    }
}
