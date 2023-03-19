package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdjacentListGraph {

    private final int VERTEX_RANGE; // 1 to VERTEX_RANGE
    private final List<List<Integer>> adjacentList;


    public AdjacentListGraph(int maxVertexNumber) {
        VERTEX_RANGE = maxVertexNumber;
        adjacentList = new LinkedList<>();
        for (int i = 0; i <= VERTEX_RANGE; i++) {
            adjacentList.add(new ArrayList<>());
        }
    }

    public void addDirectedEdge(int u, int v) {
        adjacentList.get(u).add(v);
    }

    public void addUndirectedEdge(int u, int v) {
        adjacentList.get(u).add(v);
        adjacentList.get(v).add(u);
    }

    public void dfs(int v) {
        boolean[] visited = new boolean[VERTEX_RANGE + 1];
        dfs(visited, v);
    }

    private void dfs(boolean[] visited, int v) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int neighbor : adjacentList.get(v)) {
            if (!visited[neighbor]) {
                dfs(visited, neighbor);
            }
        }
    }

    public void bfs(int v) {
        /* Prepare BFS */
        boolean[] visited = new boolean[VERTEX_RANGE + 1];
        Queue<Integer> bfsQueue = new LinkedList<>();

        /* Visit v */
        bfsQueue.add(v);
        visited[v] = true;
        System.out.print(v + " ");

        while (!bfsQueue.isEmpty()) {
            int vertex = bfsQueue.poll();

            for (int neighbor : adjacentList.get(vertex)) {
                if (!visited[neighbor]) {
                    bfsQueue.add(neighbor);
                    visited[neighbor] = true;
                    System.out.print(neighbor + " ");
                }
            }
        }

    }

    public static void main(String[] args) {
        AdjacentListGraph graph = new AdjacentListGraph(5);
        graph.addUndirectedEdge(5, 4);
        graph.addUndirectedEdge(5, 2);
        graph.addUndirectedEdge(1, 2);
        graph.addUndirectedEdge(3, 4);
        graph.addUndirectedEdge(3, 1);

        graph.dfs(3);
        System.out.println();

        graph.bfs(3);
    }
}
