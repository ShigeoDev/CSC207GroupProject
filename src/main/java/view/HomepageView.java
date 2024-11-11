package view;

import interface_adapter.Homepage.HomepageState;
import interface_adapter.Homepage.HomepageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomepageView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String name = "Homepage";
    private final HomepageViewModel homepageViewModel;

    final JButton SavedRecipes;
    final JButton SearchRecipes;

    /**
     * A window with a title and a JButton.
     */
    public HomepageView(HomepageViewModel homepageViewModel) {
        this.homepageViewModel = homepageViewModel;
        this.homepageViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Homepage Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        SavedRecipes = new JButton(homepageViewModel.Saved_BUTTON_LABEL);
        buttons.add(SavedRecipes);
        SearchRecipes = new JButton(homepageViewModel.Search_BUTTON_LABEL);
        buttons.add(SearchRecipes);

        SavedRecipes.addActionListener(this);
        SearchRecipes.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
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
        HomepageState state = (HomepageState) evt.getNewValue();
        // setFields(state);
    }

}

