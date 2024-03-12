import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
	static int N;
	static int[] dp, arr;
	static List<Integer> lis = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(bf.readLine());
		dp = new int[N];
		arr = new int[N];

		
//		String[] line = bf.readLine().split(" ");
		
		// 첫번째 수 배열, 리스,디피에 저
		arr[0] = Integer.parseInt(bf.readLine());
		lis.add(arr[0]);
		dp[0] = lis.size();
		
		for(int i = 1; i < N; i++) {
			arr[i] = Integer.parseInt(bf.readLine());
			
			// 마지막 숫자 보다 크면 
			if(arr[i] > lis.get(lis.size()-1)) {
				lis.add(arr[i]);
				dp[i] = lis.size();
			}
			


			// 마지막 숫자보다 작거나 같으면
			else {
				int idx = binarySearchIdx(0, lis.size(), arr[i]);
				
				// 더 크면 인덱스 증가
				if(lis.get(idx) < arr[i]) idx++;
				
				// 증가된 인덱스를 바꾸기
				lis.set(idx, arr[i]);
				dp[i] = idx+1;
			}

		}
		sb.append((N-lis.size())+"\n");
		
//		Stack<Integer> stack = new Stack<>();
//		
//		int match = lis.size();
//		for(int i = arr.length-1; i >= 0; i--) {
//			if(dp[i] == match) {
//				match--;
//				stack.add(arr[i]);
//				if(match == 0) break;
//			}
//		}
//		
//		while(!stack.isEmpty()) {
//			sb.append(stack.pop() + " ");
//		}
		
		System.out.println(sb);
//		System.out.println(Arrays.toString(dp));
//		System.out.println(lis.toString());
	}
	private static int binarySearchIdx(int bottom, int top, int num) {
		if(lis.size() == 0) return 0;
		
		int mid = (bottom + top) / 2;
		
		// 미드랑 바텀이랑 같지 않으면 ㄱ ㄱ
		while(bottom != mid) {
			// 미드 인데스의 수와 같으면 mid 인덱스 반환
			if(lis.get(mid) == num) {
				return mid;
			}
			// 내가 더 크면
			else if(lis.get(mid) < num) {
				// 탑은 냅두고
				bottom = mid;
				mid = (bottom + top) / 2;
			}
			// 니가 더 크면
			else if(lis.get(mid) > num) {
				// 바텀은 냅두고
				top = mid;
				mid = (bottom + top) / 2;
			}
			
		}
		return mid;
	}
	
}
