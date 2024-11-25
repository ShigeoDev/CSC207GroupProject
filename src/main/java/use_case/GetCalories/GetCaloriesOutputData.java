package use_case.GetCalories;

public class GetCaloriesOutputData {
    private final int calories;
    private final String recipeName;

    public GetCaloriesOutputData(String recipeName, int calories) {
        this.recipeName = recipeName;
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public String getRecipeName() {
        return recipeName;
    }
}