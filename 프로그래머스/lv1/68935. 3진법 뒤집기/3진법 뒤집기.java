class Solution {
    public int solution(int n) {
        StringBuilder sb = new StringBuilder();
        String thirdRadix = Integer.toString(n, 3);
        
        String reversed = sb.append(thirdRadix).reverse().toString();
        int result = Integer.parseInt(reversed, 3);
        return result;
    }
}