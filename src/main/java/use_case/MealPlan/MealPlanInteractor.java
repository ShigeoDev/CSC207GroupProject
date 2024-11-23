package use_case.MealPlan;

import use_case.store_recipe.StoreRecipeDataAccessInterface;
import use_case.store_recipe.StoreRecipeInputBoundary;

/**
 * The Signup Interactor.
 */
public class MealPlanInteractor implements MealPlanInputBoundary {
    private MealPlanOutputBoundary presenter;

    public MealPlanInteractor(MealPlanOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void goHome() {
        presenter.goHome();
    }
}
