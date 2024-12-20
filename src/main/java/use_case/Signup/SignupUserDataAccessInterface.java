package use_case.Signup;

import entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface SignupUserDataAccessInterface {

    /**
     * Checks if the given user exists.
     * @param user the User entity to look for
     * @return true if given user exists; false otherwise
     */
    boolean existsByName(User user);

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void saveUser(User user);
}
