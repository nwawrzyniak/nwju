package nwawsoft.util;

public class GraphNode {
    private String name;
    private List edges;
    private boolean marked;

    /**
     * Ein Knoten mit dem Namen pName wird erzeugt.
     * Der Knoten ist nicht markiert.
     *
     * @param pName Bezeichnung des Knotens
     */
    public GraphNode(String pName) {
        name = pName;
        edges = new List();
        marked = false;
    }

    // Ende Klasse Edge

    /**
     * Der Knoten wird markiert. Falls er
     * nicht markiert ist, sonst bleibt er unveraendert.
     */
    public void mark() {
        marked = true;
    }

    /**
     * Die Markierung des Knotens wird entfernt, falls er markiert ist,
     * sonst bleibt er unveraendert.
     */
    public void unmark() {
        marked = false;
    }

    /**
     * Die Anfrage liefert den Wert true, wenn der Knoten markiert ist,
     * sonst liefert sie den Wert false.
     *
     * @return true falls markiert, sonst false
     */
    public boolean isMarked() {
        return marked;

    }

    /**
     * Die Anfrage liefert den Namen des Knotens.
     *
     * @return Bezeichnung des Knotens
     */
    public String getName() {
        return name;
    }

    /**
     * Interne Methode
     */
    void addEdge_(GraphNode pNode, double pWeight) {
        Edge lEdge = new Edge(pNode, pWeight);
        edges.append(lEdge);
    }

    // Hilfsmethoden

    /**
     * Interne Methode
     */
    double getEdgeWeight_(GraphNode pNode) {
        boolean ok = false;
        Edge e = null;
        edges.toFirst();
        while (!ok && edges.hasAccess()) {
            e = (Edge) edges.getObject();
            if (e.getNeighbour() == pNode)
                ok = true;
            edges.next();
        }
        if (!ok)
            return Double.NaN; // Not a Number
        else
            return e.getWeight();
    }

    /**
     * Interne Methode
     */
    void removeEdge_(GraphNode pNode) {
        if (pNode != null) {
            Edge e;
            edges.toFirst();
            while (edges.hasAccess()) {
                e = (Edge) edges.getObject();
                if (e.getNeighbour() == pNode)
                    edges.remove();
                edges.next();
            }
        }
    }

    /**
     * Interne Methode
     */
    List getNeighbours_() {
        // liefert eine Liste mit den Nachbarknoten
        List lList = new List();
        edges.toFirst();
        while (edges.hasAccess()) {
            Edge lEdge = (Edge) edges.getObject();
            lList.append(lEdge.getNeighbour());
            edges.next();
        }
        return lList;
    }

    // Klasse Edge
    public class Edge {
        protected GraphNode neighbour;
        protected double weight;
        protected boolean marked;

        public Edge(GraphNode pNeighbour, double pWeight) {
            neighbour = pNeighbour;
            weight = pWeight;
            marked = false;
        }

        public GraphNode getNeighbour() {
            return neighbour;
        }

        public double getWeight() {
            return weight;
        }

        public boolean isMarked() {
            return marked;
        }

    }

}
