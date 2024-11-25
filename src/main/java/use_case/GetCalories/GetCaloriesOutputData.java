package use_case.GetCalories;

import org.json.JSONObject;

public class GetCaloriesOutputData {
    private final int calories;
    private final String recipeName;
    private final JSONObject caloriesJSON;

    public GetCaloriesOutputData(String recipeName, int calories, JSONObject caloriesJSON) {
        this.recipeName = recipeName;
        this.calories = calories;
        this.caloriesJSON = caloriesJSON;
    }

    public int getCalories() {
        return calories;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public JSONObject getRecipeObject() {
        return caloriesJSON;
    }
}