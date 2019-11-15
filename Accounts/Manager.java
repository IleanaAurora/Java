/*
Write the code for a class called Manager that extends Employee. A manager is paid a fixed amount annually.  Write the following methods for your class:
a. A default constructor that sets the name, id number and date hired to empty strings and the annual salary to zero.
b. A constructor that is passed a name, id number, date hired, and annual salary.
c. A get weekly salary method that returns the annual salary divided by 52.
d. get and set methods for the annual salary.
e. A toString method that returns a String containing the name, id number, date hired, and annual salary. (Use the String format method)
 */
public class Manager extends Employee
{
//Create a new instance variable.
	private double annualSal;

//Initialize the instance variables to default variables.
	public Manager()
	{
		super();
		double annualSal = 0;
	}
	
//Let the given name, idNum, hireDate and annualSal be equal to the instance variables of the same name.
	public Manager(String name, String idNum, String hireDate, double annualSal)
	{
		super(name, idNum, hireDate);
		this.annualSal = annualSal;
	}
	
//Return the weeklySal. @returns the manager's salary.
  public double getweeklySal()
  {return annualSal/52;}
  
//Set the instance variable annualSal to the given value of the same name. @param a String that is the new annualSal
  public void setannualSal(double sal)
   { annualSal = sal;}
  
//Return the annualSal. @return the annualSal
  public double getannualSal()
  {  return annualSal;  }
  
//Return the value of the manager's name, idNum, hireDate, and annualSal in the form of a string. @return a string representation of these four values
  public String toString()
  {
	  String result =  super.toString();
	  String temp = String.format("Annual Salary: $%-10s\n", annualSal);
	  return result + temp;
  }
}
