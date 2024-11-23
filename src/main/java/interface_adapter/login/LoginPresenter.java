package interface_adapter.login;

import interface_adapter.Homepage.HomepageState;
import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;
import use_case.Login.LoginOutputBoundary;
import use_case.Login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final HomepageViewModel homepageViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          HomepageViewModel homepageViewModel, SignupViewModel signupViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {

        final HomepageState homepageState = homepageViewModel.getState();
        this.homepageViewModel.setState(homepageState);
        this.homepageViewModel.firePropertyChanged();

        this.viewManagerModel.setState(homepageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSignupView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
