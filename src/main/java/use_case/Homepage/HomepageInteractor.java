package use_case.Homepage;

import entity.User;

public class HomepageInteractor implements HomepageInputBoundary{

    private final HomepageOutputBoundary userPresenter;

    public HomepageInteractor(HomepageOutputBoundary homepageOutputBoundary) {
        this.userPresenter = homepageOutputBoundary;
    }

    @Override
    public void execute(HomepageInputData homepageInputData) {
        userPresenter.prepareSuccessView();
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToStoreRecipeView();
    }
}
