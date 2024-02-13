import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;


// n log n 구현
public class Main {
	static int N;
	static PriorityQueue<Integer> max_heap = new PriorityQueue<>();
	static PriorityQueue<Integer> min_heap = new PriorityQueue<>(Collections.reverseOrder());
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N =Integer.parseInt(bf.readLine());
		
		// n회 수행
		int num1 = Integer.parseInt(bf.readLine());
		int num2 = Integer.parseInt(bf.readLine());
		
		max_heap.add(Math.max(num1, num2));
		min_heap.add(Math.min(num1, num2));
		
		sb.append(num1 + "\n");
		sb.append(Math.min(num1, num2) + "\n");
		
		for(int i = 2 ; i < N; i++) {
			int input = Integer.parseInt(bf.readLine());

			int max = max_heap.peek();
			int min = min_heap.peek();
			
			// 크기가 같으면 나눠 그냥 넣기 => 그리고 그녀석을 출력하기 
			if(max_heap.size() == min_heap.size()) {
				
				
				// 범위 내에 있으면 맥스로
				if( min < input && input < max || input >= max) {
					max_heap.add(input);
					sb.append(max_heap.peek() + "\n");
				}
				
				else if(input <= min) {
					min_heap.add(input);
					sb.append(min_heap.peek() + "\n");
				}
			}
			
			// 맥스가 더 클때
			else if(max_heap.size() > min_heap.size()) {
				
				// 범위 내에 있으면 맥스로
				if( min < input && input < max || input <= min ) {
					min_heap.add(input);
					sb.append(min_heap.peek() + "\n");
				}
			
				else if(input >= max) {
					min_heap.add(max_heap.poll());
					max_heap.add(input);
					sb.append(min_heap.peek() + "\n");
				}
			}
			
			// 맥스가 더 클때
			else if(max_heap.size() < min_heap.size()) {
				
				// 범위 내에 있으면 맥스로
				if( min < input && input < max ||  input >= max ) {
					max_heap.add(input);
					sb.append(min_heap.peek() + "\n");
				}
				
				else if(input <= min) {
					max_heap.add(min_heap.poll());
					min_heap.add(input);
					sb.append(min_heap.peek() + "\n");
				}
			}
		}
		System.out.println(sb);
	}
}
