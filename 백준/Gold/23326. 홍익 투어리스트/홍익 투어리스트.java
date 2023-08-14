import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N;
        private int Q;
        private TreeSet<Integer> sights = new TreeSet<>(); // 인덱스를 기준으로 정렬되어 있음.
        private int position = 0;
        private StringBuilder sb = new StringBuilder();

        public void solve() throws IOException {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());

            // Q * log N = 210만
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                var isSight = Integer.parseInt(st.nextToken());
                if (isSight == 1) {
                    sights.add(i + 1);
                }
            }

            executeQuery(br);
            System.out.print(sb);
        }

        private void executeQuery(BufferedReader br) throws IOException {
            for (int i = 0; i < Q; i++) {
                var st = new StringTokenizer(br.readLine());
                var command = Integer.parseInt(st.nextToken());
                executeCommand(command, st);
            }
        }

        private void executeCommand(int command, StringTokenizer st) {
            switch (command) {
                case 1:
                    assignSight(st);
                    break;
                case 2:
                    movePosition(st);
                    break;
                case 3:
                    moveMinimumFindSight();
            }
        }

        private void assignSight(StringTokenizer st) {
            var indexSight = Integer.parseInt(st.nextToken());

            if (!sights.contains(indexSight)) {
                sights.add(indexSight);
            } else {
                sights.remove(indexSight);
            }
        }

        private void movePosition(StringTokenizer st) {
            var moveCount = Integer.parseInt(st.nextToken());
            position = (position + moveCount) % N;
        }

        private void moveMinimumFindSight() {
            var convertPosition = position + 1;

            if (sights.isEmpty()) {
                sb.append("-1").append("\n");
                return;
            }

            var ceiling = sights.ceiling(convertPosition);

            if (ceiling != null) {
                sb.append(ceiling - convertPosition).append("\n");
            } else {
                sb.append((N - convertPosition + sights.first()) % N).append("\n");
            }
        }
    }
}
