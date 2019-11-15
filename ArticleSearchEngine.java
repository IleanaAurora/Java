import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
/*
Problem: Create a search engine class that will allow the user to find articles that contain one or more search terms connected using either an 'and' or an 'or'. With the
'and' option the search engine should return articles that contain all of the search terms. With the 'or' option the search engine should return articles that contain any
one of the search terms.

Data structures used and why:
1.In this assignment I will use JOPtionPane's to easily retrieve inputs for the word(s) to search and the file to search through, as well as to display all error messages
and search engine results (for consistency). I used a StringTokenizer to chop up the search input, determine whether the input contained any "and"'s or "or"'s, and count
the number of terms present. I will use a StringBuffer to read the contents of the file for efficient string concatenation and to avoid creating a new immutable string with
each line of the file read. I will also use a map with keys of article id #'s (because they are unique) which have corresponding ArrayLists as values to hold each word in
said article. I used ArrayLists because they allow for duplicate values whereas sets do not. I also used another map with keys of words (they should be listed only once)
which have corresponding inner maps as values. I used an inner map so that each inner map could again house map entries with keys of article id #'s (which are unique) and
values that correspond to the frequency of each word in said article. I used a variable as a flag to easily keep track of whether there was only one word being searched,
multiple connected by "and"'s, or multiple connected by "or"'s. I used a map of sets to hold each word (key) and the articles that individual word appears in (value, which
is a collection of unique article id's, so a set is an appropriate data structure). Using a map of sets was helpful because I could perform an intersection of two sets at a
time to find the "and" searches and a union of two sets at a time to find the "or" searches. I used an iterator to traverse the map of sets because it allowed for both easy
comparisons of sets and intersection/union operations.

Algorithms for each method:
2a.A method to check whether a value from the ArrayList (a word) is present in the key of a map.
	If the map is empty return false, otherwise return whether the map contains the key using the .containsKey() method.
2b.A method to check whether an article id (stored in the variable id) is present in the key of a map.
	Return whether the map contains the key using the .containsKey() method.

Algorithm:
1.Create JOptionPane to retrieve word for input (to search).
2.Create JOptionPane to retrieve file to search. If file does not exist, print error message.
3.Open the file to search, "News.txt".
4.Create a map called articles, a map called result, and a variable called id.
5.Parse the file and place the ID # of each article in the key of the map and for the corresponding value, create an ArrayList to hold the words found within that article.
6.For each entry in the map articles, place the key of the next map entry into the variable id.
7.Also for each entry, traverse the corresponding ArrayList (the value in the map) and check to see if the value at each index in the ArrayList (a word) is present in the
  key of the result map. Do this using the inResult method.
	A)If it is not already present, add it to the key of the result map and for the corresponding value create another map. To this map key add the variable id and to the
	corresponding value add a '1'.
	B)If it is present, search through the inner map in the value of the result map to check if there's a key present that matches the variable id using the inResult method.
		a)If any of the keys in the map matches the variable id (you are within the same article) update the value associated with that key to be the value+1.
		b)If none of the keys in the map matches the variable id (you have started a new article) then create a new entry in the inner map with a key equal to the variable
		id and the value set to '1'.		
8.Create variable AndOr and set to -1.
9.Create a map of sets called S.
10.Count the number of words in the input. If input is more than one word:
	A)Check if input contains an "and," if yes, delete all occurrences of "and" and set variable AndOr to 0.
	B)Check if input contains an "or," if yes, delete all occurrences of "or" and set variable AndOr to 1.
	C)Else input is invalid so send error message.
11.Count the number of words in the input. Place each word in the key of map S and create a set in the value of map S for each count/word.
12.If count is 1 (or if AnOr is set to -1):
	A)Take the key in map S and search for it in the map result. If the key in S is not present in result, print a message saying "Sorry, but this term has no matches."	
13.Go through map S and at each iteration take the key and search for it in the map result.
	A)If the word is present in the key of map result:
		a)Go through the corresponding value (an inner map), and place the key of each inner map in the corresponding set within the value of map S.
	B)Else if AnOr is set to 0, print a message saying "Sorry, but this expression has no matches."
14.If count is 1 (or if AnOr is set to -1), print the corresponding set of values in map S.
15.Else if count is > 1:
	A)If AnOr is equal to 0, go through map S, make variables k1 and k2 equal to the first two values and perform an intersection of the two sets, k3.
		a)While map S has more keys, make k1=k3 and k2 equal to the next value in S. Perform an intersection of the two sets, k1 and k2, to create k3.
	B)Else if AnOr is equal to 1, go through map S, make variables k1 and k2, take the first two values and perform a union of the two sets, k3.
		b)While map S has more keys, make k1=k3 and k2 equal to the next value in S. Perform a union of the two sets, k1 and k2, to create k3.
	C)Print the set k3.
 */
public class ArticleSearchEngine
{
	public static void main(String[] args)
	{
		String input = JOptionPane.showInputDialog("Search for a word or multiple words connected by either 'and' or 'or': ");
		String file = JOptionPane.showInputDialog("Enter a file from which to search from: ");
		try (FileReader reader = new FileReader(file);
				Scanner in = new Scanner(reader))
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
				{System.out.println("Error " + e);}
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
			int AndOr = -1;
			int count = 0;
			MyMap<String, MySet<Integer>> S = new MyListMap<String, MySet<Integer>>();
			StringTokenizer tok1 = new StringTokenizer(input);
			if(tok1.hasMoreTokens())
				count = tok1.countTokens();
			else
				JOptionPane.showMessageDialog(null, "Invald input. You did not enter any text.", "Error", JOptionPane.ERROR_MESSAGE);
			if(count>1)
			{
				if(input.contains(" and "))
				{
					input = input.replaceAll(" and ", " ");
					AndOr = 0;
				}
				else if(input.contains(" or "))
				{
					input = input.replaceAll(" or ", " ");
					AndOr = 1;
				}
				else
					JOptionPane.showMessageDialog(null, "Invalid input. You did not use 'and' or 'or' in between words.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			StringTokenizer tok2 = new StringTokenizer(input);
			if(tok2.hasMoreTokens())
				count = tok2.countTokens();
			else
				JOptionPane.showMessageDialog(null, "Invald input. You only used 'and' and/or 'or'.", "Error", JOptionPane.ERROR_MESSAGE);
			while(tok2.hasMoreTokens())
			{
				S.put(tok2.nextToken(), new MyListSet<Integer>());
			}

			if(S.size() == 1)
			{
				for(MapEntry<String, MySet<Integer>> entry : S)
				{
					String key = entry.getKey();
					boolean found = result.containsKey(key);
					if(!found)
					{
						JOptionPane.showMessageDialog(null, "Sorry, but this term has no matches.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			for(MapEntry<String, MySet<Integer>> entry : S)
			{
				String key = entry.getKey();
				boolean found = result.containsKey(key);
				if(found)
				{
					MyMap<Integer, Integer> temp = result.get(key);
					for(MapEntry<Integer, Integer> v : temp)
						{entry.getValue().add(v.getKey());}
				}
				else if(AndOr == 0)
					JOptionPane.showMessageDialog(null, "Sorry, but this expression has no matches.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			MySet<Integer> k1, k2 = null, k3 = null;
			if(AndOr == -1)
			{
				for(MapEntry<String, MySet<Integer>> entry : S)
				{
					JOptionPane.showMessageDialog(null, entry.getValue(), "Related article(s): ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else if(AndOr >= 0)
			{
				if(AndOr == 0)
				{
					Iterator<MapEntry<String, MySet<Integer>>> It = S.iterator();
					while(It.hasNext())
					{
						if(k2 == null)
						{
							k1 = It.next().getValue();
							if(It.hasNext())
							{
								k2 = It.next().getValue();
							}
							k3 = k1.intersection(k2);
						}
						else
						{
							k1 = k3;
							k2 = It.next().getValue();
							k3 = k1.intersection(k2);
						}
					}
				}
				else if(AndOr == 1)
				{
					Iterator<MapEntry<String, MySet<Integer>>> It = S.iterator();
					while(It.hasNext())
					{
						if(k2 == null)
						{
							k1 = It.next().getValue();
							if(It.hasNext())
							{
								k2 = It.next().getValue();
							}
							k3 = k1.union(k2);
						}
						else
						{
							k1 = k3;
							k2 = It.next().getValue();
							k3 = k1.union(k2);
						}
					}
				}
				int articleCount = k3.size();
				JOptionPane.showMessageDialog(null, k3, articleCount + " Related article(s): ", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File could not be located.", "Error", JOptionPane.ERROR_MESSAGE);
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