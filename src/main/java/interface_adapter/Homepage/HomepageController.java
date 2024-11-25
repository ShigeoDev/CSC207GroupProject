package interface_adapter.Homepage;

import use_case.Homepage.HomepageInputBoundary;
import use_case.Homepage.HomepageInputData;

public class HomepageController {
    private final HomepageInputBoundary userHomepageUseCaseInteractor;

    public HomepageController(HomepageInputBoundary userHomepageUseCaseInteractor) {
        this.userHomepageUseCaseInteractor = userHomepageUseCaseInteractor;
    }

    public void savedRecipe(String username) {
        final HomepageInputData homepageInputData = new HomepageInputData(username);

        userHomepageUseCaseInteractor.savedRecipe(homepageInputData);
    }

    public void MealPlan(String username) {
        userHomepageUseCaseInteractor.mealPlan(username);
    }

    public void switchToDishType(){
        userHomepageUseCaseInteractor.switchToDishType();
    }
}
