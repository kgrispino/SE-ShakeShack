import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, 1200, 500, Color.GREEN);

    GridPane gridpane = new GridPane();
    gridpane.setPadding(new Insets(5));
    gridpane.setHgap(10);
    gridpane.setVgap(10);
    Color green = Color.web("0x2faf00");
    gridpane.setBackground(new Background(new BackgroundFill(green, CornerRadii.EMPTY, Insets.EMPTY)));
    ColumnConstraints column1 = new ColumnConstraints(150, 100,
        Double.MAX_VALUE);
    ColumnConstraints column2 = new ColumnConstraints(100, 300,
            Double.MAX_VALUE);
    ColumnConstraints column3 = new ColumnConstraints(150, 100,
        Double.MAX_VALUE);
    column1.setHgrow(Priority.ALWAYS);
    column3.setHgrow(Priority.ALWAYS);
    gridpane.getColumnConstraints().addAll(column1, column2, column3);

    Label candidatesLbl = new Label("Location");
    GridPane.setHalignment(candidatesLbl, HPos.CENTER);
    gridpane.add(candidatesLbl, 0, 0);
    
    Label selectedLbl2 = new Label("Order");
    gridpane.add(selectedLbl2, 1, 0);
    GridPane.setHalignment(selectedLbl2, HPos.CENTER);

    Label selectedLbl = new Label("Menu");
    gridpane.add(selectedLbl, 2, 0);
    GridPane.setHalignment(selectedLbl, HPos.CENTER);

    // Candidates
    final ObservableList<String> locations = FXCollections.observableArrayList("1700 Broadway", "154 East 86th Street", "691 8th Avenue", "49 Grand Central Terminal", "170 Flatbush Avenue");
    final ListView<String> candidatesListView = new ListView<>(locations);
    gridpane.add(candidatesListView, 0, 1);

    final ObservableList<String> selected = FXCollections.observableArrayList("Hamburger - 390 cal\n" + 
    		"Let us know if you would like lettuce, tomato, pickle or onion", "SmokeShack®- 620 cal\n" + 
    				"Cheeseburger with all-natural smoked\n" + 
    				"Niman Ranch bacon, chopped cherry pepper, ShackSauce", "Hot Dog - 350 cal\n" + 
    						"Vienna all-beef", "Fries - 420 cal", "Chick’n Shack - 550 cal\n" + 
    								"Crispy chicken breast with lettuce, \n" + 
    								"pickles,  buttermilk herb mayo\n" + 
    								"buttermilk herb mayo");
    final ListView<String> heroListView = new ListView<>(selected);
    gridpane.add(heroListView, 2, 1);
    
    final ObservableList<String> menu = FXCollections.observableArrayList();
    final ListView<String> menuListView = new ListView<>(menu);
    gridpane.add(menuListView, 1, 1);

//    Button sendRightButton = new Button(" > ");
//    sendRightButton.setOnAction((ActionEvent event) -> {
//      String potential = candidatesListView.getSelectionModel()
//          .getSelectedItem();
//      if (potential != null) {
//        candidatesListView.getSelectionModel().clearSelection();
//        candidates.remove(potential);
//        selected.add(potential);
//      }
//    });
//
//    Button sendLeftButton = new Button(" < ");
//    sendLeftButton.setOnAction((ActionEvent event) -> {
//      String s = heroListView.getSelectionModel().getSelectedItem();
//      if (s != null) {
//        heroListView.getSelectionModel().clearSelection();
//        selected.remove(s);
//        candidates.add(s);
//      }
//    });
    VBox vbox = new VBox(5);
    //vbox.getChildren().addAll(sendRightButton, sendLeftButton);

    gridpane.add(vbox, 1, 1);
    root.setCenter(gridpane);

    GridPane.setVgrow(root, Priority.ALWAYS);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }
}