package use_case.GetCalories;

/**
 * Output boundary for the Get Calories use case.
 * Defines the required methods for presenting the results.
 */
public interface GetCaloriesOutputBoundary {

    /**
     * Prepares the view for displaying the succesfull view.
     * @param getCaloriesOutputData The data to show
     */
    void prepareSuccessView(GetCaloriesOutputData getCaloriesOutputData);

    /**
     * Prepares the view for an error.
     * @param error The error message
     */
    void prepareFailView(String error);

    /**
     * Prepares the homepage view/
     */
    void prepareHomeView();

    /**
     * Prepares the get calories view for the user.
     * @param username Name of the user
     */
    void prepareGetCaloriesView(String username);
}