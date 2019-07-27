package nwawsoft.util;

/**
 * Defines GraphNode objects as they are used in the Graph class. A GraphNode has a name, a list of edges to other
 * GraphNode objects including their respective edge weights (often referred to as edgeWeights or edgeWeight) and the
 * option to either be marked or not marked (controlled by the boolean marked).
 */
public class GraphNode {
    private String name;
    private List edges;
    private boolean marked;

    /**
     * Creates a new GraphNode object with name nodeName. It is unmarked.
     *
     * @param nodeName the name of the new GraphNode object.
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
     * Returns whether the GraphNode object's "marked" flag is set.
     *
     * @return true if marked, else false.
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * Returns the name of the GraphNode object.
     *
     * @return the name of the GraphNode object.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a new edge between the calling and the specified GraphNode objects with the specified weight to the list of
     * known edges of the GraphNode.
     *
     * @param graphNode the GraphNode object to create a new edge to.
     * @param weight the weight of the new edge between "this" and graphNode.
     */
    protected void addEdge(final GraphNode graphNode, final double weight) {
        Edge edge = new Edge(graphNode, weight);
        edges.append(edge);
    }

    /**
     * Returns the edgeWeight of the calling and the specified GraphNode objects if there is any, otherwise Double.NaN.
     *
     * @param graphNode the GraphNode object to find the weight to.
     * @return edgeWeight if any edge was found, otherwise Double.NaN.
     */
    protected double getEdgeWeight(final GraphNode graphNode) {
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
     * Removes any edges between the calling and the specified GraphNode objects if graphNode != null.
     * If there were none, nothing happens.
     *
     * @param graphNode the GraphNode object to remove the edge to.
     */
    protected void removeEdge(final GraphNode graphNode) {
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
    protected List getNeighbors() {
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
     * Defines an Edge object (referred to as edge) as it is used in the GraphNode object. An edge has one
     * connected neighbor GraphNode object, can have a weight (also referred to as edgeWeight) and can either be marked
     * or not marked (controlled by the boolean marked).
     */
    private class Edge {
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
