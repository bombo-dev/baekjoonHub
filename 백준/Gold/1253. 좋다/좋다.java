import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int N;
        private int[] arr;
        private Number[] result = new Number[2];

        public void solution() throws IOException {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Map<Long, List<SumCombination>> sumMap = new HashMap<>();
            calcSum(0, 0, sumMap);

            int count = 0;
            for (int i = 0; i < N; i++) {
                if (sumMap.containsKey((long) arr[i])) {
                    for (SumCombination sumCombination : sumMap.get((long) arr[i])) {
                        if (sumCombination.a.equals(new Number(i, arr[i]))
                                || sumCombination.b.equals(new Number(i, arr[i]))) {
                            continue;
                        }
                        count++;
                        break;
                    }
                }
            }

            System.out.print(count);
        }

        private void calcSum(int start, int depth, Map<Long, List<SumCombination>> sumMap) {
            if (depth == 2) {
                Number a = result[0];
                Number b = result[1];
                long sum = a.number + b.number;

                if (!sumMap.containsKey(sum)) {
                    sumMap.put(sum, new ArrayList<>());
                    sumMap.get(sum).add(new SumCombination(a, b));
                } else {
                    sumMap.get(sum).add(new SumCombination(a, b));
                }

                return;
            }

            for (int i = start; i < N; i++) {
                result[depth] = new Number(i, arr[i]);
                calcSum(i + 1, depth + 1, sumMap);
            }
        }

        private static class SumCombination {
            Number a;
            Number b;

            public SumCombination(Number a, Number b) {
                this.a = a;
                this.b = b;
            }
        }

        private static class Number {
            int index;
            int number;

            public Number(int index, int number) {
                this.index = index;
                this.number = number;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Number number1 = (Number) o;
                return index == number1.index && number == number1.number;
            }

            @Override
            public int hashCode() {
                return Objects.hash(index, number);
            }
        }
    }
}
