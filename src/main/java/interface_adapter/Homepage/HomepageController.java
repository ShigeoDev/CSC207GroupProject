package interface_adapter.Homepage;

import use_case.Homepage.HomepageInputBoundary;
import use_case.Homepage.HomepageInputData;

/**
 * Controller class for the Homepage view.
 * Handles user interactions.
 */
public class HomepageController {
    private final HomepageInputBoundary userHomepageUseCaseInteractor;

    /**
     * Constructs a new HomepageController.
     * @param userHomepageUseCaseInteractor The use case interactor to hand homepage interactions
     */
    public HomepageController(HomepageInputBoundary userHomepageUseCaseInteractor) {
        this.userHomepageUseCaseInteractor = userHomepageUseCaseInteractor;
    }

    /**
     * Executes the homepage use case.
     * Starts the execution of the homepage interacteor.
     */
    public void execute() {
        userHomepageUseCaseInteractor.execute();
    }
}

