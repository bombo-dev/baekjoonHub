import java.util.*;

class Solution {
    int[] newCards;
    boolean[] visited;
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    public int solution(int[] cards) {
        int answer = 0;
        newCards = new int[cards.length + 1];
        visited = new boolean[cards.length + 1];
        
        for (int i = 1; i < cards.length + 1; i++) {
            newCards[i] = cards[i - 1];
        }
        
        for (int i = 1; i < cards.length + 1; i++) {
            int total = 0;
            if (!visited[i]) {
                total = dfs(i, total);
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
    
    int dfs(int i, int total) {
        
        if (visited[i]) {
            return total;
        }
        
        visited[i] = true;
        int nextBox = newCards[i];
        return dfs(nextBox, total + 1);
    }
}