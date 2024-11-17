package use_case.store_recipe;

/**
 * The Signup Interactor.
 */
public class StoreRecipeInteractor implements StoreRecipeInputBoundary {
    private final StoreRecipeOutputBoundary userPresenter;

    public StoreRecipeInteractor(StoreRecipeOutputBoundary signupOutputBoundary) {
        this.userPresenter = signupOutputBoundary;
    }

    @Override
    public void execute(StoreRecipeInputData signupInputData) {
        final StoreRecipeOutputData storeRecipeOutputData = new StoreRecipeOutputData();
        userPresenter.prepareSuccessView(storeRecipeOutputData);
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
