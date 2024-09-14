package Grafo;

import java.util.*;

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

    public int[][] bfs(int r){

        int n = matrix.length;
        int[] colors = new int[n];
        int[] distance = new int[n];
        int[] predecessor = new int[n];
        for(int i = 0; i < n; i++){
            colors[i] = 0;
            distance[i] = Integer.MAX_VALUE;
            predecessor[i] = -1;
        }
        colors[r] = 1;
        distance[r] = 0;
        predecessor[r] = -1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(r);

        while(!queue.isEmpty()){
            int u = queue.poll();
            for(int v = 0; v < n; v++){
                if(matrix[u][v] == 1 && colors[v] == 0){
                    colors[v] = 1;
                    predecessor[v] = u;
                    distance[v] = distance[u] + 1;
                    queue.add(v);
                }
            }
            colors[u] = 2;
        }
        int[][] ans = new int[][]{
                colors,
                distance,
                predecessor
        };
        return ans;
    }

    public int distance(int r, int vertex){
        int n = matrix.length;
        int[] colors = new int[n];
        int[] distance = new int[n];
        for(int i = 0; i < n; i++){
            colors[i] = 0;
            distance[i] = Integer.MAX_VALUE;
        }
        colors[r] = 1;
        distance[r] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(r);

        while(!queue.isEmpty()){
            int u = queue.poll();
            for(int v = 0; v < n; v++){
                if(matrix[u][v] == 1){
                    if(colors[v] == 0){
                        colors[v] = 1;
                        distance[v] = distance[u] + 1;
                        queue.add(v);
                    }
                    else if(distance[v] > (distance[u] + 1)){
                        distance[v] = distance[u] + 1;
                    }
                }
            }
            colors[u] = 2;
        }
        return distance[vertex];
    }

    public int[][] distance(int r){
        int n = matrix.length;
        int[] colors = new int[n];
        int[] distance = new int[n];
        for(int i = 0; i < n; i++){
            colors[i] = 0;
            distance[i] = Integer.MAX_VALUE;
        }
        colors[r] = 1;
        distance[r] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(r);

        while(!queue.isEmpty()){
            int u = queue.poll();
            for(int v = 0; v < n; v++){
                if(matrix[u][v] == 1){
                    if(colors[v] == 0){
                        colors[v] = 1;
                        distance[v] = distance[u] + 1;
                        queue.add(v);
                    }
                    else if(distance[v] > (distance[u] + 1)){
                        distance[v] = distance[u] + 1;
                    }
                }
            }
            colors[u] = 2;
        }
        return new int[][]{
                distance,
                colors
        };
    }

    public int diameter(){
        int n = matrix.length;
        int[] vmax = new int[n];
        int[] dmax = new int[n];
        for(int i = 0; i < n; i++){
            int[][] ans = distance(i);
            int[] distance = ans[0];
            int[] colors = ans[1];
            vmax[i] = i;
            dmax[i] = 0;
            for(int j = 0; j < n; j++){
                if(distance[j] > dmax[i] && colors[j] != 0){
                    vmax[i] = j;
                    dmax[j] = distance[j];
                }
            }
        }
        int max = 0;
        for(int i : dmax){
            if(i > max){
                max = i;
            }
        }
        return max;
    }

}
