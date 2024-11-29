package interface_adapter.DishType;

import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import org.json.JSONArray;
import use_case.searchByDishType.DishTypeOutputBoundary;
import use_case.searchByDishType.DishTypeOutputData;

import java.util.List;

/**
 * The DishTypePresenter is responsible for preparing and presenting the view
 * related to dish type search results. It is responsible for preparing the
 * success or failure views based on the output data from the use case and
 * managing the state of the view.
 */
public class DishTypePresenter implements DishTypeOutputBoundary {

    private final DishTypeViewModel dishTypeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;

    /**
     * Constructs a DishTypePresenter with the provided dishTypeViewModel and ViewManager.
     *
     * @param dishTypeViewModel the ViewModel responsible for managing dish type data.
     * @param viewManagerModel the ViewManager responsible for managing the view state.
     */
    public DishTypePresenter(DishTypeViewModel dishTypeViewModel, ViewManagerModel viewManagerModel,
                             HomepageViewModel homepageViewModel) {
        this.dishTypeViewModel = dishTypeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
    }

    /**
     * Prepares the success view by setting the recipe data in the ViewModel
     * and updating the view state accordingly.
     *
     * @param outputData the output data containing the recipe results for the dish type.
     */
    @Override
    public void prepareSuccessView(DishTypeOutputData outputData) {
        JSONArray recipe = outputData.getRecipe();
        // Here, you would typically pass the recipe data to the ViewModel
        // to be displayed in the view, like setting recipe data in the view.
    }

    /**
     * Prepares the failure view when the use case operation fails.
     * In this case, an error message would be displayed.
     *
     * @param errorMessage the output data containing the error message or failure details.
     */
    @Override
    public void prepareFailView(DishTypeOutputData errorMessage) {
        // Handle failure view (e.g., display an error message)
    }

    /**
     * Prepares the dish type view by firing a property change event in the ViewModel
     * and updating the state in the ViewManager.
     */
    @Override
    public void prepareDishType(String username) {
        DishTypeState state = new DishTypeState();
        state.setUsername(username);
        dishTypeViewModel.setState(state);
        // Show that the ViewModel state has changed
        dishTypeViewModel.firePropertyChanged();
        // Sets the current view state
        viewManagerModel.setState(dishTypeViewModel.getViewName());
        // Show that the ViewManager state has changed
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareHomepage(String username) {
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
