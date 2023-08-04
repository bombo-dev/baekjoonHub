import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private int N;
        private int C;
        private int[] house;

        public void solution() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            house = new int[N];

            for (int i = 0; i < N; i++) {
                house[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(house);
            int maxDistance = parametricSearch(house);
            System.out.print(maxDistance);
        }

        private int parametricSearch(int[] house) {
            int start = 1;
            int end = house[N - 1] - house[0];
            int maxDistance = 0;
            
            while (start <= end) {
                int mid = (start + end) / 2;
                int count = 1;
                int preHouse = house[0];
                
                for (int i = 1; i < N; i++) {
                    if (house[i] - preHouse >= mid) {
                        count++;
                        preHouse = house[i];
                    }
                }
                
                if (count >= C) {
                    maxDistance = Math.max(maxDistance, mid);
                    start = mid + 1;
                } else {
                    end = mid -1;
                }
            }
            
            return maxDistance;
        }

    }
}
