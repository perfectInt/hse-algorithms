import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

class Graph {
    int V;
    List<Edge> edges;

    public Graph(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        edges.add(edge);
    }

    private int find(int[] parent, int vertex) {
        if (parent[vertex] == vertex)
            return vertex;
        return find(parent, parent[vertex]);
    }

    private void union(int[] parent, int x, int y) {
        int xParent = find(parent, x);
        int yParent = find(parent, y);
        parent[yParent] = xParent;
    }

    public List<Edge> kruskalMST() {
        List<Edge> result = new ArrayList<>();
        Collections.sort(edges); // Сортируем ребра по возрастанию весов

        int[] parent = new int[V];
        for (int i = 0; i < V; i++)
            parent[i] = i;

        int edgeCount = 0;
        int i = 0;
        while (edgeCount < V - 1) {
            Edge edge = edges.get(i);
            int srcParent = find(parent, edge.src);
            int destParent = find(parent, edge.dest);

            if (srcParent != destParent) {
                result.add(edge);
                union(parent, srcParent, destParent);
                edgeCount++;
            }

            i++;
        }

        return result;
    }
}

public class Kruskal {
    public static void main(String[] args) {
        int V = 6; // Количество вершин
        Graph graph = new Graph(V);

        // Добавляем ребра графа
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);

        List<Edge> minimumSpanningTree = graph.kruskalMST();

        // Выводим минимальное остовное дерево
        System.out.println("Минимальное остовное дерево:");
        for (Edge edge : minimumSpanningTree) {
            System.out.println(edge.src + " -- " + edge.dest + ", вес: " + edge.weight);
        }
    }
}
