package use_case.store_recipe;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface StoreRecipeInputBoundary {

    /**
     * Executes the signup use case.
     * @param signupInputData the input data
     */
    void execute(StoreRecipeInputData signupInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();
}
