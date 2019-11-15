import java.util.ArrayList;
import java.util.StringTokenizer;
/*
Create a class called Student that implements the Comparable interface.  An instance of a Student should have a student name, a student id number, and an ArrayList of 
CollegeCourses taken by the student. An instance of the Student class should have the following methods:
1. A default constructor that initializes the instance variables to their default values.
2. A constructor that is passed a student name and id number and initializes the instance variables.
3. Mutator methods (set methods) for the student name, and the idNumber.
4. Accessor methods (get methods) for the student name, and the id number.
5. An add course method that is given a course title, the number of credits, and a course grade.  The method should add a course to the student's ArrayList of courses.
6. A get method that returns the ArrayList of courses taken by the student.
7. A get method that returns the number of courses taken by the student.
8. A get gpa method that calculates and returns student's grade point average.
9. A toString method.
10. An equals method. Two instances of the Student class are equal if they have equal id numbers.
11. A compareTo method that compares the students based on their id numbers.
 */
public class Student implements Comparable<Student>
{
//Create new instance variables.
		private String name;
		private int idNum;
		private ArrayList<String> coursesTaken;
		private static int currentId = 1;
		
//Initialize the instance variables to default variables.
	public Student()
	{
	   name = "";
	   idNum = currentId++;
	   coursesTaken = new ArrayList<String>();
	}
	
//Let the given student name and id number be equal to the instance variables of the same name.
	public Student(String name, int idNum)
	{
       this.name = name;
       this.idNum = idNum;
	   coursesTaken = new ArrayList<String>();
	}
	
//Set the instance variable name to the given value of the same name. @param a String that is the new student name
	public void setName(String name)
	{ this.name = name;}
	
//Set the instance variable idNum to the given value of the same name. @param an int that is the new id number
	public void setidNum(int idNum)
	{ this.idNum = idNum;}
	
//Return the student name. @return the student name
	public String getname()
	{  return name;  }
	   
//Return the ID number. @return the ID number
	public int getidNum()
	{  return idNum;  }
	
//Add a course to the student's ArrayList of courses. @param a course title, the number of credits, and a course grade
	public void addCourse(String course, int credNum, String letGrade)
	{
		String nextCourse = course + "  " + credNum + "  " + letGrade;
		coursesTaken.add(nextCourse);
	}
	
//Return the ArrayList of courses taken by the student. @return the ArrayList of courses taken by the student
	public ArrayList<String> getCourseList()
	{  return coursesTaken;  }
	
//Return the number of courses taken by the student. @return the number of courses taken by the student
	public int getNumOfCourses()
	{  return coursesTaken.size(); }
	
//Calculate and returns a student's grade point average. @return the student's grade point average
	public double getGPA()
	{  
		int totcred = 0, cred = 0, qp = 0;
		double total = 0;
		for(int i = 0; i < coursesTaken.size(); i++)
		{
			StringTokenizer st = new StringTokenizer(coursesTaken.get(i));
			if(st.countTokens() == 3)
			{
				 String course = st.nextToken();
				 String credits = st.nextToken();
				 String letGrade = st.nextToken();
				 try
				 {
					cred = Integer.parseInt(credits);
					totcred += cred;
				 }
				 catch(NumberFormatException e)
				 {System.out.println("Invalid data: " + st);}
			 if(letGrade.equalsIgnoreCase("A"))
				 qp = 4;
			 if(letGrade.equalsIgnoreCase("B"))
				 qp = 3;
			 if(letGrade.equalsIgnoreCase("C"))
				 qp = 2;
			 if(letGrade.equalsIgnoreCase("D"))
				 qp = 1;
			 if(letGrade.equalsIgnoreCase("F"))
				 qp = 0;
			 int grade = qp;
			 total += cred*grade;
			}
		}
		return (total/totcred); 
	}
	
//Return the value of name, idNum, and coursesTaken in the form of a string. @return a string representation of these three values
   public String toString()
   {
       String s = name;
       s += "  " + idNum;
       for(int i = 0; i < coursesTaken.size(); i++)
       {
    	   StringTokenizer st = new StringTokenizer(coursesTaken.get(i));
			if(st.countTokens() == 3)
			{
				s += "\n" + st.nextToken();
				s += "\t" + st.nextToken();
				s += "\t" + st.nextToken();
			}
       }
       return s;
   }
   
//Check if two instances of the Student class are equal by checking if they have equal id numbers
   public boolean equals(Object other)
   {
     if(other == this)
       return true;
     if(other == null)
	     return false;
     if( this.getClass() != other.getClass() )
         return false;
     Student otherid = (Student)other;
     return (this.idNum) == (otherid.idNum);
   }
   
//Compares the two students based on their id numbers
   public int compareTo(Student other) throws NullPointerException
   {
 	  if(other == null)
 	    throw new NullPointerException();
       return  (this.idNum) - (other.idNum);
   }
}
