import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
	static List<Integer> result = new ArrayList<>();
	static List<Integer> array = new LinkedList<>();
	static List<Integer> delete = new ArrayList<>();
	static List<Integer> MaxHeap = new LinkedList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] firstline = bf.readLine().split("");
		for(String str : firstline) {
			array.add(Integer.parseInt(str));
		}
		
		String[] secondline = bf.readLine().split("");
		for(String str : secondline) {
			int num = Integer.parseInt(str);
			delete.add(num);
		}
		
		makeMaxHeap(new ArrayList<>(array) , delete);
		
		// 여기서 부터 찐임
		
		int[] delete_clone = makeCount(delete);
		

		
		while(!array.isEmpty() && !MaxHeap.isEmpty()) {		
//			System.out.println("array : "+ array.toString());
//			System.out.println("delete : "+ delete.toString());
//			System.out.println("MaxHeap : "+ MaxHeap.toString());
//			System.out.println("result : "+ result.toString());
//			System.out.println("=====================================");
			
			Integer candidate = MaxHeap.get(0);
			// 어레이에서 첫번째 수를 가져옴
			
			Integer temp_max = 0;
			int[] temp_delete = delete_clone.clone();
			
			int idx = 0;
			while(true) {
				Integer temp_num = array.get(idx);
				
				if(MaxHeap.contains(temp_num))
					temp_max = Math.max(temp_max, temp_num);
				
				temp_delete[temp_num]--;
	
				// 더이상 지울 수 없거나, 현재수가 가능한 최대후보수면 stop
				if(temp_delete[temp_num] < 0 || 
				   temp_num.equals(candidate)) break;
				idx++;
			}
			
//			System.out.println(temp_max);
				
			// 최대수가 등장할떄까지 삭제, 최대수 등장하면 넣기
			while(true) {
				Integer temp_num = array.get(0);
				if(temp_num.equals(temp_max)) {
					result.add(temp_num);
					MaxHeap.remove(temp_num);
					array.remove(temp_num);
					break;
				}
				
				// 삭제할 수 있는지 체크하기, 삭제할 수 있으면, 삭제
				else if (delete_clone[temp_num] > 0) {
					delete_clone[temp_num]--;
					delete.remove(temp_num);
					array.remove(temp_num);
				}
			}
		}

		for(Integer num : result) {
			sb.append(num + "");
		}
		System.out.println(sb);
	}
	
	private static int[] makeCount(List<Integer> delete) {
		int[] count = new int[1001];
		for(Integer num : delete ) {
			count[num]++;
		}
		return count;
	}
	
	private static int freq(Integer num) {
		int freq = 0;
		for(Integer n : MaxHeap) 
			if(n.equals(num)) freq++;
		return freq;
	}
	private static void makeMaxHeap(List<Integer> array, List<Integer> delete) {
		for(Integer n : delete) {
			array.remove(n);
		}
		for(Integer n : array) {
			MaxHeap.add(n);
		}
		Collections.sort(MaxHeap, Collections.reverseOrder());
	}
}