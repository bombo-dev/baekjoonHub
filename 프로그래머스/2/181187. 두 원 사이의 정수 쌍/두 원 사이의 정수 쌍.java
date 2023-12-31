class Solution {
    // 더 큰 반지름을 가진 원 안에 존재하는 점의 개수 
    // - 더 작은 반지름을 가진 원 안에 존재하는 점의 개수 
    // + 4
    // 원 안에서 존재 할 수 있는 정 점의 개수
    
    // 각 사분면에 존재 하는 점의 개수 * 4
    // (r2 - r1 + 1) * 4
    private int big;
    private int small;
    private int remain = 0;
    
    // r1 < r2
    public long solution(int r1, int r2) {
        long answer = 0;
        for( int i = 1; i <= r2 ; i++){
            double y2 = Math.sqrt(Math.pow(r2,2) - Math.pow(i,2));
            double y1 = Math.sqrt(Math.pow(r1,2) - Math.pow(i,2));
            answer += ((long)y2 - (long)Math.ceil(y1) + 1);
        }
        answer *= 4;
        
        return answer;
    }
}