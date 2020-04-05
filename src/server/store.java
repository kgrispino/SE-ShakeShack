package server;

public class store {
	String location;
	int order_total;
	int total_profit;
	
	int burger;
	int fires;
	int chicken;
	int shackburger;
	int hotdog;
	int smokeshack;
			
	
	public store (String loc, int order_tot, int profit, int burg, int fri, int chi, int sha, int hot, int smoke ) {
		location = loc;
		order_total = order_tot;
		total_profit = profit;
		
		burger = burg;
		fires = fri;
		chicken= chi;
		shackburger = sha;
		hotdog = hot;
		smokeshack = smoke;
	}

}
