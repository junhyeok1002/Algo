import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static PriorityQueue<Integer> PQ = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		String[] line = bf.readLine().split(" ");
		for(int i =0; i < N; i ++) {
			PQ.add(Integer.parseInt(line[i]));
		}
		
		int sum = 0;
		while(!PQ.isEmpty()) {
			int size = PQ.size();
			sum += size * PQ.poll();
		}
		
		System.out.println(sum);
	}
	
}
