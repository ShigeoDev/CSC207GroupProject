package use_case.GetCalories;

import org.json.JSONObject;

public class GetCaloriesOutputData {
    private final int calories;
    private final String recipeName;
    private final JSONObject caloriesJSON;
    private final String username;

    public GetCaloriesOutputData(String recipeName, int calories, JSONObject caloriesJSON, String username) {
        this.recipeName = recipeName;
        this.calories = calories;
        this.caloriesJSON = caloriesJSON;
        this.username = username;
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

    public String getUsername() {
        return username;
    }
}