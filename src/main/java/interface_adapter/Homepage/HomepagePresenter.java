package interface_adapter.Homepage;

import interface_adapter.ViewManagerModel;
import interface_adapter.store_recipe.StoreRecipeState;
import interface_adapter.store_recipe.StoreRecipeViewModel;
import use_case.Homepage.HomepageOutputBoundary;
import use_case.Homepage.HomepageOutputData;
import use_case.store_recipe.StoreRecipeOutputBoundary;

public class HomepagePresenter implements HomepageOutputBoundary {
    private final HomepageViewModel homepageViewModel;
    private final ViewManagerModel viewManagerModel;
    private final StoreRecipeViewModel storeRecipeViewModel;

    public HomepagePresenter(ViewManagerModel viewManagerModel,
                             HomepageViewModel homepageViewModel,
                             StoreRecipeViewModel storeRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.storeRecipeViewModel = storeRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(HomepageOutputData homepageOutputData) {
        // On success, switch to the login view.
        final StoreRecipeState storeRecipeState = storeRecipeViewModel.getState();
        storeRecipeState.setUsername(homepageOutputData.getUsername());
        storeRecipeState.setRecipes(homepageOutputData.getRecipes());
        this.storeRecipeViewModel.setState(storeRecipeState);
        storeRecipeViewModel.firePropertyChanged();

        viewManagerModel.setState(storeRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToStoreRecipeView() {
        viewManagerModel.setState(storeRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
