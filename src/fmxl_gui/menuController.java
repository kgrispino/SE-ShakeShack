package fmxl_gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

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
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}