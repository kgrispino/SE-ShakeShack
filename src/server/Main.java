package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane mainPane = (GridPane) FXMLLoader.load(Main.class.getResource("serverAlex_ver.fxml"));
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
