import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int value : money) {
            for (int i = value; i <= n; i++) {
                dp[i] = dp[i] + dp[i - value];
            }
        }
        
        int answer = dp[n];
        return answer;
    }
}