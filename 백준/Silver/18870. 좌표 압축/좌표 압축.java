import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	static int N;
	static List<Integer> list = new ArrayList<>();
	static HashMap<Integer, List<Integer>> dict = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(bf.readLine());
		int[] answer = new int[N]; 
		
		String[] line = bf.readLine().split(" ");
		for(int i =0; i < N; i ++) {
			int temp = Integer.parseInt(line[i]);
			list.add(temp);
			
			
			if(dict.containsKey(temp)) {
				dict.get(temp).add(i);
			}else {
				List<Integer> value = new ArrayList<>();
				value.add(i);
				dict.put(temp, value);
			}
		}
		
		Set<Integer> set = new HashSet<>(list);
		List<Integer> unique_sorted = new ArrayList<>(set);
		Collections.sort(unique_sorted);
		
		
		for(int i =0; i < unique_sorted.size(); i ++) {
			List<Integer> coordis = dict.get(unique_sorted.get(i));
			
			for(Integer num : coordis) {
				answer[num] = i;
			}
		}
		for(int i = 0; i < answer.length; i++) {
			sb.append(answer[i]+ " ");
		}
		System.out.println(sb);
	}
	
}
