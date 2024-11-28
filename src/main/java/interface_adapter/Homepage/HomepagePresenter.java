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

/**
 * Presenter for the Homepage view component.
 * Handles the presentation for the homepage.
 */
public class HomepagePresenter implements HomepageOutputBoundary {
    private final HomepageViewModel homepageViewModel;
    private final ViewManagerModel viewManagerModel;
    private final StoreRecipeViewModel storeRecipeViewModel;
    private final MealPlanViewModel mealPlanViewModel;
    private final DishTypeViewModel dishTypeViewModel;
    private final GetCaloriesViewModel getCaloriesViewModel;

    /**
     * Constructor for a new HomepagePresenter.
     * @param viewManagerModel View manager model for Homepage use case
     * @param homepageViewModel Homepage view model
     * @param storeRecipeViewModel Store recipe view model
     * @param mealPlanViewModel Meal plan view model
     * @param dishTypeViewModel Dish type view model
     * @param getCaloriesViewModel Get calories view model
     */
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

    /**
     * Prepares the success view for the homepage.
     * Updates the state.
     */
    public void prepareSuccessView() {
        // On success, switch to the login view.
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
