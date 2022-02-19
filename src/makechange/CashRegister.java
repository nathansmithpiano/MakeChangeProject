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
			result += makePlural ("hundred", numBills);
		}
		
		return result;
	}
	
	public static String makePlural(String bill, int numBills) {
		String output = "";
		
		switch (output) {
			case "hundred":
			case "ten":
			case "five":
			case "one":
			case "quarter":
			case "dime":
			case "nickel":
				if (numBills > 1) {
					output = "s";
				}
				break;
			case "twent":
			case "penn":
				if (numBills > 1) {
					output = "ies";
				} else {
					output = "y";
				}
				break;
		}
		
		return output;
	}

}
