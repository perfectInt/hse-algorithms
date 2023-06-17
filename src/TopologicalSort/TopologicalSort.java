package TopologicalSort;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph must be acyclic
 */
public class TopologicalSort {
    static List<Integer> result = new ArrayList<>();

    private static void dfs(int v, boolean[] used, List<List<Integer>> graph) {
        used[v] = true;
        List<Integer> adjacencyList = graph.get(v);
        for (Integer i : adjacencyList) {
            if (!used[i]) {
                dfs(i, used, graph);
            }
        }
        result.add(v);
    }

    public static void main(String[] args) {
        int n = 5;
        boolean[] used = new boolean[n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(0).add(3);
        graph.get(1).add(3);
        graph.get(1).add(4);
        graph.get(2).add(4);
        graph.get(3).add(4);
        /*
        0: 1 2 3
        1: 3 4
        2: 4
        3: 4
         */
        dfs(0, used, graph);
        for (int i = result.size() - 1; i >= 0; i--) {
            System.out.print(result.get(i) + " ");
        }
    }
}
