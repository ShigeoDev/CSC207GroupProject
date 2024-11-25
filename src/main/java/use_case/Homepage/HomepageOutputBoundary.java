package use_case.Homepage;

import org.json.JSONArray;
import org.json.JSONObject;
import use_case.store_recipe.StoreRecipeOutputBoundary;

public interface HomepageOutputBoundary {

    public void prepareSuccessView(HomepageOutputData homepageOutputData);
    public void prepareMealPlanView(JSONObject[] recipes);
    void prepareDishType();
}
