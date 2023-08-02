import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int result = new Solution().solution();
        System.out.print(result);
    }

    public static class Solution {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] students = new char[25];
        boolean[] visited = new boolean[25];
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{-1, 1, 0, 0};
        int[][] position = new int[7][2];
        int result = 0;

        public int solution() throws IOException {
            initStudents();
            backTracking(0, 0, 0, 0);
            return result;
        }

        private void initStudents() throws IOException {
            for (int i = 0; i < 5; i++) {
                String s = br.readLine();
                for (int j = 0; j < 5; j++) {
                    students[5 * i + j] = s.charAt(j);
                }
            }
        }

        private void backTracking(int S, int Y, int depth, int start) {

            if (depth == 7 && S >= 4) {
                int[][] batch = new int[5][5];
                for (int[] pos : position) {
                    batch[pos[0]][pos[1]] = 1;
                }

                if (isSeven(batch)) {
                    result++;
                }
                return;
            }

            if (depth >= 7) {
                return;
            }

            if (Y > 3) {
                return;
            }

            for (int i = start; i < 25; i++) {
                if (students[i] == 'Y') {
                    position[depth][0] = i / 5;
                    position[depth][1] = i % 5;
                    backTracking(S, Y + 1, depth + 1, i + 1);
                } else {
                    position[depth][0] = i / 5;
                    position[depth][1] = i % 5;
                    backTracking(S + 1, Y, depth + 1, i + 1);
                }
            }
        }

        private boolean isSeven(int[][] batch) {
            boolean[][] visited = new boolean[5][5];

            int link = 0;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (batch[i][j] == 1 && !visited[i][j]) {
                        link++;
                        dfs(i, j, batch, visited);
                    }
                }
            }

            return link == 1;
        }

        private void dfs(int y, int x, int[][] batch, boolean[][] visited) {

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx == 5 || ny < 0 || ny == 5 || visited[ny][nx]) {
                    continue;
                }

                if (batch[ny][nx] != 1) {
                    continue;
                }

                visited[ny][nx] = true;
                dfs(ny, nx, batch, visited);
            }
        }
    }
}
