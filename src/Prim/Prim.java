package Prim;

import java.util.*;

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    int V;
    List<List<Edge>> adjList;

    public Graph(int V) {
        this.V = V;
        this.adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        adjList.get(src).add(edge);
        adjList.get(dest).add(edge);
    }

    public List<Edge> primMST() {
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        int[] key = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        List<Edge> mst = new ArrayList<>();

        // Start with vertex 0
        int startVertex = 0;
        key[startVertex] = 0;
        pq.offer(new Edge(-1, startVertex, 0)); // -1 as a dummy source

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int vertex = edge.dest;

            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;
            int parentVertex = edge.src;
            int weight = edge.weight;

            if (parentVertex != -1) {
                mst.add(new Edge(parentVertex, vertex, weight));
            }

            for (Edge adjEdge : adjList.get(vertex)) {
                int adjVertex = adjEdge.dest;
                int adjWeight = adjEdge.weight;
                if (!visited[adjVertex] && adjWeight < key[adjVertex]) {
                    pq.offer(new Edge(vertex, adjVertex, adjWeight));
                    parent[adjVertex] = vertex;
                    key[adjVertex] = adjWeight;
                }
            }
        }

        return mst;
    }
}

public class Prim {
    public static void main(String[] args) {
        int V = 5; // Количество вершин
        Graph graph = new Graph(V);

        // Добавляем ребра графа
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);

        List<Edge> minimumSpanningTree = graph.primMST();

        // Выводим минимальное остовное дерево
        System.out.println("Минимальное остовное дерево:");
        for (Edge edge : minimumSpanningTree) {
            System.out.println(edge.src + " -- " + edge.dest + ", вес: " + edge.weight);
        }
    }
}

