import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] arr;
    static StringTokenizer st;
    static int leftValue = 0;
    static int rightValue = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.print(leftValue + " " + rightValue);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solve() {
        int left = 0;
        int right = N - 1;
        while (left < right) {
            int subtract = arr[left] + arr[right];

            if (arr[left] + arr[right] <= 0) {
                if(min > Math.abs(subtract)) {
                    min = Math.abs(subtract);
                    leftValue = arr[left];
                    rightValue = arr[right];
                }
                left++;
            } else {
                if(min > Math.abs(subtract)) {
                    min = Math.abs(subtract);
                    leftValue = arr[left];
                    rightValue = arr[right];
                }
                right--;
            }
        }
    }
}
