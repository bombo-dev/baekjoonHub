class Solution {
    private int max = Integer.MIN_VALUE;
    private int[] dp1;
    private int[] dp2;
    public int solution(int sticker[]) {
        if (sticker.length == 1) {
            return sticker[0];
        }
        
        // 첫 번째 선택한 경우
        dp1 = new int[sticker.length];
        
        // 첫 번째 선택하지 않은 경우
        dp2 = new int[sticker.length];
        
        dp1[0] = sticker[0];
        dp1[1] = dp1[0];
        
        dp2[0] = 0;
        dp2[1] = sticker[1];
        
        for (int i = 2; i < sticker.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 2] + sticker[i], dp1[i - 1]);
        }
        
        for (int i = 2; i < sticker.length; i++) {
            dp2[i] = Math.max(dp2[i - 2] + sticker[i], dp2[i - 1]);
        }

        int answer = Math.max(dp1[sticker.length - 2], dp2[sticker.length - 1]);
        return answer;
    }
}