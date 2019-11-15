/*
Write the code for a class called Employee. The class will be used to hold information about a single employee.  Each employee has a name, an id number, a date hired and a weekly
salary.  Write the following methods for your class:
a. A default constructor that sets the name and id number and date hired to empty strings and the weekly salary to zero.
b. A constructor that is passed a name, id number, and date hired.
c. get and set methods for the name, id number, date hired and weekly salary.
d. An equals method that is passed an Object and returns a boolean. The method should return true if two employees have the same id number.
f. A toString method that returns a String containing the employee's name, id number and date hired. (Use the String format method)
 */
public class Employee
{

//Create new instance variables.
	private String name;
	private String idNum;
	private String hireDate;
	private double weeklySal;
	
//Initialize the instance variables to default variables.
	public Employee()
	{
	   name = "";
	   idNum = "";
       hireDate = "";
       weeklySal = 0;
	}
	
//Let the given course and credit number be equal to the instance variables of the same name.
	public Employee(String name, String idNum, String hireDate)
	{
       this.name = name;
       this.idNum = idNum;
       this.hireDate = hireDate;
       weeklySal = 0;
	}
	
//Set the instance variable name to the given value of the same name. @param a String that is the new name
  public void setName(String name)
   { this.name = name;}
  
//Set the instance variable idNum to the given value of the same name. @param a String that is the new idNum
  public void setidNum(String idNum)
   { this.idNum = idNum;}
  
//Set the instance variable hireDate to the given value of the same name. @param a String that is the new hireDate
  public void sethireDate(String hireDate)
   { this.hireDate = hireDate;}
  
//Set the instance variable weeklySal to the given value of the same name. @param a String that is the new weeklySal
  public void setweeklySal(double weeklySal)
   { this.weeklySal = weeklySal;}
  
//Return the name. @return the name
  public String getName()
  {  return name;  }
  
//Return the idNum. @return the idNum
  public String getidNum()
  {  return idNum;  }
  
//Return the hireDate. @return the hireDate
  public String gethireDate()
  {  return hireDate;  }
  
//Return the weeklySal. @return the weeklySal
  public double getweeklySal()
  {  return weeklySal;  }
  
//Check if two instances of the Employee class are equal by checking if they have equal id numbers
  public boolean equals(Object other)
  {
    if(other == this)
      return true;
    if(other == null)
	     return false;
    if( this.getClass() != other.getClass() )
        return false;
    Employee otherid = (Employee)other;
    return (this.idNum) == (otherid.idNum);
  }
  
//Return the value of name, idNum, and hireDate in the form of a string. @return a string representation of these three values
  public String toString()
  {
    return String.format("Name: %-20s ID #: %-18s Date hired: %9s\n", name, idNum, hireDate);
  }
}
