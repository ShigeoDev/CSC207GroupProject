package use_case.store_recipe;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Output Data for the Signup Use Case.
 */
public class StoreRecipeOutputData {
    private String username;

    public StoreRecipeOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

