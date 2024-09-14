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

    public int[][] getMatrix() {
        return matrix;
    }

    public int[] getNeighbours(int v){
        return matrix[v];
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public boolean isBinary(){
        for(int[] i : matrix){
            for(int j : i){
                if(j != 0 && j != 1){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSymetric(){
        for(int[] i : matrix){
            if(i.length != matrix.length){
                return false;
            }
        }
        return true;
    }

    public boolean isBinarySymetric(){
        return isBinary() && isSymetric();
    }

}
