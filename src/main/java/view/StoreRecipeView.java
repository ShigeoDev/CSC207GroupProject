package view;

import interface_adapter.store_recipe.StoreRecipeState;
import interface_adapter.store_recipe.StoreRecipeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StoreRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String name = "StoreRecipe";
    private final StoreRecipeViewModel storeRecipeViewModel;

    /**
     * A window with a title and a JButton.
     */
    public StoreRecipeView(StoreRecipeViewModel storeRecipeViewModel) {
        this.storeRecipeViewModel = storeRecipeViewModel;
        this.storeRecipeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Stored Recipes");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
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
        final StoreRecipeState state = (StoreRecipeState) evt.getNewValue();
        // setFields(state);
    }

}


