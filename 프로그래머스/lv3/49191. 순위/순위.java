import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] graph = new boolean[n+1][n+1];
        
        for (int[] result : results) {
            int a = result[0];
            int b = result[1];
            graph[a][b] = true;
        }

        for (int mid = 1; mid <= n; mid++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    if (graph[start][mid] && graph[mid][end]) {
                        graph[start][end] = true;
                    }
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] || graph[j][i]) count++;
            }
            if (count == n - 1) answer++;
        }
        
        return answer;
    }
}