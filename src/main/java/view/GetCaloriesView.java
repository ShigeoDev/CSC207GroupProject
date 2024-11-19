package view;

import interface_adapter.GetCalories.GetCaloriesState;
import interface_adapter.GetCalories.GetCaloriesViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetCaloriesView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String name = "CaloriesView";
    private final GetCaloriesViewModel getCaloriesViewModel;

    final JButton Submit;

    public GetCaloriesView(GetCaloriesViewModel getCaloriesViewModel) {
        this.getCaloriesViewModel = getCaloriesViewModel;
        this.getCaloriesViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Get Calories");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel recipeNameInfo = new JLabel(GetCaloriesViewModel.RECIPE_LABEL);
        final JTextField recipeNameTextField = new JTextField(15);

        final JPanel buttons = new JPanel();
        Submit = new JButton(getCaloriesViewModel.Submit_BUTTON_LABEL);
        buttons.add(Submit);

        Submit.addActionListener(this);

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
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetCaloriesState state = (GetCaloriesState) evt.getNewValue();
        // setFields(state);
    }
}
