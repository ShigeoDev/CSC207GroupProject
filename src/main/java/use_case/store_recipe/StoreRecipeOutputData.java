package use_case.store_recipe;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Output Data for the Signup Use Case.
 */
public class StoreRecipeOutputData {
    private String username;
    private ArrayList<JSONObject> recipe;

    public StoreRecipeOutputData(String username, ArrayList<JSONObject> recipe) {
        this.username = username;
        this.recipe = recipe;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<JSONObject> getRecipes() {
        return recipe;
    }
}

