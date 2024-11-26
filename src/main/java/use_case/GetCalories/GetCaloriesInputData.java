package use_case.GetCalories;

public class GetCaloriesInputData {
    private final String recipeName;
    private final String username;

    public GetCaloriesInputData(String recipeName, String username) {
        this.recipeName = recipeName;
        this.username = username;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getUsername() {
        return username;
    }
}
