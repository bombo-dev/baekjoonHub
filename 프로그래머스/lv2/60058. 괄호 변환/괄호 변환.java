import java.util.*;

class Solution {
    
    public String solution(String p) {
        String result = solve(p);
        return result;
    }
    
    private String solve(String p) {
        if (p.length() == 0) {
            return p;
        }
        
        StringBuilder sb = new StringBuilder();
        String[] splitValue = split(p);
        
        if (isCorrect(splitValue[0])) {
            return splitValue[0] + solve(splitValue[1]);
        } else { 
            sb.append("(")
                .append(solve(splitValue[1]))
                .append(")")
                .append(reverseBracket(splitValue[0]));
        }
        
        return sb.toString();
    }
    
    private boolean isCorrect(String p) {
        Stack<Character> stack = new Stack<>();
        for (char c : p.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
    
    private static String[] split(String p) {
        int open = 0;
        int close = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                String u = p.substring(0, i + 1);
                String v = "";
                if (p.length() >= i + 1) {
                    v = p.substring(i + 1);
                }
                return new String[] {u, v};
            }
        }
        return new String[] {"", ""};
    }
    
    private String reverseBracket(String p) {
        StringBuilder sb2 = new StringBuilder();
        
        for (int i = 1; i < p.length() - 1; i++) {
            if (p.charAt(i) == '(') {
                sb2.append(")");
            } else {
                sb2.append("(");
            }
        }
        
        return sb2.toString();
    }
}