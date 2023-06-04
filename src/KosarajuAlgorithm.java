import java.util.*;

class KosarajuAlgorithm {
    private int vertices;
    private ArrayList<Integer>[] adjacencyList;
    private boolean[] visited;
    private Stack<Integer> stack;

    @SuppressWarnings("unchecked")
    public KosarajuAlgorithm(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            this.adjacencyList[i] = new ArrayList<>();
        }
        this.visited = new boolean[vertices];
        this.stack = new Stack<>();
    }

    public void addEdge(int source, int destination) {
        this.adjacencyList[source].add(destination);
    }

    public void fillOrder(int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;

        for (int adjacent : adjacencyList[vertex]) {
            if (!visited[adjacent]) {
                fillOrder(adjacent, visited, stack);
            }
        }

        stack.push(vertex);
    }

    public void DFSUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int adjacent : adjacencyList[vertex]) {
            if (!visited[adjacent]) {
                DFSUtil(adjacent, visited);
            }
        }
    }

    public void printStronglyConnectedComponents() {
        // Заполняем стек в порядке завершения обхода в глубину
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }

        // Создаем обращенный граф
        KosarajuAlgorithm reversedGraph = getReversedGraph();

        // Снимаем отметку посещения вершин
        Arrays.fill(visited, false);

        // Обрабатываем вершины из стека
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                reversedGraph.DFSUtil(vertex, visited);
                System.out.println();
            }
        }
    }

    public KosarajuAlgorithm getReversedGraph() {
        KosarajuAlgorithm reversedGraph = new KosarajuAlgorithm(vertices);

        for (int i = 0; i < vertices; i++) {
            for (int adjacent : adjacencyList[i]) {
                reversedGraph.addEdge(adjacent, i);
            }
        }

        return reversedGraph;
    }

    public static void main(String[] args) {
        int vertices = 8;
        KosarajuAlgorithm graph = new KosarajuAlgorithm(vertices);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);

        System.out.println("Strongly Connected Components:");
        graph.printStronglyConnectedComponents();
    }
}
