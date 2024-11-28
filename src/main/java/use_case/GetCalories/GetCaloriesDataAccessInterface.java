package use_case.GetCalories;

import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Interface for data access in the Get Calories use case.
 * Defines the required methods for storing and retrieving recipe data.
 */
public interface GetCaloriesDataAccessInterface {
    void saveRecipe(JSONObject recipe, String username);
    ArrayList getRecipes(String username);
}