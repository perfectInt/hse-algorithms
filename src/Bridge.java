import java.util.*;

class Bridge {
    private int vertices;
    private ArrayList<Integer>[] adjacencyList;
    private int time;

    @SuppressWarnings("unchecked")
    public Bridge(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            this.adjacencyList[i] = new ArrayList<>();
        }
        this.time = 0;
    }

    public void addEdge(int source, int destination) {
        this.adjacencyList[source].add(destination);
        this.adjacencyList[destination].add(source);
    }

    public void findBridges() {
        boolean[] visited = new boolean[vertices];
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        int[] parent = new int[vertices];

        // Инициализация массивов
        Arrays.fill(visited, false);
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                findBridgesUtil(i, visited, disc, low, parent);
            }
        }
    }

    private void findBridgesUtil(int vertex, boolean[] visited, int[] disc, int[] low, int[] parent) {
        visited[vertex] = true;
        disc[vertex] = low[vertex] = ++time;

        for (int adjacent : adjacencyList[vertex]) {
            if (!visited[adjacent]) {
                parent[adjacent] = vertex;
                findBridgesUtil(adjacent, visited, disc, low, parent);

                low[vertex] = Math.min(low[vertex], low[adjacent]);

                if (low[adjacent] > disc[vertex]) {
                    System.out.println(vertex + "--" + adjacent + " is a bridge");
                }
            } else if (adjacent != parent[vertex]) {
                low[vertex] = Math.min(low[vertex], disc[adjacent]);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 7;
        Bridge graph = new Bridge(vertices);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 6);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        System.out.println("Bridges:");
        graph.findBridges();
    }
}
