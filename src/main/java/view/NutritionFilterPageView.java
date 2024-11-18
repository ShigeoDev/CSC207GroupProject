package view;

import interface_adapter.NutritionFilterPage.NutritionFilterPageState;
import interface_adapter.NutritionFilterPage.NutritionFilterPageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NutritionFilterPageView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String name = "NutritionFilterPage";
    private final NutritionFilterPageViewModel nutritionFilterPageViewModel;

    final JButton FilterBasedOnNutrition;

    /**
     * A window with a title and a JButton.
     */
    public NutritionFilterPageView(NutritionFilterPageViewModel nutritionFilterPageViewModel) {
        this.nutritionFilterPageViewModel = nutritionFilterPageViewModel;
        this.nutritionFilterPageViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Nutrition_Filter Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        FilterBasedOnNutrition = new JButton(nutritionFilterPageViewModel.Filter_BUTTON_LABEL);
        buttons.add(FilterBasedOnNutrition);

        FilterBasedOnNutrition.addActionListener(this);

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
        NutritionFilterPageState state = (NutritionFilterPageState) evt.getNewValue();
        // setFields(state);
    }
}
