package use_case.store_recipe;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The output boundary for the Signup Use Case.
 */
public interface StoreRecipeOutputBoundary {

    /**
     * Prepares the success view for the Signup Use Case.
     * @param outputData the output data
     */
    void goView(StoreRecipeOutputData storeRecipeOutputData);
}
