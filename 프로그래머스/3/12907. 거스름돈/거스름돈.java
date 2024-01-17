import java.util.*;

class Solution {
    // money의 배열의 길이 100
    // 시작 금액의 최대 10만
    // 개수는 1,000,000,007로 나눈 나머지 
    // 모듈러 연산은 계산 결과에 연산을 미치지 않으므로 항상 결과에 나머지를 사용하기.
    private static final int MODULAR = 1000000007;
    
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1; // 금액이 0인 경우의 초기값 설정

        for (int coin : money) {
            for (int i = coin; i <= n; i++) {
                dp[i] += dp[i - coin];
                dp[i] %= MODULAR;
            }
        }

        return dp[n];
    }
}