package Grafo;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 1, 1, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0}
        };
        Grafo g = new Grafo(matrix);
        int[] ans = g.dfs();
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
