// Name:   
// Date:
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/**************** Graphs 3: EdgeList *****/
interface VertexInterface
{
   public String getName();
   public HashSet<Vertex> getAdjacencies();
   
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    because adjacencies is a HashSet, this method should operate in O(1)
   */
   public void addAdjacent(Vertex v);
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString(); 
 
} 
 
/*************************************************************/
class Vertex implements VertexInterface, Comparable<Vertex> //2 vertexes are equal if and only if they have the same name
{
   private final String name;
   private HashSet<Vertex> adjacencies;
  /* enter your code here  */
  
  public Vertex(String n) {
   adjacencies = new HashSet<Vertex>();
   name = n;
  }
  
  public String getName() {
   return name;
  }
  
  public HashSet<Vertex> getAdjacencies() {
   return adjacencies;
  }
  
  public void addAdjacent(Vertex v) {
   adjacencies.add(v);
  }
  
  public int compareTo(Vertex v) {
   return getName().compareTo(v.getName());
  }
  
  public String toString() {
  String ret = name + " [";
  for(Vertex v : adjacencies) {
   ret = ret + v.getName() + " ";
  }
  ret = ret.trim();
  ret = ret + "]";
  return ret;
  
  }
  
  public int hashCode() {
   return name.hashCode();
  }
  
  public boolean equals(Vertex v) {
   return name.equals(v.getName());
  }
  
  
}   

/*************************************************************/
interface AdjListInterface 
{
   public Set<Vertex> getVertices();
   public Vertex getVertex(String vName);
   public Map<String, Vertex> getVertexMap();  //this is just for codepost testing
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName);
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(log n)
   */
   public void addEdge(String source, String target); 
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(); 

}

  
/********************** Graphs 4: DFS and BFS *****/
interface DFS_BFS
{
   public String depthFirstSearch(String name);
   public String breadthFirstSearch(String name);
   /*   extra credit  */
  // public String depthFirstRecur(String name);
  // public List<Vertex> depthFirstRecurHelper(Vertex v, List<Vertex> reachable);
}

/****************** Graphs 5: Edgelist with Cities *****/
interface EdgeListWithCities
{
   public void readData(String cities, String edges) throws FileNotFoundException;
   public int edgeCount();
   public int vertexCount();
   public boolean isReachable(String source, String target);
   public boolean isStronglyConnected(); //return true if every vertex is reachable from every 
                                          //other vertex, otherwise false 
}


/*************  start the Adjacency-List graph  *********/
public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();
      
   /* constructor is not needed because of the instantiation above */
  
   /* enter your code here  */
   public Set<Vertex> getVertices() {
      HashSet<Vertex> set = new HashSet<>();
      for(String s : vertexMap.keySet()) {
         set.add(vertexMap.get(s));
      } 
      
      return set; 
   }
   public Vertex getVertex(String vName) {
      return vertexMap.get(vName);
   }
   public Map<String, Vertex> getVertexMap() {
      return vertexMap;
   } 
   public void addVertex(String vName) {
      if (!vertexMap.keySet().contains(vName))
         vertexMap.put(vName, new Vertex(vName));
   }
   public void addEdge(String source, String target){
      vertexMap.get(source).addAdjacent(vertexMap.get(target));
   }
   public String toString() {
      String ret = "";
      for(String s : vertexMap.keySet()) {
         ret += vertexMap.get(s).toString() + "\n";
      }
      return ret;
   }
   
   public String depthFirstSearch(String name) {
      Stack<Vertex> q = new Stack<Vertex>(); 
      ArrayList<Vertex> reachable = new ArrayList<>();
      Vertex v = getVertex(name); 
      q.push(v);
      while(!q.isEmpty())
      {
          Vertex currv = q.pop();
          if(!reachable.contains(currv))
          {
             reachable.add(currv);
             for(Vertex city : currv.getAdjacencies()) {
                q.push(city);
             }
          } 
      }
      String ret = "";
      for(Vertex r : reachable)
         ret += r.getName() + " ";
      return ret;

   }
   public String breadthFirstSearch(String name) {
      Queue<Vertex> q = new LinkedList<Vertex>(); 
      ArrayList<Vertex> reachable = new ArrayList<>();
      Vertex v = getVertex(name); 
      q.add(v);
      while(!q.isEmpty())
      {
          Vertex currv = q.remove();
          if(!reachable.contains(currv))
          {
             reachable.add(currv);
             for(Vertex city : currv.getAdjacencies()) {
                q.add(city);
             }
          } 
      }
      String ret = "";
      for(Vertex r : reachable)
         ret += r.getName() + " ";
      return ret;
   }
   
   public void readData(String cities, String edges) throws FileNotFoundException {
      File sources = new File(cities);
      Scanner sourceRead = new Scanner(sources);
      while(sourceRead.hasNextLine()) {
         addVertex(sourceRead.nextLine());
      }
      
      File borders = new File(edges);
      Scanner borderRead = new Scanner(borders);
      while(borderRead.hasNextLine()) {
         String[] pair = borderRead.nextLine().split(" ");
         addEdge(pair[0], pair[1]);
      }

   }
   public int edgeCount() {
      int edgeCount = 0;
      for (String name : getVertexMap().keySet() )
         edgeCount += getVertex(name).getAdjacencies().size();
      return edgeCount;
   }
   public int vertexCount() {
      int vertexCount = 0;
      for (String name : getVertexMap().keySet() ) {
         vertexCount++;
      }  
      return vertexCount;
   }
   public boolean isReachable(String source, String target) {
      String reachable = depthFirstSearch( source );
      return reachable.contains( target );
   }
   public boolean isStronglyConnected() {
      for(String source : getVertexMap().keySet())
        for(String target : getVertexMap().keySet())
           if(! isReachable(source, target) )
               return false;
      return true;
   } 

 
 
 
 
 
 
}


