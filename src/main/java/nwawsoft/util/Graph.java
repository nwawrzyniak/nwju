package nwawsoft.util;

/**
 * Graph data structure. A Graph can contain none to infinitely many GraphNode objects with or without edges between
 * them, which again can be either weighted ur unweighted.
 */
public class Graph {
    private List nodeList;

    /**
     * Creates a Graph object with no GraphNode objects.
     */
    public Graph() {
        nodeList = new List();
    }

    /**
     * Returns true if the Graph contains no GraphNode objects. Else false.
     *
     * @return true if the Graph contains no GraphNode objects. Else false.
     */
    public boolean isEmpty() {
        return nodeList.isEmpty();
    }

    /**
     * Adds the specified GraphNode object to the Graph.
     * If it is null or a GraphNode object with the same name exists in the Graph already it is not added.
     *
     * @param graphNode the GraphNode object to add to the Graph.
     */
    public void addNode(final GraphNode graphNode) {
        if (graphNode != null && !this.contains(graphNode.getName())) {
            nodeList.append(graphNode);
        } else {
            DebugPrinter.dp(this, "GraphNode not added. GraphNode was either null or a GraphNode with the specified " +
                    "name is already in the Graph.");
        }
    }

    /**
     * Returns whether a GraphNode object with the specified name is in the Graph.
     *
     * @param nodeName the name of a GraphNode object.
     * @return true if a GraphNode object with the specified name is in the Graph, else false.
     */
    public boolean contains(final String nodeName) {
        GraphNode currentNode;
        nodeList.toFirst();
        while (nodeList.hasAccess()) {
            currentNode = (GraphNode) nodeList.getObject();
            if (currentNode.getName().equals(nodeName)) {
                return true;
            } else {
                nodeList.next();
            }
        }
        return false;
    }

    /**
     * Returns whether a specified GraphNode object is in the Graph.
     *
     * @param graphNode a GraphNode object.
     * @return true if the GraphNode is in the Graph, else false.
     */
    public boolean contains(final GraphNode graphNode) {
        nodeList.toFirst();
        while (nodeList.hasAccess()) {
            if ((nodeList.getObject()).equals(graphNode)) {
                return true;
            } else {
                nodeList.next();
            }
        }
        return false;
    }

    /**
     * Returns the GraphNode object with the specified name.
     * Returns null if there is no such GraphNode in the Graph.
     *
     * @param nodeName the name of a GraphNode object.
     * @return the GraphNode object with the specified name or null if not on the Graph.
     */
    public GraphNode getNode(final String nodeName) {
        GraphNode specifiedNode = null;
        GraphNode currentNode;
        nodeList.toFirst();
        boolean found = false;
        while (nodeList.hasAccess() && !found) {
            currentNode = (GraphNode) nodeList.getObject();
            if (currentNode.getName().equals(nodeName)) {
                specifiedNode = currentNode;
                found = true;
            } else {
                nodeList.next();
            }
        }
        if (specifiedNode == null) {
            DebugPrinter.dp(this, "GraphNode not found in Graph. Returning null.");
        }
        return specifiedNode;
    }

    /**
     * If graphNode != null and graphNode is in the Graph it and all its edges are removed from the Graph.
     * If graphNode == null or not in the Graph nothing happens.
     *
     * @param graphNode the graphNode object to remove from the Graph.
     */
    public void removeNode(final GraphNode graphNode) {
        if (graphNode != null) {
            List neighbors;
            GraphNode currentNode;
            nodeList.toFirst();
            while (nodeList.hasAccess()) {
                currentNode = (GraphNode) nodeList.getObject();
                if (currentNode.getName().equals(graphNode.getName())) {
                    neighbors = this.getNeighbors(currentNode);
                    neighbors.toFirst();
                    while (neighbors.hasAccess()) {
                        GraphNode currentNeighbor = (GraphNode) neighbors.getObject();
                        this.removeEdge(currentNode, currentNeighbor);
                        neighbors.next();
                    }
                    nodeList.remove();
                }
                nodeList.next();
            }
        }
    }

    /**
     * Adds an edge from graphNode1 to graphNode2 with the specified weight.
     * If an edge from graphNode1 to graphNode2 already exists only its weight is set to the new weight.
     * If any specified GraphNode object is null or not in the Graph, nothing changes.
     *
     * @param graphNode1 the GraphNode object to create an edge to graphNode2 from.
     * @param graphNode2 the GraphNode object to create an edge from graphNode 1 to.
     * @param weight the weight of the new edge.
     */
    public void addEdge(final GraphNode graphNode1, final GraphNode graphNode2, final double weight) {
        if (graphNode1 != null && graphNode2 != null) {
            if (this.hasEdge(graphNode1, graphNode2)) {
                this.removeEdge(graphNode1, graphNode2);
            }
            graphNode1.addEdge(graphNode2, weight);
        }
    }

    /**
     * Adds an edge from graphNode1 to graphNode2 and from graphNode1 to graphNode2, both with the specified weight.
     * If edges between graphNode1 and graphNode2 already exist only their weight is set to the new weight.
     * If any specified GraphNode object is null or not in the Graph, nothing changes.
     *
     * @param graphNode1 the GraphNode object to create an edge to and from graphNode2.
     * @param graphNode2 the GraphNode object to create an edge to and from graphNode1.
     * @param weight the weight of the new edges.
     */
    public void addBidirectionalEdge(final GraphNode graphNode1, final GraphNode graphNode2, final double weight) {
        if (graphNode1 != null && graphNode2 != null) {
            if (this.hasEdge(graphNode1, graphNode2)) {
                this.removeEdge(graphNode1, graphNode2);
                this.removeEdge(graphNode2, graphNode1);
            }
            graphNode1.addEdge(graphNode2, weight);
            graphNode2.addEdge(graphNode1, weight);
        }
    }

    /**
     * Returns whether there is an edge between the specified GraphNode objects in the Graph.
     * If any GraphNode object is null or not in the Graph the result is always false.
     *
     * @param graphNode1 a GraphNode object.
     * @param graphNode2 another GraphNode object.
     * @return true if edge exists, else false.
     */
    public boolean hasEdge(final GraphNode graphNode1, final GraphNode graphNode2) {
        List neighbors;
        GraphNode neighbor;
        if ((graphNode1 != null) && (graphNode2 != null)) {
            neighbors = graphNode1.getNeighbors();
            if (!neighbors.isEmpty()) {
                neighbors.toFirst();
                while (neighbors.hasAccess()) {
                    neighbor = (GraphNode) neighbors.getObject();
                    if (neighbor.getName().equals(graphNode2.getName())) {
                        return true;
                    } else {
                        neighbors.next();
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes the edge from graphNode1 to graphNode2 if there was any.
     *
     * @param graphNode1 a GraphNode object in the Graph.
     * @param graphNode2 another GraphNode object in the Graph.
     */
    public void removeEdge(final GraphNode graphNode1, final GraphNode graphNode2) {
        if (graphNode1 != null && graphNode2 != null && this.hasEdge(graphNode1, graphNode2)) {
            graphNode1.removeEdge(graphNode2);
        } else {
            DebugPrinter.dp(this, "Either " + graphNode1 + " or " + graphNode2 + " is null or there was no edge " +
                    "between them. Skipping.");
        }
    }

    /**
     * Removes both edges between graphNode1 and graphNode2 if there were any.
     *
     * @param graphNode1 a GraphNode object in the Graph.
     * @param graphNode2 another GraphNode object in the Graph.
     */
    public void removeEdges(final GraphNode graphNode1, final GraphNode graphNode2) {
        boolean found = false;
        if (graphNode1 != null && graphNode2 != null) {
            if (this.hasEdge(graphNode1, graphNode2)) {
                graphNode1.removeEdge(graphNode2);
                found = true;
            }
            if (this.hasEdge(graphNode2, graphNode1)) {
                graphNode2.removeEdge(graphNode1);
                found = true;
            }
            if (!found) {
                DebugPrinter.dp(this, "No edges found between " + graphNode1 + " and " + graphNode2 + ". Skipping.");
            }
        } else {
            DebugPrinter.dp(this, "Either " + graphNode1 + " or " + graphNode2 + " is null. Skipping.");
        }
    }

    /**
     * Returns the weight of the edge from graphNode1 to graphNode2.
     *
     * @param graphNode1 a GraphNode object.
     * @param graphNode2 another GraphNode object.
     * @return the weight of the edge or Double.NaN if there is no such edge.
     */
    public double getEdgeWeight(final GraphNode graphNode1, final GraphNode graphNode2) {
        return graphNode1.getEdgeWeight(graphNode2);
    }

    /**
     * Sets the 'marked' boolean to false for all GraphNode objects in the Graph.
     */
    public void removeMarks() {
        if (!nodeList.isEmpty()) {
            nodeList.toFirst();
            while (nodeList.hasAccess()) {
                ((GraphNode) nodeList.getObject()).removeMark();
                nodeList.next();
            }
        }
    }

    /**
     * Returns whether all GraphNode objects in the Graph are marked.
     *
     * @return true if all GraphNode objects in the Graph are marked, else false. Also true if Graph is empty.
     */
    public boolean allNodesMarked() {
        boolean foundUnmarked = false;
        if (!nodeList.isEmpty()) {
            nodeList.toFirst();
            while (nodeList.hasAccess() && !foundUnmarked) {
                if (!((GraphNode) nodeList.getObject()).isMarked()) {
                    foundUnmarked = true;
                } else {
                    nodeList.next();
                }
            }
        }
        return !foundUnmarked;
    }

    /**
     * Returns a new List object containing all GraphNode objects in the Graph.
     *
     * @return a new List object containing all GraphNode objects in the Graph.
     */
    public List getNodes() {
        List nodeList = new List();
        this.nodeList.toFirst();
        while (this.nodeList.hasAccess()) {
            nodeList.append(this.nodeList.getObject());
            this.nodeList.next();
        }
        return nodeList;
    }

    /**
     * Returns a new List object containing all GraphNode objects that are neighbors of the specified GraphNode object.
     *
     * @param graphNode a GraphNode object which the Graph contains.
     * @return a new List object containing all GraphNode objects that are neighbors of the specified GraphNode object.
     */
    public List getNeighbors(final GraphNode graphNode) {
        return graphNode.getNeighbors();
    }

    /**
     * Returns the sum of all edgeWeights in the Graph.
     *
     * @return all edgeWeights in the Graph summed up.
     */
    public double edgeWeightSum() {
        double combinedWeight = 0;
        List allNodes = getNodes();
        allNodes.toFirst();
        while (allNodes.hasAccess()) {
            combinedWeight = combinedWeight + cumulativeEdgeWeight((GraphNode) allNodes.getObject());
            allNodes.next();
        }
        return combinedWeight;
    }

    /**
     * Returns the summed up edgeWeights of all edges of the specified GraphNode object.
     *
     * @param graphNode a GraphNode object.
     * @return the sum of all edgeWeights of the GraphNode (and his neighbors).
     */
    public double cumulativeEdgeWeight(final GraphNode graphNode) {
        double nodeWeight = 0;
        List neighbors = graphNode.getNeighbors();
        neighbors.toFirst();
        while (neighbors.hasAccess()) {
            nodeWeight = nodeWeight + getEdgeWeight(graphNode, (GraphNode) neighbors.getObject());
            neighbors.next();
        }
        return nodeWeight;
    }

    /**
     * Returns the GraphNode object with the highest summed up edgeWeights in the Graph.
     *
     * @return the GraphNode with the highest summed up edgeWeights.
     */
    public GraphNode highestWeight() {
        GraphNode heaviestNode;
        List nodes = this.getNodes();
        nodes.toFirst();
        heaviestNode = (GraphNode) nodes.getObject();
        while (nodes.hasAccess()) {
            if (cumulativeEdgeWeight(heaviestNode) < cumulativeEdgeWeight((GraphNode) nodes.getObject())) {
                heaviestNode = (GraphNode) nodes.getObject();
            }
            nodes.next();
        }
        return heaviestNode;
    }
}
