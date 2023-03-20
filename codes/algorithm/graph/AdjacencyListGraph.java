package graph;

import java.util.*;

public class AdjacencyListGraph {

    private final int VERTEX_RANGE; // 1 to VERTEX_RANGE
    private final List<List<Integer>> adjacentList;


    public AdjacencyListGraph(int maxVertexNumber) {
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

    private void sortList() {
        for (List<Integer> neighbors : adjacentList) {
            Collections.sort(neighbors);
        }
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


    public void findCycleOnDirectedGraph(int vertex) {
        boolean[] visited = new boolean[VERTEX_RANGE + 1];
        boolean[] finished = new boolean[VERTEX_RANGE + 1];

        dfs(visited, finished, vertex);
    }

    private void dfs(boolean[] visited, boolean[] finished, int v) {
        visited[v] = true;

        for (int neighbor : adjacentList.get(v)) {
            if (!visited[neighbor]) {
                dfs(visited, finished, neighbor);
            } else if (!finished[neighbor]) {
                System.out.printf("Cycle exist from %d to %d\n", neighbor, neighbor);
            }
        }

        finished[v] = true;
    }

    public static void main(String[] args) {
        /* Init graph */
        AdjacencyListGraph graph = new AdjacencyListGraph(5);
        graph.addDirectedEdge(5, 4);
        graph.addDirectedEdge(5, 2);
        graph.addDirectedEdge(1, 2);
        graph.addDirectedEdge(3, 4);
        graph.addDirectedEdge(3, 1);
        graph.addDirectedEdge(1, 3);

        graph.sortList(); // Visit neighbors in ascending order of vertex numbers

        /* DFS */
        graph.dfs(3);
        System.out.println();

        /* BFS */
        graph.bfs(3);
        System.out.println();

        /* Find cycle on directed graph */
        graph.findCycleOnDirectedGraph(3);
    }
}
