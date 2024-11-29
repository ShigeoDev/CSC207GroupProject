package use_case.store_recipe;

import org.json.JSONObject;
import use_case.Homepage.HomepageInputData;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface StoreRecipeInputBoundary {

    /**
     * Executes the signup use case.
     * @param signupInputData the input data
     */
    public void execute(StoreRecipeInputData storeRecipeInputData);

    void goView(String username);

}
