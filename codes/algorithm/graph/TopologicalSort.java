package algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {

    private static final int MAX_VERTEX_NUMBER = 7; // Vertex number is 1 ~ MAX_VERTEX_NUMBER

    private static List<Integer> sort(List<List<Integer>> graph) {
        List<Integer> result = new ArrayList<>();
        int[] inDegree = new int[MAX_VERTEX_NUMBER + 1];

        /* Compute in-degree each vertex */
        for (int i = 1; i <= MAX_VERTEX_NUMBER; i++) {
            for (int adj : graph.get(i)) {
                inDegree[adj]++;
            }
        }

        /* Enqueue vertices which has in-degree 0 */
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= MAX_VERTEX_NUMBER; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        /* Run topological sort */
        while (!q.isEmpty()) {
            int u = q.poll();
            result.add(u);

            for (int v : graph.get(u)) {
                if (--inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        /* Init directed acyclic graph(DAG) */
        List<List<Integer>> graph = new ArrayList<>(); // Adjacent List
        for (int i = 0; i <= MAX_VERTEX_NUMBER; i++) { // Init vertex
            graph.add(new LinkedList<>());
        }

        /* Add edges */
        graph.get(1).add(2);
        graph.get(1).add(7);
        graph.get(2).add(3);
        graph.get(2).add(4);
        graph.get(3).add(4);
        graph.get(3).add(6);
        graph.get(4).add(5);
        graph.get(5).add(6);
        graph.get(7).add(4);

        /* Sort graph */
        List<Integer> sortedVertexList = sort(graph);

        /* Print result */
        System.out.println(sortedVertexList);
    }
}
