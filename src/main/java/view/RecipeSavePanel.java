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

        saveButton = new JButton("Save");

        this.add(imageLabel);
        this.add(name);
        this.add(saveButton);
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
