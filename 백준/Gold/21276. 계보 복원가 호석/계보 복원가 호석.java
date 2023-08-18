import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private int N;
        private int E;
        private StringBuilder sb = new StringBuilder();
        private Map<Integer, String> numberToNameMap = new HashMap<>();
        private Map<String, Integer> nameToNumberMap = new HashMap<>();
        private Map<String, TreeSet<String>> childSet = new HashMap<>();
        private List<List<Integer>> graph = new ArrayList<>();
        private String[] lastParent;
        private int[] inDegree;

        public void solve() throws IOException {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            lastParent = new String[N + 1];
            inDegree = new int[N + 1];

            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }

            var st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                var name = st.nextToken();
                nameToNumberMap.put(name, i + 1);
                numberToNameMap.put(i + 1, name);
            }

            for (String key : nameToNumberMap.keySet()) {
                childSet.put(key, new TreeSet<>());
            }

            E = Integer.parseInt(br.readLine());

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                var descendant = nameToNumberMap.get(st.nextToken());
                var ancestor = nameToNumberMap.get(st.nextToken());

                graph.get(ancestor).add(descendant);
                inDegree[descendant]++;
            }

            recover();
            displayPedigree();
        }

        private void recover() {
            var root = 0;
            PriorityQueue<String> rootQ = new PriorityQueue<>();
            var q = (Queue<Integer>) new LinkedList<Integer>();

            for (int i = 1; i <= N; i++) {
                if (inDegree[i] == 0) {
                    root++;
                    q.offer(i);
                    rootQ.offer(numberToNameMap.get(i));
                }
            }

            sb.append(root).append("\n");

            while (!rootQ.isEmpty()) {
                sb.append(rootQ.poll()).append(" ");
            }
            sb.append("\n");

            recoverPedigree(q);
        }

        private void displayPedigree() {
            var names = new ArrayList<>(childSet.keySet());
            Collections.sort(names);

            for (String name : names) {
                var size = childSet.get(name).size();
                sb.append(name).append(" ").append(size).append(" ");

                var iterator = childSet.get(name).iterator();

                while (iterator.hasNext()) {
                    sb.append(iterator.next()).append(" ");
                }
                sb.append("\n");
            }

            System.out.print(sb);
        }

        private void recoverPedigree(Queue<Integer> q) {
            while (!q.isEmpty()) {
                var index = q.poll();
                var name = numberToNameMap.get(index);

                for (var next : graph.get(index)) {
                    childSet.get(name).add(numberToNameMap.get(next));
                    inDegree[next]--;

                    if (lastParent[next] != null) {
                        var lastParentName = lastParent[next];
                        childSet.get(lastParentName).remove(numberToNameMap.get(next));
                    }
                    lastParent[next] = name;

                    if (inDegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }
    }
}
