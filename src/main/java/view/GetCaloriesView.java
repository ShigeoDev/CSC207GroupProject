package view;

import interface_adapter.GetCalories.GetCaloriesController;
import interface_adapter.GetCalories.GetCaloriesState;
import interface_adapter.GetCalories.GetCaloriesViewModel;
import interface_adapter.Homepage.HomepageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetCaloriesView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String name = "CaloriesView";
    private final GetCaloriesViewModel getCaloriesViewModel;
    private GetCaloriesController getCaloriesController;
    private final JTextField recipeNameTextField;

    final JButton Submit;
    final JButton backButton;

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

    public String getName() {
        return this.name;
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(Submit)) {
            String recipeName = recipeNameTextField.getText();
            getCaloriesController.execute(recipeName, getCaloriesViewModel.getUsername());
        } else if (evt.getSource().equals(backButton)) {
            getCaloriesController.backToHome();
        }
    }

    public void setGetCaloriesController(GetCaloriesController controller) {
        this.getCaloriesController = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetCaloriesState state = (GetCaloriesState) evt.getNewValue();
        if (state.getCaloriesError() != null) {
            JOptionPane.showMessageDialog(this, state.getCaloriesError());
        }
    }
}
