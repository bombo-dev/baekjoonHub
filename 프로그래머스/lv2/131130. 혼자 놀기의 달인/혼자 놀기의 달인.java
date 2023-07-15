import java.util.*;

class Solution {
    int[] newCards;
    boolean[] visited;
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    static int total = 0;
    public int solution(int[] cards) {
        int answer = 0;
        newCards = new int[cards.length + 1];
        visited = new boolean[cards.length + 1];
        
        for (int i = 1; i < cards.length + 1; i++) {
            newCards[i] = cards[i - 1];
        }
        
        for (int i = 1; i < cards.length + 1; i++) {
            total = 0;
            if (!visited[i]) {
                dfs(i);
            }
            pq.offer(total);
        }
        
        if (pq.size() >= 2) {
            int a = pq.poll();
            int b = pq.poll();
            answer = a * b;
        } 
        
        
        
        return answer;
    }
    
    void dfs(int i) {
        
        if (visited[i]) {
            return;
        }
        
        visited[i] = true;
        total++;
        int nextBox = newCards[i];
        dfs(nextBox);
        return;
    }
}