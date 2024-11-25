package interface_adapter.GetCalories;

public class GetCaloriesState {
    private String recipeName = "";
    private int calories = 0;
    private String caloriesError = null;

    public GetCaloriesState(GetCaloriesState copy) {
        recipeName = copy.recipeName;
        calories = copy.calories;
        caloriesError = copy.caloriesError;
    }

    public GetCaloriesState() {}

    public String getRecipeName() { return recipeName; }
    public void setRecipeName(String recipeName) { this.recipeName = recipeName; }

    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }

    public String getCaloriesError() { return caloriesError; }
    public void setCaloriesError(String error) { this.caloriesError = error; }
}
