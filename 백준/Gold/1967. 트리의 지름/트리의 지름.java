import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N;
        private List<List<Node>> graph = new ArrayList<>();
        private boolean[] visited;
        private int maxIndex = 1;
        private int maxWeight = 0;

        public void solve() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N + 1];

            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.get(u).add(new Node(v, weight));
                graph.get(v).add(new Node(u, weight));
            }

            visited[1] = true;
            // 1. 루트 노드로 부터 가장 멀리 있는 노드를 찾는다.
            dfs(1, 0);

            Arrays.fill(visited, false);
            visited[maxIndex] = true;
            // 2. 해당 노드로 부터 가장 멀리 있는 노드를 찾는다.
            dfs(maxIndex, 0);
            System.out.print(maxWeight);
        }

        private void dfs(int parent, int weight) {

            for (Node child : graph.get(parent)) {
                if (visited[child.node]) {
                    continue;
                }

                if (maxWeight < weight + child.weight) {
                    maxWeight = weight + child.weight;
                    maxIndex = child.node;
                }
                visited[child.node] = true;
                dfs(child.node, weight + child.weight);
            }
        }
    }

    private static class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
