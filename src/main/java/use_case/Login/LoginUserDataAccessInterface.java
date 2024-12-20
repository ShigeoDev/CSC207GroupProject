package use_case.Login;

import entity.User;

/**
 * DAO for the Login Use Case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if the given user exists.
     * @param user the user entity to look for
     * @return true if the user exists; false otherwise
     */
    boolean existsByName(User user);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void saveUser(User user);

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

    /**
     * Returns the username of the current user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUsername();

    /**
     * Sets the username indicating who is the current user of the application.
     * @param username the new current username; null to indicate that no one is currently logged into the application.
     */
    void setCurrentUsername(String username);
}
