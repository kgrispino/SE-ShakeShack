package fmxl_gui;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class menuController implements Initializable {	
	//Global list to track what the user clicked on the menu
	ObservableList<String> order = FXCollections.observableArrayList();
	double cost;
	double tax;
	double total;
	@FXML
    private ListView<String> userOrder;
	//Function for handling what the user clicked, costs are calculated on click  
	@FXML
    private void handleOnButtonAction(MouseEvent event)
    {
        //System.out.println("You clicked button: " + ((Rectangle)event.getSource()).getId());
        order.add(((Rectangle)event.getSource()).getId());
        userOrder.setItems(order);
        
        cost = calCost();
        tax = calTax(calCost());
        total = cost + tax;
        
        orderCost.setText("Cost:	" + String.format("%.2f", cost));
        orderTax.setText("Tax:	" + String.format("%.2f", tax));
        totalOrdercost.setText("Total:	" + String.format("%.2f", total));
    }
    
    @FXML
    private Label clock;
    //Creates a clock in the corner of the screen
    private void initClock() {
        Timeline dateTime = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            clock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        dateTime.setCycleCount(Animation.INDEFINITE);
        dateTime.play();
    }
    
    @FXML
    private ListView<String> locationList;
    //https://stackoverflow.com/questions/40964403/filling-listview-from-fxml-file-in-javafx-empty-listview
    private void initLocations(ObservableList<String> elements) {
    	elements.add("Location 1");
        elements.add("Location 2");
        elements.add("Location 3");
        locationList.setItems(elements);
    }
    @FXML
    private Label selectedLocation;
    
    @FXML
    private Label orderCost;
    private double calCost() {
    	//https://stackoverflow.com/questions/31262040/how-can-i-edit-a-specific-object-in-observablelist
    	int order_length = order.size();
    	double cost = 0;
    	for (int i = 0; i < order_length; i++) {
    		if("hamburger".equals(order.get(i))){
    			cost = cost + 4.19;
    		}
    		else if("chicken".equals(order.get(i))){
    			cost = cost + 6.19;
    		}
    		else if("fries".equals(order.get(i))){
    			cost = cost + 2.95;
      		}
    		else if("hotdog".equals(order.get(i))){
    			cost = cost + 3.25;
    		}
    		else if("shackburger".equals(order.get(i))){
    			cost = cost + 5.19;
      		}
    		else if("smokeshack".equals(order.get(i))){
    			cost = cost + 6.69;
    		}
    	}  	
    	return cost;
    }
    
    @FXML
    private Label orderTax;
    private double calTax(double cost) {
    	double tax = cost * .08875;
    	return tax;	
    }
    
    @FXML
    private Label totalOrdercost;
    
    @FXML
    private Button helpButton;
    @FXML
    private void helpButtonAction(MouseEvent event) {
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("helpWindow.fxml"));
            /* 
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Help");
            stage.setScene(scene);
            //Make it so that the user needs to x out of the window to continue
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            
            
            
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    	
    }
    
    @FXML
    private Button removeButton;
    @FXML
    private void removeButtonAction( MouseEvent event) {
    	//String selectedItem = userOrder.getSelectionModel().getSelectedItem();
        int index = userOrder.getSelectionModel().getSelectedIndex();
        //System.out.println("Removed: " + selectedItem + index);
        
        if("hamburger".equals(order.get(index))){
			cost = cost - 4.19;
		}
		else if("chicken".equals(order.get(index))){
			cost = cost - 6.19;
		}
		else if("fries".equals(order.get(index))){
			cost = cost - 2.95;
  		}
		else if("hotdog".equals(order.get(index))){
			cost = cost - 3.25;
		}
		else if("shackburger".equals(order.get(index))){
			cost = cost - 5.19;
  		}
		else if("smokeshack".equals(order.get(index))){
			cost = cost - 6.69;
		}
        //To fix bug where zero is negative
        cost = cost + 0;
        tax = calTax(cost);
        total = cost + tax;
        
        orderCost.setText("Cost:	" + String.format("%.2f", cost));
        orderTax.setText("Tax:	" + String.format("%.2f", tax));
        totalOrdercost.setText("Total:	" + String.format("%.2f", total));
        
        userOrder.getItems().remove(index);
    }
    

    @FXML
    private Button submitButton;
    @FXML
    private void submitButtonAction(MouseEvent event) {
    	fileIO processText = new fileIO();
    	 //Time of order
		 processText.wrTransactionData(clock.getText());
		 //Location of order
		 processText.wrTransactionData(selectedLocation.getText());
		 //Get order
		 processText.wrTransactionData(userOrder.getItems().toString());
		 
		 processText.wrTransactionData(orderCost.getText());
		 processText.wrTransactionData(orderTax.getText());
		 processText.wrTransactionData(totalOrdercost.getText());
		 
		 order.clear();
		 userOrder.setItems(order);
		 cost = 0;
		 tax = 0;
		 total = 0;
		 
		 orderCost.setText("Cost:	" + String.format("%.2f", cost));
	     orderTax.setText("Tax:	" + String.format("%.2f", tax));
	     totalOrdercost.setText("Total:	" + String.format("%.2f", total));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClock(); 
        ObservableList<String> elements = FXCollections.observableArrayList();
        initLocations(elements);
       //Update label in corner with selected location
       //https://self-learning-java-tutorial.blogspot.com/2018/06/javafx-listview-get-selected-item.html
        locationList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            String selectedItem = locationList.getSelectionModel().getSelectedItem();
            //int index = locationList.getSelectionModel().getSelectedIndex();
            selectedLocation.setText("Location Selected: " + selectedItem);
           });
    }
}