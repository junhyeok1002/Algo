import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N = 0;
	static long[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(bf.readLine());
		int[] numbers = new int[test];
		
		for(int i=0; i < test;i++) {
			numbers[i] = Integer.parseInt(bf.readLine());
			N = Math.max(N, numbers[i]);
		}
		
		if(N <= 5) {
			dp = new long[]{0,1,1,1,2,2};
		}else {
			dp = new long[N+1];
			dp[1] = 1; dp[2] = 1; dp[3] = 1; dp[4] = 2; dp[5] = 2;
		}
		
		for(int i = 6; i < N+1; i ++) {
			dp[i] = dp[i-1] + dp[i-5];
			if(i == N) break;
		}
		for(int i=0; i < test;i++) {
			sb.append(dp[numbers[i]]+"\n");
		}
		
		System.out.println(sb);
	}
}
