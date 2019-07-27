package nwawsoft.util;

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
     * @return true, falls leer, sonst false
     */
    public boolean isEmpty() {
        return nodeList.isEmpty();
    }

    /**
     * Der Knoten graphNode wird dem Graphen hinzugefuegt.
     * Falls bereits ein Knoten mit gleichem Namen im
     * Graphen existiert, wird dieser Knoten nicht eingefuegt.
     * Falls graphNode null ist, veraendert sich der Graph nicht.
     *
     * @param graphNode neuer Knoten
     */
    public void addNode(final GraphNode graphNode) {
        if (graphNode != null && !this.hasNode(graphNode.getName())) {
            nodeList.append(graphNode);
        }
    }

    /**
     * Die Anfrage liefert true, wenn ein Knoten mit dem Namen
     * pName im Graphen existiert.
     * Sonst wird false zurueck gegeben.
     *
     * @param nodeName Knotenbezeichnung
     * @return true, falls es Knoten gibt, sonst false
     */
    public boolean hasNode(final String nodeName) {
        boolean found = false;
        nodeList.toFirst();
        while (nodeList.hasAccess() && !found) {
            GraphNode node = (GraphNode) nodeList.getObject();
            found = node.getName().equals(nodeName);
            nodeList.next();
        }
        return found;
    }

    /**
     * Die Anfrage liefert den Knoten mit dem Namen nodeName zurueck.
     * Falls es keinen Knoten mit dem Namen im Graphen gibt,
     * wird null zurueck gegeben.
     *
     * @param nodeName Knotenbezeichnung
     * @return Objekt der Klasse GraphNode
     */
    public GraphNode getNode(final String nodeName) {
        GraphNode lNode0 = null;
        nodeList.toFirst();
        boolean lStop = false;
        while (nodeList.hasAccess() && !lStop) {
            GraphNode lNode = (GraphNode) nodeList.getObject();
            if (lNode.getName().equals(nodeName)) {
                lNode0 = lNode;
                lStop = true;
            }
            nodeList.next();
        }
        return lNode0;
    }

    /**
     * Falls graphNode ein Knoten des Graphen ist, so werden er und alle
     * mit ihm verbundenen Kanten aus dem Graphen entfernt.
     * Sonst wird der Graph nicht veraendert.
     *
     * @param graphNode Knoten
     */
    public void removeNode(final GraphNode graphNode) {
        if (graphNode != null) {
            nodeList.toFirst();
            while (nodeList.hasAccess()) {
                GraphNode lNode = (GraphNode) nodeList.getObject();
                if (lNode.getName().equals(graphNode.getName())) {
                    List neighbors = this.getNeighbors(lNode);
                    neighbors.toFirst();
                    while (neighbors.hasAccess()) {
                        GraphNode lNode1 = (GraphNode) neighbors.getObject();
                        this.removeEdge(lNode, lNode1);
                        neighbors.next();
                    }
                    nodeList.remove();
                }
                nodeList.next();
            }
        }
    }

    /**
     * Falls eine Kante zwischen graphNode1 und graphNode2 noch nicht existiert,
     * werden die Knoten graphNode1 und graphNode2 durch eine Kante verbunden,
     * die das Gewicht weight hat. graphNode1 ist also Nachbarknoten
     * von graphNode2 und umgekehrt. Falls eine Kante zwischen graphNode1 und graphNode2
     * bereits existiert, erhaelt sie das Gewicht weight.
     * Falls einer der Knoten graphNode1 oder graphNode2 im Graphen nicht existiert oder null ist,
     * veraendert sich der Graph nicht.
     *
     * @param graphNode1  Knoten
     * @param graphNode2  Knoten
     * @param weight Kantengewicht
     */
    public void addEdge(final GraphNode graphNode1, final GraphNode graphNode2, final double weight) {
        if (graphNode1 != null && graphNode2 != null) {
            if (this.hasEdge(graphNode1, graphNode2)) {
                this.removeEdge(graphNode1, graphNode2);
            }
            graphNode1.addEdge(graphNode2, weight);
            graphNode2.addEdge(graphNode1, weight);
        }
    }

    /**
     * Die Anfrage liefert true, wenn ein Knoten mit
     * dem Namen pName im Graphen existiert.
     * Sonst wird false zurueck gegeben.
     *
     * @param pNode1 Knoten
     * @param pNode2 Knoten
     * @return true, falls Kante existiert, sonst false
     */
    public boolean hasEdge(final GraphNode pNode1, final GraphNode pNode2) {
        boolean result = false;
        List lNeighbours;
        GraphNode lNeighbour;
        if ((pNode1 != null) && (pNode2 != null)) {
            lNeighbours = pNode1.getNeighbors();
            if (!lNeighbours.isEmpty()) {
                lNeighbours.toFirst();
                while (lNeighbours.hasAccess()) {
                    lNeighbour = (GraphNode) lNeighbours.getObject();
                    if (lNeighbour.getName().equals(pNode2.getName())) {
                        result = true;
                    }
                    lNeighbours.next();
                }
            }
        }
        return result;
    }

    /**
     * Falls pNode1 und pNode2 nicht null sind und eine Kante zwischen
     * pNode1 und pNode2 existiert, wird die Kante geloescht. Sonst
     * bleibt der Graph unveraendert.
     *
     * @param pNode1 Knoten
     * @param pNode2 Knoten
     */
    public void removeEdge(final GraphNode pNode1, final GraphNode pNode2) {
        if (pNode1 != null && pNode2 != null && this.hasEdge(pNode1, pNode2)) {
            pNode1.removeEdge(pNode2);
            pNode2.removeEdge(pNode1);
        }
    }

    /**
     * Die Anfrage liefert das Gewicht der Kante zwischen pNode1 und pNode2.
     * Falls die Kante nicht existiert, wird Double.NaN (not a number)
     * zurueck gegeben.
     *
     * @param pNode1 Knoten
     * @param pNode2 Knoten
     * @return Kantengewicht
     */
    public double getEdgeWeight(final GraphNode pNode1, final GraphNode pNode2) {
        return pNode1.getEdgeWeight(pNode2);
    }

    /**
     * Unmarks all GraphNode objects in the Graph.
     */
    public void unmark() {
        if (!nodeList.isEmpty()) {
            nodeList.toFirst();
            while (nodeList.hasAccess()) {
                ((GraphNode) nodeList.getObject()).removeMark();
                nodeList.next();
            }
        }
    }

    /**
     * Die Anfrage liefert den Wert true, wenn alle Knoten des Graphen
     * markiert sind, sonst liefert sie den Wert false.
     *
     * @return true, lass alle Knoten markiert, sonst false
     */
    public boolean allNodesMarked() {
        if (!nodeList.isEmpty()) {
            nodeList.toFirst();
            boolean lAllMarked = true;
            while (nodeList.hasAccess() && lAllMarked) {
                if (!((GraphNode) nodeList.getObject()).isMarked()) {
                    lAllMarked = false;
                }
                nodeList.next();
            }
            return lAllMarked;
        } else return true;
    }

    /**
     * Die Anfrage liefert eine Liste, die alle Knoten des Graphen enthaelt.
     *
     * @return Knotenliste
     */
    public List getNodes() {
        List lList = new List();
        nodeList.toFirst();
        while (nodeList.hasAccess()) {
            GraphNode g = (GraphNode) nodeList.getObject();
            lList.append(g);
            nodeList.next();
        }
        return lList;
    }

    /**
     * Die Anfrage liefert eine Liste, die alle Nachbarknoten des
     * Knotens pNode enthaelt.
     *
     * @param pNode Knoten
     * @return Liste mit allen Nachbarknoten
     */
    public List getNeighbors(final GraphNode pNode) {
        return pNode.getNeighbors();
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
            combinedWeight = combinedWeight + edgeWeight((GraphNode) allNodes.getObject());
            allNodes.next();
        }
        return combinedWeight;
    }

    /**
     * Returns the added edgeWeights of all edges of the specified GraphNode object.
     *
     * @param pNode a GraphNode object.
     * @return the sum of all edgeWeights of the GraphNode (and his neighbors).
     */
    public double edgeWeight(final GraphNode pNode) {
        double nodeWeight = 0;
        List neighbors = pNode.getNeighbors();
        neighbors.toFirst();
        while (neighbors.hasAccess()) {
            nodeWeight = nodeWeight + getEdgeWeight(pNode, (GraphNode) neighbors.getObject());
            neighbors.next();
        }
        return nodeWeight;
    }

    /**
     * Returns the GraphNode object with the highest added edgeWeights in the Graph.
     *
     * @return the GraphNode with the highest added edgeWeights.
     */
    public GraphNode highestWeight() {
        GraphNode heaviestNode;
        List nodes = this.getNodes();
        nodes.toFirst();
        heaviestNode = (GraphNode) nodes.getObject();
        while (nodes.hasAccess()) {
            if (edgeWeight(heaviestNode) < edgeWeight((GraphNode) nodes.getObject())) {
                heaviestNode = (GraphNode) nodes.getObject();
            }
            nodes.next();
        }
        return heaviestNode;
    }
}
