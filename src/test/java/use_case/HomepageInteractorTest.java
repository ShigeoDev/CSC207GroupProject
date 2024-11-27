package use_case;

import org.junit.jupiter.api.Test;
import use_case.Homepage.HomepageInputBoundary;
import use_case.Homepage.HomepageInputData;
import use_case.Homepage.HomepageInteractor;

public class HomepageInteractorTest {
    @Test
    public void successTest() {




        HomepageInputBoundary presenter = new HomepageInputBoundary() {
            @Override
            public void execute() {

            }
        };
        HomepageInteractor interactor = new HomepageInteractor();

    }
}
