import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solve();
    }

    private static class Solution {
        private final int MAX_CHANNEL = 500000;
        private final int MIN_CHANNEL = 0;
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private int channel;
        private boolean[] possible = new boolean[10];
        private StringTokenizer st;

        public void solve() throws IOException {
            channel = stoi(br.readLine().trim());

            Arrays.fill(possible, true);

            int brokenButtonCount = stoi(br.readLine());

            if (brokenButtonCount > 0) {
                st = getStringTokenizer();
            }

            for (int i = 0; i < brokenButtonCount; i++) {
                int brokenButton = stoi(st.nextToken());
                possible[brokenButton] = false;
            }

            System.out.print(findPushButtonCount());
        }

        private int findPushButtonCount() {
            int onlyPlusMinusButton = Math.abs(channel - 100);

            int onlyUpButton = findPushUpButtonCount(onlyPlusMinusButton);
            int onlyDownButton = findPushDownButtonCount(onlyPlusMinusButton);

            int moveToMinButton = Math.min(onlyUpButton, onlyDownButton);

            return Math.min(onlyPlusMinusButton, moveToMinButton);
        }

        private int findPushUpButtonCount(int limit) {
            int initChannel = channel;
            int initLength = String.valueOf(initChannel).length();
            int count = initLength;

            while (initChannel >= MIN_CHANNEL && !isPossibleChannel(initChannel)) {
                initChannel--;
                count++;

                if (count > limit) {
                    return limit;
                }
            }

            if (initChannel < MIN_CHANNEL) {
                return limit;
            }

            return initChannelLengthConvert(initChannel, initLength, count);
        }

        private int findPushDownButtonCount(int limit) {
            int initChannel = channel;
            int initLength = String.valueOf(initChannel).length();
            int count = initLength;

            while (!isPossibleChannel(initChannel)) {
                initChannel++;
                count++;

                if (count > limit) {
                    return limit;
                }
            }

            return initChannelLengthConvert(initChannel, initLength, count);
        }

        private int initChannelLengthConvert(int lastChannel, int initLength, int count) {
            int lastChannelLength = String.valueOf(lastChannel).length();
            if (lastChannelLength > initLength) {
                return count + 1;
            }

            if (lastChannelLength < initLength) {
                return count - 1;
            }

            return count;
        }

        // 6 * 500000 = 300만 * 2 = 600만
        private boolean isPossibleChannel(int channel) {
            char[] channelToken = String.valueOf(channel).toCharArray();
            for (char ch : channelToken) {
                if (!possible[ch - '0']) {
                    return false;
                }
            }

            return true;
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
