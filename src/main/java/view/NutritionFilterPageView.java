package view;

import interface_adapter.Homepage.HomepageController;
import interface_adapter.NutritionFilterPage.NutritionFilterPageController;
import interface_adapter.NutritionFilterPage.NutritionFilterPageState;
import interface_adapter.NutritionFilterPage.NutritionFilterPageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * The View for the Nutrition Filter Page.
 */
public class NutritionFilterPageView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Nutrition Filter";

    private final NutritionFilterPageViewModel viewModel;

    private NutritionFilterPageController controller;
    private HomepageController homepageController;

    private final JLabel errorLabel = new JLabel();

    private final JTextArea resultsArea = new JTextArea();

    private final JButton submitButton = new JButton("Find Recipes");;
    private final JButton homeButton = new JButton("Home");

    private final JCheckBox vitaminACheckBox = new JCheckBox("Vitamin A");
    private final JCheckBox vitaminCCheckBox = new JCheckBox("Vitamin C");
    private final JCheckBox vitaminB1CheckBox = new JCheckBox("Vitamin B1");
    private final JCheckBox vitaminB2CheckBox = new JCheckBox("Vitamin B2");
    private final JCheckBox vitaminB3CheckBox = new JCheckBox("Vitamin B3");
    private final JCheckBox vitaminB6CheckBox = new JCheckBox("Vitamin B6");
    private final JCheckBox vitaminB12CheckBox = new JCheckBox("Vitamin B12");
    private final JCheckBox vitaminDCheckBox = new JCheckBox("Vitamin D");
    private final JCheckBox vitaminECheckBox = new JCheckBox("Vitamin E");
    private final JCheckBox vitaminKCheckBox = new JCheckBox("Vitamin K");
    private final JCheckBox carbsCheckBox = new JCheckBox("CARBOHYDRATES");
    private final JCheckBox fatCheckBox = new JCheckBox("FAT");
    private final JCheckBox sugarCheckBox = new JCheckBox("SUGAR");
    private final JCheckBox fiberCheckBox = new JCheckBox("FIBER");
    private final JCheckBox proteinCheckBox = new JCheckBox("PROTEIN");
    private final JCheckBox ironCheckBox = new JCheckBox("IRON");
    private final JCheckBox calciumCheckBox = new JCheckBox("CALCIUM");
    private final JCheckBox potassiumCheckBox = new JCheckBox("POTASSIUM");
    private final JCheckBox sodiumCheckBox = new JCheckBox("SODIUM");
    private final JCheckBox magnesiumCheckBox = new JCheckBox("MAGNESIUM");
    private final JCheckBox phosphorusCheckBox = new JCheckBox("PHOSPHORUS");

    public NutritionFilterPageView(NutritionFilterPageViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Nutrition Filter Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nutrient selection panel
        final JPanel nutrientPanel = new JPanel();
        nutrientPanel.setLayout(new GridLayout(0, 2));
        nutrientPanel.add(vitaminACheckBox);
        nutrientPanel.add(vitaminCCheckBox);
        nutrientPanel.add(vitaminB1CheckBox);
        nutrientPanel.add(vitaminB2CheckBox);
        nutrientPanel.add(vitaminB3CheckBox);
        nutrientPanel.add(vitaminB6CheckBox);
        nutrientPanel.add(vitaminB12CheckBox);
        nutrientPanel.add(vitaminDCheckBox);
        nutrientPanel.add(vitaminECheckBox);
        nutrientPanel.add(vitaminKCheckBox);
        nutrientPanel.add(carbsCheckBox);
        nutrientPanel.add(fatCheckBox);
        nutrientPanel.add(sugarCheckBox);
        nutrientPanel.add(fiberCheckBox);
        nutrientPanel.add(proteinCheckBox);
        nutrientPanel.add(ironCheckBox);
        nutrientPanel.add(calciumCheckBox);
        nutrientPanel.add(potassiumCheckBox);
        nutrientPanel.add(sodiumCheckBox);
        nutrientPanel.add(magnesiumCheckBox);
        nutrientPanel.add(phosphorusCheckBox);

        // Error label
        errorLabel.setForeground(Color.RED);

        // Button panel with submit button
        final JPanel buttons = new JPanel();
        buttons.add(submitButton);
        buttons.add(homeButton);

        // Set action listener for home button
        homeButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        final NutritionFilterPageState currentState = viewModel.getState();
                        controller.switchToHomepage(currentState.getUsername());
                    }
                }
        );

        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(nutrientPanel);
        this.add(errorLabel);
        this.add(buttons);
        this.add(scrollPane);

        submitButton.addActionListener(this);

        // Update the ViewModel when checkboxes change
        vitaminACheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminCCheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminB1CheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminB2CheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminB3CheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminB6CheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminB12CheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminDCheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminECheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminKCheckBox.addActionListener(e -> updateSelectedNutrients());
        carbsCheckBox.addActionListener(e -> updateSelectedNutrients());
        fatCheckBox.addActionListener(e -> updateSelectedNutrients());
        sugarCheckBox.addActionListener(e -> updateSelectedNutrients());
        fiberCheckBox.addActionListener(e -> updateSelectedNutrients());
        proteinCheckBox.addActionListener(e -> updateSelectedNutrients());
        ironCheckBox.addActionListener(e -> updateSelectedNutrients());
        calciumCheckBox.addActionListener(e -> updateSelectedNutrients());
        potassiumCheckBox.addActionListener(e -> updateSelectedNutrients());
        sodiumCheckBox.addActionListener(e -> updateSelectedNutrients());
        magnesiumCheckBox.addActionListener(e -> updateSelectedNutrients());
        phosphorusCheckBox.addActionListener(e -> updateSelectedNutrients());
    }

    /**
     * Updates the selected nutrients in the view model based on the current state of the nutrient checkboxes.
     * <p>
     * This method collects the nutrients that are currently selected by the user and updates the
     * {@code NutritionFilterPageState} in the view model. It ensures that the view model always reflects
     * the latest selections made by the user.
     */
    private void updateSelectedNutrients() {
        ArrayList<String> selectedNutrients = new ArrayList<>();
        if (vitaminACheckBox.isSelected()) {
            selectedNutrients.add("Vitamin A");
        }
        if (vitaminCCheckBox.isSelected()) {
            selectedNutrients.add("Vitamin C");
        }
        if (vitaminB1CheckBox.isSelected()) {
            selectedNutrients.add("Vitamin B1");
        }
        if (vitaminB2CheckBox.isSelected()) {
            selectedNutrients.add("Vitamin B2");
        }
        if (vitaminB3CheckBox.isSelected()) {
            selectedNutrients.add("Vitamin B3");
        }
        if (vitaminB6CheckBox.isSelected()) {
            selectedNutrients.add("Vitamin B6");
        }
        if (vitaminB12CheckBox.isSelected()) {
            selectedNutrients.add("Vitamin B12");
        }
        if (vitaminDCheckBox.isSelected()) {
            selectedNutrients.add("Vitamin D");
        }
        if (vitaminECheckBox.isSelected()) {
            selectedNutrients.add("Vitamin E");
        }
        if (vitaminKCheckBox.isSelected()) {
            selectedNutrients.add("Vitamin K");
        }
        if (carbsCheckBox.isSelected()) {
            selectedNutrients.add("CARBOHYDRATES");
        }
        if (fatCheckBox.isSelected()) {
            selectedNutrients.add("FAT");
        }
        if (sugarCheckBox.isSelected()) {
            selectedNutrients.add("SUGAR");
        }
        if (fiberCheckBox.isSelected()) {
            selectedNutrients.add("FIBER");
        }
        if (proteinCheckBox.isSelected()) {
            selectedNutrients.add("PROTEIN");
        }
        if (ironCheckBox.isSelected()) {
            selectedNutrients.add("IRON");
        }
        if (calciumCheckBox.isSelected()) {
            selectedNutrients.add("CALCIUM");
        }
        if (potassiumCheckBox.isSelected()) {
            selectedNutrients.add("POTASSIUM");
        }
        if (sodiumCheckBox.isSelected()) {
            selectedNutrients.add("SODIUM");
        }
        if (magnesiumCheckBox.isSelected()) {
            selectedNutrients.add("MAGNESIUM");
        }
        if (phosphorusCheckBox.isSelected()) {
            selectedNutrients.add("PHOSPHORUS");
        }

        NutritionFilterPageState currentState = viewModel.getState();
        if (currentState == null) {
            currentState = new NutritionFilterPageState();
        }
        currentState.setSelectedNutrients(selectedNutrients);
        viewModel.setState(currentState);
    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(submitButton)) {
            NutritionFilterPageState currentState = viewModel.getState();
            if (currentState != null && controller != null) {
                controller.execute(currentState.getSelectedNutrients());
            }
        }
    }

    /**
     * Handles action events triggered by user interactions, such as button clicks.
     * <p>
     * Specifically, this method reacts to the submit button click by invoking the controller's execute method
     * with the currently selected nutrients. It initiates the use case to find recipes based on the user's selections.
     * @param evt the {@code ActionEvent} that triggered this method
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        NutritionFilterPageState state = (NutritionFilterPageState) evt.getNewValue();

        if (state.getSearchError() != null) {
            errorLabel.setText(state.getSearchError());
            resultsArea.setText("");
        } else if (state.getRecipeDetails() != null) {
            errorLabel.setText("");
            String results = String.join("\n", state.getRecipeDetails());
            resultsArea.setText(results);
        }
    }

    /**
     * Returns the name of this view.
     * <p>
     * This method is used to identify the view, typically when switching between different views
     * in a card layout or view manager.
     * @return the name of the view as a {@code String}
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the controller for this view.
     * <p>
     * The controller handles user actions and interacts with the interactor (use case).
     * This method establishes the connection between the view and its controller.
     * @param controller the {@code NutritionFilterPageController} to handle user actions
     */
    public void setController(NutritionFilterPageController controller) {
        this.controller = controller;
    }

    public void setHomepageController(HomepageController controller) { this.homepageController = controller;
    }
}
