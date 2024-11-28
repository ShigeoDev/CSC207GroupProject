package use_case.Homepage;

import org.json.JSONArray;
import org.json.JSONObject;
import use_case.store_recipe.StoreRecipeOutputBoundary;

/**
 * Output boundary interface for the Homepage use case.
 * Defines a method that the presenter must implment.
 */
public interface HomepageOutputBoundary {

    /**
     * Prepares the success view for the homepage.
     * Called when successfully executed.
     */
    public void prepareSuccessView();

}
