import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    // V = 100000
    // E = 500000
    // 1000 = 10, 10000, 14, 100000 = 17
    // VlogE = 190만 * 3 = 570만 + 3V = 600만
    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int M;
        private int[] friends = new int[3];
        private int[][] distances = new int[3][];
        private ArrayList<Node>[] graph;

        // 가장 가까운 거리 중에 가장 먼 거리를 찾고 만약 가장 먼 거리가 같다면 가장 숫자가 작은 인덱스
        private int[][] results = new int[3][2];

        public void solve() throws IOException {
            N = stoi(br.readLine());
            graph = new ArrayList[N + 1];

            for (int i = 0; i < N + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            StringTokenizer st = getStringTokenizer();

            for (int i = 0; i < 3; i++) {
                friends[i] = stoi(st.nextToken());
                distances[i] = new int[N + 1];
                Arrays.fill(distances[i], (int) 1e9);
            }

            M = stoi(br.readLine());

            for (int i = 0; i < M; i++) {
                st = getStringTokenizer();
                int start = stoi(st.nextToken());
                int end = stoi(st.nextToken());
                int distance = stoi(st.nextToken());

                graph[start].add(new Node(end, distance));
                graph[end].add(new Node(start, distance));
            }

            for (int i = 0; i < 3; i++) {
                findDijkstra(i);
            }
            
            int result = findResult();
            System.out.print(result);
        }

        private int findResult() {
            int minIndex = -1;
            int maxDistances = 0;
            for (int i = 1; i < N + 1; i++) {
                int min = (int) 1e9;
                for (int j = 0; j < 3; j++) {
                    if (distances[j][i] < min) {
                        min = distances[j][i];
                    }
                }

                if (min > maxDistances) {
                    maxDistances = min;
                    minIndex = i;
                }
            }

            return minIndex;
        }

        private void findDijkstra(int start) {
            int startPoint = friends[start];
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
            pq.offer(new Node(startPoint, 0));

            while (!pq.isEmpty()) {
                Node now = pq.poll();

                if (now.distance > distances[start][now.index]) {
                    continue;
                }

                for (Node next : graph[now.index]) {
                    int cost = now.distance + next.distance;

                    if (distances[start][next.index] > cost) {
                        distances[start][next.index] = cost;
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
