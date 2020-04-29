package server;

import java.io.IOException;
import java.io.BufferedReader;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javafx.application.Platform;

import java.io.InputStreamReader;
import java.io.PrintStream;

public class sockServer implements Runnable
{
	   Socket csocket;
	   String ipString;
	   char threadType;

	   static Vector<String> vec = new Vector<String>(5);
	   
	   public static Hashtable<String, store> clients = new Hashtable<String, store>();
	   
	   static final String newline = "\n";
	   static int first_time = 1;
	   
	   static int port_num = 3333;
	   
	   static int numOfConnections = 0;
	   static int numOfMessages = 0;
	   static int max_connections = 5;
	   static int numOfTransactions = 0; 

	   sockServer(Socket csocket, String ip)
	   {
	      this.csocket  = csocket;
	      this.ipString = ip;
	   } 

	   public static void runSockServer()  // throws Exception
	   {
	     boolean sessionDone = false;
	  
	     ServerSocket ssock = null;
	   
	     try
	     {
		   ssock = new ServerSocket(port_num);
	     }
	     catch (BindException e)
	     {
		    e.printStackTrace();
	     }
	     catch (IOException e)
	     {
		    e.printStackTrace();
	     }
	 
	     // update the status text area to show progress of program
	     try 
	     {
		     InetAddress ipAddress = InetAddress.getLocalHost();
		     Controllers.getMainController().userLog.appendText("IP Address : " + ipAddress.getHostAddress() + newline);
	     }
	     catch (UnknownHostException e1)
	     {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
	     }
	 
	     Controllers.getMainController().userLog.appendText("Listening on port " + port_num + newline);
	 
	     //
	     // initialize the hash table to the following keys

	     clients.put("Location 1", new store("Location 1", 0, 0.0, 0, 0, 0, 0, 0, 0));
	     clients.put("Location 2", new store("Location 2", 0, 0.0, 0, 0, 0, 0, 0, 0));
	     clients.put("Location 3", new store("Location 3", 0, 0.0, 0, 0, 0, 0, 0, 0));
	     
	     //Final lab
	     
//	     for (int i = 0; i < 1000000; i++) {
//	    	 clients.put("Location " + i, new store("Location " + i, 0, 1.00, 0, 1, 0, 0, 0, 0));
//	     }
//	     System.out.print("After loop");
	     
	     sessionDone = false;
	     while (sessionDone == false)
	     {
	        Socket sock = null;
		    try
		    {
		    // blocking system call
			   sock = ssock.accept();
		    }
		    catch (IOException e)
		    {
			   e.printStackTrace();
		    }
		 
		    // update the status text area to show progress of program
		    Controllers.getMainController().userLog.appendText("Client Connected : " + sock.getInetAddress() + newline);
	        
	        new Thread(new sockServer(sock, sock.getInetAddress().toString())).start();
	     }
	 
	     try 
	     {
		    ssock.close();
	     }
	     catch (IOException e) 
	     {
		    e.printStackTrace();
	     }
	}	  

	   
//Todo: Add all menu items	   
	   
	static synchronized void hashOperation(char type, String key, String profit, String burger, String chicken, String fries, String hotdog, String shack, String smoke)
	//static synchronized void hashOperation(char type, String key, String profit)
	{
		switch (type)
		{
		
			case 'T':
				if (clients.containsKey(key) == true)
		        {
					clients.get(key).addBurger(Integer.parseInt(burger.trim()));
					clients.get(key).addFries(Integer.parseInt(fries.trim()));
					clients.get(key).addHotdog(Integer.parseInt(hotdog.trim()));
					clients.get(key).addChicken(Integer.parseInt(chicken.trim()));
					clients.get(key).addShackburger(Integer.parseInt(shack.trim()));
					clients.get(key).addSmokeshack(Integer.parseInt(smoke.trim()));
					clients.get(key).incrementOrder_total();
					clients.get(key).addTotal_profit(Double.parseDouble(profit));
					
					//System.out.println(clients.get(key).toString());
		        }	
			break;
		}
	}

	//
	// add a new kiosk entry and number to the hash table
	//
	public static void createNewKiosk()
	{
		int nextKioskNumber, currentSize = 0;
		String kioskString;
		
		currentSize     = clients.size();
		nextKioskNumber = currentSize + 1;
		kioskString     = "kiosk#" + nextKioskNumber;
				
		clients.put(kioskString, new store(kioskString, 0, 0.0, 0, 0, 0, 0, 0, 0));
	}
	
	
	
	//
	// get all transaction data from the hash table keys
	//
	public static String getAllTransactions()
	{
		String rs="";
		
		List<String> v = new ArrayList<String>(clients.keySet());
	    Collections.sort(v);
		
	    for (String str : v) { 
	        rs = rs + clients.get(str.toString()) + "\r\n";
	    }
				
		return rs;
	}

	public static int[] getAllTotals()
	{
		String rs="";
		
		int total_burg = 0;
		int total_chicken = 0;
		int total_fries = 0;
		int total_hotdogs = 0;
		int total_shack = 0;
		int total_smoke = 0;
		
		List<String> v = new ArrayList<String>(clients.keySet());
	    Collections.sort(v);
		
	    for (String str : v) { 
	        rs = rs + clients.get(str.toString()) + "\r\n";
	        total_burg = total_burg + (clients.get(str.toString()).burger);
	        total_fries = total_fries + (clients.get(str.toString()).fries);
	        total_chicken = total_chicken + (clients.get(str.toString()).chicken);
	        total_hotdogs = total_hotdogs + (clients.get(str.toString()).hotdog);
	        total_shack = total_shack + (clients.get(str.toString()).shackburger);
	        total_smoke = total_smoke + (clients.get(str.toString()).smokeshack);
	    }
	    int[] totals = {total_burg, total_chicken, total_fries, total_hotdogs, total_shack, total_smoke};
		return totals;
	}
	
	
	// This is the thread code that ALL clients will run()
	public void run()
	{
	   try
	   {
		  boolean session_done = false; 
	      long threadId;
	      String clientString;
	      String keyString = "";
	    
	      threadId = Thread.currentThread().getId();
	      
	      numOfConnections++;
	      
	      Controllers.getMainController().userLog.appendText("Num of Connections = " + numOfConnections + newline);
	      
	      //https://stackoverflow.com/questions/49343256/threads-in-javafx-not-on-fx-application-thread
	      //To update UI on server screen from here
	      Platform.runLater(new Runnable() {
	    	    @Override
	    	    public void run() {
	    	    	Controllers.getMainController().num_users.setText(String.valueOf(numOfConnections));
	    	    }
	    	});
	    
	      
	      keyString = ipString + ":" + threadId;
	      
	      if (vec.contains(keyString) == false)
	      {
	    	    int counter = 0;
	        	vec.addElement(keyString);
	        	
	        	Controllers.getMainController().foodLog.setText("");
	        	Enumeration<String> en = vec.elements();
	        	while (en.hasMoreElements())
	        	{
	        		Controllers.getMainController().foodLog.appendText(en.nextElement() + " || ");
	        		
	        		if (++counter >= 6)
	        		{
	        			Controllers.getMainController().foodLog.appendText("\r\n");
	        			counter = 0;
	        		}
	        	}
	      }
	       
	      PrintStream pstream = new PrintStream (csocket.getOutputStream());
	      BufferedReader rstream = new BufferedReader(new InputStreamReader(csocket.getInputStream()));

	      while (session_done == false)
	      {
	       	if (rstream.ready())   // check for any data messages
	       	{
	              clientString = rstream.readLine();             
	              
	              //
	              // write to transaction log
	              //
	              fileIO transLog = new fileIO();
	              transLog.wrTransactionData(clientString);
	              
	              	              
	              // update the status text area to show progress of program
	              Controllers.getMainController().userLog.appendText("RECV : " + clientString + newline);
	     	       
	     	       // update the status text area to show progress of program
	              Controllers.getMainController().userLog.appendText("RLEN : " + clientString.length() + newline);
	              
	              if (clientString.length() > 1000)
	              {
	           	   session_done = true;
	           	   continue;
	              }

	              if (clientString.contains("quit"))
	              {
	                 session_done = true;
	              }
	              else if (clientString.contains("QUIT"))
	              {
	                 session_done = true;
	              }
	              else if (clientString.contains("Quit"))
	              {
	                 session_done = true;
	              }
	              else if (clientString.contains("Query>"))
	              {
	            	  String tokens[] = clientString.split("\\>");
	            	  
	            	  if (clients.containsKey(tokens[1]) == true)
	            	  {
	            		  pstream.println(clients.get(tokens[1]).toString());  
	            	  }
	            	  else
	            	  {
	            		  pstream.println("NACK : ERROR : No such kiosk number!");
	            	  }
	              }
	              
	              else if (clientString.contains("Transaction>"))
	              {
	            	  String tokens[] = clientString.split("\\>");
	            	  String args[]   = tokens[1].split("\\,");
	            	  
	            	  if (clients.containsKey(args[0]) == true)
	            	  {
	            		  hashOperation('T', args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
	            		  pstream.println("ACK");
	            		  
	            	  }
	            	  else
	            	  {
	            		  pstream.println("NACK : ERROR : No such kiosk number!");
	            	  }
	              }
	              
	              else if (clientString.contains("Date>"))
	              {
	            	numOfMessages++;
	            	  
	            	// Create an instance of SimpleDateFormat used for formatting 
	            	// the string representation of date (month/day/year)
	            	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	            	// Get the date today using Calendar object.
	            	Date today = Calendar.getInstance().getTime();
	            	
	            	// Using DateFormat format method we can create a string 
	            	// representation of a date with the defined format.
	            	String reportDate = df.format(today);

	            	// Print what date is today!
	            	pstream.println("Num Of Messages : " + numOfMessages + "   Simple Date: " + reportDate);
	              }
	              else
	              {
	            	  pstream.println("NACK : ERROR : No such command!");
	              }
	       	   }
	         			    		        	
	           Thread.sleep(500);
	           
	        }    // end while loop
	
            keyString = ipString + ":" + threadId;
	      
	        if (vec.contains(keyString) == true)
	        {
	        	int counter = 0;
	        	vec.removeElement(keyString);
	        	
	        	Controllers.getMainController().foodLog.setText("");
	        	Enumeration<String> en = vec.elements();
	        	while (en.hasMoreElements())
	        	{        		     		
	        		Controllers.getMainController().foodLog.appendText(en.nextElement() + " || ");
	        		
	        		if (++counter >= 6)
	        		{
	        			Controllers.getMainController().foodLog.appendText("\r\n");
	        			counter = 0;
	        		}
	        	}

  	            //sss5.textArea_2.repaint();
	        }
	      
	        numOfConnections--;

	        // close client socket
	        csocket.close();
	       
	        // update the status text area to show progress of program
	        Controllers.getMainController().userLog.appendText("Child Thread : " + threadId + " : is Exiting!!!" + newline);
	        //Controllers.getMainController().userLog.appendText("Num of Connections = " + numOfConnections);
	        Controllers.getMainController().num_users.setText(String.format("%.2f", numOfConnections));
		     
	     } // end try  
	 
	     catch (SocketException e)
	     {
		  // update the status text area to show progress of program
	    	 Controllers.getMainController().userLog.appendText("ERROR : Socket Exception!" + newline);
	     }
	     catch (InterruptedException e)
	     {
		  // update the status text area to show progress of program
	    	 Controllers.getMainController().userLog.appendText("ERROR : Interrupted Exception!" + newline);
	     }
	     catch (UnknownHostException e)
	     {
		  // update the status text area to show progress of program
	    	 Controllers.getMainController().userLog.appendText("ERROR : Unkonw Host Exception" + newline);
	     }
	     catch (IOException e) 
	     {
	     // update the status text area to show progress of program
	    	 Controllers.getMainController().userLog.appendText("ERROR : IO Exception!" + newline);
	     }     
//	     catch (Exception e)
//	     { 
//		  numOfConnections--;
//		  
//		  // update the status text area to show progress of program
//		  Controllers.getMainController().userLog.appendText("ERROR : Generic Exception!" + newline);
//	     }
	   
	  }  // end run() thread method
}