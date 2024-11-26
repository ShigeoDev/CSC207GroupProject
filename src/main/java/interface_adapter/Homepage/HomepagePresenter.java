package interface_adapter.Homepage;

import interface_adapter.DishType.DishTypeViewModel;
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
    private final DishTypeViewModel dishTypeViewModel;
    private final GetCaloriesViewModel getCaloriesViewModel;

    public HomepagePresenter(ViewManagerModel viewManagerModel,
                             HomepageViewModel homepageViewModel,
                             StoreRecipeViewModel storeRecipeViewModel,
                             MealPlanViewModel mealPlanViewModel, DishTypeViewModel dishTypeViewModel, GetCaloriesViewModel getCaloriesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.storeRecipeViewModel = storeRecipeViewModel;
        this.mealPlanViewModel = mealPlanViewModel;
        this.dishTypeViewModel = dishTypeViewModel;
        this.getCaloriesViewModel = getCaloriesViewModel;
    }

    public void prepareSuccessView() {
        // On success, switch to the login view.
        viewManagerModel.setState(homepageViewModel.getViewName());
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

    public void prepareGetCaloriesView(String username) {
        getCaloriesViewModel.getState().setUsername(username);

        viewManagerModel.setState(getCaloriesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareDishType(){
        dishTypeViewModel.firePropertyChanged();
        viewManagerModel.setState(dishTypeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
