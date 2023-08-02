import java.io.*;

public class Main {
   
    static int count = 0;
    static int n;
    static boolean[] check;
    static boolean[] leftCheck;
    static boolean[] rightCheck;
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        check = new boolean[n];
        leftCheck = new boolean[2 * n - 1];
        rightCheck = new boolean[2 * n - 1];
        DFS(0, 0);
        System.out.print(count);
    }
    public static void DFS(int r, int c){
        if(n == c) return;
        if(n == r) {
            count++;
            return;
        }

        int temp1 = n - r - 1 + c;
        int temp2 = r + c;
        if(!leftCheck[temp1] && !rightCheck[temp2] && !check[c]){
            leftCheck[temp1] = true;
            rightCheck[temp2] = true;
            check[c] = true;
            DFS(r + 1, 0);
            leftCheck[temp1] = false;
            rightCheck[temp2] = false;
            check[c] = false;
        }
        DFS(r, c + 1);
    }
}