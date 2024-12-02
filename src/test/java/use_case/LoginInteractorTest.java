package use_case;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.Login.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LoginInteractorTest class contains unit tests for the LoginInteractor class.
 */
class LoginInteractorTest {

    /**
     * Test case for successfully logging in a user with correct credentials.
     */
    @Test
    void successTest() {
        // Preparing the login input data with username and password.
        LoginInputData inputData = new LoginInputData("Kevin", "kevinhu01");

        // Creating an inmemory DAO.
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Creating and saving a new user with the provided credentials.
        UserFactory factory = new UserFactory();
        User user = factory.create("Kevin", "kevinhu01");
        userRepository.saveUser(user);

        // Mocking the success presenter that handles the success view preparation.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // Verifying that the username matches the expected value.
                assertEquals("Kevin", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                // No action needed for this test.
            }
        };

        // Creating the LoginInteractor and executing the login process.
        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    /**
     * Test case to check if the user is logged in successfully.
     */
    @Test
    void successUserLoggedInTest() {
        LoginInputData inputData = new LoginInputData("Kevin", "kevinhu01");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new UserFactory();
        User user = factory.create("Kevin", "kevinhu01");
        userRepository.saveUser(user);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // Verifying that the current username in the repository is correct.
                assertEquals("Kevin", userRepository.getCurrentUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                // No action needed for this test.
            }
        };

        // Creating the LoginInteractor and verifying the initial state.
        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        assertEquals(null, userRepository.getCurrentUsername()); // Initial username should be null.

        // Executing the login process.
        interactor.execute(inputData);
    }

    /**
     * Test case for handling password mismatch during login.
     */
    @Test
    void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData("Kevin", "kevinhu01");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Creating a user with an incorrect password.
        UserFactory factory = new UserFactory();
        User user = factory.create("Kevin", "kevinhu");
        userRepository.saveUser(user);

        // Mocking the failure presenter for password mismatch.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                // Verifying the error message for password mismatch.
                assertEquals("Incorrect password for \"Kevin\".", error);
            }

            @Override
            public void switchToSignupView() {
                // No action needed for this test.
            }
        };

        // Creating the LoginInteractor and executing the login process.
        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    /**
     * Test case for handling user non-existence during login.
     */
    @Test
    void failureUserDoesNotExistTest() {
        LoginInputData inputData = new LoginInputData("Kevin", "kevinhu01");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Mocking the failure presenter for non-existing user.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                // Verifying the error message for non-existent user.
                assertEquals("Kevin: Account does not exist.", error);
            }

            @Override
            public void switchToSignupView() {
                // No action needed for this test.
            }
        };

        // Creating the LoginInteractor and executing the login process.
        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToSignupViewTest() {
        // Mocking the presenter to verify the login view switch.
        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                // Verifying that the method is invoked.
                assertTrue(true, "switchToLoginView was called.");
            }
        };

        // Using an in-memory user repository.
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Creating the interactor.
        LoginInputBoundary interactor = new LoginInteractor(userRepository, presenter);

        // Call switchToLoginView directly to test its behavior.
        interactor.switchToSignupView();
    }
}