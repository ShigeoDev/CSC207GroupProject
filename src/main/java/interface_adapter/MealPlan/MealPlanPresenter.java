package interface_adapter.MealPlan;

import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import org.json.JSONObject;
import use_case.MealPlan.MealPlanOutputBoundary;
import use_case.MealPlan.MealPlanOutputData;
import view.MealPlanView;

/**
 * The MealPlanPresenter class is responsible for presenting meal plan data to the view layer.
 * It implements the MealPlanOutputBoundary interface to handle output data from the interactor
 * and updates the associated view models to reflect changes in the application state.
 */
public class MealPlanPresenter implements MealPlanOutputBoundary {

    // A model for managing the state of different views in the application.
    private final ViewManagerModel viewManagerModel;

    // A model representing the state of the homepage view.
    private final HomepageViewModel homepageViewModel;

    // A model representing the state of the meal plan view.
    private final MealPlanViewModel mealPlanViewModel;

    /**
     * Constructor for MealPlanPresenter.
     *
     * @param viewManagerModel  Manages view transitions and state updates for the application.
     * @param homepageViewModel Represents the state and data of the homepage view.
     * @param mealPlanViewModel Represents the state and data of the meal plan view.
     */
    public MealPlanPresenter(ViewManagerModel viewManagerModel,
                             HomepageViewModel homepageViewModel,
                             MealPlanViewModel mealPlanViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.mealPlanViewModel = mealPlanViewModel;
    }

    /**
     * Prepares and updates the view to reflect a successful meal plan operation.
     *
     * @param outputData The data output from the interactor containing meal plan information,
     *                   such as recipes and the username.
     */
    @Override
    public void prepareSuccessView(MealPlanOutputData outputData) {
        // Retrieve the current state of the meal plan view model.
        final MealPlanState mealPlanState = mealPlanViewModel.getState();

        // Update the state with new meal plan data (e.g., recipes and username).
        mealPlanState.setRecipes(outputData.getRecipes());
        mealPlanState.setUser(outputData.getUsername());

        // Notify observers that the meal plan view model has changed.
        mealPlanViewModel.firePropertyChanged();

        // Update the view manager model to reflect the current view state.
        viewManagerModel.setState(mealPlanViewModel.getViewName());

        // Notify observers that the view manager model has changed.
        viewManagerModel.firePropertyChanged();
    }
}
