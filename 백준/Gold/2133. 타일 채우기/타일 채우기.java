import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		
		// N일때의 경우의 수
		dp = new int[N+1];
		
		if( N % 2 != 0) {
			System.out.println(0);
			return;
		}
		
		dp[0] = 0;
		dp[2] = 3;
		
		
		for(int i = 4; i <= N; i+= 2) {
			
			int wallNum = N/2 + 1;
			
			dp[i] = (dp[i-2] * 3)  + 2;
			
			int j = i - 4;
			while(j >= 0) {
				dp[i] += (2 * dp[j]);
				j-=2;
			}
		}
		
		
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[N]);
	}
}

