import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Main{
	static int N;
	static int[] dp;
	static List<Elec> arr;
	static List<Integer> lis = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(bf.readLine());
		dp = new int[N];
		arr = new ArrayList<>();

		
		String[] line;
		
		// 첫번째 수 배열, 리스,디피에 저
		
		for(int i = 0; i < N; i++) {
			line = bf.readLine().split(" ");
			arr.add(new Elec(Integer.parseInt(line[0]), 
							 Integer.parseInt(line[1])));    
		}
		Collections.sort(arr);
		
		lis.add(arr.get(0).n2);
		dp[0] = lis.size();
		
		for(int i = 1; i < N; i++) {
	
			// 마지막 숫자 보다 크면 
			if(arr.get(i).n2 > lis.get(lis.size()-1)) {
				lis.add(arr.get(i).n2);
				dp[i] = lis.size();
			}
			
			// 마지막 숫자보다 작거나 같으면
			else {
				int idx = binarySearchIdx(0, lis.size(), arr.get(i).n2);
				
				// 더 크면 인덱스 증가
				if(lis.get(idx) < arr.get(i).n2 ) idx++;
				
				// 증가된 인덱스를 바꾸기
				lis.set(idx, arr.get(i).n2);
				dp[i] = idx+1;
			}

		}
		
		sb.append((N-lis.size())+"\n");
		System.out.println(sb);
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
class Elec<T> implements Comparable<T>{
	int n1; 
	int n2;
	
	public Elec(int n1, int n2){
		this.n1 = n1;
		this.n2 = n2;		
	}

	@Override
	public int compareTo(T o) {
		if( this.n1 > ((Elec) o).n1 ) return 1;
		if( this.n1 < ((Elec) o).n1 ) return -1;

		return 0;
	}
	
	@Override
	public String toString() {
		return "n1 : "+n1 + ", n2 : "+n2+"\n";
	}
}