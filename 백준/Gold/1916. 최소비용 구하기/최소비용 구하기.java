import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int M;
        private int START;
        private int END;
        private List<ArrayList<Node>> nodes = new ArrayList<>();
        private int[] distances;
        private StringTokenizer st;

        public void solve() throws IOException {
            N = stoi(br.readLine());
            M = stoi(br.readLine());

            distances = new int[N + 1];
            Arrays.fill(distances, (int) 1e9);
            for (int i = 0; i <= N; i++) {
                nodes.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = getStringTokenizer();
                int start = stoi(st.nextToken());
                int end = stoi(st.nextToken());
                int distance = stoi(st.nextToken());

                nodes.get(start).add(new Node(end, distance));
            }

            st = getStringTokenizer();
            START = stoi(st.nextToken());
            END = stoi(st.nextToken());

            dijkstra();
            System.out.print(distances[END]);
        }

        private void dijkstra() {
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distance));
            distances[START] = 0;
            pq.add(new Node(START, 0));

            while (!pq.isEmpty()) {
                Node pNode = pq.poll();

                if (distances[pNode.index] < pNode.distance) {
                    continue;
                }

                for (Node next : nodes.get(pNode.index)) {

                    int cost = pNode.distance + next.distance;

                    if (distances[next.index] > cost) {
                        distances[next.index] = cost;
                        pq.offer(new Node(next.index, cost));
                    }
                }
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
