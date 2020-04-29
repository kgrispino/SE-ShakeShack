package server;

import java.text.DecimalFormat;

public class store {
	String location;
	int order_total;
	double total_profit;
	
	int burger;
	int fries;
	int chicken;
	int shackburger;
	int hotdog;
	int smokeshack;
			
	
	public store (String loc, int order_tot, double profit, int burg, int fri, int chi, int sha, int hot, int smoke ) {
		location = loc;
		order_total = order_tot;
		total_profit = profit;
		
		burger = burg;
		fries = fri;
		chicken= chi;
		shackburger = sha;
		hotdog = hot;
		smokeshack = smoke;
	}
	
	public String toString(){
		DecimalFormat f = new DecimalFormat("0.00");
		
		return location + " : " 
				+ "Total Orders = " + order_total 
				+ "  Total Profit = " + f.format(total_profit)
				+ "  Total Burgers = " + burger 
				+ "  Total Fries = " + fries
				+ "  Total Chicken Burgers = " + chicken
				+ "  Total Shack Burgers = " + shackburger 
				+ "  Total Hotdogs = " + hotdog
				+ "  Total Smokeshack = " + smokeshack
			   ;
	}
	
	public void incrementOrder_total(){
		order_total++;
	}
	
	public void addTotal_profit(double c){
		total_profit = total_profit + c;
	}
	
	public void addBurger(int d){
		burger = burger + d;
	}
	
	public void addFries(int d){
		fries = fries + d;
	}
	
	public void addChicken(int d){
		chicken = chicken + d;
	}	
	
	public void addShackburger(int d){
		shackburger = shackburger + d;
	}
	
	public void addHotdog(int d){
		hotdog = hotdog + d;
	}
	
	public void addSmokeshack(int d){
		smokeshack = smokeshack + d;
	}
}
