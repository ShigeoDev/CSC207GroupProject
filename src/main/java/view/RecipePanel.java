package view;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RecipePanel extends JPanel {

    private BufferedImage image;
    private JLabel imageLabel;
    private JLabel name;

    public RecipePanel(JSONObject recipe) {
        boolean imageexists = true;
        try {
            image = ImageIO.read(new URL(recipe.getJSONObject("images")
                    .getJSONObject("THUMBNAIL")
                    .getString("url")));
        }
        catch (IOException e) {
            System.out.println("No Image");
            imageexists = false;
        }
        finally {
            if (imageexists) {
                imageLabel = new JLabel(new ImageIcon(image));
                this.add(imageLabel);
            }
            name = new JLabel(recipe.getString("label"));

            this.add(name);
        }
    }
}
