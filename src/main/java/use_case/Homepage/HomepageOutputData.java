package use_case.Homepage;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomepageOutputData {
    private String username;
    private ArrayList<JSONObject> recipes;

    public HomepageOutputData(String username, ArrayList<JSONObject> recipes) {
        this.username = username;
        this.recipes = recipes;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<JSONObject> getRecipes() {
        return recipes;
    }
}
