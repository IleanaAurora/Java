import java.util.Scanner;

/*
Problem: Calculate a person’s BMI.

Test Data:
Weight(lb)		Height(in)		BMI
120				61				22.7
150				64				25.7
225				75				28.1
178				71				24.8
212				70				30.4
160				73				21.1
65				49				19.0
98				65				16.3
5				12				24.4
-3				25				invalid input
2				-4				invalid input
0				0				invalid input

Algorithm:
1. Prompt user for their weight in pounds
2. Read user input
3. Prompt user for their height in inches
4. Read user input
5. If either the weight or height is less than or equal to zero, print an error message and terminate program
6. Otherwise,
	a. weight is equal to pounds times 0.45359237
	b. height is equal to inches times 2.54
	c. BMI = w/(h/100)^2
	d. Print the BMI value
 */

public class BMIcalculator
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a weight in pounds: ");
		double pounds=in.nextDouble();
		System.out.print("Enter a height in inches: ");
		double inches=in.nextDouble();
		if (pounds <=0 || inches <=0)
			System.out.print("Sorry, but that is an invalid input. ");
		else
		{
			double weight = pounds*0.45359237;
			double height = inches*2.54;
			double bmi = weight/Math.pow((height/100), 2);
			System.out.println("Your BMI is " + bmi);
		}
		in.close();
	}
}