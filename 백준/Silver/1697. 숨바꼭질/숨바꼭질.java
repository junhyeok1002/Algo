import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N, M, max;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line =  bf.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
	
		max = Math.max(N, M);
		dp = new int[max*2+1];
		
		if(N == M) {
			System.out.println(0);
			return;
		}
		
		dijkstra();
		
		System.out.println(dp[M]);
		
	}
	
	private static void dijkstra() {
		boolean visited[] = new boolean[max*2+1];
		
		Arrays.fill(dp, max);
		
		PriorityQueue<Integer2> PQ = new PriorityQueue<>();
		PQ.offer(new Integer2(N, 0, 0));
		
		while(!PQ.isEmpty()) {
			Integer2 int2 = PQ.poll();
	
			
			if(visited[int2.num]) continue;
			
//			System.out.println(int2.toString());
			
			//방문체크 해죠잉~
			visited[int2.num] = true;
			
			List<Integer2> list = new ArrayList<>();
			
			
			list.add (new Integer2(int2.num*2, int2.order+1, int2.sum + 1));
			
			if(int2.num+1 != int2.num*2) {
				list.add( new Integer2(int2.num+1, int2.order+1, int2.sum+1 ));				
			}
			
			if(int2.num - 1 > 0) {
				list.add(new Integer2(int2.num-1, int2.order+1, int2.sum+1 ));				
			}
			
			// 전부 큐에 넣고, dp 초기회
			for(Integer2 integer2 : list) {
				if(0 <= integer2.num && integer2.num < 2*max) {
					dp[integer2.num] = Math.min(integer2.sum, dp[integer2.num]);
					PQ.offer(integer2);
				}
			}
		}
	}
}
class Integer2<T> implements Comparable<T>{
	int num;
	int sum;
	int order;
	
	Integer2(int num, int order, int sum){
		this.num = num;
		this.order = order;
		this.sum = sum;
	}

	@Override
	public int compareTo(T o) {
		Integer2 input = (Integer2) o;
		
		if(this.sum > input.sum) return 1;
		if(this.sum < input.sum) return -1;
		
		return 0;
	}

	@Override
	public String toString() {
		return "Integer2 [num=" + num + ", sum=" + sum + ", order=" + order + "]";
	}
	
	
}
