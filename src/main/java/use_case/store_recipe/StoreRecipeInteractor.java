package use_case.store_recipe;


import data_access.FileUserDataAccessObject;
import use_case.Homepage.HomepageOutputData;

/**
 * The Signup Interactor.
 */
public class StoreRecipeInteractor implements StoreRecipeInputBoundary {
    private final StoreRecipeOutputBoundary userPresenter;
    private final StoreRecipeDataAccessInterface storeRecipeDAO;

    public StoreRecipeInteractor(StoreRecipeDataAccessInterface storeRecipeDAO, StoreRecipeOutputBoundary signupOutputBoundary) {
        this.userPresenter = signupOutputBoundary;
        this.storeRecipeDAO = storeRecipeDAO;
    }

    @Override
    public void execute(StoreRecipeInputData storeRecipeInputData) {
        storeRecipeDAO.saveRecipe(storeRecipeInputData.getRecipe(), storeRecipeInputData.getUsername());
    }

    @Override
    public void goView(String username) {
        final StoreRecipeOutputData outputData = new StoreRecipeOutputData(username, storeRecipeDAO.getRecipes(username));
        userPresenter.goView(outputData);

    }

}
