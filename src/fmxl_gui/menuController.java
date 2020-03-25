package fmxl_gui;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class menuController implements Initializable {	
	//Global list to track what the user clicked on the menu
	ObservableList<String> order = FXCollections.observableArrayList();
	@FXML
    private ListView<String> userOrder;
	//Function for handling what the user clicked, costs are calculated on click  
	@FXML
    private void handleOnButtonAction(MouseEvent event)
    {
        //System.out.println("You clicked button: " + ((Rectangle)event.getSource()).getId());
        order.add(((Rectangle)event.getSource()).getId());
        userOrder.setItems(order);
        
        double cost = calCost();
        double tax = calTax(calCost());
        double total = cost + tax;
        
        orderCost.setText("Cost:	" + String.format("%.2f", cost));
        orderTax.setText("Tax:	" + String.format("%.2f", tax));
        totalOrdercost.setText("Total:	" + String.format("%.2f", total));
    }
    
    @FXML
    Label clock;
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
    			cost = cost + 3.49;
    		}
    		else if("chicken".equals(order.get(i))){
    			cost = cost + 3.49;
    		}
    		else if("fries".equals(order.get(i))){
    			cost = cost + 1;
      		}
    		else if("hotdog".equals(order.get(i))){
    			cost = cost + 3.49;
    		}
    		else if("shackburger".equals(order.get(i))){
    			cost = cost + 3.49;
      		}
    		else if("smokeshack".equals(order.get(i))){
    			cost = cost + 3.49;
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