package interface_adapter.GetCalories;

import interface_adapter.ViewManagerModel;
import use_case.GetCalories.GetCaloriesOutputBoundary;
import use_case.GetCalories.GetCaloriesOutputData;

public class GetCaloriesPresenter implements GetCaloriesOutputBoundary {
    private final GetCaloriesViewModel getCaloriesViewModel;
    private final ViewManagerModel viewManagerModel;

    public GetCaloriesPresenter(ViewManagerModel viewManagerModel, GetCaloriesViewModel getCaloriesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getCaloriesViewModel = getCaloriesViewModel;
    }

    @Override
    public void prepareSuccessView(GetCaloriesOutputData getCaloriesOutputData) {
        GetCaloriesState getCaloriesState = getCaloriesViewModel.getState();
        getCaloriesState.setRecipeName(getCaloriesOutputData.getRecipeName());
        getCaloriesState.setCalories(getCaloriesOutputData.getCalories());
        getCaloriesState.setRecipeObject(getCaloriesOutputData.getRecipeObject());
        getCaloriesViewModel.firePropertyChanged();

        // Update the view state using setState
        viewManagerModel.setState("Calorie Result");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        GetCaloriesState getCaloriesState = getCaloriesViewModel.getState();
        getCaloriesState.setCaloriesError(error);
        getCaloriesViewModel.firePropertyChanged();
    }
}