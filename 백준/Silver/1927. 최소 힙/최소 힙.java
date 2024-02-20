import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static PriorityQueue<Integer> PQ = new PriorityQueue<>();
	static PriorityQueue<Integer> minus_PQ = new PriorityQueue<>(Collections.reverseOrder());
	static StringBuilder sb = new StringBuilder();
	
	HashSet<String> tetro = new HashSet<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		for(int i = 0 ; i < N; i ++) {
			
			int num = Integer.parseInt(bf.readLine());
			if(num == 0) {
				if(PQ.isEmpty() && minus_PQ.isEmpty()) sb.append(0+"\n");
				else if(PQ.isEmpty() && !minus_PQ.isEmpty()) sb.append(minus_PQ.poll()+"\n");
				else if(!PQ.isEmpty() && minus_PQ.isEmpty()) sb.append(PQ.poll()+"\n");
				else {
					if(PQ.peek() < Math.abs(minus_PQ.peek())) {
						sb.append(PQ.poll()+"\n");
					}else {
						sb.append(minus_PQ.poll()+"\n");
					}
				}
			}
			else {
				if(num < 0) minus_PQ.add(num);
				else PQ.add(num);
			}
//			System.out.println(PQ.peek() + " "+ minus_PQ.peek());
		}	
		System.out.println(sb);
	}
}
