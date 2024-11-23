package use_case.Homepage;

import data_access.FileUserDataAccessObject;

public class HomepageInteractor implements HomepageInputBoundary{

    private final HomepageOutputBoundary userPresenter;
    private final FileUserDataAccessObject fileUserDataAccessObject;

    public HomepageInteractor(FileUserDataAccessObject fileUserDataAccessObject, HomepageOutputBoundary homepageOutputBoundary) {
        this.userPresenter = homepageOutputBoundary;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
    }

    @Override
    public void execute(HomepageInputData homepageInputData) {
        final HomepageOutputData homepageOutputData = new HomepageOutputData(homepageInputData.getUsername(),
                fileUserDataAccessObject.getRecipes(homepageInputData.getUsername()));
        userPresenter.prepareSuccessView(homepageOutputData);
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToStoreRecipeView();
    }
}
