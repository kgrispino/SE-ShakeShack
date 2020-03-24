package fmxl_gui;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class menuController implements Initializable {
	
	@FXML
    private void handleOnButtonAction(ActionEvent event)
    {
        System.out.println("You clicked button: " + ((Button)event.getSource()).getId());
    }

    @FXML private void hamburger_btn_MouseClicked(MouseEvent event)
    {
        System.out.println("You clicked rectangle: " + ((Rectangle)event.getSource()).getId());
    }
    
    @FXML private void chicken_btn_MouseClicked(MouseEvent event)
    {
        System.out.println("You clicked rectangle: " + ((Rectangle)event.getSource()).getId());
    }
    
    @FXML private void fries_btn_MouseClicked(MouseEvent event)
    {
        System.out.println("You clicked rectangle: " + ((Rectangle)event.getSource()).getId());
    }
    
    @FXML private void hotdog_btn_MouseClicked(MouseEvent event)
    {
        System.out.println("You clicked rectangle: " + ((Rectangle)event.getSource()).getId());
    }
    
    @FXML private void shackburger_btn_MouseClicked(MouseEvent event)
    {
        System.out.println("You clicked rectangle: " + ((Rectangle)event.getSource()).getId());
    }
    
    @FXML private void smokeshack_btn_MouseClicked(MouseEvent event)
    {
        System.out.println("You clicked rectangle: " + ((Rectangle)event.getSource()).getId());
    }
    
    @FXML
    Label clock;

    private void initClock() {

        Timeline dateTime = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            clock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        dateTime.setCycleCount(Animation.INDEFINITE);
        dateTime.play();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClock();
    }

}