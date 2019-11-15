import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
Problem: Given a file "ecoli.txt", read the file and store the dna sequence in a string. Scan the string and count the number of occurrences of each codon using "codons.txt".

Algorithm:
1) Open files ecoli.txt and codons.txt.
2) Create an ArrayList of ecoli and an ArrayList of codons.
3) Create and initialize an array of 64 zeros, called freq, to act as a counter.
4) For each line in codons.txt, add the line to the ArrayList codons.
5) Create a StringBuffer buffer.
6) For each line in ecoli.txt, add the line to the StringBuffer buffer.
7) Separate the StringBuffer buffer using substring and add each substring into ArrayList ecoli.
8) For each index of ArrayList ecoli, use a binary search to compare the index to ArrayList codons and get the position of the codon which the index is equal to.
9) Increment the position in the array frequency.
10) Print the ArrayList codons and the array frequency.
 */
public class CountingCodonsUsingStringBuffer
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		try (FileReader reader1 = new FileReader("ecoli.txt");
			Scanner in1 = new Scanner(reader1);
			FileReader reader2 = new FileReader("codons.txt");
			Scanner in2 = new Scanner(reader2);
			FileWriter writer = new FileWriter("codonCount.out");
			PrintWriter out = new PrintWriter(writer))
			
		{
			ArrayList<String> codons = new ArrayList<String>();
			ArrayList<String> ecoli = new ArrayList<String>();
			int[] freq = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			int n = 0;
			while(in2.hasNextLine())
			{
				String next = in2.nextLine();
				codons.add(next);
			}
			StringBuffer buffer = new StringBuffer();
			while(in1.hasNextLine())
			{
				buffer.append(in1.nextLine());
			}
			for(int i = 3; i < buffer.length(); i+=3)
			{
				ecoli.add(buffer.substring(n,i));
				n += 3;
			}
			for(int i = 0; i < ecoli.size(); i++)
			{
				int pos = binSearch(ecoli, i, codons);
				if(pos>=0)
				{
					freq[pos]++;
				}
			}
			for(int i = 0; i < codons.size(); i++)
			{
				out.printf("%s %s\n",codons.get(i),freq[i]);
			}
		}
		catch(IOException e)
		{
			System.out.println("Error processing file: " + e);
			System.exit(1);
		}
	}
	private static int binSearch(ArrayList<String> ecoli, int i, ArrayList<String> codons) {
		int low = 0, high = codons.size()-1;
		while(low<=high)
		{
			int mid = (low+high)/2;
			int n = ecoli.get(i).compareTo(codons.get(mid));
			if(n == 0)
				return mid;
			if(n > 0)
				low = mid + 1;
			else high = mid - 1;
		}
		return -low-1;
	}
}
