import java.util.*;

class Solution {
    private int[][] map;
    private List<Integer> results = new ArrayList<>();
    
    public int[] solution(int rows, int columns, int[][] queries) {
        initMap(rows, columns);
        rotateMap(queries);
        return results.stream().mapToInt(i -> i).toArray();
    }
    
    private void rotateMap(int[][] queries) {
        int playCount = queries.length;
        
        for (int i = 0; i < playCount; i++) {
            int result = getAfterRotateSmaller(queries[i]);
            results.add(result);
        }
    }
    
    private int getAfterRotateSmaller(int[] query) {

        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;

        int tmp = map[x1 + 1][y1];
        int smaller = tmp;
        int tmp1;

        for (int y = y1; y <= y2; y++) {
            tmp1 = map[x1][y];
            map[x1][y] = tmp;
            tmp = tmp1;
            smaller = Math.min(smaller, tmp);
        }

        for (int x = x1 + 1; x <= x2; x++) {
            tmp1 = map[x][y2];
            map[x][y2] = tmp;
            tmp = tmp1;
            smaller = Math.min(smaller, tmp);

        }

        for (int y = y2 - 1; y >= y1; y--) {
            tmp1 = map[x2][y];
            map[x2][y] = tmp;
            tmp = tmp1;
            smaller = Math.min(smaller, tmp);
        }

        for (int x = x2 - 1; x >= x1; x--) {
            tmp1 = map[x][y1];
            map[x][y1] = tmp;
            tmp = tmp1;
            smaller = Math.min(smaller, tmp);
        }
        
        return smaller;
    }
    
    private void initMap(int rows, int columns) {
        int value = 1;
        map = new int[rows][columns];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = value++;
            }
        }
    }
    
    private void printMap() {
        for(int[] maps: map) {
            System.out.println(Arrays.toString(maps));
        }
    }
}