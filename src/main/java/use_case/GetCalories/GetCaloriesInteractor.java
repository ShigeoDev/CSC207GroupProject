package use_case.GetCalories;

import org.json.JSONArray;
import org.json.JSONObject;

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
            JSONObject response = apiDataAccessObject.getRecipebyName(getCaloriesInputData.getRecipeName());
            JSONArray hits = response.getJSONArray("hits");
            if (hits.length() > 0) {
                JSONObject firstRecipe = hits.getJSONObject(0).getJSONObject("recipe");
                String actualRecipeName = firstRecipe.getString("label");
                int calories = firstRecipe.getInt("calories");

                GetCaloriesOutputData getCaloriesOutputData =
                        new GetCaloriesOutputData(actualRecipeName, calories);
                getCaloriesPresenter.prepareSuccessView(getCaloriesOutputData);
            }
        } catch (Exception e) {
            getCaloriesPresenter.prepareFailView(e.getMessage());
        }
    }
}