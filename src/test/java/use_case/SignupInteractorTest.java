package use_case;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import entity.UserFactory;
import use_case.Signup.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SignupInteractorTest class contains unit tests for the SignupInteractor class.
 */
class SignupInteractorTest {

    /**
     * Test SignupInteractor.
     */
    @Test
    void successTest() {
        // Creating input data for the signup with a valid username and matching passwords.
        SignupInputData inputData = new SignupInputData("Kevin", "kevinhu01", "kevinhu01");

        // Using an in-memory user repository to store the user data.
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Mocking the success presenter to verify the success scenario.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // Verifying that the user data is correct and the user was successfully saved in the repository.
                assertEquals("Kevin", user.getUsername());
                assertTrue(userRepository.existsByUsername("Kevin"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
            }
        };

        // Creating the interactor and executing the signup use case.
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new UserFactory());
        interactor.execute(inputData);
    }

    /**
     * Test case to verify that the signup fails when passwords do not match.
     */
    @Test
    void failurePasswordMismatchTest() {
        // Creating input data with mismatched passwords.
        SignupInputData inputData = new SignupInputData("Kevin", "kevinhu01", "kevinhu");

        // Using an in-memory user repository.
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Mocking the failure presenter to verify the failure scenario.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                // Verifying that the correct error message is displayed for password mismatch.
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
            }
        };

        // Creating the interactor and executing the signup use case.
        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new UserFactory());
        interactor.execute(inputData);
    }

    /**
     * Test case to verify that the signup fails when the username already exists.
     */
    @Test
    void failureUserExistsTest() {
        // Creating input data with an already existing username.
        SignupInputData inputData = new SignupInputData("Kevin", "kevinhu01", "kevinhu");

        // Using an in-memory user repository and saving an existing user.
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserFactory factory = new UserFactory();
        User user = factory.create("Kevin", "kevin01");
        userRepository.saveUser(user);

        // Mocking the failure presenter to verify the failure scenario when the user already exists.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                // Verifying that the correct error message is displayed when the user already exists.
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToLoginView() {
            }
        };

        // Creating the interactor and executing the signup use case.
        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new UserFactory());
        interactor.execute(inputData);
    }
}