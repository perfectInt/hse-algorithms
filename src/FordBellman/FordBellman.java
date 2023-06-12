package FordBellman;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Task from acmp.ru
 * Implementation by list of edges
 * Time complexity - O(n * m), where n - number of vertexes, m - number of edges
 */
public class FordBellman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            edges[i][0] = scanner.nextInt() - 1;
            edges[i][1] = scanner.nextInt() - 1;
            edges[i][2] = scanner.nextInt();
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 30000);
        ans[0] = 0;

        for (int k = 1; k < n; k++) {
            for (int[] edge : edges) {
                int start = edge[0];
                int finish = edge[1];
                int weight = edge[2];
                if (ans[start] + weight < ans[finish] && ans[start] < 30000) {
                    ans[finish] = ans[start] + weight;
                }
            }
        }

        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
}
