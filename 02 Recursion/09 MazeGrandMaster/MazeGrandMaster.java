// Name:
// Date:

import java.util.*;
import java.io.*;

public class MazeGrandMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      Maze m = new Maze(sc.next()+".txt");  //append the .txt 
      m.display();      
      
      System.out.println("Options: ");
      System.out.println("1: Length of the shortest path\n\tIf no path exists, say so.");
      System.out.println("2: Length of the shortest path\n\tList the shortest path\n\tDisplay the shortest path\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      
      m.solve(sc.nextInt());
   } 
}

class Maze
{
   //constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char TEMP = 'o';
   private final char PATH = '*';
   
   //instance fields
   private char[][] maze;
   private int startRow, startCol;
  
	
  /** 
	 * Constructor.
    * Creates a "deep copy" of the array.
    * We use this in Codepost. 
	 */
   public Maze(char[][] m)  
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
	
  /** 
	 * Write this one-arg constructor.
    * Use a try-catch block.
	 * Use next(), not nextLine() 
    * Search the maze to find the location of 'S' 
	 */
   public Maze(String filename)    
   {
       
   }
   
   public char[][] getMaze()
   {
      return maze;
   }
   
   public void display()
   {
   
   }
   
   public void solve(int n)
   {
      switch(n)
      {    
         case 1:  
            int shortestPath = findShortestLengthPath(startRow, startCol);
            if( shortestPath < 999 )
               System.out.println("Shortest path is " + shortestPath);
            else
               System.out.println("No path exists."); 
            display();
            break;
            
         case 2:
            String strShortestPath = findShortestPath(startRow, startCol);
            if( strShortestPath.length()!=0 )
            {
               System.out.println("Shortest length path is: " + getPathLength(strShortestPath));
               System.out.println("Shortest path is: " + strShortestPath);
               markPath(strShortestPath);
               display();  //display solved maze
            }
            else
               System.out.println("No path exists."); 
            break;
         default:
            System.out.println("File not found");   
      }
   }
   
   
 /*  MazeGrandMaster 1   
     recur until you find E, then return the shortest path
     returns 999 if it fails
     precondition: Start can't match with Exit
 */ 
   public int findShortestLengthPath(int r, int c)
   {
      return 999; 
   }  
   
/*  MazeGrandMaster 2   
    recur until you find E, then build the path with (r,c) locations
    and the number of steps, e.g. ((5,0),10),((5,1),9),((6,1),8),((6,2),7),
    ((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)

    as you build, choose the shortest path at each step
    returns empty String if there is no path
    precondition: Start can't match with Exit
 */
   public String findShortestPath(int r, int c)
   {
      return ""; 
   }	

	/** MazeGrandMaster 2 
       returns the length, i.e., third number when the format of the strPath is 
            "((3,4),10),((3,5),9),..."
       returns 999 if the string is empty
       precondition: strPath is either empty or follows the format above
    */
   public int getPathLength(String strPath)
   {
      return 999;
   } 

  /** MazeGrandMaster 2 
      recursive method that takes a String created by findShortestPath(r, c)     
      in the form of ((5,0),10),((5,1),9),((6,1),8),((6,2),7),
              ((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),
              ((4,7),0) and marks the actual path in the maze 
      precondition: the String is either an empty String or one that    
                    has the format shown above
                    the (r,c) must be correct for this method to work 
   */
   public void markPath(String strPath)
   {
      if(strPath.equals(""))
         return;
      /*  enter your code  below  */   
   }
}


   /*************************************************************
 ----jGRASP exec: java MazeGrandMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.W...W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 W......W
 WWWWWWWW
 
 Options: 
 1: Length of the shortest path
 	If no path exists, say so.
 2: Length of the shortest path
 	List the shortest path
 	Display the shortest path
 	If no path exists, say so.
 Please make a selection: 1
 Shortest path is 10
 WWWWWWWW
 W....W.W
 WW.W...W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 W......W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.

 ******************************************************************/
 /**************************************************************
      ----jGRASP exec: java MazeGrandMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.W...W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 W......W
 WWWWWWWW
 
 Options: 
 1: Length of the shortest path
 	If no path exists, say so.
 2: Length of the shortest path
 	List the shortest path
 	Display the shortest path
 	If no path exists, say so.
 Please make a selection: 2
 Shortest length path is: 10
 Shortest path is: ((5,0),10),((5,1),9),((6,1),8),((6,2),7),((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
 WWWWWWWW
 W....W.W
 WW.W...W
 W....W.W
 W.W.WW*E
 S*W.WW*W
 W******W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
  
  ******************************************/