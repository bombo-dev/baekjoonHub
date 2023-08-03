import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> scoreMap = new HashMap<>();
        createCondition(info, scoreMap);
        
        return calc(query, scoreMap);
    }
    
    private void createCondition(String[] info, Map<String, List<Integer>> scoreMap) {
        StringBuilder sb = new StringBuilder();
        for (String information : info) {
            String[] splitInfo = information.split(" ");
            String[] result = new String[splitInfo.length - 1];
            int score = Integer.parseInt(splitInfo[splitInfo.length - 1]);
            
            putScoreMap(scoreMap, splitInfo, score, result, 0, sb);
        }
        
        for (List<Integer> list : scoreMap.values()) {
            Collections.sort(list);
        }
    }
    
    private void putScoreMap(Map<String, List<Integer>> scoreMap, String[] splitInfo, int score, String[] result, int index, StringBuilder sb) {
        if (index == splitInfo.length - 1) {
            settingMap(scoreMap, result, score, sb);
            return;
        }
        
        result[index] = splitInfo[index];
        putScoreMap(scoreMap, splitInfo, score, result, index + 1, sb);
        result[index] = "-";
        putScoreMap(scoreMap, splitInfo, score, result, index + 1, sb);
        
    }
    
    private void settingMap(Map<String, List<Integer>> scoreMap, String[] result, int score, StringBuilder sb) {
            sb.setLength(0);
        
            for (int i = 0 ; i < result.length; i++) {
                sb.append(result[i]);
            }
            
            String key = sb.toString();
            
            if(!scoreMap.containsKey(key)) {
                scoreMap.put(key, new ArrayList<>());
                scoreMap.get(key).add(score);
            } else {
                scoreMap.get(key).add(score);
            }
    }
    
    private int[] calc(String[] query, Map<String, List<Integer>> scoreMap) {
        
        int[] result = new int[query.length];
        
        for (int i = 0; i < query.length; i++) {
            String queryInfo = query[i].replaceAll("and", "");
            String[] splitQuery = queryInfo.split(" +");
            
            int score = Integer.parseInt(splitQuery[splitQuery.length - 1]);
            String key = String.join("", Arrays.copyOf(splitQuery, splitQuery.length - 1));
            
            if (!scoreMap.containsKey(key)) {
                result[i] = 0;
            } else {
                result[i] = scoreMap.get(key).size() - count(scoreMap.get(key), score);
            }
        }
        
        return result;
    }
    
    private int count(List<Integer> scores, int score) {
        return binarySearch(scores, score);
    }
    
    private int binarySearch(List<Integer> scores, int score) {
        int start = 0;
        int end = scores.size();
        
        while(start < end) {
            int mid = (start + end) / 2;
            
            if (scores.get(mid) >= score) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        
        return start;
    }
}