import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
	static int N, M, X;
	static HashMap<Integer, Integer>[] graph;
	static boolean[] visited;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 첫줄 입력
		String[] line = bf.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		X = Integer.parseInt(line[2]);
		
		// 인접리스트 구현, 방문배열 구현
		graph = new HashMap[N+1];
		
		
		// n번 -> x번으로 이동하는 최단 경로
		dp = new int[N+1][N+1]; 
		
		for(int i =0; i < M; i++) {
			line = bf.readLine().split(" ");
			int V1 = Integer.parseInt(line[0]);
			int V2 = Integer.parseInt(line[1]);
			int W = Integer.parseInt(line[2]);
			
			// 인접리스트와 방문 배열을 만듦.
			if(graph[V1] == null) graph[V1] = new HashMap<>();
			graph[V1].put(V2, W);
		}
		 
		for(int i = 1; i <= N; i++) {
			Dijkstra(i);
		}
	
		
//		System.out.println(Arrays.toString(graph));
//		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++) {
//				System.out.print( dp[i][j] + " " );
//			}
//			System.out.println();
//		}
		
		int answer = 0;
		for(int i = 1; i <= N; i++) {
			// X뺴고
			if( i == X ) continue;
		
			// X까지 가는길, X부터 오는 길
			answer = Math.max(answer, dp[i][X] + dp[X][i]);
		}
		System.out.println(answer);
		
	}
	
	private static void Dijkstra(int S) {
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		visited = new boolean[N+1];
		
		PQ.offer(new Node(S, 0));
		
		while(!PQ.isEmpty()) {
			Node node = PQ.poll();
			
			
			// 방문 했으면 넘기기, 최소가 아니면 넘어가!!
			// if(dp[S][node.num] > node.sum) continue;
			
			if(visited[node.num]) continue;
			visited[node.num] = true;
			
			for(Integer key : graph[node.num].keySet()) {
				
				if(dp[S][key] == 0) {
					// 노드의 지금까지의 sum과 이동하려는 노드로의 가중치 합
					dp[S][key] = node.sum + graph[node.num].get(key);
				}
				else {
					// 원래가 0이 아니면, 원래 있던 것과 비교 하여 갱신
					dp[S][key] = Math.min(dp[S][key], node.sum + graph[node.num].get(key));
				}
				
				PQ.offer(new Node(key, dp[S][key]));
			}
			
		}
		
		
		
	}


}
class Node<T> implements Comparable<T>{
	int num;
	int sum = 0;
	
	Node(int num, int sum){
		this.num = num;
		this.sum = sum;
	}
	
	@Override
	public String toString() {
		return "num : " + num + ", sum : "+ sum;
	}

	@Override
	public int compareTo(T o) {
		if(this.sum > ((Node)o).sum ) return 1;
		if(this.sum < ((Node)o).sum ) return -1;
		return 0;
	}
}
