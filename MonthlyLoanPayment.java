import java.util.Scanner;

/*
Problem: Write a program that calculates the monthly payment on a loan, the total amount left to pay, and the total amount of interest.

Test Data:
Amount		Annual Interest Rate(%)		# of Years		Monthly Payment			Amount Left to Pay		Total Interest
5000			5=.05=.0041667				10=120			53.03					6363.93					1363.93
1				6=.06=.005					2=24			0.04					1.06					0.06
-35				16							-8				invalid input			---------				------						
1000000			4=.04=.003333				23=276			5,547.50				1531111.22				531111.22
76.52			4.50=.0450=.00375			8=96			0.95					91.26					14.74
0				3=.03=.0025					10=120			0						0						0
300				.001=.00001=8.33e-7			70=840			0.36					300.11					0.11
150				0							15				invalid input			---------				------
600				3.5=.035=.002916			0				invalid input			---------				------

Algorithm:
1. Prompt user for the amount of a loan
2. Read input value
3. Prompt user for the annual interest rate (as a percent)
4. Read input value
5. Prompt user for the number of years to repay the loan
6. If the amount < 0, the annual interest rate <= 0, or the # of years <= 0, print an error message and terminate the program
7. Convert the annual interest rate (as a percent) to monthly interest rate (as a decimal) by dividing by 100 and then dividing by 12
8. Convert the number of years to repay the loan to months by multiplying by 12
9. Calculate the monthly payment using the formula: monthly payment = amount * rate/(1 - (1 + rate)^-N)
10. Calculate the total amount left to pay (the principle & interest) by multiplying the monthly payment by the number of months
11. Calculate the total amount of interest by subtracting the loan amount from the total amount left to pay (the principle & interest)
12. Print the monthly payment, total amount left to pay, and total amount of interest
 */
public class MonthlyLoanPayment
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the loan amount: ");
		double amount=in.nextDouble();
		System.out.print("Enter the annual interest rate (as a percent): ");
		double rate=in.nextDouble();
		System.out.print("Enter the number of years with which to repay the loan: ");
		double time=in.nextDouble();
		if (amount<0 || rate<=0 || time<=0)
			System.out.print("Sorry, but that is an invalid input. ");
		else
		{
			rate=(rate/100.0)/12;
			time*=12;
			double monthlypayment = amount*rate/(1-Math.pow((1.0 + rate), -time));
			double totalpayment = monthlypayment*time;
			double totalinterest = totalpayment-amount;
			System.out.println("Your total monthly payment is " +monthlypayment+ ". The total amount you have yet to pay is " +totalpayment+ ". The total amount of interest is " +totalinterest);
		}
		in.close();
		}

}
