package use_case.searchByDishType;

/**
 * The DishTypeOutputBoundary interface defines the methods for preparing the output views
 * in the search by dish type use case. It provides methods for both successful
 * and failed cases, as well as for preparing the dish type view.
 */
public interface DishTypeOutputBoundary {

    /**
     * Prepares the success view for the search by dish type use case.
     * This method is called when the operation completes successfully,
     * providing the relevant output data to the presenter.
     *
     * @param outputData the output data containing the recipes or related information
     *                   to be presented in the success view.
     */
    void prepareSuccessView(DishTypeOutputData outputData);

    /**
     * Prepares the failure view for the search by dish type use case.
     * This method is called when an error or failure occurs during the operation,
     * providing the relevant output data to the presenter with an explanation of the failure.
     *
     * @param outputData the output data containing the explanation of the failure
     *                   and any associated information to be presented in the failure view.
     */
    void prepareFailView(DishTypeOutputData outputData);

    /**
     * Prepares the dish type view, typically used for displaying or transitioning to
     * the user interface related to dish types.
     */
    void prepareDishType(String username);
}
