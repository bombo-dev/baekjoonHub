import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> gemTypes = new HashSet<>(Arrays.asList(gems));
        Map<String, Integer> gemCount = new HashMap<>();
        int start = 0;
        int end = 0;
        int[] answer = {1, gems.length};
        int minLen = gems.length + 1;

        while (end < gems.length) {
            gemCount.put(gems[end], gemCount.getOrDefault(gems[end], 0) + 1);
            end++;

            while (gemCount.size() == gemTypes.size()) {
                if (end - start < minLen) {
                    minLen = end - start;
                    answer[0] = start + 1;
                    answer[1] = end;
                }

                gemCount.put(gems[start], gemCount.get(gems[start]) - 1);
                if (gemCount.get(gems[start]) == 0) {
                    gemCount.remove(gems[start]);
                }
                start++;
            }
        }

        return answer;
    }
}
