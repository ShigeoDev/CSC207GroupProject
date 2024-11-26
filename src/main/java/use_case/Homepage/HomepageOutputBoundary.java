package use_case.Homepage;

import org.json.JSONArray;
import org.json.JSONObject;
import use_case.store_recipe.StoreRecipeOutputBoundary;

public interface HomepageOutputBoundary {

    public void prepareMealPlanView(JSONObject[] recipes, String username);
    public void prepareGetCaloriesView(String username);

    public void prepareSuccessView();

}