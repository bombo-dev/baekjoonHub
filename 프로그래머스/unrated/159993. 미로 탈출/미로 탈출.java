import java.util.*;

class Solution {
    String[][] graph;
    int[][] distance;
    int[] startPoint = new int[2];
    int[] leverPoint = new int[2];
    int[] endPoint = new int[2];
    int result = 0;
    boolean[][] visited;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int rowLength = 0;
    int colLength = 0;
    public int solution(String[] maps) {
        rowLength = maps.length;
        colLength = maps[0].length();
        graph = new String[rowLength][colLength];
        
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                graph[i][j] = String.valueOf(maps[i].charAt(j));
                
                if (graph[i][j].equals("S")) {
                    startPoint[0] = i;
                    startPoint[1] = j;
                }
                
                if (graph[i][j].equals("L")) {
                    leverPoint[0] = i;
                    leverPoint[1] = j;
                }
                
                if (graph[i][j].equals("E")) {
                    endPoint[0] = i;
                    endPoint[1] = j;
                }
            }
        }
        int total = bfs(startPoint, leverPoint);
        if (total == 0) {
            return -1;
        }
        result += total;
        total = bfs(leverPoint, endPoint);
        
        if (total == 0) {
            return -1;
        }
        result += total;
        
        return result;
    }
    
    int bfs(int[] start, int[] end) {
        distance = new int[rowLength][colLength];
        visited = new boolean[rowLength][colLength];
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        
        while(!q.isEmpty()) {
            int[] p = q.poll();
            int px = p[0];
            int py = p[1];
            
            if (px == end[0] && py == end[1]) {
                return distance[px][py];
            }
            
            for (int i = 0 ; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];
                
                if (nx < 0 || nx >= rowLength || ny < 0 || ny >= colLength || visited[nx][ny]) {
                    continue;
                }
                
                if (graph[nx][ny].equals("X")) {
                    continue;
                }
                
                q.offer(new int[]{nx, ny});
                distance[nx][ny] = distance[px][py] + 1;
                visited[nx][ny] = true;
            }
        }
        
        return 0;
    }
}