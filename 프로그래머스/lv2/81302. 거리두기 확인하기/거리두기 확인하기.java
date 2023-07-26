import java.util.*;

class Solution {
    
    // 반대 방향을 알아야함 3 - d 상 좌 우 하
    static final int[] dx = {0, -1, 1, 0};
    static final int[] dy = {-1, 0, 0, 1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for (int t = 0; t < answer.length; t++) {
            String[] place = places[t];
            char[][] room = new char[places.length][places[0].length];
            
            for (int i = 0; i < place.length; i++) {
                room[i] = place[i].toCharArray();
            }
            
            if (isDistanced(room)) {
                answer[t] = 1;
            }
        }
        return answer;
    }
    
    // 이전 방향으로 되돌아가면 안된다.
    private boolean isNextToVolunteer(char[][] room, int x, int y, int exclude) {
        for (int d = 0; d < 4; d++) {
            
            if (d == exclude) {
                continue;
            }
            
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) {
                continue;
            }
            
            if (room[ny][nx] == 'P') {
                return true;
            }
        }
        return false;
    }
    
    private boolean isDistanced(char[][] room) {
        for (int y = 0; y < room.length; y++) {
            for (int x = 0; x < room[y].length; x++) {
                if (room[y][x] != 'P') {
                    continue;
                }
                
                if (!isDistanced(room, x, y)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean isDistanced(char[][] room, int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) {
                continue;
            }
            
            if (room[ny][nx] == 'P') {
                return false;
            }
            
            if (room[ny][nx] == 'O') {
                if (isNextToVolunteer(room, nx, ny, 3 - d)) {
                    return false;
                }
            }
        }
        return true;
    }
}