import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		
		dp = new int[N+1];
		dp[1] = 0;
		for(int i = 2; i < N+1; i ++) {
			fillDP(i);
			if(i == N) break;
		}
		System.out.println(dp[N]);
	}
	private static void fillDP(int num) {
		
		int min = N;
		if(num % 3 ==0 && (num/3 == 1 || dp[num/3]!= 0)) {
			min = Math.min(min, dp[num/3]+1);
		}
		if(num % 2 ==0 && (num/2 == 1 || dp[num/2]!= 0)) {
			min = Math.min(min, dp[num/2]+1);
		}
		if(dp[num-1]!= 0) {
			min = Math.min(min, dp[num-1]+1);
		}
		dp[num] = min;
	}
	
}
