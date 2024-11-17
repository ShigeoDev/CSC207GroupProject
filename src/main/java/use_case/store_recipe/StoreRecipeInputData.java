package use_case.store_recipe;

/**
 * The Input Data for the Signup Use Case.
 */
public class StoreRecipeInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;

    public StoreRecipeInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
