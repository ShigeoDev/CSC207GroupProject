package use_case.store_recipe;

/**
 * The Input Data for the Signup Use Case.
 */
public class StoreRecipeInputData {

    private String username;

    public StoreRecipeInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
