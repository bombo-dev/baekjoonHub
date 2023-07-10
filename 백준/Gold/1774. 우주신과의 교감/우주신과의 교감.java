import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static List<Universe> list = new ArrayList<>();
    static List<Edge> edges = new ArrayList<>();
    static int[] parent;
    static double total = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        // 부모 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Universe(i + 1, x, y));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            unionParent(a, b);
        }

        for (int i = 0; i < list.size(); i++) {
            Universe universeA = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                Universe universeB = list.get(j);
                edges.add(new Edge(universeA.number, universeB.number, universeA.getDistance(universeB)));
            }
        }
        edges.sort((o1, o2) -> Double.compare(o1.distance, o2.distance));

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int start = edge.start;
            int end = edge.end;
            if (unionParent(start, end)) {
                total += edge.distance;
            }
        }
        double result = Math.round(total * 100) / 100.0;
        String resultFormat = String.format("%.2f", result);

        System.out.print(resultFormat);
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

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
        return true;
    }

    static class Universe {
        int number;
        int x;
        int y;

        public Universe(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }

        public double getDistance(Universe u) {
            return Math.sqrt(Math.pow(this.x - u.x, 2) + Math.pow(this.y - u.y, 2));
        }
    }

    static class Edge {
        int start;
        int end;
        double distance;

        public Edge(int start, int end, double distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }
}
