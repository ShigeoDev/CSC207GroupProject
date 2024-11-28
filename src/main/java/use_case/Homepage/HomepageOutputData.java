package use_case.Homepage;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Data class representing the output data from the Homepage use case.
 * Contains information to display the view.
 */
public class HomepageOutputData {
    private String username;

    /**
     * Constructor for new HomepageOutput data with the specific username.
     * @param username Username of the current user
     */
    public HomepageOutputData(String username) {
        this.username = username;
    }

    /**
     * Getter for the username stored in the output.
     * @return Username of current user
     */
    public String getUsername() {
        return username;
    }

}
