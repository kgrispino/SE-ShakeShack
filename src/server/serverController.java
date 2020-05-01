package server;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import server.sockServer;

public class serverController implements Initializable {	
	@FXML 
	private Button exitButton;
	
	@FXML
	private Label hotItem;

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
	
	@FXML
	public Button logBtn;
	
	@FXML
    public Label hotLoc;
	
	@FXML
	private void logClick(MouseEvent event) {   
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("--- Ticket Kiosk ---");
        alert.setHeaderText("Total Number of Transactions");    
        alert.setContentText(sockServer.getAllTransactions());
        alert.getDialogPane().setPrefSize(1100, 480);   
        alert.showAndWait();
	}
	
	@FXML
	private void openStoreClick(MouseEvent event) {   
		int nextStoreNumber, currentSize = 0;
		String storeString;
		currentSize     = sockServer.clients.size();
		nextStoreNumber = currentSize + 1;
		storeString     = "Location " + nextStoreNumber;
				
		sockServer.clients.put(storeString, new store(storeString, 0, 0.00, 0, 0, 0, 0, 0, 0));
	}
	
    private void initTotals() {
        Timeline updateTotals = new Timeline(new KeyFrame(Duration.ZERO, e -> {
        	int[] totals = sockServer.getAllTotals();
        	int maxIndex = 0;
        	for (int i = 0; i < totals.length; i++) {
        		if (totals[maxIndex] <= totals[i])
        			maxIndex = i;	
        	}
        	if (maxIndex == 0) 
        		hotItem.setText("Hamburger");
        	else if (maxIndex == 1) 
        		hotItem.setText("Chicken Burger");
        	else if (maxIndex == 2) 
        		hotItem.setText("Fries");
        	else if (maxIndex == 3) 
        		hotItem.setText("Hotdogs");
        	else if (maxIndex == 4) 
        		hotItem.setText("Shack Burger");
        	else if (maxIndex == 5) 
        		hotItem.setText("SmokeShack Burger");
        }), new KeyFrame(Duration.seconds(1)));
        updateTotals.setCycleCount(Animation.INDEFINITE);
        updateTotals.play();
    }
    
    private void initOrderTotal() {
        Timeline updateTotals = new Timeline(new KeyFrame(Duration.ZERO, e -> {
        	List<Integer> orderNumList = new ArrayList<Integer>();
        	orderNumList = sockServer.getAllOrderNums();
        	int maxIndex = 0;
        	for (int i = 0; i < orderNumList.size(); i++) {
        		if (orderNumList.get(maxIndex) <= orderNumList.get(i))
        			maxIndex = i;	
        	}
        	
        	hotLoc.setText("Location " + String.valueOf(maxIndex));
        	if (orderNumList.get(maxIndex).equals(0)) {
        		hotLoc.setText("None");
        	}
        	
        }), new KeyFrame(Duration.seconds(1)));
        updateTotals.setCycleCount(Animation.INDEFINITE);
        updateTotals.play();
    }
    @FXML
    public Label totalProf;
    
    private void initTotalProfit() {
        Timeline updateTotals = new Timeline(new KeyFrame(Duration.ZERO, e -> {
        	List<Double> totalProfitList = new ArrayList<Double>();
        	totalProfitList = sockServer.getTotalProfit();
        	Double total = 0.00;
        	for (int i = 0; i < totalProfitList.size(); i++) {
        		total = total + totalProfitList.get(i);
        	}
        	DecimalFormat f = new DecimalFormat("0.00");
        	totalProf.setText("Total Profit:  " + String.valueOf(f.format(total)));
        	if (total.equals(0.00)) {
        		totalProf.setText("None");
        	}
        	
        }), new KeyFrame(Duration.seconds(1)));
        updateTotals.setCycleCount(Animation.INDEFINITE);
        updateTotals.play();
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
	
	private void startSockServer(){	
		Thread ServerThread = new Thread(){
		    public void run(){ 	
				  sockServer.runSockServer();
		    }
		};
	    ServerThread.start();
	}
	

	public void initialize(URL location, ResourceBundle resources) {
		Controllers.setMainController(this);
		initOrderTotal();
		initTotals();
		initTotalProfit();
		initClock();
		startSockServer();
	}
} 