package use_case.GetCalories;

/**
 * Class represents the input for the Get Calories use case.
 * Contains necessary information to look up the calories.
 */
public class GetCaloriesInputData {
    private final String recipeName;
    private final String username;

    /**
     * Constructor for the input data.
     * @param recipeName Name of the recipe to look up
     * @param username Name of the user making the request
     */
    public GetCaloriesInputData(String recipeName, String username) {
        this.recipeName = recipeName;
        this.username = username;
    }

    /**
     * Getter for the recipe name.
     * @return Name of recipe
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Getter for the username.
     * @return Name of user requesting information
     */
    public String getUsername() {
        return username;
    }
}
