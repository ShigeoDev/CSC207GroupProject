package use_case.GetCalories;

import org.json.JSONObject;

public interface GetCaloriesInputBoundary {
    void execute(GetCaloriesInputData getCaloriesInputData);

    void saveRecipe(JSONObject recipe, String username);
}