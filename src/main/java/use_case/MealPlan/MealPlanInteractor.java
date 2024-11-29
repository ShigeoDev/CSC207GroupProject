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

    public void execute(MealPlanInputData mealPlanInputData) {
        final JSONObject[] recipes = new JSONObject[3];
        final JSONObject breakfast = mealPlanDataAccessInterface.getRecipebyMeal("breakfast");
        final JSONObject lunch = mealPlanDataAccessInterface.getRecipebyMeal("lunch");
        final JSONObject dinner = mealPlanDataAccessInterface.getRecipebyMeal("dinner");
        recipes[0] = breakfast;
        recipes[1] = lunch;
        recipes[2] = dinner;
        final MealPlanOutputData mealPlanOutputData = new MealPlanOutputData(recipes, mealPlanInputData.getUsername());
        presenter.prepareSuccessView(mealPlanOutputData);
    }

}
