package interface_adapter.Homepage;

import interface_adapter.GetCalories.GetCaloriesViewModel;
import interface_adapter.MealPlan.MealPlanState;
import interface_adapter.MealPlan.MealPlanViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.store_recipe.StoreRecipeState;
import interface_adapter.store_recipe.StoreRecipeViewModel;
import interface_adapter.GetCalories.GetCaloriesState;
import org.json.JSONObject;
import use_case.Homepage.HomepageOutputBoundary;
import use_case.Homepage.HomepageOutputData;
import use_case.store_recipe.StoreRecipeOutputBoundary;
import view.GetCaloriesView;

public class HomepagePresenter implements HomepageOutputBoundary {
    private final HomepageViewModel homepageViewModel;
    private final ViewManagerModel viewManagerModel;
    private final StoreRecipeViewModel storeRecipeViewModel;
    private final MealPlanViewModel mealPlanViewModel;
    private final GetCaloriesViewModel getCaloriesViewModel;

    public HomepagePresenter(ViewManagerModel viewManagerModel,
                             HomepageViewModel homepageViewModel,
                             StoreRecipeViewModel storeRecipeViewModel,
                             MealPlanViewModel mealPlanViewModel,
                             GetCaloriesViewModel getCaloriesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.storeRecipeViewModel = storeRecipeViewModel;
        this.mealPlanViewModel = mealPlanViewModel;
        this.getCaloriesViewModel = getCaloriesViewModel;
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
    public void prepareMealPlanView(JSONObject[] recipes, String username) {
        final MealPlanState mealPlanState = mealPlanViewModel.getState();
        mealPlanState.setRecipes(recipes) ;
        mealPlanState.setUser(username);
        mealPlanViewModel.firePropertyChanged();

        viewManagerModel.setState(mealPlanViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareGetCaloriesView(String username) {
        getCaloriesViewModel.getState().setUsername(username);

        viewManagerModel.setState(getCaloriesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
