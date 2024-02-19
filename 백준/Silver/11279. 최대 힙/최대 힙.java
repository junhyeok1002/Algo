import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static PriorityQueue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());
	static StringBuilder sb = new StringBuilder();
	
	HashSet<String> tetro = new HashSet<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		for(int i = 0 ; i < N; i ++) {
			int num = Integer.parseInt(bf.readLine());
			if(num == 0) {
				if(PQ.isEmpty()) sb.append(0+"\n");
				else sb.append(PQ.poll()+"\n");
			}
			else {
				PQ.add(num);
			}
		}	
		System.out.println(sb);
	}
}
