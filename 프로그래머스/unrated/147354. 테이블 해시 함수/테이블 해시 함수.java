import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        Arrays.sort(data, new Comparator<int[]>(){
            
            @Override
            public int compare(int[] data1, int[] data2){
                int result = 0;
                result = data1[col - 1] - data2[col - 1];
                if(result == 0){
                    result = data2[0] - data1[0];
                }
                return result;
            }
        });
        
        
        // row_begin = 2 -> [1], row_end = 3 -> [2]
        for(int i = row_begin - 1; i < row_end; i++){
            int second = 0;
            for(int j = 0; j < data[i].length; j++){
                second += data[i][j] % (i + 1);
            }
            answer = answer ^ second;
        }
        
        return answer;
    }
}