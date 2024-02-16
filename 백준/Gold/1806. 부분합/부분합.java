import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, S;
	static int[] array;
	static int max = 0;
	static int p1 = 0, p2 = 1;
	static int sum = 0;
	static int min_length;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] line = bf.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		S = Integer.parseInt(line[1]);
		
		array = new int[N];
		min_length = N;
		
		line = bf.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(line[i]);
		}
		
		sum = array[0];
		boolean is_exist = false;
		
		while(true) {
			// 합이 원하는 값이면 계소개서 p1을 늘려 길이를 줄임
			if(sum >= S) {
				is_exist = true;
				
				min_length = Math.min(min_length, p2-p1);
				sum -= array[p1];
				if(p1 != N-1) {
					p1++;
				}
			}else if(p2 == N ) break;
			// 합이 원하는 값보다 작으면 계속해서 p2를 늘려서 더함
			else { 
				if(p2 == N) p2--;
				sum += array[p2++];
			}
//			System.out.println(p1 + " "+ p2 + " "+ sum);
		}
		if(!is_exist) {
			System.out.println(0);
		}else {
			System.out.println(min_length);
		}
	}
	
}