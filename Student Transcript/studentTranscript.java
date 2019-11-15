import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
Problem: Write an application that creates an ArrayList of students, reads the data file and produces a report that lists the information for each student including the
student's gpa.

Algorithm:
1)Open the file "StudentGrades.txt".
2)Make an ArrayList of students.
3)For each line of data, use a string tokenizer to split the 6 pieces of information into lastName, firstName, idNum, course, credNum, and letGrade.
4)Create a new student using their lastName, firstName, and idNum.
5)Compare this student to the ArrayList students using a binary search to determine if the name is already in the ArrayList, and if not, the return the position where it should be placed.
6)If the student was not in the ArrayList, to this student, add the course they took, the credNum, and letGrade that they received.
	a)Add this student to the ArrayList in the position dictated by the binary search.
7)Otherwise, add to the student in the ArrayList the course they took, the credNum, and letGrade that they received.
8)Once the file has been read, for each student in the ArrayList students, calculate the GPA.
9)Send each student's name, ID, course list, and GPA to an output file.
*/
public class studentTranscript
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	    try
	    (
			FileReader reader = new FileReader("StudentGrades.txt");
	        Scanner in = new Scanner(reader);
	        FileWriter writer = new FileWriter("StudentTranscripts.out");
	        PrintWriter out   = new PrintWriter(writer);
	    )
	    {
	          ArrayList<Student> students = new ArrayList<Student>();
	          while(in.hasNextLine())
	          {
	              String nextLine = in.nextLine();
	              StringTokenizer st = new StringTokenizer(nextLine);
	              String lastName = st.nextToken();
	              String firstName = st.nextToken();
	              String id = st.nextToken();
	              String course = st.nextToken();
	              String credit = st.nextToken();
	              String letGrade = st.nextToken();
	              try
	              {
	            	 int idNum = Integer.parseInt(id);
	            	 int credNum = Integer.parseInt(credit);
	            	 String name = lastName + firstName;
	                 Student hold = new Student(name, idNum);
	                 int position = binarySearch(students, hold);
	                 if(position < 0)
	                 {
						 position = -position - 1;
	                     hold.addCourse(course, credNum, letGrade);
	                     students.add(position, hold);
	                 }
	                 else (students.get(position)).addCourse(course, credNum, letGrade);
	              }
	              catch(NumberFormatException e)
	              { System.out.println("Bad data: " + nextLine);}
	          }
	          for(Student s : students)
	          {
	        	  double GPA = s.getGPA();
	        	  out.println(s.toString() + "\n" + GPA);
	        	  out.println(" ");
	          }
	    }
	    catch(IOException e)
	    {
	        System.out.println("Error opening file: " + e);
	        System.exit(1);
	    }
	}
	  public static <T extends Comparable<T> >
	  int binarySearch(ArrayList<T> table, T key)
	  {
	      int low = 0, high = table.size() - 1;
	      while(high >= low)
	      {
	          int mid = (low + high)/2;
	          T midElement = table.get(mid);
	          int result = key.compareTo(midElement);
	          if(result == 0)
	             return mid;
	          if(result < 0)
	               high = mid - 1;
	          else low  = mid + 1;
	      }
	      return -low - 1;
	  }
}
