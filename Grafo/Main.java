package Grafo;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };
        Grafo g = new Grafo(matrix);
        g.printMatrix();
    }
}
