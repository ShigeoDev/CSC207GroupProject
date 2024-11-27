package interface_adapter.DishType;

import org.json.JSONArray;
import use_case.searchByDishType.DishTypeInputBoundary;
import use_case.searchByDishType.DishTypeInputData;

/**
 * The DishTypeController serves as the interface between the user input
 * and the use case logic for searching recipes by dish type.
 * It acts as an adapter that interacts with the use case (Interactor) and
 * processes the input data to produce the required output.
 */
public class DishTypeController {
    private final DishTypeInputBoundary dishTypeInteractor;

    /**
     * Constructs a DishTypeController with the provided DishTypeInputBoundary.
     *
     * @param dishTypeInteractor the use case interactor for handling dish type related logic.
     */
    public DishTypeController(DishTypeInputBoundary dishTypeInteractor) {
        this.dishTypeInteractor = dishTypeInteractor;
    }

    /**
     * Executes the use case to fetch recipes based on the provided dish type.
     *
     * @param dishType the type of dish to search for recipes.
     * @return a JSONArray containing the list of recipes for the given dish type.
     */
    public JSONArray execute(String dishType) {
        DishTypeInputData inputData = new DishTypeInputData(dishType);
        return dishTypeInteractor.execute(inputData);
    }

    /**
     * Switches to the dish type view (may be used for navigation or UI-related logic).
     */
    public void switchToDishType(String username) {
        dishTypeInteractor.switchToDishType(username);
    }
}

