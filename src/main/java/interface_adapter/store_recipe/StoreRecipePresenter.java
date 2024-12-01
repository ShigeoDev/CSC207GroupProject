package interface_adapter.store_recipe;

import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import org.json.JSONObject;
import use_case.store_recipe.StoreRecipeOutputBoundary;
import use_case.store_recipe.StoreRecipeOutputData;

import java.util.ArrayList;

/**
 * Presenter for handling the output of recipe storage operations and updating the view.
 */
public class StoreRecipePresenter implements StoreRecipeOutputBoundary {

    // Manages view transitions and state.
    private final ViewManagerModel viewManagerModel;
    // Represents the state of the recipe storage view.
    private final StoreRecipeViewModel storeRecipeViewModel;
    // Represents the state of the homepage view.
    private final HomepageViewModel homepageViewModel;

    /**
     * Constructor for initializing the presenter with view models.
     * @param viewManagerModel      Manages view transitions.
     * @param storeRecipeViewModel  State model for the recipe storage view.
     * @param homepageViewModel     State model for the homepage view.
     */
    public StoreRecipePresenter(ViewManagerModel viewManagerModel,
                                StoreRecipeViewModel storeRecipeViewModel,
                                HomepageViewModel homepageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.storeRecipeViewModel = storeRecipeViewModel;
        this.homepageViewModel = homepageViewModel;
    }

    /**
     * Updates the view to reflect the stored recipe data and user information.
     * @param storeRecipeOutputData The output data containing user and recipe details.
     */
    @Override
    public void goView(StoreRecipeOutputData storeRecipeOutputData) {
        // Update the state of the recipe storage view model.
        final StoreRecipeState storeRecipeState = storeRecipeViewModel.getState();
        storeRecipeState.setUsername(storeRecipeOutputData.getUsername());
        storeRecipeState.setRecipes(storeRecipeOutputData.getRecipes());
        this.storeRecipeViewModel.setState(storeRecipeState);
        storeRecipeViewModel.firePropertyChanged();

        // Update the view manager to reflect the current view.
        viewManagerModel.setState(storeRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

