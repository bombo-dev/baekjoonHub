class Solution {
    // 임의로 두 풍선 그 중 하나만 뿌시기
    // 번호가 작은 풍선은 단 한 번만.
    // 번호가 큰 풍선 만 부실 수 있음.
    
    // 임의로 2개.
    
    // 만약 값을 찾아다닌다면 N^2
    
    // 1. 마지막에 남을 수 있는 풍선을 찾기. (완탐)
    // 2. 전체 풍선 개수 - 마지막까지 남기는 것이 불가능한 풍선 찾기
    
    // 마지막 까지 남기는 것이 불가능 한 것은 좌우가 둘 다 자신보다 작을 경우이다.
    
    public int solution(int[] a) {
        int minLeft = a[0];
        int minRight = a[a.length -1];
        
        int[] left = new int[a.length];
        int[] right = new int[a.length];
        
        for (int i = 1; i < a.length; i++) {
            if (a[i] < minLeft) {
                minLeft = a[i];
            }
            
            left[i] = minLeft;
        }
        
        for (int i = a.length - 2; i > 0; i--) {
            if (a[i] < minRight) {
                minRight = a[i];
            }
            
            right[i] = minRight;
        }
        
        if (a.length == 1) {
            return 1;
        } 
        
        // 양 쪽 끝은 무조건 살아남을 수 있다.
        int answer = 2;
        
        for (int i = 1; i < a.length - 1; i++) {
            if (left[i] < a[i] && right[i] < a[i]) {
                continue;    
            }
            answer++;
        }
        
        return answer;
    }
}