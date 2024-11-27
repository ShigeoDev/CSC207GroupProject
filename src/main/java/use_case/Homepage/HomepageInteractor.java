package use_case.Homepage;

public class HomepageInteractor implements HomepageInputBoundary{

    private final HomepageOutputBoundary userPresenter;

    public HomepageInteractor(HomepageOutputBoundary homepageOutputBoundary) {
        this.userPresenter = homepageOutputBoundary;
    }

    @Override
    public void execute() {
        userPresenter.prepareSuccessView();
    }

}
