import java.util.*;

class Solution {
    public int[] solution(long n) {
        StringBuilder sb = new StringBuilder();
        String reversed = sb.append(String.valueOf(n)).reverse().toString();
        char[] arr = reversed.toCharArray();
        
        int[] answer = new int[reversed.length()];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = arr[i] - '0';
        }
        
        return answer;
    }
}