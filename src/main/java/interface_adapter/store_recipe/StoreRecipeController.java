package interface_adapter.store_recipe;

import org.json.JSONObject;
import use_case.store_recipe.StoreRecipeInputBoundary;
import use_case.store_recipe.StoreRecipeInputData;

public class StoreRecipeController {
    private final StoreRecipeInputBoundary userStoreRecipeUseCaseInteractor;

    public StoreRecipeController(StoreRecipeInputBoundary userStoreRecipeUseCaseInteractor) {
        this.userStoreRecipeUseCaseInteractor = userStoreRecipeUseCaseInteractor;
    }

    public void execute(JSONObject recipe, String username) {
        final StoreRecipeInputData storeRecipeInputData = new StoreRecipeInputData(username, recipe);

        userStoreRecipeUseCaseInteractor.execute(storeRecipeInputData);
    }

    public void goHome() {
        userStoreRecipeUseCaseInteractor.goHome();
    }

    public void goView(String username) {
        userStoreRecipeUseCaseInteractor.goView(username);
    }

}
