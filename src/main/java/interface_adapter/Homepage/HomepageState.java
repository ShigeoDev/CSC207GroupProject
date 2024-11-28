package interface_adapter.Homepage;

/**
 * State class for the Homepage view.
 * Stores the current state data for the homepage.
 */
public class HomepageState {
    private String username;

    /**
     * Setter for the username in the homepage state.
     * @param username Username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the current username.
     * @return Current username
     */
    public String getUsername() {
        return username;
    }
}

