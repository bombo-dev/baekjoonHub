import java.util.*;

class Solution {
    private static final int[] dx = {0, 1, -1};
    private static final int[] dy = {1, 0, -1};
    
    public int[] solution(int n) {
        int[][] triangle = new int[n][n];
        int number = 1;
        int x = 0;
        int y = 0;
        int d = 0;
        
        while (true) {
            triangle[y][x] = number++;
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if (nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) {
                d = (d + 1) % 3;
                
                nx = x + dx[d];
                ny = y + dy[d];
                
                if (nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) {
                    break;
                }
            }
            
            x = nx;
            y = ny;
        }
        
        int[] answer = getResult(triangle, number, n);
        return answer;
    }
    
    private int[] getResult(int[][] triangle, int number, int n) {
        int index = 0;
        int[] result = new int[number - 1];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result[index++] = triangle[i][j];
            }
        }
        
        return result;
    }
}