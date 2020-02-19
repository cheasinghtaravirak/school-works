
/**
 * class MyGraph. Will use Matrix to represent a simple weighted directed graph. There is no loop at vertex.
 * No more than one edge from vertex i to another vertex j. The vertices are numbered as 1, 2, ..., n
 * The graph with n vertices is reprented by an (n+1) by (n+1) matrix with row 0 and column 0 unused.
 * If there is an edge from vertex i to vertex j (i != j), then entry on row i column j of the matrix will 
 * be 1. If there is no edge between vertex i to vertex j (i != j), then the entry on row i column j of the 
 * matrix will be Integer.MAX_VALUE
 * 
 * @author Singhtaravirak Chea 
 * @version April 28, 2019
 */
import java.util.*;
public class MyGraph
{
    private int[][] graph;
    private int numberOfVertices;
    
    /**
     * create a graph with given number of vertices with no edges
     * @param numberOfVertices number of vertices of the graph
     */
    public MyGraph(int numberOfVertices){
        this.numberOfVertices = numberOfVertices;
        graph = new int[numberOfVertices+1][numberOfVertices+1];
    }
    
    /**
     * create a graph with given matrix representation
     * @param graph The matrix representation on the given graph. Assume column 0 and row 0 are not used
     */
    public MyGraph(int [][] graph){
        this.graph = graph;
        // change any 0 to infinity if the 0 is not on diagonal
        for(int i = 1; i < graph.length; i++){
            for(int j = 1; j < graph.length; j++){
                if(i == j) graph[i][j] = 0;
                else if(i != j && graph[i][j] == 0)
                    graph[i][j] = Integer.MAX_VALUE;
            }
        }
        numberOfVertices = graph.length - 1; 
    }
    
    /**
     * return a String that represent the vertices in order if the BFS algorithm is used to traversal the graph
     * starting from the given vertex. If the startVertex not exists, return an error message
     * @param startVertex The vertex where the traversal starts
     * @return A String that describes the vertices visited in order
     */
    public String bfs(int startVertex) {
        // student implement this    	
    	//check for invalid input
    	if ((startVertex <= 0) || (startVertex > numberOfVertices)) 
    		return "Invalid input";
    	else {
    		
    		String result = "";
    		//Use queue to implement BFS 
    		Queue<Integer> queue = new LinkedList();
    		//initialize a visited set of vertices 
            boolean[] visited = new boolean[numberOfVertices + 1];
            //startVertex is first visited 
            visited[startVertex] = true;
            //push startVertex to queue
            queue.add(Integer.valueOf(startVertex));
            
            while (!queue.isEmpty())
            {
              //set currentV to pop queue
		      int currentV = ((Integer)queue.remove()).intValue();
		      result = result + currentV + ", ";
		      //for each vertex adjacent to currentV
              for(int i = 1; i <= numberOfVertices;  i++) {
            	  if ((graph[currentV][i] >= 1) && (graph[currentV][i] < Integer.MAX_VALUE) && visited[i] == false)
                  {
		        	//Push adjacent vertice to queue
		        	queue.add(Integer.valueOf(i));
		            visited[i] = true;
                  }
              }
            }
            //delete the last coming comma and return result 
            return result.substring(0, result.length()-2);
    	}
        
    	
    }
    
    /**
     * return a String that represents the vertices in order if the DFS algorithm is used to traversal the graph
     * starting from the given vertex. If the startVertex not exist, return an error message
     * @param startVertex The vertex where the traversal starts
     * @return An ArrayList of Integer that represents the vertices visited in order
     */
    public String dfs(int startVertex){
        // the student implement this
    	if ((startVertex <= 0) || (startVertex > numberOfVertices)) 
    		return "Invalid input";
    	else {
            String result = "";
            //Initialize a set of visited vertices 
            boolean[] visited = new boolean[numberOfVertices + 1];
            //set currentV to startVertex 
            int currentV = startVertex;
            int i = startVertex;
            result = result + currentV + ", ";
            //A start vertex is first visited 
            visited[startVertex] = true;
            //Use stack to implement DFS
            Stack<Integer> stack = new Stack();
            //Push startVertex to stack
            stack.push(Integer.valueOf(startVertex));
            
            while (!stack.isEmpty()) {
            	//currentV = pop or peek stack
            	currentV = ((Integer)stack.peek()).intValue();
                i = 1;
                while (i <= numberOfVertices)
                  if ((graph[currentV][i] >= 1) && (graph[currentV][i] < Integer.MAX_VALUE) && (visited[i] == false)) {
                	//Push adjacent vertex to stack
                    stack.push(Integer.valueOf(i));
                    //Add currentV to visited set
                    visited[i] = true;
                    currentV = i;
                    i = 1;
                    result = result + currentV + ", ";
                  }
                  else {
                    i++;
                  }
                stack.pop();
              }
            
            //delete the last coming comma and return result 
            return result.substring(0, result.length()-2);
    	}
    }
}









