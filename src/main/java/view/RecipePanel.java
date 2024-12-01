package view;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * RecipePanel class represents a panel displaying a recipe's image and name.
 */
public class RecipePanel extends JPanel {

    // Stores the image of the recipe.
    private BufferedImage image;
    // Label to display the image.
    private JLabel imageLabel;
    // Label to display the recipe's name.
    private JLabel name;

    /**
     * Constructor for initializing the recipe panel with the provided recipe.
     * @param recipe The recipe data in JSONObject format.
     */
    public RecipePanel(JSONObject recipe) {
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
            System.out.println("No Image");
            // Set the flag to false if the image is not found.
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
        }
    }
}

