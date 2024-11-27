package interface_adapter.GetCalories;

import interface_adapter.ViewManagerModel;
import use_case.GetCalories.GetCaloriesOutputBoundary;
import use_case.GetCalories.GetCaloriesOutputData;

/**
 * A presenter class that handles the presentation logic for operations regarding calories.
 * Implements the GetCaloriesOutputBoundary interface and also prepares the view model based on the
 * use case output.
 * Handles formatting and view state management.
 */
public class GetCaloriesPresenter implements GetCaloriesOutputBoundary {
    private final GetCaloriesViewModel getCaloriesViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a new GetCaloriesPresenter with the specified view models.
     * @param viewManagerModel The view manager model that handles the view state
     * @param getCaloriesViewModel The view model specific for calorie views
     */
    public GetCaloriesPresenter(ViewManagerModel viewManagerModel, GetCaloriesViewModel getCaloriesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getCaloriesViewModel = getCaloriesViewModel;
    }

    /**
     * Prepares the success view after a successful calorie retrieval from the API.
     * Updates the view model state with the new retrieved info and triggers view updates
     * @param getCaloriesOutputData The output containing calorie information
     */
    @Override
    public void prepareSuccessView(GetCaloriesOutputData getCaloriesOutputData) {
        GetCaloriesState getCaloriesState = getCaloriesViewModel.getState();
        getCaloriesState.setRecipeName(getCaloriesOutputData.getRecipeName());
        getCaloriesState.setCalories(getCaloriesOutputData.getCalories());
        getCaloriesState.setRecipeObject(getCaloriesOutputData.getRecipeObject());
        getCaloriesState.setUsername(getCaloriesOutputData.getUsername());
        getCaloriesViewModel.firePropertyChanged();

        // Update the view state using setState
        viewManagerModel.setState("Calorie Result");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * This prepares the view when the calorie retrieval fails.
     * Updates accordingly with the error message.
     * @param error Error message to display
     */
    @Override
    public void prepareFailView(String error) {
        GetCaloriesState getCaloriesState = getCaloriesViewModel.getState();
        getCaloriesState.setCaloriesError(error);
        getCaloriesViewModel.firePropertyChanged();
    }

    /**
     * Prepares the homepage view.
     */
    @Override
    public void prepareHomeView() {
        viewManagerModel.setState("Homepage");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * This prepares the calorie return view for a specific user.
     * @param username The username that the view is being prepared for
     */
    @Override
    public void prepareGetCaloriesView(String username) {
        getCaloriesViewModel.getState().setUsername(username);

        viewManagerModel.setState(getCaloriesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}