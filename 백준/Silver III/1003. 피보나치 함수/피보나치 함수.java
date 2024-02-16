import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	
	static int one_count = 0;
	static int zero_count = 0;
	static int[][] remember = new int[41][2];
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		remember[0][0] = 1; remember[0][1] = 0; 
		remember[1][0] = 0; remember[1][1] = 1; 
		

		int test_case = Integer.parseInt(bf.readLine());
		for(int t = 0 ; t < test_case ; t++) {
			int N = Integer.parseInt(bf.readLine());
			remember[N] = fibonacci(N, new int[2]);
			sb.append(remember[N][0]+ " "+ remember[N][1]+"\n");
			
			one_count = 0; 
			zero_count = 0;
		}
		System.out.println(sb);		
	}
	
	private static int[] fibonacci(int n, int[] count) {
		// fibo n이 이미 연산된적이 있다. => 더해주고 종료
		if(!(remember[n][0] == 0 && remember[n][1] == 0)) {
			zero_count += remember[n][0];
			one_count += remember[n][1];
			return remember[n];
		}
		
		// 연산된적이 없다.
    	int[] count1 = fibonacci(n-1, count);
    	remember[n-1] = count1;
    	
    	int[] count2 = fibonacci(n-2, count);
    	remember[n-2] = count2;
    	
    	remember[n][0] = count1[0] + count2[0];
    	remember[n][1] = count1[1] + count2[1];
    	
        return remember[n]; 
	    
	}
}