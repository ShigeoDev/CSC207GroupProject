package use_case.store_recipe;

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
    public void execute(StoreRecipeInputData signupInputData) {
        final String username = signupInputData.getUsername();
        final StoreRecipeOutputData storeRecipeOutputData = new StoreRecipeOutputData(username);
        userPresenter.prepareSuccessView();
    }

}
