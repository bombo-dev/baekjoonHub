class Solution {
    // 두 개의 방문 배열을 쓴다.
    // 열 방문, 대각선 방문
    private boolean[] colVisited;
    private boolean[][] diaVisited;
    private int count = 0;
    
    public int solution(int n) {
        colVisited = new boolean[n];
        diaVisited = new boolean[n][n];
        
        backTracking(0, n);
        return count;
    }
    
    private void backTracking(int row, int max) {
        if (row == max) {
            count++;
            return;
        }
        
        for (int col = 0; col < max; col++) {
            if (!colVisited[col] && !isVisitedDia(row, col, max)) {
                colVisited[col] = true;
                diaVisited[row][col] = true;
                backTracking(row + 1, max);
                diaVisited[row][col] = false;
                colVisited[col] = false;
            }
        }
    }
    
    private boolean isVisitedDia(int row, int col, int max) {
        return isLeftTop(row, col) || isRightTop(row, col, max);
    }
    
    private boolean isLeftTop(int row, int col) {
        
        while(row >= 0 && col >= 0) {
            if (diaVisited[row][col]) {
                return true;
            }
            row--;
            col--;
        }
        
        return false;
    }
    
    private boolean isRightTop(int row, int col, int max) {
        
        while(row >= 0 && col < max) {
            if (diaVisited[row][col]) {
                return true;
            }
            row--;
            col++;
        }
        
        return false;
    }
}