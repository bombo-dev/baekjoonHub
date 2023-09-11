import java.util.*;

class Solution {
    private int min = Integer.MAX_VALUE;
    private boolean [] visited;
    private ArrayList<ArrayList<Integer>> graph;
    public int solution(int n, int[][] wires) {
        for (int i = 0; i < wires.length; i++) {
            graph = init(n);
            updateList(i, wires);
            calcDiff(n);
        }
        return min;
    }
    
    ArrayList<ArrayList<Integer>> init(int n) {
        visited = new boolean[n + 1];
        graph = new ArrayList<>();
        
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        return graph;
    }
    
    void updateList(int i, int[][] wires) {
        for(int w = 0; w < wires.length; w++) {
            if(w == i)
                continue;
            int start = wires[w][0];
            int end = wires[w][1];
            
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
    }
    
    void calcDiff(int n) {
        boolean isFirst = false;
        int first = 0;
        int second = 0;
        
        for(int i = 1; i <= n; i++) {
            if(!visited[i]){
                if(!isFirst){
                    first = bfs(i);
                    isFirst = true;
                }
                else
                    second = bfs(i);
            }
        }
        min = Math.min(min, Math.abs(first-second));
    }
    
    int bfs(int i) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        visited[i] = true;
        int count = 1;
        
        
        while(!q.isEmpty()) {
            int now = q.poll();
            count++;
            for(int next : graph.get(now)) {
                if(!visited[next]){
                    q.offer(next); 
                    visited[next] = true;
                }
            }
        }
        
        return count;
    }
    
    
}