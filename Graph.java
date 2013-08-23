import java.util.ArrayList;

/**
 * This program creates a Graph data structure
 * for any program to utilize
 * 
 *  @author  Monique Blake
 *  @version CSC 212, 20 April 2011
 */

public class Graph<V,E> {

  /** Master ArrayList of nodes */
  ArrayList<Node> nodes;
  
  /** Master ArrayList of edges */
  ArrayList<Edge> edges;
  
  /** Constructor Initalizes with empty node and edge lists*/
  public Graph() {
    // for verticies / nodes
    nodes = new ArrayList<Node>();
    // for edges
    edges = new ArrayList<Edge>();
  }
  
  /** adds a node to the nodes arraylist
    * @param data, the content to place in the node
    * @return the node created using the data passed in
    */
  public Node addNode(V data) {
     Node n = new Node(data);  
    // add to arraylist
    nodes.add(n);
    return n;
  }
    
  /* prints the contents of the arraylist */
  public void print() {
    for(Node n: nodes) {
      System.out.println("Nodes: "+n.getData());
    }
    for(Edge e: edges) {
      System.out.println("Edges: "+e.getData());
    }
  }
  
  
  /** adds an edge to the edges arraylist
    * @param data is the information stored for edge
    * @param head the node it links to the back
    * @param tail the node it links to the back
    * @returns e the edge created with data, head, tail
    */
  public Edge addEdge(E data, Node head, Node tail) {
    Edge e = new Edge(data, head, tail);
    edges.add(e);
    head.addEdge(e);
    tail.addEdge(e);
    return e;
  }
  
  /** accessor that gets the current node 
    * @param i index of the current spot
    * @returns the current node
    */
  public Node getNode(int i) {
    return nodes.get(i);
  }
  
  /** accessor that gets the current edge 
    * @param i index of the current spot
    * @return the current edge
    */
  public Edge getEdge(int i) {
    return edges.get(i);
  }
  
  /** accessor that gets the total number of nodes 
    * @return the total number of nodes
    */
  public int numNodes() {
    return nodes.size();
  }
  
  /** accessor that gets the total number of edges 
    * @return the total number of edges
    */
  public int numEdges() {
    return edges.size();
  }
  
  /** removes an node from the arraylist of nodes
    * @param node the node to be removed
    */ 
  public void removeNode(Node node) {
    nodes.remove(node);
  }
  
  /** removes an edge from the arraylist of edges
    * @param edge the node to be removed
    */ 
  public void removeEdge(Edge edge) {
    edges.remove(edge);
  }
  

  /** removes a edge from the head to the tail node 
    * from the arraylist of edges
    * @param head node connection
    * @param tail node connection
    */ 
  public void removeEdge(Node head, Node tail) {
    Edge edge = head.edgeTo(tail);
    removeEdge(edge);
  }
  
//  public boolean checkEdges(Edge e) {
    // method to check of edge is there, use .equals method?
    
//  }

  /**
   * Checks the consistency of the graph
   * it makes sure that every edge is connected to a node
   * and every node is connected to an edge
   * and both nodes and edges are inside of the main arraylist
   */
  public void check() {
    for (int i = 0; i<nodes.size(); i++ ) {
      for(int j = 0; j<edges.size(); j++) {
        if((edges.get(i).getHead() != null) && (edges.get(i).getTail() != null) ) {
          System.out.println("Check Works");
          System.out.println("Head: "+edges.get(i).getHead()+" Tail: "+edges.get(i).getTail());
        }
      }
    }
    for( Edge e: edges ) {
      // check if every edge associates with a node on the graph
      if(nodes.contains(e.getHead())) {
        System.out.println("Consistency check error: all edges associate with a node on graph");
      }
      // if node doesn't have reference back to edge
      if (e.getHead().nEdges.contains(e)) {
        System.out.println("Consistency check: node has reference back to edge");
      }
      for( Node n: nodes) {
        for(Edge ed: n.nEdges) {
          // if edge doesn't have reference back to node
          if( (e.getHead().equals(n)) || (e.getTail().equals(n)) ) {
            System.out.println("Consistency check: edge has refrence back to node");
          }
          if( edges.contains(e)) {
            // if one edge is not in edges arraylist (inside graph)
            System.out.println("Consistency check: edge has refrence back to masterlist");
          }
        }
      }

      // if one node is not in nodes arraylist (inside graph)
      
    }
    
  }

  /** 
   * The node class holds the node information
   * such as removal, insertion neighbors
   * the shared edge connected to neighboring nodes, etc
   */
  class Node {
    /** data of node */
    V data;
    
    /** node edges arraylist */
    ArrayList<Edge> nEdges = new ArrayList<Edge>();
    
    
    /** constructor for node
      * @param data the information passed in to the node
      */
    public Node(V data) {
      this.data = data;
    }
    
   /** Gets the edge that this current node and its neighbor shares 
     * @param neighbor the neighboring node
     * @return the edge that both nodes share
     */
    public Edge edgeTo(Node neighbor) {
      for( Edge e: nEdges) {
        if(e.oppositeTo(this) == neighbor) {
          //System.out.println("This is inside of edgeTo "+e.getData());
          return e;
        }
      }
      return null; 
    }
    
    /** adds an edge to the node's edge list 
      * @param e the edge to be added
      */
    private void addEdge(Edge e) {
      nEdges.add(e);
    }
    
    /** finds neighboring nodes 
      * @return ArrayList of neighboring nodes
      */
    public ArrayList<Node> getNeighbors() {
      ArrayList<Node> neighbor = new ArrayList<Node>();
      //for( int i = 0; i<nEdges.size(); i++) {
      for( Edge e: nEdges) {
        // get node on the other side of edge
        Node oppNode = nEdges.get(nEdges.indexOf(e)).oppositeTo(this);
        
        // place in list
        neighbor.add(oppNode);
      }
      return neighbor;
    }
    
    
    /** checks to see if the node is a neighboring node
      * @param node to be checked
      * @return true or false, false if not a neighbor
      */
    public boolean isNeighbor(Node node) {
      // two loops first loop checks to see if the first node 
      // is in that edge then check to see if the second node is in that edge, 
      // if it is return true if not keep going, if never true return false
      
      // call edgeTo if EdgeTo returns null
      if (this.edgeTo(node) == null) {
        //System.out.println("This is inside of isNeighbor if "+edgeTo(node).getData());
        return false;
      } else {
        //System.out.println("This is inside of isNeighbor "+edgeTo(node).getData());
        return true;
      }
    }
    
    
    /** Gets data contents from inside of the node */
    public V getData() {
      return data;
    }
    
    /** sets the data for the node 
      * @param data to be placed in
      */
    public void setData(V data) {
      this.data = data;
    }
    
  }
  
  /** This is the edge class
    * holds the basic operations to be performed
    * for/on an edge
    */
  class Edge {
    /** the head node edge points to */
    private Node head;
    
    /** the tail node edge points to */
    private Node tail;
    
    /** data inside of edge */
    private E data;
    
    /** edge constructor
      * @param data of edge
      * @param head node edge points to
      * @param tail node edge points to
      */
    public Edge(E data, Node head, Node tail) {
      this.head = head;
      this.tail = tail;
      this.data = data;
    }

    
    /** sees if the head and tail nodes connect to the same edge
      * @param edge to be checked
      * @return true or false, false if nodes do not connect to the same edge
      */
    public boolean equals(Edge edge) {
      // if edge1's start and endpoint is equal to edge2's start and end
      // if edge1's start compares to edge2's start
      if ( (this.getHead().equals(edge.getTail()) ) && (this.getTail().equals(edge.getHead()) && (this.getHead().equals(edge.getHead()) && (this.getTail().equals(edge.getTail()))))) {
        // return true
        return true;
      }
      // return false
      return false;
    }
    
    
    /** gets the data
      * @return data of edge
      */
     public E getData() {
      return data;
    }
     
     /** sets data of edge
       * @param the data of edge
       */
     public void setData(E data) {
       this.data = data;
     }
     
     
     /** gets the head node
       * @return the head node
       */
    public Node getHead() {
      return head;
    }
    
    
    /** gets the tail node
      * @return the tail node 
      */
    public Node getTail() {
      return tail;
    }
     
    /** calculates the hashcode value
      * @return the hashcode value
      */
    public int hashCode() {
        return getHead().hashCode() * getTail().hashCode();
    }
    
    
    /** gets the node that is opposite to the current node
      * @param the node to be checked
      */
    public Node oppositeTo(Node node) {
      if (node.equals(tail)) {
        return head; 
      } else {
        return tail; 
      }
    }
    
  }
  
}