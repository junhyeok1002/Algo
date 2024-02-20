import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N, W;
	static long[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] line = bf.readLine().split(" ");
		
		N = Integer.parseInt(line[0]);
		W = Integer.parseInt(line[1]);
		
		int[] moneys = new int[N];
		int pointer =0;
		int count = 0;
		
		for(int i=N-1; i >= 0;i--) {
			moneys[i] = Integer.parseInt(bf.readLine());
		}
		
		while(W != 0) {
			if(moneys[pointer] > W) {
				pointer++;
				continue;
			}
			
			count += (W / moneys[pointer]);
			W = W % moneys[pointer];
		}
		
		
		System.out.println(count);
		
	}
}
