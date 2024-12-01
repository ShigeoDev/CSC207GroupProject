package interface_adapter.store_recipe;

import org.json.JSONObject;
import use_case.store_recipe.StoreRecipeInputBoundary;
import use_case.store_recipe.StoreRecipeInputData;

/**
 * Controller for recipe storage operations.
 */
public class StoreRecipeController {
    private final StoreRecipeInputBoundary userStoreRecipeUseCaseInteractor; // Use case interactor for storing recipes.

    /**
     * Constructor for initializing the controller with the use case interactor.
     * @param userStoreRecipeUseCaseInteractor The interactor for storing recipes.
     */
    public StoreRecipeController(StoreRecipeInputBoundary userStoreRecipeUseCaseInteractor) {
        this.userStoreRecipeUseCaseInteractor = userStoreRecipeUseCaseInteractor;
    }

    /**
     * Executes the recipe storage use case with the given recipe and username.
     * @param recipe   The recipe to store.
     * @param username The username associated with the recipe.
     */
    public void execute(JSONObject recipe, String username) {
        final StoreRecipeInputData storeRecipeInputData = new StoreRecipeInputData(username, recipe);

        userStoreRecipeUseCaseInteractor.execute(storeRecipeInputData);
    }

    /**
     * Navigates to the view associated with the user's stored recipes.
     * @param username The username whose recipes should be displayed.
     */
    public void goView(String username) {
        userStoreRecipeUseCaseInteractor.goView(username);
    }
}
