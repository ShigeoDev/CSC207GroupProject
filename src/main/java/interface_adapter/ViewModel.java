package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class ViewModel<T> {

    private String viewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private T state;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return this.viewName;
    }

    public void setState(T state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        this.support.firePropertyChange("state", null, this.state);
    }

    public  void firePropertyChanged(String propertyName) {
        this.support.firePropertyChange(propertyName, null, this.state);
    };

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
}