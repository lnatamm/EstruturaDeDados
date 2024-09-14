package Grafo;

public class Grafo {

    private int[][] matrix;

    public Grafo(int[][] matrix) {
        this.matrix = matrix;
    }

    public void printMatrix() {
        for (int[] i : matrix) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}
