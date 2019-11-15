import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
The first data file, ecoliDNA.txt, contains the dna sequence for an ecoli. The second data file, DNA-To-AminoAcids, contains a list of codons and their associated amino acid.
In this exercise I want you to read the dna sequence and determine the positions in the sequence of each codon that codes for each amino acid. The output of your program
should be a pair. The first element of the pair should be the symbol for an amino acid, the second element of the pair should be a set containing the positions of all codons
that code for that amino acid.

The first line of the data file is:
agcttttcat tctgactgca acgggcaata tgtctctgtg tggattaaaa aaagagtctc        60

All of the lines have the same format. The dna is one continuous string of characters. However, to make it more readable the line is divided into 6 sections with 10 bases in
each section. The integer at the end of the line is not part of the sequence. The integer gives your position in the sequence. So after reading the first line you will be at
position 60. Your program should read the data file into one string and then determine the locations of the codons that code for each amino acid.

Algorithm:
1) Open files ecoliDNA.txt and DNA-To-AminoAcids.txt.
2)Create a map called AminoCodons and a map called result.
3)For each line in DNA-To-AminoAcids.txt, add the line to the map AminoCodons.
4)Iterate through the AminoCodons map and at each iteration, put the value of each key into the key of map result if it is not already present in the map result and create an
5)empty set for each value in the map result.
6)Create a StringBuffer buffer.
7)For each line in ecoliDNATest.txt, add the line to the StringBuffer buffer.
8)Separate the StringBuffer buffer using substring and for each substring, get the codon and search for it in the keys of AminoCodons.
	9)Once the codon is found, get the corresponding value in AminoCodons and search for the value in the keys of result map.
	10)Once the correct key is located, add the position of the substring to the corresponding value (which is a set).
11)Iterate through the result map and print at each iteration to a file called AminoAcidPositions.

Test file output should be:
Ala  24,87
Arg  21,117
Asn  75
Asp  42
Cys  30,36
Gln  18,63,66
Glu  
Gly  
His  
Ile  27
Leu  15,33,57,69,72,81,99,114
Lys  48,51,96,102
Met  
Phe  3,9,102
Pro  84
STOP 12,45,60,111
Ser  0,6,54,93
Thr  
Trp  78
Tyr  108
Val  39,90
 */
public class AminoAcidPositionsUsingASetMap
{
	public static void main(String[] args)
	{
		try (FileReader reader1 = new FileReader("ecoliDNATest.txt");
				Scanner in1 = new Scanner(reader1);
				FileReader reader2 = new FileReader("DNA-To-AminoAcids.txt");
				Scanner in2 = new Scanner(reader2);
				FileWriter writer = new FileWriter("AminoAcidPositions.out");
				PrintWriter out = new PrintWriter(writer))
		{
			MyMap<String, String> AminoCodons = new MyListMap<String, String>();
			MyMap<String, MySet<String>> result = new MyListMap<String, MySet<String>>();
			while (in2.hasNextLine())
			{
				String next = in2.nextLine();
				StringTokenizer st = new StringTokenizer(next);
				while(st.hasMoreTokens())
				{
					if(st.countTokens() == 2)
					{
						String tok1 = st.nextToken();
						String tok2 = st.nextToken();
						AminoCodons.put(tok1, tok2);
					}
				}
			}
			Iterator<MapEntry<String, String>> ItACMap = AminoCodons.iterator();
			while(ItACMap.hasNext())
			{
				MapEntry<String, String> next = ItACMap.next();
				MySet<String> set = new MyListSet<String>();
				result.put(next.getValue(), set);
			}
			StringBuffer buffer = new StringBuffer();
			while (in1.hasNextLine())
			{
				String n = in1.nextLine().trim();
				n = n.replaceAll(" ", "");
				String v = n.substring(60);
				int val;
				try {
					val = Integer.parseInt(v);
					int s = n.substring(60).length();
					buffer.append(n);
					for(int i = 0; i < s; i++)
					{
						buffer.deleteCharAt(val);
					}
					}
				catch (NumberFormatException e)
				{System.out.println("Error in data file " + e);}
			}
			int n = 0;
			for (int i = 3; i < buffer.length(); i += 3)
			{
				String codon = buffer.substring(n, i);
				boolean b = check1(AminoCodons, codon);
				if(b)
				{
					String c = AminoCodons.get(codon);
					boolean d = check2(result, c);
					if(d)
					{
						String num = String.valueOf(n);
						result.get(c).add(num);
					}
				}
				n += 3;
			}
			Iterator<MapEntry<String, MySet<String>>> ItRMap = result.iterator();
			while(ItRMap.hasNext())
			{
				MapEntry<String, MySet<String>> next = ItRMap.next();
				out.println(next);
			}
			
		} catch (IOException e) {
			System.out.println("Error processing file: " + e);
			System.exit(1);
		}
	}

	private static boolean check1(MyMap<String, String> map, String value) {
		return (map.containsKey(value));
	}
	
	private static boolean check2(MyMap<String, MySet<String>> map, String value) {
		return (map.containsKey(value));
	}
}
