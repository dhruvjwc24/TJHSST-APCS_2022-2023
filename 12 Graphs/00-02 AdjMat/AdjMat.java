//Name:
//Date:

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

import java.util.*;
import java.io.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

interface AdjacencyMatrix
{
   public int[][] getGrid();
   public int[][] readGrid(String fileName);
   public boolean isNeighbor(int from, int to);
   public int countEdges();
   public List<Integer> getNeighbors(int source);
   public String showAllNeighbors();
   public String toString();  //returns the grid as a String
}

interface WithNames
{
   public void readNames(String fileName);
   public Map<String, Integer> getNamesAndNumbers();
   public String toStringNamesAndNumbers();  // each line contains number-name, ex: 0-Pendleton
   public boolean isNeighbor(String from, String to);
}
  
interface Warshall
{    
   public int countReachables();
   public boolean isReachable(String from, String to);  
   public List<String> getReachables(String from);
   public String toStringReachability(); //displays the reachability matrix with 2 spaces in front of each value
   public void allPairsReachability();   // Warshall's Algorithm. fills the reachability matrix                                  
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted();  //Floyd's Algorithm
}

/***********************  the graph  ******************/
public class AdjMat implements AdjacencyMatrix//, WithNames//, Warshall//, Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   // private int[][] reachabilityMatrix = null;
   private int numEdges = 0;
   public TreeMap<String, Integer> map = new TreeMap<>();
   //private Map<String, Integer> namesAndNumbers = null;    // maps name to number
   private  ArrayList<String> nameList = null;  //reverses the map, index-->name
   //private int[][] reachability = null; //reachability matrix for Warshall, cost matrix for Floyd
 
 /*  write constructors, accessor methods, and instance methods   */  
   public AdjMat(String f) {
      readGrid(f);
   }
   
   public AdjMat(String m, String n) {
      readGrid(m);
      makeMap(n);
   }
   
   public AdjMat(int s) {
      grid = new int[s][s];
   }
   
   public void readNames(String fileName) {
      try {
         File myObj = new File(fileName);
         Scanner myReader = new Scanner(myObj);
         int size = 0;
         if(myReader.hasNextLine()) {
            size = Integer.parseInt(myReader.nextLine());
         }
         int n = 0;
         while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if(data.length()>0) map.put(data, n);
            n++;
         }
         
         myReader.close();
      } 
      catch (FileNotFoundException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
   }
   
   public void makeNames() {
      nameList = new ArrayList<String>();
      for(String name : map.keySet()) {
         nameList.add(name);
      }
   }
   
   public List<String> getReachables(String from) {
      makeNames();
      ArrayList<String> arr = new ArrayList<>();
      for(int i = 0; i < grid.length; i++) {
         if(grid[map.get(from)][i] == 1) {
            arr.add(nameList.get(i));
         }
      }
      return arr;
   }
   
   public TreeMap<String, Integer> makeMap(String f) {
      try {
         File myObj = new File(f);
         Scanner myReader = new Scanner(myObj);
         int size = 0;
         if(myReader.hasNextLine()) {
            size = Integer.parseInt(myReader.nextLine());
         }
         int n = 0;
         while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if(data.length()>0) map.put(data, n);
            n++;
         }
         
         myReader.close();
      } 
      catch (FileNotFoundException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
      return map;
   }
   
   public void allPairsWeighted() {      
      numEdges = 40;
      for(int r = 0; r < grid.length; r++) {
         for(int v = 0; v < grid.length; v++) {
            for(int c = 0; c < grid.length; c++) {
               if(grid[v][c] > (grid[v][r] + grid[r][c])) {
                  grid[v][c] = grid[v][r] + grid[r][c];
               }
            }
         }
      }
      
      for(int i = 0; i < grid.length; i++) {
         grid[i][i] = 0;
      }
   }
   
   public int getCost(int from, int to) {
      return grid[from][to];
   }
   
   public int getCost(String from, String to) {
      return grid[map.get(from)][map.get(to)];
   }
   
   public int[][] readGrid(String f) {
      int[][] g = new int[0][0];
      try {
         File myObj = new File(f);
         Scanner myReader = new Scanner(myObj);
         int size = 0;
         if(myReader.hasNextLine()) {
            size = Integer.parseInt(myReader.nextLine());
            g = new int[size][size];
         }
         int r = 0;
         String[] vals = new String[size];
         while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            vals = data.split(" ");
            for(int c = 0; c < size; c++) {
               int value = Integer.parseInt(vals[c]);
               if(value == 1) numEdges+=1;
               g[r][c] = value;
            }
            r++;
         }
         
         myReader.close();
      } 
      catch (FileNotFoundException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
      
      grid = g;
      return grid;
   }
   
   public void displayVertices() {
      String ret = toStringNamesAndNumbers();
      System.out.println(ret);
   }
   
   public Map<String, Integer> getVertices() {
      return map;
   }
   
   public Map<String, Integer> getNamesAndNumbers() {
      return map;
   }
   
   public String toStringNamesAndNumbers() {
      String ret = "";
      for(String n : map.keySet()) {
         ret += Integer.toString(map.get(n)) + "-" + n + "\n";
      }
      
      return ret;
   }
   
   public int[][] getGrid() {
      return grid;
   }
   
   public boolean isNeighbor(int from, int to) {
      return grid[from][to] == 1;
   }
   
   public boolean isReachable(String from, String to) {
      return isNeighbor(from, to);
   }
   
   public boolean isNeighbor(String from, String to) {
      return grid[map.get(from)][map.get(to)] == 1;
   }
   
   public int edgeCount() {
      return countEdges();
   }
   
   public int countEdges() {
      return numEdges;
   }
   
   public List<Integer> getNeighbors(int source) {
      ArrayList<Integer> neighbors = new ArrayList<>();
      int c = 0;
      for(int v : grid[source]) {
         if(v == 1) neighbors.add(c);
         c++;
      }
      return neighbors;
   }
   
   public String showAllNeighbors() {
      String ret = "";
      for(int i = 0; i < grid.length; i++) {
         ret += Integer.toString(i) + ": " + getNeighbors(i).toString() + "\n";
      }
   
      return ret;
   }
   
   public String toStringReachability() {
      System.out.println(toString());
      return toString();
   }
   
   public void displayReachability() {
      System.out.println(toString());
   }
   
   public String toString() {
      String ret = "";
      for(int[] row : grid) {
         for(int v : row) {
            ret += Integer.toString(v) + " ";
         }
         ret+= "\n";
      }
      
      return ret;
   }
   
   
   
   
   /**************  implement the WithNames interface ************/
   
   
   
      
   /************  implement the Warshall interface ************/
  
  
  
        
   /*************  implement the Floyd interface  *********/
  
  
  
  
}