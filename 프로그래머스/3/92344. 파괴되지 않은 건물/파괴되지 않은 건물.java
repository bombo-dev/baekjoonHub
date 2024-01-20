import java.util.*;

class Solution {
    private int maxR;
    private int maxC;
    
    public int solution(int[][] board, int[][] skill) {
        int[][] infos = new int[board.length][board[0].length];
        maxR = board.length - 1;
        maxC = board[0].length - 1;
        
        initializeInfo(infos, skill);
        int answer = calc(board, infos);
        
        return answer;
    }
    
    private void initializeInfo(int[][] infos, int[][] skills) {
        for (int i = 0; i < skills.length; i++) {
            int[] skill = skills[i];
            setInfo(infos, skill);
        }
        
        for (int i = 1; i < infos.length; i++) {
            for (int j = 0; j < infos[i].length; j++) {
                infos[i][j] += infos[i - 1][j];
            }
        }
        
        for (int i = 0; i < infos.length; i++) {
            for (int j = 1; j < infos[i].length; j++) {
                infos[i][j] += infos[i][j - 1];
            }
        }
    }
    
    private void setInfo(int[][] infos, int[] skill) {
        int type = skill[0];
        int x1 = skill[1];
        int y1 = skill[2];
        int x2 = skill[3];
        int y2 = skill[4];
        int degree = skill[5];
        
        if (type == 1) {
            degree = -degree;
        }
        
        infos[x1][y1] += degree;
        
        if (x2 != maxR) {
            infos[x2 + 1][y1] += -degree;
        }
        
        if (y2 != maxC) {
            infos[x1][y2 + 1] += -degree;
        }
        
        if (x2 != maxR && y2 != maxC) {
            infos[x2 + 1][y2 + 1] += degree;
        }
    }
    
    private int calc(int[][] board, int[][] infos) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] += infos[i][j];
                if (board[i][j] > 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
}