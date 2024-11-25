package interface_adapter.MealPlan;

import org.json.JSONObject;

public class MealPlanState {
    private JSONObject[] recipes;
    private String user;

    public JSONObject[] getRecipes() {
        return recipes;
    }

    public void setRecipes(JSONObject[] recipes) {
        this.recipes = recipes;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}
