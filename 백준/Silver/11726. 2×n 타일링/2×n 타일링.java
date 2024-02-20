import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static final int div_num = 10007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		
		int[] dp = new int[1001];
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i = 3; i < dp.length; i++) {
			dp[i] = (dp[i-1]+dp[i-2]) % div_num;
			if(i == N) break;
		}
		
		System.out.println(dp[N]);
	}
	
}
