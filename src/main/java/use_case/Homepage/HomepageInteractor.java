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
    public void savedRecipe(HomepageInputData homepageInputData) {
        final HomepageOutputData homepageOutputData = new HomepageOutputData(homepageInputData.getUsername(),
                fileUserDataAccessObject.getRecipes(homepageInputData.getUsername()));
        userPresenter.prepareSuccessView(homepageOutputData);
    }

    public void mealPlan() {
        final JSONObject recipes = apiDataAccessObject.getRecipebyName("chicken");
        userPresenter.prepareMealPlanView(recipes);
    }
}
