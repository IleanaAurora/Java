import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
/*
Problem: Read each word from a file and determine if the word appears in the
dictionary (spell check).  Write any word that does not appear in the dictionary to an output file.

Algorithm:
1. Open files and read the dictionary line by line into an ArrayList, key. Create a second ArrayList for mistakes.
2. Use String Tokenizer to read the Alice file word by word. Disregard all punctuation.
3. Search for each word from the Alice file in the contents of the ArrayList key using a binary search.
4. If the word is not present in the key, place the word in the ArrayList mistakes.
5. If the ArrayList mistakes is not empty, send to an insertion sort to sort the list alphabetically.
6. Remove any duplicate misspelled words from the list.
7. Print the ArrayList mistakes in an output file "misspelled".
*/
public class ArrayListsStringsAndFiles
{
public static void main(String[] args)
{
	// TODO Auto-generated method stub
	 try
       (FileReader reader1 = new FileReader("Unabr1U.txt");
        Scanner in1 = new Scanner(reader1);
		FileReader reader2 = new FileReader("alice 1.txt");
		Scanner in2 = new Scanner(reader2);
        FileWriter writer = new FileWriter("Misspelled.out");
        PrintWriter out = new PrintWriter(writer))
    {
		ArrayList<String> key = new ArrayList<String>();
		ArrayList<String> mistakes = new ArrayList<String>();
		while(in1.hasNextLine())
		{
			String next = in1.nextLine();
			key.add(next);
		}
		while(in2.hasNextLine())
		{
			String line = in2.nextLine();
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens())
			{
				String tok = st.nextToken();
				if(tok.endsWith(".")||tok.endsWith("?")||tok.endsWith("!")||tok.startsWith("'")||tok.endsWith("'")||tok.endsWith(",")
						||tok.endsWith(":")||tok.endsWith(";")||tok.startsWith("(")||tok.endsWith(")")||tok.contains("-"))
				{
					tok = tok.replace("."," ");
					tok = tok.replace("?"," ");
					tok = tok.replace("!"," ");
					tok = tok.replace("'"," ");
					tok = tok.replace(","," ");
					tok = tok.replace(":"," ");
					tok = tok.replace("("," ");
					tok = tok.replace(")"," ");
					tok = tok.replace(";"," ");
					tok = tok.trim();
					if(tok.length()>1)
					{
						String[] result = tok.split("-");
						for(String s:result)
						{
							tok = tok.trim();
							int search = binSearch(key, s);
							if(search < 0)
								mistakes.add(s);
						}
					}
				}
				else
					{
					tok = tok.trim();
					int check = binSearch(key, tok);
					if(check < 0)
						mistakes.add(tok);
					}
			}
		}
		if(mistakes.isEmpty())
			out.println("No words were misspelled.");
		else
		{
			insertionSort(mistakes);
			for(int k = 0; k < mistakes.size() - 1; k++)
            {
                String first = mistakes.get(k);
                String second = mistakes.get(k + 1);
                boolean result = first.equalsIgnoreCase(second);
                if(result)
                	mistakes.remove(second);
            }
			out.println("The following words were misspelled:");
			for(String s:mistakes)
				out.println(s);
		}
		}
    catch(IOException e)
    {
        System.out.println("Error processing file: " + e);
        System.exit(1);
    }
	 }

public static <T extends Comparable<? super T> >int binSearch(ArrayList<T> key, T word)          
{
  int low = 0, high = key.size() - 1;
  while(low <= high)
  {
     int mid = (low + high)/2;
     T hold = key.get(mid);
     int result = ((String) word).compareToIgnoreCase((String) hold);
     if(result == 0)
        return mid;
     else if(result < 0)
        high = mid - 1;
     else
        low = mid + 1;
  }
  return -low - 1;
}

public static <T extends Comparable<? super T> >
void insertionSort(ArrayList<T> table)
{

   for(int i = 1; i < table.size(); i++)
   {
      T temp = table.get(i);
      table.remove(i);
      int pos = binarySearch(table, temp, 0, i - 1);
      if(pos < 0)
         pos = -pos - 1;

      table.add(pos, temp);
   }
}

public static <T extends Comparable<? super T> >
int binarySearch(ArrayList<T> table, T key, int from, int to)
{
  int low = from, high = to, mid;
  while(low <= high)
  {
     mid = (low + high)/2;
     T hold = table.get(mid);
     int result = ((String) key).compareToIgnoreCase((String)hold);
     if(result == 0)
        return mid;
     else if(result < 0)
        high = mid - 1;
     else
        low = mid + 1;
  }
  return -low - 1;
}

}