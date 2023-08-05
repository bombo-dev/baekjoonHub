import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private int A;
        private int B;

        public void solution() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            int[] arr = new int[A];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < A; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < B; i++) {
                set.add(Integer.parseInt(st.nextToken()));
            }

            int count = 0;
            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < A; i++) {
                if (!set.contains(arr[i])) {
                    result.add(arr[i]);
                    count++;
                }
            }
            Collections.sort(result);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < result.size(); i++) {
                sb.append(result.get(i)).append(" ");
            }

            if (count == 0) {
                System.out.print(0);
            } else {
                System.out.println(count);
                System.out.print(sb);
            }
        }
    }
}
