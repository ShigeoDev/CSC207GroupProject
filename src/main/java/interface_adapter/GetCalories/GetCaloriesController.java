package interface_adapter.GetCalories;

import org.json.JSONObject;
import use_case.GetCalories.GetCaloriesInputBoundary;
import use_case.GetCalories.GetCaloriesInputData;
import view.RecipeSavePanel;

public class GetCaloriesController {
    final GetCaloriesInputBoundary getCaloriesUseCaseInteractor;

    public GetCaloriesController(GetCaloriesInputBoundary getCaloriesUseCaseInteractor) {
        this.getCaloriesUseCaseInteractor = getCaloriesUseCaseInteractor;
    }

    public void execute(String recipeName, String username) {
        GetCaloriesInputData getCaloriesInputData = new GetCaloriesInputData(recipeName, username);
        getCaloriesUseCaseInteractor.execute(getCaloriesInputData);
    }

    public void saveRecipe(JSONObject recipe, String username) {
        getCaloriesUseCaseInteractor.saveRecipe(recipe, username);
    }

    public void backToHome() {
        getCaloriesUseCaseInteractor.backToHome();
    }
}

