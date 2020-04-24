package nwawsoft.util.datastructures;

import nwawsoft.util.tools.DebugPrinter;

/**
 * {@code Graph} data structure. A {@code Graph} can contain none to infinitely many {@code GraphNode} objects with or
 * without edges between them, which again can be either weighted ur unweighted.
 */
public class Graph {
    private final List nodeList;

    /**
     * Creates a {@code Graph} object with no {@code GraphNode} objects.
     */
    public Graph() {
        nodeList = new List();
    }

    /**
     * Returns true if the {@code Graph} contains no {@code GraphNode} objects. Else false.
     *
     * @return true if the {@code Graph} contains no {@code GraphNode} objects. Else false.
     */
    public boolean isEmpty() {
        return nodeList.isEmpty();
    }

    /**
     * Adds the specified {@code GraphNode} object to the {@code Graph}.
     * If it is null or a {@code GraphNode} object with the same name exists in the {@code Graph} already it is not
     * added.
     *
     * @param graphNode the {@code GraphNode} object to add to the {@code Graph}.
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
     * Returns whether a {@code GraphNode} object with the specified name is in the {@code Graph}.
     *
     * @param nodeName the name of a {@code GraphNode} object.
     * @return true if a {@code GraphNode} object with the specified name is in the {@code Graph}, else false.
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
     * Returns whether a specified {@code GraphNode} object is in the {@code Graph}.
     *
     * @param graphNode a {@code GraphNode} object.
     * @return true if the {@code GraphNode} is in the {@code Graph}, else false.
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
     * Returns the {@code GraphNode} object with the specified name.
     * Returns null if there is no such {@code GraphNode} in the {@code Graph}.
     *
     * @param nodeName the name of a {@code GraphNode} object.
     * @return the {@code GraphNode} object with the specified name or null if not on the {@code Graph}.
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
     * If graphNode != null and graphNode is in the {@code Graph}, it and all its edges are removed from the
     * {@code Graph}.
     * If graphNode == null or not in the {@code Graph} nothing happens.
     *
     * @param graphNode the graphNode object to remove from the {@code Graph}.
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
     * If any specified {@code GraphNode} object is null or not in the {@code Graph}, nothing changes.
     *
     * @param graphNode1 the {@code GraphNode} object to create an edge to graphNode2 from.
     * @param graphNode2 the {@code GraphNode} object to create an edge from graphNode 1 to.
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
     * If any specified {@code GraphNode} object is null or not in the {@code Graph}, nothing changes.
     *
     * @param graphNode1 the {@code GraphNode} object to create an edge to and from graphNode2.
     * @param graphNode2 the {@code GraphNode} object to create an edge to and from graphNode1.
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
     * Returns whether there is an edge between the specified {@code GraphNode} objects in the {@code Graph}.
     * If any {@code GraphNode} object is null or not in the {@code Graph} the result is always false.
     *
     * @param graphNode1 a {@code GraphNode} object.
     * @param graphNode2 another {@code GraphNode} object.
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
     * @param graphNode1 a {@code GraphNode} object in the {@code Graph}.
     * @param graphNode2 another {@code GraphNode} object in the {@code Graph}.
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
     * @param graphNode1 a {@code GraphNode} object in the {@code Graph}.
     * @param graphNode2 another {@code GraphNode} object in the {@code Graph}.
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
     * @param graphNode1 a {@code GraphNode} object.
     * @param graphNode2 another {@code GraphNode} object.
     * @return the weight of the edge or Double.NaN if there is no such edge.
     */
    public double getEdgeWeight(final GraphNode graphNode1, final GraphNode graphNode2) {
        return graphNode1.getEdgeWeight(graphNode2);
    }

    /**
     * Sets the 'marked' boolean to false for all {@code GraphNode} objects in the {@code Graph}.
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
     * Returns whether all {@code GraphNode} objects in the {@code Graph} are marked.
     *
     * @return true if all {@code GraphNode} objects in the {@code Graph} are marked, else false. Also true if
     * {@code Graph} is empty.
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
     * Returns a new {@code List} object containing all {@code GraphNode} objects in the {@code Graph}.
     *
     * @return a new {@code List} object containing all {@code GraphNode} objects in the {@code Graph}.
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
     * Returns a new {@code List} object containing all {@code GraphNode} objects that are neighbors of the specified
     * {@code GraphNode} object.
     *
     * @param graphNode a {@code GraphNode} object which the {@code Graph} contains.
     * @return a new {@code List} object containing all {@code GraphNode} objects that are neighbors of the specified
     * {@code GraphNode} object.
     */
    public List getNeighbors(final GraphNode graphNode) {
        return graphNode.getNeighbors();
    }

    /**
     * Returns the sum of all edgeWeights in the {@code Graph}.
     *
     * @return all edgeWeights in the {@code Graph} summed up.
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
     * Returns the summed up edgeWeights of all edges of the specified {@code GraphNode} object.
     *
     * @param graphNode a {@code GraphNode} object.
     * @return the sum of all edgeWeights of the {@code GraphNode} (and his neighbors).
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
     * Returns the {@code GraphNode} object with the highest summed up edgeWeights in the {@code Graph}.
     *
     * @return the {@code GraphNode} with the highest summed up edgeWeights.
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

    /**
     * Defines {@code GraphNode} objects as they are used in the {@code Graph} class. A {@code GraphNode} has a name, a
     * list of edges to other {@code GraphNode} objects including their respective edge weights (often referred to as
     * edgeWeights or edgeWeight) and are either marked or not marked (controlled by the boolean marked).
     */
    public static class GraphNode {
        private final String name;
        private final List edges;
        private boolean marked;

        /**
         * Creates a new {@code GraphNode} object with name nodeName. It is unmarked.
         *
         * @param nodeName the name of the new {@code GraphNode} object.
         */
        public GraphNode(final String nodeName) {
            name = nodeName;
            edges = new List();
            marked = false;
        }
        /**
         * Sets "marked" to true.
         */
        public void mark() {
            marked = true;
        }

        /**
         * Sets "marked" to false.
         */
        public void removeMark() {
            marked = false;
        }

        /**
         * Returns whether the {@code GraphNode} object's "marked" flag is set.
         *
         * @return true if marked, else false.
         */
        public boolean isMarked() {
            return marked;
        }

        /**
         * Returns the name of the {@code GraphNode} object.
         *
         * @return the name of the {@code GraphNode} object.
         */
        public String getName() {
            return name;
        }

        /**
         * Adds a new edge from the calling to the specified {@code GraphNode} objects with the specified weight to the
         * {@code List} of known edges of the {@code GraphNode}.
         *
         * @param graphNode the {@code GraphNode} object to create a new edge to.
         * @param weight the weight of the new edge from "this" to graphNode.
         */
        public void addEdge(final GraphNode graphNode, final double weight) {
            Edge edge = new Edge(graphNode, weight);
            edges.append(edge);
        }

        /**
         * Returns the edgeWeight of the calling and the specified {@code GraphNode} objects if there is any, otherwise
         * Double.NaN.
         *
         * @param graphNode the {@code GraphNode} object to find the weight to.
         * @return edgeWeight if any edge was found, otherwise Double.NaN.
         */
        public double getEdgeWeight(final GraphNode graphNode) {
            boolean found = false;
            Edge edge = null;
            edges.toFirst();
            while (!found && edges.hasAccess()) {
                edge = (Edge) edges.getObject();
                if (edge.getNeighbor() == graphNode) {
                    found = true;
                }
                edges.next();
            }
            if (!found) {
                return Double.NaN;
            } else {
                return edge.getWeight();
            }
        }

        /**
         * Removes any edges between the calling and the specified {@code GraphNode} objects if graphNode != null.
         * If there were none, nothing happens.
         *
         * @param graphNode the {@code GraphNode} object to remove the edge to.
         */
        public void removeEdge(final GraphNode graphNode) {
            if (graphNode != null) {
                Edge edge;
                edges.toFirst();
                while (edges.hasAccess()) {
                    edge = (Edge) edges.getObject();
                    if (edge.getNeighbor() == graphNode) {
                        edges.remove();
                    }
                    edges.next();
                }
            }
        }

        /**
         * Returns a List object of all neighbors.
         *
         * @return a list of all neighbors.
         */
        public List getNeighbors() {
            List neighbors = new List();
            edges.toFirst();
            while (edges.hasAccess()) {
                Edge edge = (Edge) edges.getObject();
                neighbors.append(edge.getNeighbor());
                edges.next();
            }
            return neighbors;
        }

        /**
         * Defines an {@code Edge} object (referred to as edge) as it is used in the {@code GraphNode} object. An edge
         * has one connected neighbor {@code GraphNode} object, can have a weight (referred to as edgeWeight) and can
         * either be marked or not marked (controlled by the boolean marked).
         */
        private static class Edge {
            protected GraphNode neighbor;
            protected double weight;
            protected boolean marked;

            public Edge(final GraphNode neighborNode) {
                neighbor = neighborNode;
                weight = 0.0;
                marked = false;
            }

            public Edge(final GraphNode neighborNode, final double weight) {
                neighbor = neighborNode;
                this.weight = weight;
                marked = false;
            }

            public Edge(final GraphNode neighborNode, final double weight, final boolean marked) {
                neighbor = neighborNode;
                this.weight = weight;
                this.marked = marked;
            }

            public Edge(final GraphNode neighborNode, final boolean marked) {
                neighbor = neighborNode;
                weight = 0.0;
                this.marked = marked;
            }

            public GraphNode getNeighbor() {
                return neighbor;
            }

            public double getWeight() {
                return weight;
            }

            public boolean isMarked() {
                return marked;
            }
        }
    }
}
