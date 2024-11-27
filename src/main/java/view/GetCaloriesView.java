package view;

import interface_adapter.GetCalories.GetCaloriesController;
import interface_adapter.GetCalories.GetCaloriesState;
import interface_adapter.GetCalories.GetCaloriesViewModel;
import interface_adapter.Homepage.HomepageController;
import interface_adapter.Homepage.HomepageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View class that allows users to input a recipe name and request calorie information.
 * Panel provides a text field to enter recipe name and a button to submit.
 */
public class GetCaloriesView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String name = "CaloriesView";
    private final GetCaloriesViewModel getCaloriesViewModel;
    private GetCaloriesController getCaloriesController;
    private HomepageController homepageController;
    private final JTextField recipeNameTextField;

    final JButton Submit;
    final JButton backButton;

    /**
     * Constructs a new GetCaloriesView.
     * @param getCaloriesViewModel The view model for calorie lookup
     */
    public GetCaloriesView(GetCaloriesViewModel getCaloriesViewModel) {
        this.getCaloriesViewModel = getCaloriesViewModel;
        this.getCaloriesViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Get Calories");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel recipeNameInfo = new JLabel(GetCaloriesViewModel.RECIPE_LABEL);
        recipeNameTextField = new JTextField(15);

        final JPanel buttons = new JPanel();
        Submit = new JButton(getCaloriesViewModel.Submit_BUTTON_LABEL);
        backButton = new JButton("Back to home");
        buttons.add(Submit);
        buttons.add(backButton);

        Submit.addActionListener(this);
        backButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(recipeNameInfo);
        this.add(recipeNameTextField);
        this.add(buttons);
    }

    /**
     * Getter for the name of this view.
     * @return The view name "CaloriesView"
     */
    public String getName() {
        return this.name;
    }

    /**
     * Handles button clicks.
     * @param evt the event to be processed
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(Submit)) {
            String recipeName = recipeNameTextField.getText();
            getCaloriesController.execute(recipeName, getCaloriesViewModel.getUsername());
        } else if (evt.getSource().equals(backButton)) {
            homepageController.execute();
        }
    }

    /**
     * Setter for the GetCalories controller.
     * @param controller Controller for GetCalories
     */
    public void setGetCaloriesController(GetCaloriesController controller) {
        this.getCaloriesController = controller;
    }

    /**
     * Setter for the Homepage controller
     * @param controller Controller for Homepage
     */
    public void setHomepageController(HomepageController controller) {
        this.homepageController = controller;
    }

    /**
     * Handles updates to the view model.
     * Displays error messages accordingly.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetCaloriesState state = (GetCaloriesState) evt.getNewValue();
        if (state.getCaloriesError() != null) {
            JOptionPane.showMessageDialog(this, state.getCaloriesError());
        }
    }
}
