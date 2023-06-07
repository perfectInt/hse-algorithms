package Cycle;

import java.util.ArrayList;
import java.util.List;

class Graph {
    private int vertices;
    private List<List<Integer>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[vertices];
        boolean[] recursiveStack = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (dfs(i, visited, recursiveStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean dfs(int vertex, boolean[] visited, boolean[] recursiveStack) {
        if (recursiveStack[vertex]) {
            return true; // Обнаружен цикл
        }

        if (visited[vertex]) {
            return false; // Вершина уже посещена, цикл не обнаружен
        }

        visited[vertex] = true;
        recursiveStack[vertex] = true;

        List<Integer> neighbors = adjacencyList.get(vertex);
        for (Integer neighbor : neighbors) {
            if (dfs(neighbor, visited, recursiveStack)) {
                return true; // Обнаружен цикл
            }
        }

        recursiveStack[vertex] = false;

        return false; // Цикл не обнаружен
    }
}

public class Cycle {
    public static void main(String[] args) {
        int vertices = 4;
        Graph graph = new Graph(vertices);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1); // Образуется цикл 1 -> 2 -> 3 -> 1

        boolean hasCycle = graph.hasCycle();
        System.out.println("Граф содержит цикл? " + hasCycle);
    }
}
