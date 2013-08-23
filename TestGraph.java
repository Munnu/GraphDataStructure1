import java.util.ArrayList;

/**
 * This program tests the graph data structure
 * 
 *  @author  Monique Blake
 *  @version CSC 212, 20 April 2011
 */

public class TestGraph extends Graph{
  
  public static void main(String[] args) {
    /** instance of the graph class */
    Graph g = new Graph();
    
    /** initializes a new graph */
    Graph<String, String> animals = new Graph<String, String>();
    
   // Node n1 = new Node();
    //add some nodes
    Node n1 = animals.addNode("Zebra"); 
    Node n2 = animals.addNode("Lion"); 
    Node n3 = animals.addNode("Grizzly Bear");
    Node n4 = animals.addNode("Dog");
    Node n5 = animals.addNode("Cat");
    Node n6 = animals.addNode("Bunny");
    // add edges
    Edge e1 = animals.addEdge(" savanna ",n1,n2);
    Edge e2 =animals.addEdge(" tundra ",n3,n4);
    Edge e3 = animals.addEdge(" rain forest ",n2,n4);
    //Edge e4 =animals.addEdge(" arctic ",n3,n4);
    Edge e5 = animals.addEdge("House", n5,n6);
   animals.print();
   
   System.out.println(animals.getEdge(2));
   System.out.println("Number of nodes: "+animals.numNodes());
   System.out.println("Number of edges: "+animals.numEdges());
    
    animals.removeEdge(e2);
    
    System.out.println();
    System.out.println("The new list");
    animals.print();
    
    animals.removeNode(n4);
    
    System.out.println();
    System.out.println("The new list w/o a node");
    animals.print();  
    
    ///////////// Node class stuff
    //System.out.println(n1.edgeTo(n2)); works
   // System.out.println(e1);
    System.out.println("--------------GetNeighbors-----------");
    System.out.println(n1.getNeighbors());
    System.out.println(n2.getData()); 
    
    System.out.println("---------------isNEighbors--------------");
    System.out.println(n3.isNeighbor(n4));
    System.out.println(n1.isNeighbor(n4));
    System.out.println(n1.isNeighbor(n6)); // something wrong with neighbor
    
    ////////////////// Edge class stuff
    //System.out.println
    System.out.println(e1.getHead().getData());
    System.out.println(n1.getData());
    System.out.println(e1.getTail().getData());  
    System.out.println(n2.getData());
    
    System.out.println(n1.hashCode());

    System.out.println(e1.oppositeTo(n2).getData());
    
    ///////////////////////// check test
    System.out.println("----------------Check test-------------------");
    
  }
  
}