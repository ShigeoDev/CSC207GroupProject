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

    public void execute() {
        userHomepageUseCaseInteractor.execute();
    }

    public void getCalories(String username) {
        userHomepageUseCaseInteractor.getCalories(username);
    }

    public void switchToDishType(){
        userHomepageUseCaseInteractor.switchToDishType();
    }
}

