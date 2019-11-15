import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
Test Data:
Brown,Mary			2.7142857142857144
Charles,Edward		2.58823529
Thomas,Joseph		
Williams,Karen		
Samuels,Ralph		
Dunn,Robert			

Algorithm:
1. Open the file Students.txt and create an output file.
2. Read the names from Students.txt into the ArrayList names.
3. For each student, add up the credits and the quality points (determined from their grade).
4. For each student, multiply the credits by the quality points to get total.
5. For each student, divide total by credits to get GPA.
6. Add each GPA to the ArrayList GPA.
7. Send both ArrayLists names and GPA to a file.
 */
public class GradePointAverage
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	    try
	       (FileReader reader = new FileReader("SampleData.txt");
	        Scanner in = new Scanner(reader);
	        FileWriter writer = new FileWriter("StudentGPAs.out");
	        PrintWriter out = new PrintWriter(writer))
	    {
	        ArrayList<Double> GPA = new ArrayList<Double>();
	        int t = 0, cred = 0,qp = 0,grade = 0;
	        double total = 0,totcred = 0;
	        while(in.hasNextLine())
	        {
			  String nextLine = in.nextLine();
			  StringTokenizer st = new StringTokenizer(nextLine);
			  if(st.countTokens() == 2)
			  {
				 String name = st.nextToken();
				 String times = st.nextToken();
				 out.printf("%s %s\n",name,times);
				 try
				 {
					int time = Integer.parseInt(times);
					t = time;
				 }
				 catch(NumberFormatException e)
				 {System.out.println("Invalid data: " + nextLine);}
			  }
			  do
			  {
				  if(st.countTokens() == 4)
				  {
					 String subject = st.nextToken();
					 String course = st.nextToken();
					 String credit = st.nextToken();
					 String letgrade = st.nextToken();
					 out.printf("%s %s %s %s\n",subject,course,credit,letgrade);
					 try
					 {
						int creditnum = Integer.parseInt(credit);
						cred = creditnum;
						totcred += cred;
					 }
					 catch(NumberFormatException e)
					 	{System.out.println("Invalid data: " + nextLine);}
					 if(letgrade == "A")
						 qp = 4;
					 if(letgrade.equalsIgnoreCase("B"))
						 qp = 3;
					 if(letgrade.equalsIgnoreCase("C"))
						 qp = 2;
					 if(letgrade.equalsIgnoreCase("D"))
						 qp = 1;
					 if(letgrade.equalsIgnoreCase("F"))
						 qp = 0;
					 grade = qp;
					 total += cred*grade;
			      }
				  t--;
		        }while(t != 0);		  
			  if(t == 0 && totcred > 0)
			  	{
			  		GPA.add(total/totcred);
			  		out.println(GPA.get(GPA.size() - 1));
			  		//GPA.clear();
			  		//total = 0;
			  		//totcred = 0;
			  	}
	       }
	    }
	    catch(IOException e)
	    {
	        System.out.println("Error processing file: " + e);
	        System.exit(1);
	    }
	}
}
