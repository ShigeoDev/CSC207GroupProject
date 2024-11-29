package view;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class RecipeSavePanel extends JPanel {

    private BufferedImage image;
    private JLabel imageLabel;
    private JLabel name;
    private JButton saveButton;

    public RecipeSavePanel(JSONObject recipe) {
        boolean imageexists = true;
        try {
            image = ImageIO.read(new URL(recipe.getJSONObject("images")
                    .getJSONObject("THUMBNAIL")
                    .getString("url")));
        }
        catch (IOException e) {
            System.out.println("No Image Found");
            imageexists = false;
        }
        finally {
            if (imageexists) {
                imageLabel = new JLabel(new ImageIcon(image));
                this.add(imageLabel);
            }
            name = new JLabel(recipe.getString("label"));

            saveButton = new JButton("Save");

            this.add(name);
            this.add(saveButton);
        }
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
