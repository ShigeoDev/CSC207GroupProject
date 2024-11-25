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
        try {
            image = ImageIO.read(new URL(recipe.getJSONObject("images")
                    .getJSONObject("THUMBNAIL")
                    .getString("url")));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageLabel = new JLabel(new ImageIcon(image));
        name = new JLabel(recipe.getString("label"));

        this.add(imageLabel);
        this.add(name);
    }
}
