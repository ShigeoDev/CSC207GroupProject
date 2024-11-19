package interface_adapter.store_recipe;

import org.json.JSONObject;

import java.util.ArrayList;

public class StoreRecipeState {

    private String username;
    private ArrayList<JSONObject> recipes;

    public ArrayList getRecipes() {
        return recipes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRecipes(ArrayList<JSONObject> recipes) {
        this.recipes = recipes;
    }
}
