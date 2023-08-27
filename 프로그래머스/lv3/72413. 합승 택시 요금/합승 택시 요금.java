import java.util.*;

class Solution {
    private List<List<Node>> graph = new ArrayList<>();
    private int N;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int distance = fare[2];
            graph.get(u).add(new Node(v, distance));
            graph.get(v).add(new Node(u, distance));
        }
        
        int result = Integer.MAX_VALUE;
        
        int[] togetherDistance = dijkstra(s);
        for (int i = 1; i < n + 1; i++) {
            int[] distances = dijkstra(i);
            int total = togetherDistance[i] + distances[a] + distances[b];
            
            result = Math.min(result, total);
        }
        
        return result;
    }
    
    private int[] dijkstra(int start) {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});
        
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int u = now[0];
            int distance = now[1];
            
            if (distances[u] < distance) {
                continue;
            }
            
            for (Node node : graph.get(u)) {
                int cost = distance + node.distance;
                
                if (distances[node.index] > cost) {
                    distances[node.index] = cost;
                    pq.offer(new int[]{node.index, cost});
                }
            }
        }
        
        return distances;
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