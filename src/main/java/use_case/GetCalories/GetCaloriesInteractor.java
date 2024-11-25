package use_case.GetCalories;

import data_access.ApiDataAccessObject;

public class GetCaloriesInteractor implements GetCaloriesInputBoundary {
    final GetCaloriesOutputBoundary getCaloriesPresenter;
    final ApiDataAccessObject apiDataAccessObject;

    public GetCaloriesInteractor(ApiDataAccessObject apiDataAccessObject,
                                 GetCaloriesOutputBoundary getCaloriesOutputBoundary) {
        this.apiDataAccessObject = apiDataAccessObject;
        this.getCaloriesPresenter = getCaloriesOutputBoundary;
    }

    @Override
    public void execute(GetCaloriesInputData getCaloriesInputData) {
        try {
            // Use the getRecipeCalories method instead of parsing JSON directly
            int calories = apiDataAccessObject.getRecipeCalories(getCaloriesInputData.getRecipeName());
            GetCaloriesOutputData getCaloriesOutputData =
                    new GetCaloriesOutputData(getCaloriesInputData.getRecipeName(), calories);
            getCaloriesPresenter.prepareSuccessView(getCaloriesOutputData);
        } catch (Exception e) {
            getCaloriesPresenter.prepareFailView(e.getMessage());
        }
    }
}