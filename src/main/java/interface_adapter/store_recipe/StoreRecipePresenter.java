package interface_adapter.store_recipe;

import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import org.json.JSONObject;
import use_case.store_recipe.StoreRecipeOutputBoundary;
import use_case.store_recipe.StoreRecipeOutputData;

import java.util.ArrayList;

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
    public void goHome() {
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void goView(StoreRecipeOutputData storeRecipeOutputData) {
        final StoreRecipeState storeRecipeState = storeRecipeViewModel.getState();
        storeRecipeState.setUsername(storeRecipeOutputData.getUsername());
        storeRecipeState.setRecipes(storeRecipeOutputData.getRecipes());
        this.storeRecipeViewModel.setState(storeRecipeState);
        storeRecipeViewModel.firePropertyChanged();

        viewManagerModel.setState(storeRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
