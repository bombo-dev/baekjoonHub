import java.util.*;

class Solution {
    // 디딤돌의 숫자가 0이 되면 밟을 수 없을 때 그 다음 디딤돌을 밟는데, 가장 가까운 디딤돌을 밟아야 함.
    // 최대 몇 명까지 징검다리를 건 널 수 있는가.
    // 연속으로 0이 k개 나오는 것을 찾아야 함.
    
    public int solution(int[] stones, int k) {
        // 디딤돌에서 가장 최솟값을 찾는다.
        // 최소값은 이진 탐색으로 찾는다.
        // 근데 stones도 20만
        // k도 20만인데, 1 ~ 20만
        
        // k = 1 ~ max 까지 돌면서, mid를 뺐을 때 k개 이상 있는가.
        
        // 1. max 값 찾기
        int max = 0;
        max = Arrays.stream(stones)
            .max()
            .getAsInt();
        
        int result = findResult(0, max, k, stones);
        return result;
    }
    
    // 3. 연속으로 k개 이상의 마지막 값 찾기. Upper Bound
    private int findResult(int start, int end, int k, int[] stones) {
        int currentStart = start;
        int currentEnd = end;
        
        while (currentStart < currentEnd) {
            int mid = (currentStart + currentEnd) / 2;
            
            if (findOverLimit(stones, mid, k)) {
                currentEnd = mid;
            } else {
                currentStart = mid + 1;
            }
        }
        
        return currentStart;
    }
    
    // 2. 이진탐색으로 시작점부터 끝까지 갔을 때 연속으로 k개 이상 0인 값이 있는지 확인
    private boolean findOverLimit(int[] stones, int mid, int k) {
        int count = 0;
        
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] - mid <= 0) {
                count++;
                if (count == k) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        
        return false;
    }
}