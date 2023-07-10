import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static int total = 0;
    static int max = 0;
    static PriorityQueue<Edge> edges = new PriorityQueue<>((a, b) -> a.distance - b.distance);
    static int[] parent = new int[100001];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, distance));
        }

        kruskal();
        System.out.print(total - max);
    }

    static void kruskal() {
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            int start = edge.start;
            int end = edge.end;
            if (unionParent(start, end)) {
                total += edge.distance;
                max = Math.max(max, edge.distance);
            }
        }
    }

    static int findParent(int n) {
        if (parent[n] != n) {
            parent[n] = findParent(parent[n]);
        }
        return parent[n];
    }

    static boolean unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a == b) {
            return false;
        }

        parent[b] = a;
        return true;
    }

    static class Edge {
        int start;
        int end;
        int distance;

        public Edge(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }
}
