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
        private int M;

        public void solution() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            int[] arr = settingArr(br);
            Arrays.sort(arr);

            M = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                if (Arrays.binarySearch(arr, Integer.parseInt(st.nextToken())) >= 0) {
                    sb.append(1).append(" ");
                } else {
                    sb.append(0).append(" ");
                }
            }

            System.out.println(sb);
        }

        private int[] settingArr(BufferedReader br) throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            return arr;
        }
    }
}
