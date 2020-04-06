package server;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import server.sockServer;

public class serverController implements Initializable {	
	@FXML 
	private Button exitButton;

	@FXML
	private void removeButtonAction( MouseEvent event) {
		System.exit(0);
	}
	
	@FXML
	public TextArea userLog;
	
	public void appendUserLog(String s) {
		userLog.appendText(s);
	}
	
	@FXML
	public TextArea foodLog;
	
	@FXML
	public Label num_users;
		
	
	private void startSockServer()
	  {	
		 Thread refreshWeatherThread = new Thread()
		 {
		    public void run()
			  { 	
				  sockServer.runSockServer();
		      }
		 };

	    refreshWeatherThread.start();
	  }
	

	public void initialize(URL location, ResourceBundle resources) {
		Controllers.setMainController(this);
		startSockServer();
	}
} 