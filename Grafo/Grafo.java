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

    private void visit(int v, int[] time, int[] timeD, int[] timeF, int[] colors, int[] predecessor){
        colors[v] = 1;
        time[0]++;
        timeD[v] = time[0];
        for(int i = 0; i < matrix[v].length; i++){
            if(matrix[v][i] == 1){
                if(colors[i] == 0){
                    predecessor[i] = v;
                    visit(i, time, timeD, timeF, colors, predecessor);
                }
            }
        }
        colors[v] = 2;
        time[0]++;
        timeF[v] = time[0];
    }

    private void visit(int s, int t, int[] colors, Stack<Integer> path, boolean[] flag){
        colors[s] = 1;
        if(flag[0]){
            for(int i = 0; i < matrix[s].length; i++){
                if(flag[0]){
                    if(matrix[s][i] == 1){
                        if(i == t){
                            path.push(i);
                            flag[0] = false;
                        }
                        if(colors[i] == 0 && flag[0]){
                            path.push(i);
                            visit(i, t, colors,path, flag);
                        }
                    }
                }
            }
        }
        if(flag[0]){
            path.pop();
            colors[s] = 2;
        }
    }

    public int[] dfs(){
        int n = matrix.length;
        int[] colors = new int[n];
        int[] predecessor = new int[n];
        int[] timeD = new int[n];
        int[] timeF = new int[n];
        int[] time = new int[]{0};
        for(int i = 0; i < n; i++){
            if(colors[i] == 0){
                visit(i, time, timeD, timeF, colors, predecessor);
            }
        }
        return timeF;
    }

    public int[] findPath(int s, int t){
        int n = matrix.length;
        int[] colors = new int[n];
        Stack<Integer> path = new Stack<>();
        boolean[] flag = new boolean[]{true};
        path.push(s);
        visit(s, t, colors, path, flag);
        int[] ans = new int[path.size()];
        for(int i = 0; i < path.size(); i++){
            ans[i] = path.get(i);
        }
        return ans;
    }

}
