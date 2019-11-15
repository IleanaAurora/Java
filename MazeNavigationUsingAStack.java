import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
Algorithm:
1. Open the maze file and read the first line of code to obtain two integers which will serve as the number of rows and columns in the maze, respectively.
2. Create a 2D array with a size that corresponds to the number of rows and columns as given by the two integers (the first line of code).
3. Read the remaining lines of code and place each character in its corresponding array position.
4. Create a class Coordinate to create objects for each position in the maze. Each Coordinate will consist of the x and y coordinate of the position, its value (either O or
X), the boolean values indicating which directions are permissible to take (north, south, east, and west, all initialized to true), a directions stack to keep track of the
order of the directions taken, and an ArrayList dir to hold the direction from which the Coordinate came.
5. Begin at position [rows-1][columns-1] in the array by creating a start Coordinate and placing it in a stack, Path. Create another ArrayList, taken, to hold the taken
Coordinates.
6. Do: check the the truth value for each direction in the Coordinate.
	7. If the North direction for the Coordinate is true, send to the MoveUp function, where you check if the index of the would-be position exists, and if it doesn't, you
	set North to false for that Coordinate. If it exists, you create a new Coordinate at the up position and you check to see if the Coordinate is present in the ArrayList
	taken, and if it is, set North to false and return false. If it's not in taken, push it onto the stack. Check the value of the Coordinate (either O or X) and if it's X,
	set North to false and pop the stack. If it's O, set South to false (so you don't backtrack prematurely) and add the Coordinate to taken.
	8. Return true if the MoveUp function resulted in a new position at an O. If true, subtract one from x and subtract one from row.
	9. If the South direction for the Coordinate is true, send to the MoveDown function, where you check if the index of the would-be position exists, and if it doesn't, you
	set South to false for that Coordinate. If it exists, you create a new Coordinate at the down position and you check to see if the Coordinate is present in the ArrayList
	taken, and if it is, set South to false and return false. If it's not in taken, push it onto the stack. Check the value of the Coordinate (either O or X) and if it's X,
	set South to false and pop the stack. If it's O, set North to false (so you don't backtrack prematurely) and add the Coordinate to taken.
	10. Return true if the MoveDown function resulted in a new position at an O. If true, add one to x and add one to row.
	11. If the East direction for the Coordinate is true, send to the MoveRight function where you check if the index of the would-be position exists, and if it doesn't, you
	set East to false for that Coordinate. If it exists, you create a new Coordinate at the right position and you check to see if the Coordinate is present in the ArrayList
	taken, and if it is, set East to false and return false. If it's not in taken, push it onto the stack. Check the value of the Coordinate (either O or X) and if it's X,
	set East to false and pop the stack. If it's O, set West to false (so you don't backtrack prematurely) and add the Coordinate to taken.
	12. Return true if the MoveRight function resulted in a new position at an O. If true, add one to y and add one to column.
	13. If the West direction for the Coordinate is true, send to the MoveLeft function where you check if the index of the would-be position exists, and if it doesn't, you
	set West to false for that Coordinate. If it exists, you create a new Coordinate at the left position and you check to see if the Coordinate is present in the ArrayList
	taken, and if it is, set West to false and return false. If it's not in taken, push it onto the stack. Check the value of the Coordinate (either O or X) and if it's X,
	set West to false and pop the stack. If it's O, set East to false (so you don't backtrack prematurely) and add the Coordinate to taken.
	14. Return true if the MoveLeft function resulted in a new position at an O. If true, subtract one from y and one from column.
	15.If a Coordinate has false boolean values for all directions and the direction it came from was south (must backtrack), set north to false for the previous Coordinate,
	remove the Coordinate from taken, and pop the stack.
	16.If a Coordinate has false boolean values for all directions and the direction it came from was north (must backtrack), set south to false for the previous Coordinate,
	remove the Coordinate from taken, and pop the stack.
	17.If a Coordinate has false boolean values for all directions and the direction it came from was east (must backtrack), set west to false for the previous Coordinate,
	remove the Coordinate from taken, and pop the stack.
	18.If a Coordinate has false boolean values for all directions and the direction it came from was west (must backtrack), set east to false for the previous Coordinate,
	remove the Coordinate from taken, and pop the stack.
	19. If the x and x of the current Coordinate are equal to 1, break out of the while loop.
	20. While the last Coordinate in the stack is not at position [0][0] in the maze and the size of the stack path is greater than 1, repeat the do-while loop.
21. If the stack Path is not empty and contains only one Coordinate, then you either could not move forward or moved forward and were blocked from all directions and had to
backtrack. There is no path through the maze.
22.If the stack Path is not empty, print each Coordinate in an output file, "Path.out."
 */
public class MazeNavigationUsingAStack
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		try
	       (FileReader reader = new FileReader("Maze3.txt");
	        Scanner in = new Scanner(reader);
	        FileWriter writer = new FileWriter("Path.out");
	        PrintWriter out = new PrintWriter(writer))
	       {
			Integer row = 0, column = 0;
			Integer x, y;
			char [][] maze = null;
			int rowCount = 0;
			while(in.hasNextLine())
			{
				String line = in.nextLine();
				StringTokenizer st = new StringTokenizer(line);
				if(st.countTokens() == 2)
				{
					String tok1 = st.nextToken();
					String tok2 = st.nextToken();
					try {
						row = Integer.parseInt(tok1);
						column = Integer.parseInt(tok2);
						}
					catch (NumberFormatException e)
						{System.out.println("Error in data file " + e);}
					maze = new char [row][column];
				}
				else
				{
					if(st.countTokens() == column)
					{
						for(int i = 0; i < column; i++)
						{
							String tok3 = st.nextToken();
							char c = tok3.charAt(0);
							maze[rowCount][i] = c;
						}
						rowCount++;
					}
				}
			}
			x = row;
			y = column;
			row=row-1;
			column=column-1;
			for(int i = 0; i < maze.length; i++)
			{
			    for (int j = 0; j < maze[i].length; j++)
			    {
			        out.printf("%2c", maze[i][j]);
			    }
			    out.println();
			}
			Coordinate start = new Coordinate(x, y, maze[row][column]);
			ListStack<Coordinate> path = new ListStack<Coordinate>();
			ArrayList<Coordinate> taken = new ArrayList<>();
			path.push(start);
			taken.add(start);
			do
			{
				if(path.peek().getNorth() == true)
				{ 
					boolean t = MoveUp(path, row, column, maze, x, y, taken);
					if(t == true)
					{
						x = x-1;
						row = row-1;
					}
				}
				if(path.peek().getSouth() == true)
				{  
					boolean t = MoveDown(path, row, column, maze, x, y, taken);
					if(t == true)
					{
						x = x+1;
						row = row+1;
					}
				}
				if(path.peek().getEast() == true)
				{ 
					boolean t = MoveRight(path, row, column, maze, x, y, taken);
					if(t == true)
					{
						y = y+1;
						column = column+1;
					}
				}
				if(path.peek().getWest() == true)
				{  
					boolean t = MoveLeft(path, row, column, maze, x, y, taken);
					if(t == true)
					{
						y = y-1;
						column = column-1;
					}
				}
				boolean N = path.peek().getNorth();
				boolean S = path.peek().getSouth();
				boolean E = path.peek().getEast();
				boolean W = path.peek().getWest();
				if(N == false && S == false && E == false  && W == false && path.peek().getLastDirection() == "s")
				{
					path.peek().getPrev().setNorth(false);
					for(int i = 0; i < taken.size()-1; i++)
					{
						if(taken.get(i).getRow() == path.peek().getRow() && taken.get(i).getColumn() == path.peek().getColumn() && taken.get(i).getVal() ==
								path.peek().getVal())
						{
							taken.remove(i);
						}
					}
					path.pop();
				}
				else if(N == false && S == false && E == false && W == false && path.peek().getLastDirection() == "n")
				{
					path.peek().getPrev().setSouth(false);
					for(int i = 0; i < taken.size()-1; i++)
					{
						if(taken.get(i).getRow() == path.peek().getRow() && taken.get(i).getColumn() == path.peek().getColumn() && taken.get(i).getVal() ==
								path.peek().getVal())
						{
							taken.remove(i);
						}
					}
					path.pop();
				}
				else if(N == false && S == false && E == false && W == false && path.peek().getLastDirection() == "e")
				{
					path.peek().getPrev().setWest(false);
					for(int i = 0; i < taken.size()-1; i++)
					{
						if(taken.get(i).getRow() == path.peek().getRow() && taken.get(i).getColumn() == path.peek().getColumn() && taken.get(i).getVal() ==
								path.peek().getVal())
						{
							taken.remove(i);
						}
					}
					path.pop();
				}
				else if(N == false && S == false && E == false && W == false && path.peek().getLastDirection() == "w")
				{
					path.peek().getPrev().setEast(false);
					for(int i = 0; i < taken.size()-1; i++)
					{
						if(taken.get(i).getRow() == path.peek().getRow() && taken.get(i).getColumn() == path.peek().getColumn() && taken.get(i).getVal() ==
								path.peek().getVal())
						{
							taken.remove(i);
						}
					}
					path.pop();
				}
				if(path.peek().getCoordinate().equals("1,1"))
				{
					out.println("Congratulations! You successfully traversed the maze.");
					break;
				}
			}while(path.peek().getCoordinate().equals("1,1") == false && path.size() > 1);
			if(path.isEmpty() == false)
			{
				if(path.size() == 1)
				{
					out.println("There is no path through the maze.");
				}
				else {
				out.println("Path Solution: ");
				for (int i = path.size() - 1; i >= 0; i--)
				{
			        out.println(path.pop().toString());
			    }}
			}
	       }
		catch(IOException e)
			{
		        System.out.println("Error processing file: " + e);
		        System.exit(1);
		    }
	    }
	
	private static boolean MoveRight(ListStack<Coordinate> path, Integer row, Integer column, char[][] maze, Integer x, Integer y, ArrayList<Coordinate> taken)
	{
		// TODO Auto-generated method stub
		boolean exists = false;
		if(row >= 0 && row < maze.length && column+1 >= 0 && column+1 < maze[1].length)
	    {
	        exists = true;
	    }
		if(exists == true)
		{
			Coordinate right = new Coordinate(x, y+1, maze[row][column+1], path.peek());
			for(int i = 0; i < taken.size()-2; i++)
			{
				if(taken.get(i).getRow() == right.getRow() && taken.get(i).getColumn() == right.getColumn() && taken.get(i).getVal() == right.getVal())
				{
					path.peek().setEast(false);
					return false;}
			}
			path.push(right);
			if(right.getVal() == 'X')
			{
				path.peek().getPrev().setEast(false);
				path.pop();
				return false;
			}
			else
			{
				path.peek().setWest(false);
				taken.add(right);
				return true;
			}
		}
		else
		{ path.peek().setEast(false); 
		return false;}
	}

	private static boolean MoveDown(ListStack<Coordinate> path, Integer row, Integer column, char[][] maze, Integer x, Integer y, ArrayList<Coordinate> taken)
	{
		// TODO Auto-generated method stub
		boolean exists = false;
		if(row+1 >= 0 && row+1 < maze.length && column >= 0 && column < maze[1].length)
	    {
	        exists = true;
	    }
		if(exists == true)
		{
			Coordinate down = new Coordinate(x+1, y, maze[row+1][column], path.peek());
			for(int i = 0; i < taken.size()-2; i++)
			{
				if(taken.get(i).getRow() == down.getRow() && taken.get(i).getColumn() == down.getColumn() && taken.get(i).getVal() == down.getVal())
				{
					path.peek().setSouth(false);
					return false;}
			}
			path.push(down);
			if(down.getVal() == 'X')
			{
				path.peek().getPrev().setSouth(false);
				path.pop();
				return false;
			}
			else
			{
				path.peek().setNorth(false);
				taken.add(down);
				return true;
			}
		}
		else
		{ path.peek().setSouth(false);
		return false;}
	}

	private static boolean MoveLeft(ListStack<Coordinate> path, Integer row, Integer column, char[][] maze, Integer x, Integer y, ArrayList<Coordinate> taken)
	{
		// TODO Auto-generated method stub
		boolean exists = false;
		if(row >= 0 && row < maze.length && column-1 >= 0 && column-1 < maze[1].length)
	    {
	        exists = true;
	    }
		if(exists == true)
		{
			Coordinate left = new Coordinate(x, y-1, maze[row][column-1], path.peek());
			for(int i = 0; i < taken.size()-2; i++)
			{
				if(taken.get(i).getRow() == left.getRow() && taken.get(i).getColumn() == left.getColumn() && taken.get(i).getVal() == left.getVal())
				{
					path.peek().setWest(false);
					return false;}
			}
			path.push(left);
			if(left.getVal() == 'X')
			{
				path.peek().getPrev().setWest(false);
				path.pop();
				return false;
			}
			else
			{
				path.peek().setEast(false);
				taken.add(left);
				return true;
			}
		}
		else
		{ path.peek().setWest(false); 
		return false;}
	}

	private static boolean MoveUp(ListStack<Coordinate> path, Integer row, Integer column, char[][] maze, Integer x, Integer y, ArrayList<Coordinate> taken)
	{
		// TODO Auto-generated method stub
		boolean exists = false;
		if(row-1 >= 0 && row-1 < maze.length && column >= 0 && column < maze[1].length)
	    {
			exists = true;
	    }
		if(exists == true)
		{
			Coordinate up = new Coordinate(x-1, y, maze[row-1][column], path.peek());
			for(int i = 0; i < taken.size()-2; i++)
			{
				if(taken.get(i).getRow() == up.getRow() && taken.get(i).getColumn() == up.getColumn() && taken.get(i).getVal() == up.getVal())
				{
					path.peek().setNorth(false);
					return false;}
			}
			path.push(up);
			if(up.getVal() == 'X')
			{
				path.peek().getPrev().setNorth(false);
				path.pop();
				return false;
			}
			else
			{
				path.peek().setSouth(false);
				taken.add(up);
				return true;
			}	
		}
		else
		{ path.peek().setNorth(false);
		return false;
		}
	}

	private static class Coordinate
	  {
	    private int row;
	    private int column;
	    private char data;
	    private boolean north;
	    private boolean south;
	    private boolean east;
	    private boolean west;
	    ListStack<String> directions;
	    ArrayList<String> dir;
	    String c;
	    
	    Coordinate previous;
	    String n = "n";
	    String s = "s";
	    String e = "e";
	    String w = "w";
	    
	    public Coordinate(int x, int y, char value)
	    { 
	    	row = x;
	    	column = y;
	    	data = value;
	    	north = true;
	    	south = true;
	    	east = true;
	    	west = true;
	    	directions = new ListStack<String>();
	    	dir = new ArrayList<String>();
	    }

		public Coordinate(int x, int y, char value, Coordinate c)
	    {
	    	row = x;
	    	column = y;
	    	data = value;
	    	previous = c;
	    	north = true;
	    	south = true;
	    	east = true;
	    	west = true;
	    	directions = new ListStack<String>();
	    	dir = new ArrayList<String>();
	    }
	    
	    public void setNorth(boolean b)
	    { north = b; 
	    directions.push(n);}
	    
	    public void setSouth(boolean b)
	    { south = b;
	    directions.push(s);}
	    
	    public void setEast(boolean b)
	    { east = b;
	    directions.push(e);}
	    
	    public void setWest(boolean b)
	    { west = b; 
	    directions.push(w);}
	    
	    public int getRow()
	    { return row; }
	    
	    public int getColumn()
	    { return column; }
	    
	    public char getVal()
	    { return data; }
	    
	    public Coordinate getPrev()
	    { return previous; }
	    
	    public boolean getNorth()
	    { return north; }
	    
	    public boolean getSouth()
	    { return south; }
	    
	    public boolean getEast()
	    { return east; }
	    
	    public boolean getWest()
	    { return west; }
	    
	    public String getLastDirection()
	    {
	    	for(int i = 0; i < directions.size(); i++)
	    	{dir.add(directions.pop());}
	    	String d = dir.get(dir.size() - 1);
	    	return d;
	    }
	    
	   public String getCoordinate()
	    { String c = row + "," + column;
		return c;}
	   
	   public boolean equals(Coordinate otherC)
	   {
	 	  if(otherC == this)
	 	     return true;
	 	  if(otherC == null)
	 	     return false;
	 	  if(this.getClass() != otherC.getClass())
	 	     return false;
	 	  Coordinate otherCoor = (Coordinate)otherC;
	 	  return c.equals(otherC.c);
	   }
	   
	    public String toString() 
	    { return "Coordinate: (" + row + "," + column + ")"; }
}
}