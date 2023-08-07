import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private int N;

        public void solution() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            int[] sequence = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                sequence[i] = Integer.parseInt(st.nextToken());
            }

            long count = getListOfUniqueNumbers(sequence);
            System.out.print(count);
        }

        private long getListOfUniqueNumbers(int[] sequence) {
            boolean[] numberCheckArr = new boolean[100001];
            long count = 0;
            int startingPoint = 0;
            int endPoint = 0;

            while (startingPoint < sequence.length) {

                while (endPoint + 1 <= N && !numberCheckArr[sequence[endPoint]]) {
                    numberCheckArr[sequence[endPoint]] = true;
                    endPoint++;
                }
                count += (endPoint - startingPoint);

                numberCheckArr[sequence[startingPoint]] = false;
                startingPoint++;
            }

            return count;
        }
    }
}
