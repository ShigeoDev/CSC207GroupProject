package interface_adapter.store_recipe;

import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import use_case.store_recipe.StoreRecipeOutputBoundary;

public class StoreRecipePresenter implements StoreRecipeOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final StoreRecipeViewModel storeRecipeViewModel;
    private final HomepageViewModel homepageViewModel;

    public StoreRecipePresenter(ViewManagerModel viewManagerModel,
                                StoreRecipeViewModel storeRecipeViewModel,
                                HomepageViewModel homepageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.storeRecipeViewModel = storeRecipeViewModel;
        this.homepageViewModel = homepageViewModel;
    }

    @Override
    public void prepareSuccessView() {
        // On success, switch to the login view.
        final StoreRecipeState storeRecipeState = storeRecipeViewModel.getState();
        // storeRecipeState.setUsername(response.getUsername());
        this.storeRecipeViewModel.setState(storeRecipeState);
        storeRecipeViewModel.firePropertyChanged();

        viewManagerModel.setState(storeRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void goHome() {
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
