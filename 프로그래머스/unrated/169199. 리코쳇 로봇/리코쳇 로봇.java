import java.util.*;

class Solution {
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1, 0, -1};
    private int N;
    private int M;
    private char[][] newBoard;
    private int[] start;
    private int[] goal;
    
    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        
        newBoard = new char[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newBoard[i][j] = board[i].charAt(j);
                if (newBoard[i][j] == 'R') {
                    start = new int[]{i, j};
                } else if (newBoard[i][j] == 'G') {
                    goal = new int[]{i, j};
                }
            }
        }
        
        int result = bfs();
        
        return result;
    }
    
    private int bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(new int[]{start[0], start[1], 0});
        
        while(!q.isEmpty()) {
            int[] position = q.poll();
            int px = position[0];
            int py = position[1];
            int count = position[2];
            
            // System.out.println("위치 : " + Arrays.toString(position));
            
            if (px == goal[0] && py == goal[1]) {
                return count;
            }
            
            if (count == 10000) {
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = px;
                int ny = py;
                
                while (true) {
                    nx += dx[i];
                    ny += dy[i];
                    
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || newBoard[nx][ny] == 'D') {
                        nx -= dx[i];
                        ny -= dy[i];
                        
                        if (!visited[nx][ny]) {
                            visited[nx][ny] = true;
                            q.offer(new int[]{nx, ny, count + 1});
                        }
                        break;
                    }
                }
            }
        }
        
        return -1;
    }
}