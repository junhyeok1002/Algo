import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	static int N;
	static int[] money = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
	static int[] nums;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t <= T; t++) {
			nums = new int[8];
			
			sb.append("#"+ t + "\n");
			N = Integer.parseInt(bf.readLine());
			
			for(int i = 0 ; i < 8; i++) {
				if(N < money[i]) continue;
				

				nums[i] = N / money[i];
				N -= nums[i] * money[i];
				
				
			}
			
			for(int i = 0 ; i < 8; i++) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			
		}
		System.out.println(sb);
	}
}
