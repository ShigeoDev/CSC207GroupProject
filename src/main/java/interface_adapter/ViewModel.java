package interface_adapter;

import interface_adapter.signup.SignupState;

import java.beans.PropertyChangeListener;

public abstract class ViewModel<T> {

    private String viewName;
    private T state;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();

    public abstract void addPropertyChangeListener(PropertyChangeListener listener);

    public T getState() {
        return this.state;
    }

    public void setState(T state) {
        this.state = state;
    }

}