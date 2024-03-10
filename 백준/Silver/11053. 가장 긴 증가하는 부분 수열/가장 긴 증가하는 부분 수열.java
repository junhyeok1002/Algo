import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] dp, arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		dp = new int[N];
		arr = new int[N];
		
		int max = -1;
		
		String[] line = bf.readLine().split(" ");
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(line[i]);
			
			int max_dp = 0;
			
			for( int j = 0; j < i; j++ ) {
				// 지금이 커야만 실행  
				if(arr[j] >= arr[i]) 
					continue;
				
				if(max_dp < dp[j]) 
					max_dp = dp[j];
			}
		
			dp[i] = max_dp + 1;
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
	
}
