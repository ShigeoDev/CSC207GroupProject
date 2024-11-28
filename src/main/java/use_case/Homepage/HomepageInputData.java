package use_case.Homepage;

/**
 * Data class representing the input data for the Homepage use case.
 * Contains the username of the current user.
 */
public class HomepageInputData {
    private String username;

    /**
     * Constructor for new HomepageInputData with a specific username.
     * @param username Username of the current user
     */
    public HomepageInputData(String username) {
        this.username = username;
    }

    /**
     * Getter for the username stored in the input data.
     * @return Username of current user
     */
    public String getUsername() {
        return username;
    }
}
