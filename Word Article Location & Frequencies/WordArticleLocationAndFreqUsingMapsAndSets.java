import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
Problem: Read and analyze a text file that contains a number of news articles. Print a report of each word and with each word, the id number of the articles that the word
appears in along with the frequency of the word in the article.

Data structures used and why:
1.In this assignment I will use a StringBuffer to read the contents of the file for efficient string concatenation and to avoid creating a new immutable string with each
line of the file read. I will also use a map with keys of article id #'s (because they are unique) which have corresponding ArrayLists as values to hold each word in said
article. I used ArrayLists because they allow for duplicate values whereas sets do not. I also used another map with keys of words (they should be listed only once) which
have corresponding inner maps as values. I used an inner map so that each inner map could again house map entries with keys of article id #'s (which are unique) and values
that correspond to the frequency of each word in said article.

Algorithms for each method:
2a.A method to check whether a value from the ArrayList (a word) is present in the key of a map.
	If the map is empty return false, otherwise return whether the map contains the key using the .containsKey() method.
2b.A method to check whether an article id (stored in the variable id) is present in the key of a map.
	Return whether the map contains the key using the .containsKey() method.

Algorithm:
1.Open the file News.txt (test using Reuters00.txt).
2.Create a map called articles, a map called result, and a variable called id.
3.Parse the file and place the ID # of each article in the key of the map and for the corresponding value, create an ArrayList to hold the words found within that article.
4.For each entry in the map articles, place the key of the next map entry into the variable id.
5.Also for each entry, traverse the corresponding ArrayList (the value in the map) and check to see if the value at each index in the ArrayList (a word) is present in the
key of the result map. Do this using the inResult method.
	A)If it is not already present, add it to the key of the result map and for the corresponding value create another map. To this map key add the variable id and to the
	corresponding value add a '1'.
	B)If it is present, search through the inner map in the value of the result map to check if there's a key present that matches the variable id using the inResult method.
		a)If any of the keys in the map matches the variable id (you are within the same article) update the value associated with that key to be the value+1.
		b)If none of the keys in the map matches the variable id (you have started a new article) then create a new entry in the inner map with a key equal to the variable
		id and the value set to '1'.
6.For each entry in the map result, print the key (a word) and for each corresponding value (which is the inner map), print the keys and values (the article id's and
frequency within the article of each word).
 */
public class WordArticleLocationAndFreqUsingMapsAndSets
{
	public static void main(String[] args)
	{
		try (FileReader reader = new FileReader("News.txt");
				Scanner in = new Scanner(reader);
				FileWriter writer = new FileWriter("WordLocationsAndFreq.out");
				PrintWriter out = new PrintWriter(writer))
		{
			MyMap<Integer, ArrayList<String>> articles = new MyListMap<Integer, ArrayList<String>>();
			MyMap<String, MyMap<Integer, Integer>> result = new MyListMap<String, MyMap<Integer, Integer>>();
			StringBuffer buffer1 = new StringBuffer();
			int id = 0;
			while (in.hasNextLine())
			{
				String n = in.nextLine().trim();
				buffer1.append(n + " ");
			}
			while(buffer1.length() != 0)
			{
				int s1 = buffer1.indexOf("<ID>", 0);
				int e1 = buffer1.indexOf("</ID>", s1);
				String st1 = buffer1.substring(s1+4, e1);
				int k = 0;
				try
				{ k = Integer.parseInt(st1); }
				catch (NumberFormatException e)
				{System.out.println("Error in data file " + e);}
				articles.put(k, new ArrayList<String>());
				int s2 = buffer1.indexOf("<BODY>", e1);
				int e2 = buffer1.indexOf("</BODY>", s2);
				String st2 = buffer1.substring(s2+6, e2);
				String[] words = st2.replaceAll("-", " ").replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
				for (String w: words)
					articles.get(k).add(w);
				buffer1.delete(0, e2+9);
			}
			for(MapEntry<Integer, ArrayList<String>> entry : articles)
			{
			  id = entry.getKey();
			  for(String word : entry.getValue())
			  {
				  boolean b = inResult(result, word);
				  if(!b)
				  {
					  result.put(word, new MyListMap<Integer,Integer>());
					  result.get(word).put(id, 1);
				  }
				  else
				  {
					  MyMap<Integer, Integer> InMap = result.get(word);
					  boolean c = inResult(InMap, id);
					  if(c)
					  {
						  Integer freq = InMap.get(id);
						  InMap.put(id, freq+1);
					  }
					  else
					  { InMap.put(id, 1); }
				  }
			  }
			}
			for(MapEntry<String, MyMap<Integer, Integer>> e : result)
			{
				out.print(e.getKey() + "\t\t");
				for(MapEntry<Integer, Integer> v : e.getValue())
				{
					out.print("(" + v.getKey() + "," + v.getValue() + ")");
				}
				out.println("");
			}
		} catch (IOException e) {
			System.out.println("Error processing file: " + e);
			System.exit(1);}
	}
	
	private static boolean inResult(MyMap<Integer, Integer> inMap, int k)
	{
		return(inMap.containsKey(k));
	}

	private static boolean inResult(MyMap<String, MyMap<Integer, Integer>> map, String k)
	{
		if(map.isEmpty())
			return false;
		else return(map.containsKey(k));
	}
}