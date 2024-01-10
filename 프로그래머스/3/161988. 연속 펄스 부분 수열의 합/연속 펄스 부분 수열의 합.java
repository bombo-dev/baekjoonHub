class Solution {
    // sequence의 길이는 50만
    // logN = 19;
    // NlogN 가능
    
    // 1, -1, 1 을 곱 할 수도 있고
    // -1, 1, -1 을 곱 할 수도 있다.
    
    // 두 개의 상태를 가진다.
    // dp[2][N]을 가진다.
    // 자신이 혼자 있는 것, 그리고 계산 된 결과를 생각했을 때 더 큰 값을 저장한다.
    
    private long[][] dp;
    private long max = 0;
    
    public long solution(int[] sequence) {
        long answer = 0;
        dp = new long[2][sequence.length];
        // 첫 번째가 1로 시작
        // 두 번째가 -1로 시작
        // 두 개의 상태에 따라 무엇이 곱해졌는지 알 수 가 없다. 상태 추가.
        dp[0][0] = sequence[0] * 1;
        dp[1][0] = sequence[0] * -1;
        max = Math.max(dp[0][0], dp[1][0]);
        
        
        
        for (int i = 1; i < sequence.length; i++) {
            dp[0][i] = Math.max(sequence[i], sequence[i] + dp[1][i - 1]);
            dp[1][i] = Math.max(sequence[i] * -1, sequence[i] * -1 + dp[0][i - 1]);
            max = Math.max(max, Math.max(dp[0][i], dp[1][i]));
        }        
        
        return max;
    }
}