/*
Create a class called CollegeCourse. An instance of a CollegeCourse should have a course title, the number of course credits, and a letter grade.
An instance of the CollegeCourse class should have the following methods:
1. A default constructor that initializes the instance variables to their default values.
2. A constructor that is passed a course title and the number of course credits.
3. Mutator methods (set methods) for the course title, the course credits, and the letter grade.
4. Accessor methods (get methods) for the course title, the course credits, and the letter grade.
5. A toString method.
 */
public class CollegeCourse
{
//Create new instance variables.
	private String course;
	private int credNum;
	private String letGrade;
	
//Initialize the instance variables to default variables.
	public CollegeCourse()
	{
       course = "";
       credNum = 0;
       letGrade = "";
	}
	
//Let the given course and credit number be equal to the instance variables of the same name.
	public CollegeCourse(String course, int credNum)
	{
       this.course = course;
       this.credNum = credNum;
	}
	
//Set the instance variable course to the given value of the same name. @param a String that is the new course
	  public void setCourse(String course)
	   { this.course = course;}
	  
//Set the instance variable credNum to the given value of the same name. @param an int that is the new credit number
	  public void setcredNum(int credNum)
	   { this.credNum = credNum;}
	  
//Set the instance variable letGrade to the given value of the same name. @param a String that is the new letter grade
	  public void setletGrade(String letGrade)
	   { this.letGrade = letGrade;}
	  
//Return the course name. @return the course name
	   public String getCourse()
	   {  return course;  }
	   
//Return the credit number. @return the credit number
	   public int getcredNum()
	   {  return credNum;  }
	   
//Return the letter grade. @return the letter grade
	   public String getletGrade()
	   {  return letGrade;  }
	   
//Return the value of course, credNum, and letGrade in the form of a string. @return a string representation of these three values
	   public String toString()
	   {
	       String s = course;
	       s += "  " + credNum + "  ";
	       s += letGrade;
	       return s;
	   }
}
