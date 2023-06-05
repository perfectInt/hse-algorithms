package Dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {
    private static final int INFINITY = Integer.MAX_VALUE;

    public static void dijkstra(int[][] graph, int startVertex) {
        int vertices = graph.length;
        int[] distances = new int[vertices];
        boolean[] visited = new boolean[vertices];

        Arrays.fill(distances, INFINITY);
        distances[startVertex] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            int minDistanceVertex = getMinDistanceVertex(distances, visited);
            visited[minDistanceVertex] = true;

            for (int j = 0; j < vertices; j++) {
                if (!visited[j] && graph[minDistanceVertex][j] != 0
                        && distances[minDistanceVertex] != INFINITY
                        && distances[minDistanceVertex] + graph[minDistanceVertex][j] < distances[j]) {
                    distances[j] = distances[minDistanceVertex] + graph[minDistanceVertex][j];
                }
            }
        }

        printShortestPaths(distances, startVertex);
    }

    private static int getMinDistanceVertex(int[] distances, boolean[] visited) {
        int minDistance = INFINITY;
        int minDistanceVertex = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                minDistanceVertex = i;
            }
        }

        return minDistanceVertex;
    }

    private static void printShortestPaths(int[] distances, int startVertex) {
        int vertices = distances.length;
        System.out.println("Shortest paths from vertex " + startVertex + ":");

        for (int i = 0; i < vertices; i++) {
            if (i != startVertex) {
                System.out.println("Vertex " + i + ": distance = " + distances[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        int startVertex = 0;
        dijkstra(graph, startVertex);
    }
}

