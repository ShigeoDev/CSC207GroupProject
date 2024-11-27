package use_case.searchByDishType;

import org.json.JSONArray;

/**
 * DishTypeInteractor is responsible for handling the business logic for searching recipes by dish type.
 * It implements the DishTypeInputBoundary interface and coordinates between the data access layer
 * and the presenter.
 */
public class DishTypeInteractor implements DishTypeInputBoundary {

    // Interface for accessing user data, such as retrieving recipes by dish type.
    private final DishTypeUserDataAccessInterface userDataAccessInterface;

    // Interface for presenting the output data to the user.
    private final DishTypeOutputBoundary userPresenter;

    /**
     * Constructs a DishTypeInteractor instance with the specified data access interface and presenter.
     *
     * @param userDataAccessInterface the data access interface for retrieving recipes.
     * @param userPresenter the presenter interface for preparing views based on the output data.
     */
    public DishTypeInteractor(DishTypeUserDataAccessInterface userDataAccessInterface, DishTypeOutputBoundary userPresenter) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.userPresenter = userPresenter;
    }

    /**
     * Executes the search operation for recipes based on the provided dish type.
     *
     * @param dishTypeInputData an instance of {@link DishTypeInputData} containing the dish type to search for.
     * @return a {@link JSONArray} of recipes matching the dish type, or {@code null} if an error occurs.
     */
    @Override
    public JSONArray execute(DishTypeInputData dishTypeInputData) {
        String dishType = dishTypeInputData.getDishType();
        try {
            // Retrieve recipes based on dish type.
            JSONArray recipes = userDataAccessInterface.getRecipeByDishType(dishType);

            // Prepare and send the success view using the output presenter.
            DishTypeOutputData outputData = new DishTypeOutputData(recipes, false);
            userPresenter.prepareSuccessView(outputData);

            return recipes;
        } catch (Exception e) {
            // Prepare and send the failure view in case of an error.
            DishTypeOutputData errorData = new DishTypeOutputData(new JSONArray(), true);
            userPresenter.prepareFailView(errorData);
        }
        return null;
    }

    /**
     * Switches the user interface to the dish type view.
     * This method delegates to the presenter to handle the UI transition.
     */
    @Override
    public void switchToDishType(String username) {
        userPresenter.prepareDishType(username);
    }
}

