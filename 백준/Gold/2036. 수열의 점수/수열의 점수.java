import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N;
	static List<Integer> nums = new ArrayList<>();
	static long matching = 0L;
	
	static int start_idx;
	static int end_idx;
	
	static int group_idx1 = -1;
	static int group_idx2 = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N =Integer.parseInt(bf.readLine());
		
		start_idx = 0;
		end_idx = N-1;
		
		// 맵만들고 할
		for(int i = 0 ; i < N; i++) {
			nums.add(Integer.parseInt(bf.readLine()));
		}
		
		Collections.sort(nums);
//		System.out.println(nums.toString());
		
		while(true) {
			group_idx1 = -1;
			group_idx2 = -1;
			
//			System.out.println(start_idx +" "+ end_idx);
			
			if(end_idx - start_idx <= 0) {
				break;
			}
			
			// group 인덱스 정하기
			// 1번. 둘다 있을때 
			if(nums.get(start_idx+1) <= 0 && nums.get(end_idx-1) >= 2) {
				// 그냥 더한 것과 양쪽의 베네
				
				// => 양쪽의 베네핏을 비
				if(multiple_benefit(nums.get(start_idx), nums.get(start_idx+1)) > multiple_benefit(nums.get(end_idx-1), nums.get(end_idx))) {
					group_idx1 = start_idx+1;
					group_idx2 = start_idx;
				}else {
					group_idx1 = end_idx;
					group_idx2 = end_idx-1;
				}
			}
			// 2번. 하나만 있을때
			else if(nums.get(start_idx+1) <= 0) {
				group_idx1 = start_idx+1;
				group_idx2 = start_idx;
			}
			else if(nums.get(end_idx-1) >= 2) {
				group_idx1 = end_idx;
				group_idx2 = end_idx-1;
			}
			
			// 그룹지울 수 있는 것이 있으면, 그룹 짓고 삭제하
			if(group_idx1 != -1 && group_idx2 != -1) {
				
				matching += (long)(nums.get(group_idx1) * (long)nums.get(group_idx2));
				
				if(group_idx2 == start_idx) start_idx+= 2;
				else end_idx -= 2;
				
			}
			// 그룹지울 수있는 것이 없으면 루프를 그만하
			else {
				break;
			}
		}
		
		for(;start_idx <= end_idx; start_idx++) {
			matching += (long)nums.get(start_idx);
		}
		System.out.println(matching);
	}
	
	private static long multiple_benefit(int num1, int num2) {
		return (long)num1 * (long)num2 - (long)(num1 + num2);
	}
}
