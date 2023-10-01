import java.util.*;

// 마지막 바위로 부터 도착지점까지의 거리도 계산을 해야 한다.
// 따라서, 주어지는 바위의 배열보다 한 칸을 더 추가하여 distance를 원소에 추가한다.

// 이어서, n개 만큼의 바위를 제거해서 떨어져 있는 거리의 최솟 값을 구해야 한다.
// 이를 위해서, 먼저 배열을 정렬을 해준다. O(MlogM) = 약 80만

// 먼저 n 만큼 바위를 제거하는 경우를 찾는 알고리즘을 생각해보자.
// 완전탐색을 하게 되면 적어도 50000C25000 이므로, 완전 탐색은 배제한다.

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        rocks = Arrays.copyOf(rocks, rocks.length + 1);
        rocks[rocks.length - 1] = distance;
        Arrays.sort(rocks);
        
        // distance가 10억까지 있으므로, 거리의 최솟값은 10억보다 낮아 int 타입
        int result = binarySearch(distance, rocks, n);
        return result;
    }
    
    // distance의 크기가 10억으로 보아. 이진탐색을 고려한다.
    // 바위는 1개 이상이므로, start는 1부터 시작한다.
    // end는 distance가 된다. 하지만, 이때 마지막을 포함하려면 distance + 1 이 되어야 한다.
    private int binarySearch(int distance, int[] rocks, int n) {
        int start = 1;
        int end = distance + 1; 
        
        // 끝나는 지점은 거리가 1 이상이여야 한다.
        while (end - start > 1) {
            int d = (start + end) / 2;
            
            if (isValid(d, rocks, n)) {
                start = d;
            } else {
                end = d;
            }
        }
        
        return start;
    }
    
    // 제거한 바위 개수가 결국 n보다 작아야 거리의 최솟값을 구할 수 있다.
    // 이미 주어진 distance 중에서 d 만큼 도달 할 수 있다면 그게 최대값이 된다.
    private boolean isValid(int d, int[] rocks, int n) {
        int removed = 0; // 지운 돌의 개수
        int last = 0; // 마지막 돌의 위치
        
        for (int rock : rocks) {
            if (rock - last < d) {
                removed++;
                continue;
            }
            
            last = rock;
        }
        
        return removed <= n;
    }
}