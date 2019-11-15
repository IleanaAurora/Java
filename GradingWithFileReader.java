import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/*
Problem: Write a program that reads the students' answers and assigns a grade to each student.
Send a report to a file that lists each student name and the student's quiz score.

Test Data:
Dunne,Mary			35
Brown,Gail			30
Collins,Mark		40
Adams,William		35
Fox,Thomas			35
Long,Ann			35
Gordon,Elizabeth	30
Mills,Justin		30
Ellison,Jane		30

Algorithm:
1. Open two files: AnswerKey and StudentAnswers, and create an output file.
2. Read the AnswerKey into the ArrayList Answers.
3. Read the names from StudentAnswers into the ArrayList names.
4. Read every 10 strings from StudentAnswers into the ArrayList test.
5. Compare each line of test to each line of Answers and if they are equal, add 5 points to the total.
6. Add total to the ArrayList grades.
7. Send both ArrayLists names and grades to a file.
 */
public class GradingWithFileReader
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		try
		(FileReader reader = new FileReader("AnswerKey.txt");
		Scanner in = new Scanner(reader);
		FileReader reader1 = new FileReader("StudentAnswers.txt");
		Scanner in1 = new Scanner(reader1);
        FileWriter writer = new FileWriter("GradeSheet.out");
        PrintWriter out = new PrintWriter(writer))
		{
	        ArrayList<String> Answers = new ArrayList<String>();
	        ArrayList<String> names = new ArrayList<String>();
	        ArrayList<String> test = new ArrayList<String>();
	        ArrayList<Integer> grades = new ArrayList<Integer>();
	        int elements = 0, total = 0;
			while(in.hasNextLine())
	        {
	        	String nextLine = in.nextLine();
	            Answers.add(nextLine);
	        }
			while(in1.hasNextLine())
	        {
	        	String nextLine = in1.nextLine();
	        	if(nextLine.length() > 1)
	        	{
	        		names.add(nextLine);
	        	}
	        	else
	        	{
	        		test.add(nextLine);
	        		elements++;
	        	}
	        	while(elements == 10)
	        	{
					for(int i = 0; i < test.size(); i++)
					{
						if(test.get(i).equals(Answers.get(i)))
							total += 5;
					}
				grades.add(total);
				total = 0;
				elements = 0;
				test.clear();
	        	}
	        }
	         for(int i = 0; i < names.size(); i++)
	             out.printf("%20s %d\n", names.get(i), grades.get(i));
		}
		    catch(IOException e)
		    {
		        System.out.println("Error processing file: " + e);
		        System.exit(1);
		    }
	}
}