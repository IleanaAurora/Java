import java.util.Scanner;

/*
Problem: Calculate the value of an investment of p dollars for t years at an annual interest rate r compounded 4 times per year.

Test Data:
Amount		Interest Rate		# of Years		Value
1000		15=.15					6			2419.438	
500			2=.02					5			552.4477
30			1=.01					10			33.15099
10000		5=.05					20			27014.84
1			3=.03					14			1.519578
1000000		8=.08					35			15996465.98
58472		4.2=.042				1			60966.77
4523		0=.00					26			invalid input
85			6=.06					45			1239.671
364			5=.05					0			invalid input
52			-2=-.02					-8			invalid input

Algorithm:
1. Prompt user for an investment amount
2. Read user input
3. Prompt user for an annual interest rate (as a percent)
4. Read user input
5. Prompt user for the number of years
6. Read user input
7. If either the amount <= 0, rate <=0, or years is <=0 print an error message and terminate the program
8. Otherwise,
	a. Convert the annual interest rate from percent to decimal by dividing the rate by 100
	b. Calculate the investment value using the formula: p(1 + r/n)^nt
	c. Print the value of the investment after t years
 */
public class AnnualInterestRate
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter an investment amount: ");
		double amount=in.nextDouble();
		System.out.print("Enter an annual interest rate (as a percent): ");
		double rate=in.nextDouble();
		System.out.print("Enter the number of years over which your money is invested: ");
		double time=in.nextDouble();
		if (amount<=0 || rate<=0 || time<=0)
			System.out.print("Sorry, but that is an invalid input. ");
		else
		{
			rate/=100;
			double investmentval = amount*(Math.pow((1 + rate/4.0), (4*time)));
			System.out.println("Your investment value after " + time + " years is " + investmentval);
		}
		in.close();
	}

}
