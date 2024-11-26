package use_case.GetCalories;

import data_access.FileUserDataAccessObject;
import org.json.JSONArray;
import org.json.JSONObject;

import data_access.ApiDataAccessObject;

public class GetCaloriesInteractor implements GetCaloriesInputBoundary {
    final GetCaloriesOutputBoundary getCaloriesPresenter;
    final ApiDataAccessObject apiDataAccessObject;
    final FileUserDataAccessObject fileUserDataAccessObject;

    public GetCaloriesInteractor(ApiDataAccessObject apiDataAccessObject,
                                 GetCaloriesOutputBoundary getCaloriesOutputBoundary,
                                 FileUserDataAccessObject fileUserDataAccessObject) {
        this.apiDataAccessObject = apiDataAccessObject;
        this.getCaloriesPresenter = getCaloriesOutputBoundary;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
    }

    @Override
    public void execute(GetCaloriesInputData getCaloriesInputData) {
        try {
            JSONArray hits = apiDataAccessObject.getRecipebyName(getCaloriesInputData.getRecipeName());
            if (hits.length() > 0) {
                JSONObject firstRecipe = hits.getJSONObject(0).getJSONObject("recipe");
                String actualRecipeName = firstRecipe.getString("label");
                int calories = firstRecipe.getInt("calories");

                GetCaloriesOutputData getCaloriesOutputData =
                        new GetCaloriesOutputData(actualRecipeName, calories, firstRecipe, getCaloriesInputData.getUsername());
                getCaloriesPresenter.prepareSuccessView(getCaloriesOutputData);
            }
        } catch (Exception e) {
            getCaloriesPresenter.prepareFailView(e.getMessage());
        }
    }

    @Override
    public void saveRecipe(JSONObject recipe, String username) {
        fileUserDataAccessObject.saveRecipe(recipe, username);
    }

    @Override
    public void backToHome() {
        getCaloriesPresenter.prepareHomeView();
    }

    @Override
    public void getCalories(String username) {
        getCaloriesPresenter.prepareGetCaloriesView(username);
    }
}