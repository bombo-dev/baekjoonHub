import java.util.*;

class Solution {
    private int answer = (int)1e9;
    
    public int solution(int storey) {
        // 값의 범위 1억
        int[] dp = new int[storey * 2];
        Arrays.fill(dp, (int)1e9);
        
        dp[storey] = 0;
        
        bfs(storey, dp);
        
        return (int)dp[0];
    }
    
    void bfs(int storey, int[] dp) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(storey);
        
        while(!q.isEmpty()) {
            int value = q.poll();
            
            int radix = String.valueOf(value).length();
            
            for(int i = 0; i < radix; i++) {
                int moved = (int)Math.pow(10, i);
                
                if(dp[0] < dp[value] + 1)
                    continue;
                
                if (value % moved == 0) {
                    if(value + moved < storey * 2) {
                        if((dp[value] + 1) < dp[value + moved]){
                            q.offer(value + moved);
                            dp[value + moved] = dp[value] + 1;
                        }
                    }
                
                    if(value - moved >= 0) {
                        if((dp[value] + 1) < dp[value - moved]) {
                            q.offer(value - moved);
                            dp[value - moved] = dp[value] + 1;
                        }
                    }
                }
            }
        }
    }
    
}