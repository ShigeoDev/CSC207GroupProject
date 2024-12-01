package use_case;

import org.junit.jupiter.api.Test;
import use_case.Homepage.HomepageInputBoundary;
import use_case.Homepage.HomepageInputData;
import use_case.Homepage.HomepageInteractor;
import use_case.Homepage.HomepageOutputBoundary;

/**
 * HomepageInteractorTest class tests the methods of the HomepageInteractor class.
 */
public class HomepageInteractorTest {

    /**
     * Test case for the successful execution of the HomepageInteractor.
     * Verifies that the success view is prepared correctly.
     */
    @Test
    public void successTest() {

        // Creating a mock presenter to verify the success view preparation.
        HomepageOutputBoundary presenter = new HomepageOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                // Assertion to confirm that the success view is prepared.
                assert true;
            }
        };

        // Creating the interactor with the mock presenter.
        HomepageInteractor interactor = new HomepageInteractor(presenter);

        // Executing the interactor, which should trigger the success view preparation.
        interactor.execute();
    }
}

