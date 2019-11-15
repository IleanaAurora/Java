import java.util.ArrayList;
import java.util.Random;

/*
Problem: Generate a small ArrayList of integers and sort them from least to greatest without using comparisons.

Algorithm:
1. Generate a list of 10 random integers > 0 and < 999 and hold them in a ArrayList. Print the list.
2. For each integer in the ArrayList, generate a queue and store the queue in an ArrayList.
3. For each integer in the list, get the character in the ones place and place the integer in an ArrayList of queues at the index that corresponds to the character.
4. Clear the list and place all the integers back in this new, more "sorted" order and print.
5. For each integer in the list, get the character in the tens place and place the integer in an ArrayList of queues at the index that corresponds to the character.
6. Clear the list and place all the integers back in this new, more "sorted" order and print.
7. For each integer in the list, get the character in the hundreds place and place the integer in an ArrayList of queues at the index that corresponds to the character.
8. Clear the list and place all the integers, now completely sorted from least to greatest, back in the list and print.
 */
public class ArrayListSortingWithoutComparisons
{
    public static void main(String[] args)
    {
    	Random random = new Random();
    	ArrayList<String> sorted = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<ListQueue<String>> qlist1 = new ArrayList<ListQueue<String>>();
		for (int i = 0; i < 10; i++)
		{
			int next = random.nextInt(999);
			String nextString = String.format("%03d", next);
			list.add(nextString);
		}
		System.out.println("Randomly generated list of positive integers: ");
		for(int i = 0; i < list.size(); i++)
    	{
    		System.out.println(list.get(i));;
    	}
		System.out.println("");
    	for(int i = 0; i < list.size(); i++)
    	{
    		qlist1.add(new ListQueue<String>());
    	}
    	
    	for(int i = 2; i >= 0; i--)
    	{
    		sort(i, list, qlist1, sorted);
    	}
    }

	private static void sort(int i, ArrayList<String> list, ArrayList<ListQueue<String>> qlist1, ArrayList<String> sorted)
	{
		for (int j = 0; j < qlist1.size(); j++)
		{
			char val = list.get(j).charAt(i);
			int x = Character.getNumericValue(val);
			ListQueue<String> e = qlist1.get(x);
			e.offer(list.get(j));
		}
		list.clear();
		for (int j = 0; j < qlist1.size(); j++)
		{
			ListQueue<String> e = qlist1.get(j);
			while(e.isEmpty() == false)
			{sorted.add(e.poll());}
			for (int k = 0; k < sorted.size(); k++)
			{
				list.add(sorted.get(k));
			}
			sorted.clear();
		}
		if(i == 2)
			System.out.println("Sorting by the ones place: ");
		else if(i == 1)
			System.out.println("Sorting by the tens place: ");
		else
			System.out.println("Sorting by the hundreds place, the entire list sorted: ");
		for(int j = 0; j < list.size(); j++)
		{
			System.out.println(list.get(j));
		}
		System.out.println("");
		
	}
}