import java.util.*;

class Solution {
    public int solution(String s) {
        int min = Integer.MAX_VALUE;
        for (int length = 1; length <= s.length(); length++) {
            int compressed = compress(s, length);
            
            if (compressed < min) {
                min = compressed;
            }
        }
        return min;
    }
    
    
    private int compress(String source, int length) {
        StringBuilder sb = new StringBuilder();
        
        String last = "";
        int count = 0;
        
        for (String token : split(source, length)) {
            if (token.equals(last)) {
                count++;
            } else {
                if (count > 1) {
                    sb.append(count);
                }
                sb.append(last);
                last = token;
                count = 1;
            }
        }
        
        if (count > 1) {
            sb.append(count);
        }
        sb.append(last);
        
        return sb.length();
    }
    
    private List<String> split(String source, int length) {
        List<String> tokens = new ArrayList<>();
        
        for (int startIndex = 0; startIndex < source.length(); startIndex += length) {
            int endIndex = startIndex + length;
            if (endIndex > source.length()) {
                endIndex = source.length();
            }
            
            tokens.add(source.substring(startIndex, endIndex));
        }
        
        return tokens;
    }
}