package nz.ac.auckland.softeng281.a4;

import java.util.*;

/**
 * You cannot add new fields.
 */
public class Graph {

	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	private HashMap<Node, EdgesLinkedList> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	private Node root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */
	public Graph(List<String> edges, List<String> weights) {
		if (edges.isEmpty() || weights.isEmpty()) {
			throw new IllegalArgumentException("edges and weights are empty");
		}
		adjacencyMap = new HashMap<>();
		int i = 0;
		for (String edge : edges) {
			String[] split = edge.split(",");
			Node source = new Node(split[0]);
			Node target = new Node(split[1]);
			Edge edgeObject = new Edge(source, target, Integer.parseInt(weights.get(i)));
			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new EdgesLinkedList());
			}
			adjacencyMap.get(source).append(edgeObject);
			if (i == 0) {
				root = source;
			}
			i++;
		}
	}

	/**
	 * find a particular node, note that a node might not have outgoing edges but
	 * only ingoing edges so you need to check also the target nodes of the edges
	 *
	 * @param node
	 * @return true if adjacencyMap contains the node, false otherwise.
	 */
	public boolean isNodeInGraph(Node node) {
		// quick check to see if node is a key
		if (adjacencyMap.containsKey(node)) {
			return true;
		}
		// if not a key then checks if it is a target
		for (Node key : adjacencyMap.keySet()) {
			EdgesLinkedList edgeListObject = adjacencyMap.get(key);
			int i;
			for (i = 0; i < edgeListObject.size(); i++) {
				Edge edgeObject = edgeListObject.get(i);
				if (edgeObject.getTarget().equals(node)) {
					return true;
				}
			}
		}
		// if nothing matches it returns false
		return false;
	}

	/**
	 * This method finds an edge with a specific weight, if there are more than one
	 * you need to return the first you encounter. You must use Breath First Search
	 * (BFS) strategy starting from the root.
	 * <p>
	 * You can create a data structure to keep track of the visited nodes Set<Node>
	 * visited = new HashSet<>(); If you don't keep track of the visited nodes the
	 * method will run forever!
	 * <p>
	 * <p>
	 * In addition to the data structure visited you can only create new
	 * datastructures of type EdgesLinkedList and NodesStackAndQue
	 *
	 * @param weight
	 * @return the Edge with the specific weight, null if no edge with the specific
	 *         weight exists in the graph
	 */
	public Edge searchEdgeByWeight(int weight) {
		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		Set<Node> visited = new HashSet<>();
		NodesStackAndQueue queue = new NodesStackAndQueue();
		// add node to start the BFS choose first edge
		Node currentNode = root;
		queue.append(currentNode);

		// search for edges using a queue a BFS search
		while (0 != queue.size()) {
			// get first node in queue and check if there is such a weight between all the
			// nodes it points to in the graph
			currentNode = queue.peekBack();
			if (adjacencyMap.containsKey(currentNode)) {
				EdgesLinkedList edgeListObject = adjacencyMap.get(currentNode);
				int i;
				for (i = 0; i < edgeListObject.size(); i++) {
					Edge edgeCurrent = edgeListObject.get(i);
					if (edgeCurrent.getWeight() == weight) {
						// returns first instance weight found
						return edgeCurrent;
					}
					Node target = edgeCurrent.getTarget();
					if (!(visited.contains(target))) {
						queue.push(target);
						visited.add(target);

					}
				}
			}
			// remove node when completed iteration
			queue.dequeue();

		}

		// output if there is no such Edge
		return null;

	}

	/**
	 * Returns the weight of the Edge with Node source and Node target if the given
	 * Edge is inside the graph. If there is no edge with the specified source and
	 * target, the method returns -1 You must use Depth First Search (DFS) strategy
	 * starting from the root.
	 * <p>
	 * RULES You can create a data structure to keep track of the visited nodes
	 * Set<Node> visited = new HashSet<>(); If you don't keep track of the visited
	 * nodes the method will run forever!
	 * <p>
	 * In addition to the data structure visited you can only create new data
	 * structures of type
	 * <p>
	 * NodesStackAndQueue and EdgesLinkedList
	 *
	 * @param source
	 * @param target
	 * @return the weight of the first encountered edge with source and target, -1
	 *         if no edge with the given source and target exists
	 */
	public int searchWeightByEdge(Node source, Node target) {

		Set<Node> visited = new HashSet<>();
		NodesStackAndQueue stack = new NodesStackAndQueue();
		// add node to start the BFS choose first edge
		// use a stack for DFS
		Node currentNode = root;
		stack.push(currentNode);
		// visited set to check if this edge has been checked
		visited.add(root);

		while (0 != stack.size()) {
			currentNode = stack.pop();
			if (adjacencyMap.containsKey(currentNode)) {
				// check all keys ie outward edge of each key
				EdgesLinkedList edgeListObject = adjacencyMap.get(currentNode);
				int i;
				for (i = 0; i < edgeListObject.size(); i++) {
					Edge edgeCurrent = edgeListObject.get(i);
					// if 2 nodes have a path return the weight
					if (edgeCurrent.getSource().equals(source) && edgeCurrent.getTarget().equals(target)) {

						return edgeCurrent.getWeight();
					}
					//
					Node edgeTarget = edgeCurrent.getTarget();
					// add node to stack if not visited
					if (!(visited.contains(edgeTarget))) {
						stack.push(edgeTarget);
						visited.add(edgeTarget);
					}
				}
			}

		}
		return -1;

	}

	/**
	 * Given a source Node and a target Node it returns the shortest path between
	 * source and target
	 * <p>
	 * A Path is represented as an ordered sequence of nodes, together with the
	 * total weight of the path. (see Path.java class)
	 *
	 * @param source
	 * @param target
	 * @return the shortest path between source and target
	 */
	public Path computeShortestPath(Node source, Node target) {
		if (!(this.isNodeInGraph(source) && this.isNodeInGraph(target))) {
			throw new java.lang.UnsupportedOperationException("Invalid Nodes Selected");
		}

		Set<Node> inGraph = new HashSet<>();
		ArrayList<String> visited = new ArrayList<>();

		// first Get all nodes of the graph and add it to set unique nodes
		inGraph = this.getNodes();
		// edges linked list gives neighbors of all nodes

		// we sort the list of nodes so we have corresponding index positions
		ArrayList<String> sortedNodes = new ArrayList<>();
		for (Node x : inGraph) {
			sortedNodes.add(x.getValue());
		}
		Collections.sort(sortedNodes);

		// we work with String arrays so we can easily manipulate values and store them

		String sourceString = source.getValue();
		int dist_size = sortedNodes.size();

		// Initialize array lists to store previous and distance arrays
		ArrayList<Integer> distance = new ArrayList<Integer>(dist_size);
		ArrayList<String> previous = new ArrayList<String>(dist_size);

		// predefine array values
		for (int i = 0; i < dist_size; i++) {
			distance.add(Integer.MAX_VALUE);
			previous.add(null);
		}

		visited.add(source.getValue());

		// create an array identical to sorted nodes to remove as we resolve the node
		ArrayList<String> nodesToRemove = new ArrayList<>();
		for (String y : sortedNodes) {
			nodesToRemove.add(y);
		}

		// get index of sourceNode and remove it
		int indexSource = sortedNodes.indexOf(sourceString);
		nodesToRemove.remove(indexSource);
		distance.set(indexSource, 0);

		// loop to get the shortest path while there is nodes that exist in the
		// nodesToRemove array
		while (nodesToRemove.size() != 0) {

			Node sourceNode = new Node(visited.get(visited.size() - 1)); // update sourceNode to last node in visited
																			// array
			// forwardVert is the forward vertex ie, the vertex that needs to be resolved
			// next
			Node forwardVert = minDistance(sortedNodes, visited, distance, previous, sourceNode);
			visited.add(forwardVert.getValue());
			// get a lead and source vertex source is what we resolve

			// remove lead vertex if not already in visited
			if (!(sourceNode.equals(forwardVert))) {

				int removePosition = nodesToRemove.indexOf(forwardVert.getValue());
				nodesToRemove.remove(removePosition);
			}

			// loops until source and target not equal or all nodes have been resolved
			while (sourceNode.equals(forwardVert) && nodesToRemove.size() != 0) {
				// need to check if last two are the same because if this loops twice then we do
				// not remove
				// from the visited list because there will only be one instance of the node
				// value
				if (visited.get(visited.size() - 2).equals(visited.get(visited.size() - 1))) {
					visited.remove(visited.size() - 1);
				}

				// similar concept getMinDistance except nodes are not being resolved
				// only find if there is a shortest path and update
				int equalsDistance = Integer.MAX_VALUE;
				for (int i = 0; i < sortedNodes.size(); i++) {
					if (!(visited.contains(sortedNodes.get(i)))) {
						if (distance.get(i) < equalsDistance) {
							sourceNode = new Node(sortedNodes.get(i));
							equalsDistance = distance.get(i);
						}
					}
				}
				// update accordingly
				forwardVert = minDistance(sortedNodes, visited, distance, previous, sourceNode);
				visited.add(forwardVert.getValue());
				int removePosition = nodesToRemove.indexOf(forwardVert.getValue());
				nodesToRemove.remove(removePosition);
			}

			// resolve neighboring nodes method to set value of all neighboring nodes for
			// current source node
			EdgesLinkedList edgeListObject = adjacencyMap.get(sourceNode);
			if (edgeListObject != null) {
				for (int i = 0; i < edgeListObject.size(); i++) {
					Edge edgeObject = edgeListObject.get(i);
					// compare nodes distance and update accordingly
					int edgeDistance = 0;
					String prev = sourceNode.getValue();
					Node testNode = new Node(edgeObject.getTarget().getValue());
					Node prevNode = new Node(prev);
					while (prev != null) {
						edgeDistance += searchWeightByEdge(prevNode, testNode);
						testNode = new Node(prevNode.getValue());
						prevNode = getPrevious(previous, prevNode, sortedNodes);
						if (prevNode == null) {
							prev = null;
						}
					}
					// find index position of target and update if necessary
					int posTarget = sortedNodes.indexOf(edgeObject.getTarget().getValue());
					if (edgeDistance != -1 && edgeDistance < distance.get(posTarget)) {
						distance.set(posTarget, edgeDistance);
						previous.set(posTarget, sourceNode.getValue());
					}
				}

			}
		}

		// OUTPUT

		// have to output new shortest node using path class

		ArrayList<Node> returnNodes = new ArrayList<Node>();
		// get array of nodes in backwards order
		int postion = sortedNodes.indexOf(target.getValue());
		String prev = previous.get(postion);
		Node testNode = new Node(target.getValue());
		Node prevNode = new Node(prev);
		returnNodes.add(target);
		while (prev != null) {
			testNode = new Node(prevNode.getValue());
			prevNode = getPrevious(previous, prevNode, sortedNodes);
			if (prevNode == null) {
				prev = null;
			}
			returnNodes.add(testNode);
		}

		// reverse order and we got the nodes from target to source

		ArrayList<Node> returnNodesOrdered = new ArrayList<Node>();
		int j = 0;
		for (int i = returnNodes.size() - 1; i >= 0; i--) {
			returnNodesOrdered.add(j, returnNodes.get(i));
			j++;
		}

		// OUTPUT
		return new Path(distance.get(postion), returnNodesOrdered);

	}

	/**
	 * this method returns the nodes of the graph object as a set
	 * 
	 * @return nodes
	 **/
	private Set<Node> getNodes() {

		// returns all nodes of a given graph

		Set<Node> nodes = new HashSet<>();

		// inside loop checks for all nodes and targets in the graph, then it adds to
		// the set nodes to return if not added
		for (Node key : adjacencyMap.keySet()) {
			EdgesLinkedList edgeListObject = adjacencyMap.get(key);
			int i;
			for (i = 0; i < edgeListObject.size(); i++) {
				Edge edgeObject = edgeListObject.get(i);
				if (!nodes.contains(edgeObject.getSource())) {
					nodes.add(edgeObject.getSource());
				}
				if (!nodes.contains(edgeObject.getTarget())) {
					nodes.add(edgeObject.getTarget());
				}
			}
		}
		return nodes;

	}

	/**
	 * method to return the minimum distance from sourceNode to the node that is the
	 * shortest distance away in the path.
	 * 
	 * @param sortedNodes
	 * @param hasVisited
	 * @param distance
	 * @param previous
	 * @param sourceNode
	 * @return closest node in the graph from source node
	 **/
	private Node minDistance(ArrayList<String> sortedNodes, ArrayList<String> hasVisited, ArrayList<Integer> distance,
			ArrayList<String> previous, Node sourceNode) {

		// initalise starting values
		int minDistance = Integer.MAX_VALUE;
		int nodePos = -1;
		// makes min distance vertex the source in case of no outgoing edges
		Node minDistanceVertex = sourceNode;
		// get index of source
		int sourceIndex = sortedNodes.indexOf(sourceNode.getValue());

		// loops for all nodes in graph
		for (int i = 0; i < sortedNodes.size(); i++) {
			Node currentNode = new Node(sortedNodes.get(i));
			// check if node has been resolved
			if (!(hasVisited.contains(sortedNodes.get(i)))) {
				int edgeDistance = searchWeightByEdge(sourceNode, currentNode);
				if (edgeDistance != -1) {
					// get previous node and distance values of it
					if (previous.get(sourceIndex) != null) {
						String prev = previous.get(sourceIndex);
						Node testNode = new Node(sourceNode.getValue());
						Node prevNode = getPrevious(previous, sourceNode, sortedNodes);
						while (prev != null) {
							edgeDistance += searchWeightByEdge(prevNode, testNode);
							testNode = new Node(prevNode.getValue());
							prevNode = getPrevious(previous, prevNode, sortedNodes);
							if (prevNode == null) {
								prev = null;
							}
						}
					}
					// update value if needed
					if (edgeDistance < minDistance && edgeDistance < distance.get(i)) {
						minDistance = edgeDistance;
						minDistanceVertex = currentNode;
						nodePos = i;
					}

				}
			}
		}
		// returns the value with the smallest distance and updates accordingly
		if (nodePos != -1) {
			previous.set(nodePos, sourceNode.getValue());
			distance.set(nodePos, minDistance);
		}
		return minDistanceVertex;
	}

	private Node getPrevious(ArrayList<String> previous, Node currentNode, ArrayList<String> nodeList) {
		// method to the get previous node in the algorithm for the graph returns null
		// if there is no prevous node
		String currentString = currentNode.getValue();
		int nodeListPosition = nodeList.indexOf(currentString);
		// get a string value of the previous node
		String returnString = previous.get(nodeListPosition);
		if (returnString != null) {
			Node returnNode = new Node(previous.get(nodeListPosition));
			return returnNode;
		}
		return null;

	}

}
