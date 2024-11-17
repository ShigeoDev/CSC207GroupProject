package view;

import interface_adapter.DishType.DishTypeController;
import interface_adapter.DishType.DishTypeState;
import interface_adapter.DishType.DishTypeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DishTypeView extends JPanel implements ActionListener, PropertyChangeListener {

    private final DishTypeViewModel dishTypeViewModel;

    private final JLabel dishType = new JLabel("Dish Type");
    private final JTextArea dishTypeInputField = new JTextArea();
    private final JButton searchButton = new JButton("Search");
    private DishTypeController dishTypeController;

    public DishTypeView(DishTypeViewModel dishTypeViewModel) {

        dishType.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.dishTypeViewModel = dishTypeViewModel;
        this.dishTypeViewModel.addPropertyChangeListener(this);

        final JPanel buttons = new JPanel();
        buttons.add(searchButton);

        searchButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(searchButton)) {
                        dishTypeController.execute(dishTypeInputField.getText());

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(dishType);
        this.add(dishTypeInputField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final DishTypeState state = (DishTypeState) evt.getNewValue();
        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(DishTypeState state) {
        dishTypeInputField.setText(state.getDishType());
    }

    public void setDishTypeController(DishTypeController controller) {
        this.dishTypeController = controller;
    }
}
