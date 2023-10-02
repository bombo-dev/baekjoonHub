import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int person;
    private static int[] bulb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bulb = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            bulb[i] = Integer.parseInt(st.nextToken());
        }

        person = Integer.parseInt(br.readLine());

        for (int i = 0; i < person; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int position = Integer.parseInt(st.nextToken()) - 1;

            changeBulbState(gender, position);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(bulb[i]).append(" ");
            if ((i + 1) % 20 == 0 && i + 1 < N) {
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void changeBulbState(int gender, int position) {
        if (gender == 1) { // 남자
            int add = position + 1;

            for (int i = position; i < N; i += add) {
                if (bulb[i] == 1) {
                    bulb[i] = 0;
                } else {
                    bulb[i] = 1;
                }
            }
        } else { // 여자
            int i = 0;
            while (true) {
                i++;

                if (position - i < 0 || position + i >= N) {
                    i--;
                    break;
                }

                if (bulb[position - i] != bulb[position + i]) {
                    i--;
                    break;
                }
            }

            for (int j = position - i; j <= position + i; j++) {
                if (bulb[j] == 1) {
                    bulb[j] = 0;
                } else {
                    bulb[j] = 1;
                }
            }
        }
    }
}
