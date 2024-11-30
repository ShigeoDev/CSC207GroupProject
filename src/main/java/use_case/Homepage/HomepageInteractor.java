package use_case.Homepage;

import data_access.ApiDataAccessObject;
import data_access.FileUserDataAccessObject;
import org.json.JSONObject;

/**
 * Interactor class that implements the logic for the Homepage use case.
 * Handles execution of homepage-related operations
 */
public class HomepageInteractor implements HomepageInputBoundary{

    private final HomepageOutputBoundary userPresenter;

    /**
     * Constructor for a new HomepageInteractor.
     * @param homepageOutputBoundary Output boundary to handle the presentation
     */
    public HomepageInteractor(HomepageOutputBoundary homepageOutputBoundary) {
        this.userPresenter = homepageOutputBoundary;
    }

    /**
     * Executes the homepage use case logic.
     * Prepares the success view.
     */
    @Override
    public void execute() {
        userPresenter.prepareSuccessView();
    }

}
