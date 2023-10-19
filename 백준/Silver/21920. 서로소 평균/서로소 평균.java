import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int x = scanner.nextInt();

        long sum = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (gcd(a[i], x) == 1) {
                sum += a[i];
                count++;
            }
        }

        double average = (double) sum / count;
        System.out.println(average);

        scanner.close();
    }

    // 최대 공약수 계산
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}