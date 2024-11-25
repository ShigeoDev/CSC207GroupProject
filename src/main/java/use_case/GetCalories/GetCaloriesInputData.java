package use_case.GetCalories;

public class GetCaloriesInputData {
    private final String recipeName;

    public GetCaloriesInputData(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }
}
