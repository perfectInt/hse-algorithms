import java.util.Arrays;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Subset {
    int parent, rank;
}

public class BoruvkaAlgorithm {
    private int V, E;
    private Edge[] edges;

    BoruvkaAlgorithm(int V, int E) {
        this.V = V;
        this.E = E;
        edges = new Edge[E];
        for (int i = 0; i < E; ++i)
            edges[i] = new Edge(0, 0, 0);
    }

    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    void boruvkaMST() {
        Subset[] subsets = new Subset[V];
        for (int i = 0; i < V; ++i) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }
        int[] cheapest = new int[V];
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        int numTrees = V;
        int MSTweight = 0;
        while (numTrees > 1) {
            Arrays.fill(cheapest, -1);
            for (int i = 0; i < E; i++) {
                int set1 = find(subsets, edges[i].src);
                int set2 = find(subsets, edges[i].dest);
                if (set1 != set2) {
                    if (cheapest[set1] == -1 || edges[i].weight < edges[cheapest[set1]].weight)
                        cheapest[set1] = i;
                    if (cheapest[set2] == -1 || edges[i].weight < edges[cheapest[set2]].weight)
                        cheapest[set2] = i;
                }
            }
            for (int i = 0; i < V; i++) {
                if (cheapest[i] != -1) {
                    int set1 = find(subsets, edges[cheapest[i]].src);
                    int set2 = find(subsets, edges[cheapest[i]].dest);
                    if (set1 != set2) {
                        MSTweight += edges[cheapest[i]].weight;
                        System.out.println(edges[cheapest[i]].src + " - " + edges[cheapest[i]].dest);
                        union(subsets, set1, set2);
                        numTrees--;
                    }
                }
            }
        }
        System.out.println("Minimum Spanning Tree weight: " + MSTweight);
    }

    public static void main(String[] args) {
        int V = 4; // Количество вершин
        int E = 5; // Количество ребер
        BoruvkaAlgorithm graph = new BoruvkaAlgorithm(V, E);

        // Добавляем ребра в граф
        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;
        graph.edges[0].weight = 10;

        graph.edges[1].src = 0;
        graph.edges[1].dest = 2;
        graph.edges[1].weight = 6;

        graph.edges[2].src = 0;
        graph.edges[2].dest = 3;
        graph.edges[2].weight = 5;

        graph.edges[3].src = 1;
        graph.edges[3].dest = 3;
        graph.edges[3].weight = 15;

        graph.edges[4].src = 2;
        graph.edges[4].dest = 3;
        graph.edges[4].weight = 4;

        graph.boruvkaMST();
    }
}
