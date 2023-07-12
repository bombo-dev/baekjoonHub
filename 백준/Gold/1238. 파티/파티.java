import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static int X;
    static List<ArrayList<Node>> graph = new ArrayList<>();
    static final int INF = (int) 1e9;
    static int[] dX;
    static int[] d;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        dX = new int[N + 1];
        Arrays.fill(dX, INF);

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, distance));
        }

        dijkstra();
        int max = 0;

        for (int i = 1; i < N + 1; i++) {
            dijkstra(i);
            max = Math.max(max, dX[i] + d[X]);
        }

        System.out.print(max);
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        pq.offer(new Node(X, 0));
        dX[X] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.distance > dX[now.index]) {
                continue;
            }

            for (Node end : graph.get(now.index)) {
                int cost = dX[now.index] + end.distance;

                if (cost < dX[end.index]) {
                    dX[end.index] = cost;
                    pq.offer(new Node(end.index, cost));
                }
            }
        }
    }

    static void dijkstra(int i) {
        d = new int[N + 1];
        Arrays.fill(d, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        pq.offer(new Node(i, 0));
        d[i] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.distance > d[now.index]) {
                continue;
            }

            for (Node end : graph.get(now.index)) {
                int cost = d[now.index] + end.distance;

                if (cost < d[end.index]) {
                    d[end.index] = cost;
                    pq.offer(new Node(end.index, cost));
                }
            }
        }
    }

    static class Node {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
}
