import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N;
        private int M;
        private int result = 0;
        private List<List<Node>> graph = new ArrayList<>();

        public void solve() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int distance = Integer.parseInt(st.nextToken());

                graph.get(u).add(new Node(v, distance));
                graph.get(v).add(new Node(u, distance));
            }

            StringBuilder sb = new StringBuilder();


            for (int i = 0; i < M; i++) {
                result = 0;
                boolean[] visited = new boolean[N + 1];
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int destination = Integer.parseInt(st.nextToken());
                findNodeToNodeDistance(start, visited, destination, 0);
                sb.append(result).append("\n");
            }

            System.out.print(sb);
        }

        private void findNodeToNodeDistance(int parent, boolean[] visited, int destination, int total) {

            visited[parent] = true;

            if (parent == destination) {
                result = total;
                return;
            }

            for (Node next : graph.get(parent)) {
                if (visited[next.index]) {
                    continue;
                }

                visited[next.index] = true;
                findNodeToNodeDistance(next.index, visited, destination, total + next.distance);
            }
        }
    }

    private static class Node {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
}
