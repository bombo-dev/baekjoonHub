import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int max = 0;
        private int maxNode = 0;
        // int[] 0, 1 (0 Index, 1 Depth, 2 Distance)

        private ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        public void solve() throws IOException {
            N = stoi(br.readLine());

            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < N; i++) {
                StringTokenizer st = getStringTokenizer();
                int nodeIndex = stoi(st.nextToken());

                while (true) {
                    int linkNodeIndex = stoi(st.nextToken());

                    if (linkNodeIndex == -1) {
                        break;
                    }

                    int distance = stoi(st.nextToken());
                    graph.get(nodeIndex).add(new Node(linkNodeIndex, distance));
                }
            }

            findMaxDepthNode(1, 0, 0, 0);
            max = 0;
            findMaxDepthNode(maxNode, 0, 0, 0);
            System.out.print(max);
        }

        private void findMaxDepthNode(int start, int parent, int diameter, int depth) {

            if (max < diameter) {
                max = diameter;
                maxNode = start;
            }

            for (Node node : graph.get(start)) {
                int next = node.index;
                int distance = node.distance;

                if (next == parent) {
                    continue;
                }

                findMaxDepthNode(next, start, diameter + distance, depth + 1);
            }
        }

        private StringTokenizer getStringTokenizer(String delim) throws IOException {
            return new StringTokenizer(br.readLine(), delim);
        }

        private StringTokenizer getStringTokenizer() throws IOException {
            return new StringTokenizer(br.readLine());
        }

        private int stoi(String number) {
            return Integer.parseInt(number);
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
