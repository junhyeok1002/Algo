import java.util.*;
import java.io.*;

public class Main {
	static int N, B, C, default_sum = 0, greedy = 0;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		// 첫째 줄에서 NBC를 얻다.
		N = Integer.parseInt(bf.readLine());
		B = 3;
		C = 2;

		// 둘째 줄에서 arr을 얻다.
		arr = new int[N];
		
		String[] line = bf.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(line[i]);
			default_sum += (arr[i] * B);
		}
	
		if(B <= C) {
			System.out.println(default_sum);
			return;
		}
		
		// B > C 인것이 보장된 상태, 이제는 3개 2개 1개 순으로 할 수 있으면 좋다
		
		// 그리디 로직
		for(int i = 0; i < N;) {
			
			
		// 만약에 b > c >= a이다
		// 2개로 간다
			if(i < N-3 && arr[i+1] > arr[i+2]) {
				
				int buy2 = Math.min(arr[i+1] - arr[i+2], arr[i]);
				greedy += buy2 * (B+C);
				
				arr[i] -= buy2;
				arr[i+1] -= buy2;
			}
			
			
			
		// 3개 볼 수 있는 상황
			// 3번째가 가장 작은 경우
			if(i < N-2 && min3(arr[i],arr[i+1],arr[i+2])==arr[i+2]) {
				
				
				// 가장 작은 값으로 다 구매하기
				greedy += arr[i+2] * (B+2*C);
				arr[i]   -= arr[i+2];
				arr[i+1] -= arr[i+2];
				arr[i+2] -= arr[i+2];
				
				// 남은 것 sum
				greedy += (Math.min(arr[i], arr[i+1]) * (B+C) + 
						   Math.abs(arr[i]- arr[i+1]) * B);
				arr[i] = 0; arr[i+1] = 0;
				
//				System.out.println("3개 구매, +3이동 : "+ Arrays.toString(arr));
				i += 3;
			}
			
			// 2번째가 가장 작은 경우
			else if(i < N-2 && min3(arr[i],arr[i+1],arr[i+2])==arr[i+1]) {

				// 가장 작은 값으로 다 구매하기
				greedy += arr[i+1] * (B+2*C);
				arr[i]   -= arr[i+1];
				arr[i+2] -= arr[i+1];
				arr[i+1] -= arr[i+1];
				
				// 남은 것 sum
				greedy += (arr[i] * B);
				arr[i] = 0;
				
//				System.out.println("3개 구매, +2이동 : "+ Arrays.toString(arr));
				i += 2;
			}
			
			// 1번째가 가장 작은 경우
			else if(i < N-2 && min3(arr[i],arr[i+1],arr[i+2])==arr[i]) {
				// 가장 작은 값으로 다 구매하기
				greedy += arr[i] * (B+2*C);
				arr[i+1] -= arr[i];
				arr[i+2] -= arr[i];
				arr[i]   -= arr[i];
				
//				System.out.println("3개 구매, +1이동 : "+ Arrays.toString(arr));
				i += 1;
			}
			
		// 2번째
			else if(i < N-1 && Math.min(arr[i],arr[i+1]) == arr[i+1]) {
				greedy += arr[i+1] * (B+C);
				arr[i]   -= arr[i+1];
				arr[i+1] -= arr[i+1];
				
				greedy += arr[i] * B;
				arr[i] = 0;
				
//				System.out.println("2개 구매, +2이동 : "+ Arrays.toString(arr));
				i += 2;
			}
			
			else if(i < N-1 && Math.min(arr[i],arr[i+1]) == arr[i]) {
				greedy += arr[i] * (B+C);
				arr[i+1] -= arr[i];
				arr[i]   -= arr[i];
				
//				System.out.println("2개 구매, +1이동 : "+ Arrays.toString(arr));
				i += 1;
			}
			
			else if(i == N-1) {
				greedy += arr[i] * (B);
				arr[i] -= arr[i];
				
//				System.out.println("1개 구매, +1이동 : "+ Arrays.toString(arr));
				i += 1;
			}
		}
	
		System.out.println(greedy);
	}
	
	private static int min3(int n1, int n2, int n3) {
		return Math.min(Math.min(n1, n2), n3);
	}
}

