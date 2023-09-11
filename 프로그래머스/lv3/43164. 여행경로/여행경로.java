import java.util.*;

class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    
    public String[] solution(String[][] tickets) {
        // graph 구성
        for(String[] ticket : tickets){
            // 출발지가 키
            String key = ticket[0];
            if(!graph.containsKey(key)){
                graph.put(key, new PriorityQueue<>());
            }
            // 목적지는 값
            graph.get(key).offer(ticket[1]);
        }

        // 패러미터로 빈 리스트 주는 이유는 어차피 하나 필요해서임
        return dfs(new ArrayList<>(), "ICN").toArray(new String[0]);
    }

    private List<String> dfs(List<String> list, String key){
        if(!graph.containsKey(key) || graph.get(key).isEmpty()){
            // 현재 공항을 출발지로 하는 경로가 없으면 현재 공항만을 리턴
            list.add(key);
            return list;
        }

        list.add(key);
        // graph의 pq에서 자식 공항을 빼내어 탐색에 사용한다.
        List<String> right = dfs(new ArrayList<>(), graph.get(key).poll());
        // 위의 베이스 케이스가 실행되어야 제어가 여기로 넘어오므로,
        // 이때의 상황은 남은 경로가 없거나 / 먼저 간 경로가 편도 경로였거나 둘 중 하나
        if(!graph.get(key).isEmpty()){
            // pq를 사용했기 때문에 티켓을 통해 방문한 공항은 pq에서 전부 빠져있다.
            // 그렇기 때문에 현재 공항에 티켓이 남아있으면, 편도 분기가 있는 공항이다
            List<String> left = dfs(new ArrayList<>(), graph.get(key).poll());
            // 같은 방식으로 탐색해준다.
            // 추가 탐색은 while문이나 for문을 통해 남은 티켓이 없을 때까지 할 필요는 없다
            // 이유는 편도 경로는 최대 1개까지만 존재할 수 있기 때문
            list.addAll(left); // 나중에 구한 것이 순환 경로이므로, 순환 경로를 먼저 병합
        }
        // 부모를 기준으로 자식 경로를 병합
        list.addAll(right);
        // 현재 공항을 루트로 하는 최종 경로를 리턴
        return list;
    }
}