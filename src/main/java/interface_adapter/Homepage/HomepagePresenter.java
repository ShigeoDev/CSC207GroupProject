package interface_adapter.Homepage;

import interface_adapter.DishType.DishTypeViewModel;
import interface_adapter.MealPlan.MealPlanState;
import interface_adapter.MealPlan.MealPlanViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.store_recipe.StoreRecipeState;
import interface_adapter.store_recipe.StoreRecipeViewModel;
import org.json.JSONObject;
import use_case.Homepage.HomepageOutputBoundary;
import use_case.Homepage.HomepageOutputData;
import use_case.store_recipe.StoreRecipeOutputBoundary;

public class HomepagePresenter implements HomepageOutputBoundary {
    private final HomepageViewModel homepageViewModel;
    private final ViewManagerModel viewManagerModel;
    private final StoreRecipeViewModel storeRecipeViewModel;
    private final MealPlanViewModel mealPlanViewModel;
    private final DishTypeViewModel dishTypeViewModel;

    public HomepagePresenter(ViewManagerModel viewManagerModel,
                             HomepageViewModel homepageViewModel,
                             StoreRecipeViewModel storeRecipeViewModel,
                             MealPlanViewModel mealPlanViewModel, DishTypeViewModel dishTypeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.storeRecipeViewModel = storeRecipeViewModel;
        this.mealPlanViewModel = mealPlanViewModel;
        this.dishTypeViewModel = dishTypeViewModel;
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
    public void prepareMealPlanView(JSONObject[] recipes) {
        final MealPlanState mealPlanState = mealPlanViewModel.getState();
        mealPlanState.setRecipes(recipes) ;
        mealPlanViewModel.firePropertyChanged();

        viewManagerModel.setState(mealPlanViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareDishType(){
        dishTypeViewModel.firePropertyChanged();
        viewManagerModel.setState(dishTypeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
