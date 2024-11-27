package use_case.Homepage;

import data_access.ApiDataAccessObject;
import data_access.FileUserDataAccessObject;
import org.json.JSONObject;

public class HomepageInteractor implements HomepageInputBoundary{

    private final HomepageOutputBoundary userPresenter;
    private final FileUserDataAccessObject fileUserDataAccessObject;
    private final ApiDataAccessObject apiDataAccessObject;

    public HomepageInteractor(FileUserDataAccessObject fileUserDataAccessObject,
                              HomepageOutputBoundary homepageOutputBoundary,
                              ApiDataAccessObject apiDataAccessObject) {
        this.userPresenter = homepageOutputBoundary;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.apiDataAccessObject = apiDataAccessObject;
    }

    @Override
    public void execute() {
        userPresenter.prepareSuccessView();
    }

    @Override
    public void savedRecipe(HomepageInputData homepageInputData) {

    }

    public void mealPlan(String username) {
        final JSONObject[] recipes = new JSONObject[3];
        final JSONObject breakfast = apiDataAccessObject.getRecipebyMeal("Breakfast");
        final JSONObject lunch = apiDataAccessObject.getRecipebyMeal("lunch");
        final JSONObject dinner = apiDataAccessObject.getRecipebyMeal("dinner");
        recipes[0] = breakfast;
        recipes[1] = lunch;
        recipes[2] = dinner;
        userPresenter.prepareMealPlanView(recipes, username);
    }

    @Override
    public void getCalories(String username) {
        userPresenter.prepareGetCaloriesView(username);
    }
}
