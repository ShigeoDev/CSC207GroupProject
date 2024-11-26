package use_case.store_recipe;

/**
 * DAO for the Store Recipe Use Case.
 */
import org.json.JSONObject;

import java.util.ArrayList;

public interface StoreRecipeDataAccessInterface {

    public void saveRecipe(JSONObject recipe, String username);

    public ArrayList<JSONObject> getRecipes(String username);
}
