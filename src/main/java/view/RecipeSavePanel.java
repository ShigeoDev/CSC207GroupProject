package view;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * RecipeSavePanel class represents a panel that displays a recipe's image, name, and a "Save" button.
 */
public class RecipeSavePanel extends JPanel {

    // Stores the image of the recipe.
    private BufferedImage image;
    // Label to display the recipe image.
    private JLabel imageLabel;
    // Label to display the recipe name.
    private JLabel name;
    // Button to save the recipe.
    private JButton saveButton;

    /**
     * Constructor for initializing the recipe save panel with the provided recipe data.
     * @param recipe The recipe data in JSONObject format.
     */
    public RecipeSavePanel(JSONObject recipe) {
        // Flag to check if the image is available.
        boolean imageexists = true;

        try {
            // Attempt to load the recipe image from the provided URL.
            image = ImageIO.read(new URL(recipe.getJSONObject("images")
                    .getJSONObject("THUMBNAIL")
                    .getString("url")));
        }
        catch (IOException e) {
            // If image loading fails, print a message.
            System.out.println("No Image Found");
            // Set the bool to false if the image is not found.
            imageexists = false;
        }
        finally {
            // If the image exists, create a label to display it.
            if (imageexists) {
                // Create the image label.
                imageLabel = new JLabel(new ImageIcon(image));
                // Add the image label to the panel.
                this.add(imageLabel);
            }

            // Create a label to display the recipe name.
            name = new JLabel(recipe.getString("label"));
            // Add the name label to the panel.
            this.add(name);

            // Create a "Save" button.
            saveButton = new JButton("Save");
            // Add the save button to the panel.
            this.add(saveButton);
        }
    }

    /**
     * Gets the "Save" button.
     * @return The save button.
     */
    public JButton getSaveButton() {
        return saveButton;
    }
}
