import java.util.Scanner;
import java.util.StringTokenizer;

/*
Problem: Get a random number of integers from the user and give an average.
 */
public class RandomIntegersAverage
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		do{
			System.out.println("Please enter some integers: ");
			String invalues = in.nextLine();
			StringTokenizer tok = new StringTokenizer(invalues);
			try
			{
				int total = 0;
				int counter = 0;
				while(tok.hasMoreTokens())
				{
					String next = tok.nextToken();
					int n = Integer.parseInt(next);
					counter++;
					total += n;
				}
			double Avg = (double)total/counter;
			System.out.println("The average of these " +counter+ " numbers is " +Avg);
			valid = true;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid input.");
			}

		}while(!valid);
			in.close();		
	}

}
