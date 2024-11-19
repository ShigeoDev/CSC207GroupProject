package interface_adapter.store_recipe;

import use_case.store_recipe.StoreRecipeInputBoundary;
import use_case.store_recipe.StoreRecipeInputData;

public class StoreRecipeController {
    private final StoreRecipeInputBoundary userStoreRecipeUseCaseInteractor;

    public StoreRecipeController(StoreRecipeInputBoundary userStoreRecipeUseCaseInteractor) {
        this.userStoreRecipeUseCaseInteractor = userStoreRecipeUseCaseInteractor;
    }

    public void execute(String username) {
        final StoreRecipeInputData storeRecipeInputData = new StoreRecipeInputData(username);

        userStoreRecipeUseCaseInteractor.execute(storeRecipeInputData);
    }
}
