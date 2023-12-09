import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int[] numbers;

        public void solve() throws IOException {
            N = stoi(br.readLine());
            StringTokenizer st = getStringTokenizer();

            numbers = new int[N];

            for (int i = 0; i < N; i++) {
                numbers[i] = stoi(st.nextToken());
            }

            // 1. 뒤에서부터 A[i - 1] < A[i] 인 index 구하기
            int index = N - 1;

            while (index > 0 && numbers[index - 1] < numbers[index]) {
                index--;
            }

            if (index == 0) {
                System.out.print(-1);
                return;
            }

            int standardPoint = index - 1;
            int diff = Integer.MAX_VALUE;

            // index부터 standardPoint보다 작으면서 차이가 가장 적은 index 찾기
            while (index < N && numbers[standardPoint] > numbers[index] && numbers[standardPoint] - numbers[index] < diff) {
                diff = numbers[standardPoint] - numbers[index];
                index++;
            }

            index -= 1;

            // index랑 standardPoint 스왑
            int temp = numbers[standardPoint];
            numbers[standardPoint] = numbers[index];
            numbers[index] = temp;

            // standardPoint + 1 부터 마지막까지 reverse

            int size = N - (standardPoint + 1);
            int[] temps = new int[size];

            for (int i = 0; i < size; i++) {
                temps[i] = numbers[N - 1 - i];
            }

            for (int i = 0; i < size; i++) {
                numbers[standardPoint + 1 + i] = temps[i];
            }

            // 출력
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(numbers[i]).append(" ");
            }

            System.out.print(sb);
        }

        private StringTokenizer getStringTokenizer(String delim) throws IOException {
            return new StringTokenizer(br.readLine(), delim);
        }

        private StringTokenizer getStringTokenizer() throws IOException {
            return new StringTokenizer(br.readLine());
        }

        private int stoi(String number) {
            return Integer.parseInt(number);
        }
    }
}
