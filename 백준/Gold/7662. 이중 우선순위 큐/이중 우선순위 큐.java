import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T;
    static int N;
    static StringTokenizer st;
    static TreeMap<Integer, Integer> treeMap; // 숫자와 개수
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        // T 개의 테스트 데이터
        // N 은 입력데이터
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            treeMap = new TreeMap<>();
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                treeCommand();
            }

            if (treeMap.isEmpty()) {
                sb.append("EMPTY").append("\n");
            } else {
                sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
            }
        }
        System.out.print(sb);
    }

    static void treeCommand() throws IOException {
        st = new StringTokenizer(br.readLine());
        String command = st.nextToken();
        int value = Integer.parseInt(st.nextToken());

        switch (command) {
            case "I":
                treeMap.put(value, treeMap.getOrDefault(value, 0) + 1);
                break;
            case "D":
                if (!treeMap.isEmpty()) {
                    Integer minKey = treeMap.firstKey();
                    Integer maxKey = treeMap.lastKey();
                    if (value == 1) {
                        if (treeMap.get(maxKey) == 1) {
                            treeMap.remove(maxKey);
                        } else {
                            treeMap.put(maxKey, treeMap.get(maxKey) - 1);
                        }
                    } else {
                        if (treeMap.get(minKey) == 1) {
                            treeMap.remove(minKey);
                        } else {
                            treeMap.put(minKey, treeMap.get(minKey) - 1);
                        }
                    }
                }
        }
    }
}
