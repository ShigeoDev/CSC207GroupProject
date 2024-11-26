package use_case.store_recipe;

import org.json.JSONObject;

/**
 * The Input Data for the Signup Use Case.
 */
public class StoreRecipeInputData {

    private String username;
    private JSONObject recipe;

    public StoreRecipeInputData(String username, JSONObject recipe) {
        this.username = username;
        this.recipe = recipe;
    }

    public String getUsername() {
        return username;
    }

    public JSONObject getRecipe() {
        return recipe;
    }
}
