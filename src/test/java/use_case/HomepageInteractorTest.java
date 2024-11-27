package use_case;

import org.junit.jupiter.api.Test;
import use_case.Homepage.HomepageInputBoundary;
import use_case.Homepage.HomepageInputData;
import use_case.Homepage.HomepageInteractor;
import use_case.Homepage.HomepageOutputBoundary;

public class HomepageInteractorTest {
    @Test
    public void successTest() {

        HomepageOutputBoundary presenter = new HomepageOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assert true;
            }
        };

        HomepageInteractor interactor = new HomepageInteractor(presenter);
        interactor.execute();
    }
}
