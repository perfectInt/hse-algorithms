package FloydWarshall;

public class FloydWarshall {
    public static final int INF = 99999;

    public void floydWarshall(int[][] graph, int V) {
        int[][] dist = new int[V][V];
        int i, j, k;

        // Инициализация матрицы расстояний dist
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Поиск кратчайших путей
        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Вывод кратчайших расстояний
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}
        };
        int V = graph.length;

        FloydWarshall fw = new FloydWarshall();
        fw.floydWarshall(graph, V);
    }
}

