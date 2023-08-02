import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[r];
        
        overlapPerm(n, r, 0, arr);
        System.out.print(sb);
    }
    
    public static void overlapPerm(int n, int r, int index, int[] arr){
        if(index == r){
            print(arr, arr.length);
            return;
        }
        
        
        for(int i = 1; i <= n; i++){
            arr[index] = i;
            overlapPerm(n, r, index + 1, arr);
        }
    }
    
    public static void print(int[] arr, int length){
        
        for(int i = 0; i < length; i++){
            sb.append(arr[i] + " ");
        }
        sb.append("\n");
    }
    
    
}