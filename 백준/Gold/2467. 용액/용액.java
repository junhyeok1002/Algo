import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer> arr = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());

		String[] line = bf.readLine().split(" ");

		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(line[i]);
			arr.add(num);
		}
		
		Collections.sort(arr);

//		System.out.println(arr.toString());
//		System.out.println(sorted.toString());
		
		////////////////////////////////////////////////
		
		int left = 0, right = N-1;
		Pair min_Pair = new Pair(0, Integer.MAX_VALUE);
		Pair now_Pair = new Pair(0, N-1);
				
		while(left < right) {
			
			now_Pair.num1 = arr.get(left);
			now_Pair.num2 = arr.get(right);
			
			if(min_Pair.abs_sum() > now_Pair.abs_sum()) {
				min_Pair.copy(now_Pair);
			}
			
			// 0기준 크면 오른쪽 끝 하나 내리기, 작으면 왼쪽 끝 하나 올리기
			if(now_Pair.sum() > 0) right--;
			else if(now_Pair.sum() < 0) left++;
			else break;

		}
	  
	  
	  int[] result = new int[]{min_Pair.num1, min_Pair.num2};
		Arrays.sort(result);
		
		System.out.println(result[0] +" "+result[1]);
	}
}

class Pair {
	int num1;
	int num2;
	
	Pair(int num1, int num2){
		this.num1 = num1;
		this.num2 = num2;
	}
	
	public void copy(Pair p) {
		this.num1 = p.num1;
		this.num2 = p.num2;
	}
	
	public int sum() {
		return num1 + num2 ;
	}
	
	public int abs_sum() {
		return Math.abs(num1 + num2) ;
	}
	
	@Override
	public String toString() {
		return num1+ " " + num2;
	}
}

