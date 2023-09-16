import java.util.*;

class Solution {
    private int pos = 1;
    private int stationPos = 0;
    private int answer = 0;
    
    public int solution(int n, int[] stations, int w) {
        
        // pos 부터 시작하여 n까지 탐색
        while (pos <= n) {
            if (pos > stations[stationPos] + w) {
                answer++;
                pos = pos + 2 * w + 1;
            } else if (pos < stations[stationPos] - w) {
                answer++;
                pos = pos + 2 * w + 1;
            } else {
                pos = stations[stationPos] + w + 1;
                
                if (stationPos != stations.length - 1) {
                    stationPos++;
                }
            }
        }

        return answer;
    }
}

// N <= 2억 
    // N 만큼으로 찾을 수가 없다. 그렇다면 log N 만큼으로 찾아야 된다는 이야기.
    // 기본적으로 이분탐색이 있다.
    // 각 stations를 살펴보고 이 보다 작고 최대가 될 수 있는 것을 찾는 것인데.
    // 결국 position을 순환해야한다.
        // 만약 2억이고 w가 1이라면?
        // 매번 ++를 해주면서 가능한지 여부를 판단하여 돌아야 할 것이다.
        // 결국 O(N)이 안된다는 이야기인데...
    // stations size <= 10000
    // Greedy의 관점으로 바라보자.