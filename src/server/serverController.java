package server;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class serverController implements Initializable {	
	@FXML 
	private Button exitButton;

	@FXML
	private void removeButtonAction( MouseEvent event) {
		System.exit(0);
	}

	public void initialize(URL location, ResourceBundle resources) {
	}
} 