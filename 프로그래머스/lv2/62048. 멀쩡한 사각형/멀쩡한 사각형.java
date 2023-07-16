import java.math.*;

class Solution {
    public long solution(int w, int h) {
        long answer = 0;
        long lw = (long)w;
        long lh = (long)h;
        long gcd = BigInteger.valueOf(lw).gcd(BigInteger.valueOf(lh)).longValue();
        return lw * lh - (lw + lh - gcd);
    }
    
    public long gcd(long w, long h) {
        
        long big = Math.max(w, h);
        long small = Math.min(w, h);
        
        while(small != 0) {
            long calc = big % small;
            big = small;
            small = calc;
        }
        
        return big;
    }
}