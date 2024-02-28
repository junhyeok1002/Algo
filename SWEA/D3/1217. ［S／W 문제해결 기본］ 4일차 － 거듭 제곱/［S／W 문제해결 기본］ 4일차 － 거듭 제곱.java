import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t < 10 ; t++) {
			int T = Integer.parseInt(bf.readLine());
			String[] line = bf.readLine().split(" ");
			int under = Integer.parseInt(line[0]);
			int expon = Integer.parseInt(line[1]);
			
			result = 0;
			sb.append("#"+T+" "+ DIV_POW(under , expon) +"\n");
			
		}
		System.out.println(sb);
	}
	private static int DIV_POW(int under, int expon) {
		// 지수가 0이면 1반환
		if(expon == 0) return 1;

		// 지수가 짝수면, 지수/2제곱을 2번 곱하기
		if(expon % 2 ==0) {
			int num = DIV_POW(under, expon/2);
			return num * num;
		}
		// 지수가 홀수면 (지수-1)/2제곱을 2번 곱하고 밑을 한번 더 곱하기 
		else {
			int num = DIV_POW(under, (expon-1)/2);
			return num * num * under;
		}
	}
}