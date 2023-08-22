import java.util.*;

class Solution {
    private List<List<Integer>> graph = new ArrayList<>();
    private int maxSheep = 0;
    
    public int solution(int[] info, int[][] edges) {
        
        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            var u = edges[i][0];
            var v = edges[i][1];
            graph.get(u).add(v);
        }
        List<Integer> next = new ArrayList<>();
        findMaxSheep(info, next, 0, 0, 0);
        return maxSheep;
    }
    
    private void findMaxSheep(int[] info, List<Integer> list, int sheep, int wolf, int node) {
        if (info[node] == 0) {
            sheep += 1;
        }  else {
            wolf += 1;
        }
        
        if (sheep <= wolf) {
            return;
        }
        
        maxSheep = Math.max(sheep, maxSheep);
        
        List<Integer> next = new ArrayList<>(list);
        if (!graph.get(node).isEmpty()) {
            next.addAll(graph.get(node));
        }
        
        next.remove(Integer.valueOf(node));
        
        for (int n : next) {
            findMaxSheep(info, next, sheep, wolf, n);
        }
    }
}