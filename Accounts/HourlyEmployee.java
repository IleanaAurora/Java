/*
Write the code for a class called HourlyEmployee that extends Employee. An hourly employee has an hourly rate of pay and a number of hours worked per week.  Write the following
methods for your class:
a. A default constructor that sets the name and id number and date hired to empty strings, and the rate of pay and hours worked to zero.
b. A constructor that is passed a name, id number, date hired, and hourly rate of pay.
c. get and set methods for hours worked per week and the hourly rate of pay.
d. A get weekly salary method that returns the employee's salary. Assume that an hourly employee is paid their regular rate for the first 35 hour that they work in a week, and
double their rate for any hours over 35.
e. A toString method that returns a String containing the employee's name, id number, date hired, rate of pay and hours worked per week. (Use the String format method)
 */
public class HourlyEmployee extends Employee
{
//Create new instance variables.
	private double hourlyRate;
	private int weeklyHours;

//Initialize the instance variables to default variables.
	public HourlyEmployee()
	{
		super();
		double hourlyRate = 0;
		int weeklyHours = 0;
	}
	
//Let the given name, idNum, hireDate and hourlyRate be equal to the instance variables of the same name.
	public HourlyEmployee(String name, String idNum, String hireDate, double hourlyRate)
	{
		super(name, idNum, hireDate);
		this.hourlyRate = hourlyRate;
		int weeklyHours = 0;
	}
	
//Set the instance variable weeklyHours to the given value of the same name. @param a String that is the new weeklyHours
  public void setweeklyHours(int hours)
   { weeklyHours = hours;}
  
//Set the instance variable hourlyRate to the given value of the same name. @param a String that is the new hourlyRate
  public void sethourlyRate(double rate)
   { hourlyRate = rate;}
  
//Return the weeklyHours. @return the weeklyHours
  public int getweeklyHours()
  {  return weeklyHours;  }
  
//Return the hourlyRate. @return the hourlyRate
  public double gethourlyRate()
  {  return hourlyRate;  }
  
//Return the weeklySal. @returns the employee's salary.
  public double getweeklySal()
  {  
	  int hours = getweeklyHours();
	  double rate = gethourlyRate();
	  if(hours <= 35)
		  return rate * hours;
	  else 
	  {
		  int temp = hours - 35;
		  double overtime = (rate * 2) * temp;
		  double regRate = rate * 35;
		  return overtime + regRate;
	  }  
  }
  
//Return the value of the employee's name, idNum, hireDate, hourlyRate, and weeklyHours in the form of a string. @return a string representation of these five values
  public String toString()
  {
	  String result =  super.toString();
	  String temp = String.format("Pay per hour: $%-10s Hours worked per week: %3s\n", hourlyRate, weeklyHours);
	  return result + temp;
  }
}
