import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, W;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(bf.readLine());
		int[] stair = new int[N];
		
		for(int i=0; i<N;i++) {
			stair[i] = Integer.parseInt(bf.readLine());
		}
		
		if(N == 1) {
			System.out.println(stair[N-1]);
			return;
		}
		
		dp = new int[N][2];
		dp[0][0] = stair[0]; dp[0][1] = -1; 
		dp[1][0] = stair[1]; dp[1][1] = stair[0]+stair[1];
		
		for(int i = 2; i < N; i++) {
			dp[i] = new int[]{-1,-1};

			// 2단계 전이 있으면.
			if(i - 2 >=0) {
				for(int num : dp[i-2]) {
					if(num == -1) continue;
					dp[i][0] = Math.max(num+stair[i], dp[i][0]);
				}
			}
			// 1단계 전이 있으면
			if(i - 1 >=0) {
				for(int k = 0 ; k < 1; k ++) {
					if(dp[i-1][k] == -1) continue;
					dp[i][k+1] = Math.max(dp[i-1][k]+stair[i], dp[i][k+1]);
				}
			}
		}

		System.out.println(Math.max(dp[N-1][0], dp[N-1][1]));
	}
}