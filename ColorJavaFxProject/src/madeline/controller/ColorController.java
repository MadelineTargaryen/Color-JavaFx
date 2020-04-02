package madeline.controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import madeline.model.Color;

import javafx.fxml.FXML;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ColorController implements Initializable {

    private Color color;

    @FXML
    private Canvas paintArea;

    @FXML
    private TextField redValue;

    @FXML
    private TextField greenValue;

    @FXML
    private TextField blueValue;

    @FXML
    private Slider redSlider;

    @FXML
    private Slider greenSlider;

    @FXML
    private Slider blueSlider;

    @FXML
    private TextField hexValue;

    @FXML
    private Pane colorBack;

    @FXML
    private MenuItem saveButton;

    @FXML
    private MenuItem quitButton;

    @FXML
    private CheckBox eraser;

    @FXML
    private TextField brushSize;

    public void onSave(){
        try{
            Image snapshot = paintArea.snapshot(null, null);
            ImageIO.write(
                    SwingFXUtils.fromFXImage(snapshot,null),
                    "png",
                    new File("paint.png")
            );
        } catch (Exception e){
            System.out.println("Failed to save the picture" + e);
        }
    }

    public void onExit(){
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        color = new Color(0,0,0);
        redSlider.valueProperty().addListener(value -> {
            int slideRedValue = (int)redSlider.getValue();
            color.setRed(slideRedValue);
            redValue.setText(Integer.toString(slideRedValue));
            hexValue.setText(color.getHexValue());
            colorBack.setStyle("-fx-background-color:" + color.getHexValue() + ";");
        });

        greenSlider.valueProperty().addListener(value -> {
            int slideGreenValue = (int)greenSlider.getValue();
            color.setGreen(slideGreenValue);
            greenValue.setText(Integer.toString(slideGreenValue));
            hexValue.setText(color.getHexValue());
            colorBack.setStyle("-fx-background-color:" + color.getHexValue() + ";");
        });

        blueSlider.valueProperty().addListener(value -> {
            int slideBlueValue = (int)blueSlider.getValue();
            color.setBlue(slideBlueValue);
            blueValue.setText(Integer.toString(slideBlueValue));
            hexValue.setText(color.getHexValue());
            colorBack.setStyle("-fx-background-color:" + color.getHexValue() + ";");
        });

        redValue.setOnKeyReleased(value -> {
            int red = Integer.parseInt(redValue.getText());
            color.setRed(red);
            redSlider.setValue(red);
            hexValue.setText(color.getHexValue());
            colorBack.setStyle("-fx-background-color:" + color.getHexValue() + ";");

        });
        greenValue.setOnKeyReleased(value -> {
            int green = Integer.parseInt(greenValue.getText());
            color.setGreen(green);
            greenSlider.setValue(green);
            hexValue.setText(color.getHexValue());
            colorBack.setStyle("-fx-background-color:" + color.getHexValue() + ";");
        });
        blueValue.setOnKeyReleased(value -> {
            int blue = Integer.parseInt(blueValue.getText());
            color.setBlue(blue);
            blueSlider.setValue(blue);
            hexValue.setText(color.getHexValue());
            colorBack.setStyle("-fx-background-color:" + color.getHexValue() + ";");
        });

        hexValue.setOnKeyReleased(value -> {
            String hex = hexValue.getText();
            color.setHexValue(hex);
            redValue.setText(Integer.toString(color.getRed()));
            redSlider.setValue(color.getRed());
            greenValue.setText(Integer.toString(color.getGreen()));
            greenSlider.setValue(color.getGreen());
            blueValue.setText(Integer.toString(color.getBlue()));
            blueSlider.setValue(color.getBlue());
            colorBack.setStyle("-fx-background-color:" + color.getHexValue() + ";");
        });

        GraphicsContext g = paintArea.getGraphicsContext2D();
        paintArea.setOnMouseDragged(value -> {
            if (brushSize != null && !brushSize.equals("")){
                Double size = Double.parseDouble(brushSize.getText());
                Double x = value.getX() - size / 2;
                Double y = value.getY() - size / 2;

                if (eraser.isSelected()){
                    g.clearRect(x, y, size, size);
                } else {
                    javafx.scene.paint.Color c =
                            new javafx.scene.paint.Color(
                                    ((double)color.getRed())/256,
                                    ((double)color.getGreen())/256,
                                    ((double)color.getBlue())/256,
                                    1
                            );
                    g.setFill(c);
                    g.fillRect(x, y, size, size);
                }
            }
        });

    }
}