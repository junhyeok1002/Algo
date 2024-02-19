import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(bf.readLine());
		
		for(int t =0; t < test_case; t++) {
			int N = Integer.parseInt(bf.readLine());
			int size = 0;
			
			PriorityQueue<Integer> minheap = new PriorityQueue<>();
			PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
			HashMap<Integer, Integer> min_debt = new HashMap<>();
			HashMap<Integer, Integer> max_debt = new HashMap<>();
			
			int max_out = 0;
			int min_out = 0;
			
			for(int n = 0; n < N; n++) {
				String[] line = bf.readLine().split(" ");
				if(line[0].equals("I")) {
					Integer temp = Integer.parseInt(line[1]);
					minheap.add(temp);
					maxheap.add(temp);
					size++;
				}
				else if(line[0].equals("D") && line[1].equals("1")) {
					Integer temp = maxheap.poll();
					
					while(max_debt.containsKey(temp) && max_debt.get(temp) > 0 ) {
						max_debt.put(temp, max_debt.get(temp)-1);
						temp = maxheap.poll();
					}
					
					if(!min_debt.containsKey(temp)) min_debt.put(temp, 1);
					else min_debt.put(temp, min_debt.get(temp)+1);
						
					if(size > 0) size--;
				}
				else if(line[0].equals("D") && line[1].equals("-1")) {
					Integer temp = minheap.poll();
					
					while(min_debt.containsKey(temp) && min_debt.get(temp) > 0) {
						min_debt.put(temp, min_debt.get(temp)-1);
						temp = minheap.poll();
					}
					
					if(!max_debt.containsKey(temp)) max_debt.put(temp, 1);
					else max_debt.put(temp, max_debt.get(temp)+1);
					
					if(size > 0) size--;
				}
			}
			
//			System.out.println(max_debt.toString());
//			System.out.println(min_debt.toString());
			
			Integer temp1 = maxheap.poll();
			while(max_debt.containsKey(temp1) && max_debt.get(temp1) > 0 ) {
				max_debt.put(temp1, max_debt.get(temp1)-1);
				temp1 = maxheap.poll();
			}
			
			Integer temp2 = minheap.poll();
			while(min_debt.containsKey(temp2) && min_debt.get(temp2) > 0 ) {
				min_debt.put(temp2, min_debt.get(temp2)-1);
				temp2 = minheap.poll();
			}

			if(size <= 0) sb.append("EMPTY\n"); 
			else {
				sb.append(temp1 + " " +temp2+"\n");
			}
		}
		System.out.println(sb);
	}
}
