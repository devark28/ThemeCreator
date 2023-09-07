/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themecreator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class ThemeCreator extends Application {
    
    private Label color_board;
    private Slider red_slider;
    private Slider green_slider;
    private Slider blue_slider;
    private Slider color_sync;
    private Color color;
    private final VBox root = new VBox();
    private final VBox tools = new VBox(30);
    private int workBefore;
        
    @Override
    public void start(Stage primaryStage) {
        
        color = new Color();
        color_board = new Label(color.getHEX().toUpperCase());
        HBox.setHgrow(color_board, Priority.ALWAYS);
        VBox.setVgrow(tools, Priority.ALWAYS);
        color_board.setTextAlignment(TextAlignment.CENTER);
        color_board.setFont(new Font(50));
        color_board.minWidthProperty().bind(root.widthProperty());
        red_slider = new Slider(0, 255, color.getRed());
        green_slider = new Slider(0, 255, color.getGreen());
        blue_slider = new Slider(0, 255, color.getBlue());
        color_sync = new Slider(0, 255, 0);
        workBefore = (int) color_sync.getValue();
        
        red_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            color.setColor(red_slider.getValue(), color.getGreen(), color.getBlue());
            System.out.println(color.getRGB());
            tools.setStyle("-fx-padding: 20;-fx-background-color: "+color.getHEX()+";");
            color_board.textProperty().setValue(color.getHEX().toUpperCase());
        });
        green_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            color.setColor(color.getRed(), green_slider.getValue(), color.getBlue());
            System.out.println(color.getRGB());
            tools.setStyle("-fx-padding: 20;-fx-background-color: "+color.getHEX()+";");
            color_board.textProperty().setValue(color.getHEX().toUpperCase());
        });
        blue_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            color.setColor(color.getRed(), color.getGreen(), blue_slider.getValue());
            System.out.println(color.getRGB());
            tools.setStyle("-fx-padding: 20;-fx-background-color: "+color.getHEX()+";");
            color_board.textProperty().setValue(color.getHEX().toUpperCase());
        });
        color_sync.valueProperty().addListener((observable, oldValue, newValue) -> {
            int workAfter = (int)color_sync.getValue();
            int work = workAfter-workBefore;
            System.out.println(workAfter+" : "+work+" : "+workBefore);
            color.setColor(red_slider.getValue()+work, green_slider.getValue()+work, blue_slider.getValue()+work);
            red_slider.setValue(color.getRed());
            green_slider.setValue(color.getGreen());
            blue_slider.setValue(color.getBlue());
            System.out.println(color.getRGB());
            tools.setStyle("-fx-padding: 20;-fx-background-color: "+color.getHEX()+";");
            color_board.textProperty().setValue(color.getHEX().toUpperCase()+" : "+(int)color_sync.getValue());
            workBefore = workAfter;
        });
        color.valueChangingProperty().addListener((observable) -> {
            System.out.println("vovo");
        });
        
        tools.getChildren().addAll(red_slider, green_slider, blue_slider, color_sync);
        root.getChildren().addAll(color_board, tools);
        tools.setStyle("-fx-padding: 20;-fx-background-color: "+color.getHEX()+";");
        
        Scene scene = new Scene(root, 300, 500);
        
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("Theme Maker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
