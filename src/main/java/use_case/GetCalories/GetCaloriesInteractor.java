package use_case.GetCalories;

import data_access.FileUserDataAccessObject;

public class GetCaloriesInteractor implements GetCaloriesInputBoundary {
    final GetCaloriesOutputBoundary getCaloriesPresenter;
    final FileUserDataAccessObject userDataAccessObject;

    public GetCaloriesInteractor(FileUserDataAccessObject userDataAccessObject,
                                 GetCaloriesOutputBoundary getCaloriesOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.getCaloriesPresenter = getCaloriesOutputBoundary;
    }

    @Override
    public void execute(GetCaloriesInputData getCaloriesInputData) {
        try {
            // Get the calories from your DAO
            int calories = userDataAccessObject.getRecipeCalories(getCaloriesInputData.getRecipeName());
            GetCaloriesOutputData getCaloriesOutputData =
                    new GetCaloriesOutputData(getCaloriesInputData.getRecipeName(), calories);
            getCaloriesPresenter.prepareSuccessView(getCaloriesOutputData);
        } catch (Exception e) {
            getCaloriesPresenter.prepareFailView(e.getMessage());
        }
    }
}