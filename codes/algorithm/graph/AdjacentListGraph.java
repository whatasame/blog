package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public void addEdge(int u, int v) {
        adjacentList.get(u).add(v);
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

    public static void main(String[] args) {
        AdjacentListGraph graph = new AdjacentListGraph(5);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        graph.dfs(1);
    }
}
