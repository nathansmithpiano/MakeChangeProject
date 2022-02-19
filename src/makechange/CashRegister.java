package makechange;

import java.util.Scanner;

public class CashRegister {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		double amount = 0;
		double tendered = 0;
		int change = 0;
		String output = "";
		
		//inputs
		System.out.print("Amount: ");
		amount = kb.nextDouble();
		System.out.print("Tendered: ");
		tendered = kb.nextDouble();
		
		//floating-point numbers cause accuracy issues.
		//using int for all monetary calculations
		change = fixChange(amount, tendered);
		
		//verify if change is needed
		if (tendered < amount) {
			//underpaid
			output = "YOU MUST CONSTRUCT ADDITIONAL PYLONS";
		} else if (change == 0) {
			//paid exactly
			output = "No change owed.";
		} else {
			//give change
			output = makeChange(change);
		}
		
		//final print
		System.out.println("Result: " + output);
		
		kb.close();
	}
	
	public static int fixChange(double amount, double tendered) {
		int change = 0;
		
		amount *= 100;
		tendered *= 100;
		
		change = (int) (tendered - amount);
		
		return change;
	}
	
	public static String makeChange(int change) {
		String output = "";
		int numBills = 0;
		
		//value of a bill/coin
		int bill = 0;
		
		while (change > 0) {
			//set bills
			//hundreds
			if (change >= 10000) {
				bill = 10000;
			} else if (change >= 5000) {
				bill = 5000;
			} else if (change >= 2000) {
				bill = 2000;
			} else if (change >= 1000) {
				bill = 1000;
			} else if (change >= 500) {
				bill = 500;
			} else if (change >= 100) {
				bill = 100;
			} else if (change >= 20) {
				bill = 20;
			} else if (change >= 10) {
				bill = 10;
			} else if (change >= 5) {
				bill = 5;
			} else if (change >= 1) {
				bill = 1;
			}
			
			//get number of each bill
			numBills = getNumBills(change, bill);
			//add to result string
			output += billResult(numBills, bill);
			//reduce change by remaining
			change -= numBills * bill;
//			System.out.println("Change Remaining: " + change + ", Hundreds: " + numBills);
		}
		
		
		return output;
	}
	
	public static int getNumBills(double change, double value) {
		int numBills = 0;
		
		//remove change indivisible from value
		change -= change % value;
		
		while (change > 0) {
			numBills++;
			change -= value;
		}
		
		return numBills;
	}
	
	public static String billResult(int numBills, double bill) {
		String result = "";
		
		if (bill == 10000) {
			result += numBills + " hundred";
			result += makePlural("hundred", numBills);
		}
		if (bill == 5000) {
			result += numBills + " fift";
			result += makePlural("fift", numBills);
		}
		if (bill == 2000) {
			result += numBills + " twent";
			result += makePlural("twent", numBills);
		}
		if (bill == 1000) {
			result += numBills + " ten";
			result += makePlural("ten", numBills);
		}
		if (bill == 500) {
			result += numBills + " five";
			result += makePlural("five", numBills);
		}
		if (bill == 100) {
			result += numBills + " one";
			result += makePlural("one", numBills);
		}
		if (bill == 20) {
			result += numBills + " quarter";
			result += makePlural("quarter", numBills);
		}
		if (bill == 10) {
			result += numBills + " dime";
			result += makePlural("dime", numBills);
		}
		if (bill == 5) {
			result += numBills + " nickel";
			result += makePlural("nickel", numBills);
		}
		if (bill == 1) {
			result += numBills + " penn";
			result += makePlural("penn", numBills);
		}
		
		return result;
	}
	
	public static String makePlural(String bill, int numBills) {
		String output = "";
		
		switch (bill) {
			case "hundred":
			case "ten":
			case "five":
			case "one":
			case "quarter":
			case "dime":
			case "nickel":
				if (numBills > 1) {
					output = "s ";
				} else {
					output = " ";
				}
				break;
			case "fift":
			case "twent":
			case "penn":
				if (numBills > 1) {
					output = "ies ";
				} else {
					output = "y ";
				}
				break;
		}
		
		return output;
	}

}
