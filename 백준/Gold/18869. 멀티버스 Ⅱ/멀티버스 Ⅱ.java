import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int M;
    static int N;
    static StringTokenizer st;
    static List<ArrayList<Integer>> list = new ArrayList<>();
    static Map<String, Integer> equalsMap = new HashMap<>();
    static int result = 0;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.print(result);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> universe = list.get(i);
            for (int j = 0; j < N; j++) {
                universe.add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    private static void solve() {

        for (int i = 0; i < M; i++) {
            StringBuilder sb = new StringBuilder();
            List<Integer> universe = list.get(i);
            List<Integer> sortedList = getSortedList(list.get(i));
            for (int j = 0; j < universe.size(); j++) {
                sb.append(sortedList.get(j));
            }
            equalsMap.put(sb.toString(), equalsMap.getOrDefault(sb.toString(), 0) + 1);
        }

        for (int value : equalsMap.values()) {
            int count = ((value - 1) * value) / 2;
            result += count;
        }
    }

    private static List<Integer> getSortedList(ArrayList<Integer> oneList) {
        List<Integer> tempList = new ArrayList<>(oneList);
        tempList.sort(Comparator.naturalOrder());

        List<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int index = Collections.binarySearch(tempList, oneList.get(i));
            indexList.add(index);
        }
        return indexList;
    }
}
