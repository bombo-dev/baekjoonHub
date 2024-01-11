import java.util.*;

class Node implements Comparable<Node> {
    
    int direction;
    int price;
    
    public Node(int direction, int price){
        this.direction = direction;
        this.price = price;
    }
    
    @Override
    public int compareTo(Node n){
        return this.price - n.price;
    }
}

class Solution {
    static ArrayList<ArrayList<Node>> graph;
    static int[] d;
    static final int INF = (int)1e9;
    int max = 0;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        graph = new ArrayList<>();
        
        for(int i = 0; i < n + 1; i++){
            graph.add(new ArrayList<>());
        }
        
        d = new int[n + 1];
        Arrays.fill(d, INF);
        
        for(int i = 0; i < edge.length; i++){
            graph.get(edge[i][0]).add(new Node(edge[i][1], 0));
            graph.get(edge[i][1]).add(new Node(edge[i][0], 0));
        }
        
        dijkstra(1);
        
        for(int i = 1; i <= n; i++){
            if(d[i] == max)
                answer++;
        }
        return answer;
    }
    
    public void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int direction = now.direction;
            int price = now.price;
            
            if(price > d[direction])
                continue;
            
            for(Node node : graph.get(direction)){
                int cost = price + 1;
                if(d[node.direction] > cost){
                    max = Math.max(cost, max);
                    d[node.direction] = cost;
                    pq.offer(new Node(node.direction, cost));
                }
            }
        }
    }
}