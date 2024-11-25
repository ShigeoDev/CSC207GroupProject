package use_case.MealPlan;

import org.json.JSONObject;
import use_case.store_recipe.StoreRecipeDataAccessInterface;
import use_case.store_recipe.StoreRecipeInputBoundary;

/**
 * The Signup Interactor.
 */
public class MealPlanInteractor implements MealPlanInputBoundary {
    private MealPlanOutputBoundary presenter;
    private MealPlanDataAccessInterface mealPlanDataAccessInterface;

    public MealPlanInteractor(MealPlanOutputBoundary presenter, MealPlanDataAccessInterface mealPlanDataAccessInterface) {
        this.presenter = presenter;
        this.mealPlanDataAccessInterface = mealPlanDataAccessInterface;
    }

    @Override
    public void goHome() {
        presenter.goHome();
    }

    @Override
    public void saveRecipe(JSONObject recipe, String username) {
        mealPlanDataAccessInterface.saveRecipe(recipe, username);
    }
}
