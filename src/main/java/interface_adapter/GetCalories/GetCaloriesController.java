package interface_adapter.GetCalories;

import use_case.GetCalories.GetCaloriesInputBoundary;
import use_case.GetCalories.GetCaloriesInputData;

public class GetCaloriesController {
    final GetCaloriesInputBoundary getCaloriesUseCaseInteractor;

    public GetCaloriesController(GetCaloriesInputBoundary getCaloriesUseCaseInteractor) {
        this.getCaloriesUseCaseInteractor = getCaloriesUseCaseInteractor;
    }

    public void execute(String recipeName) {
        GetCaloriesInputData getCaloriesInputData = new GetCaloriesInputData(recipeName);
        getCaloriesUseCaseInteractor.execute(getCaloriesInputData);
    }
}

