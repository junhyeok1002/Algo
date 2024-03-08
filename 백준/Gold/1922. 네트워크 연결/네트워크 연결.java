import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
	static int N, M;
	static HashMap<Integer, Integer>[] graph;
	static boolean[] visited;
	static int sum = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		// 첫줄 입력
		String[] line;
		N = Integer.parseInt(bf.readLine());
		M = Integer.parseInt(bf.readLine());
		
		// 인접리스트 구현, 방문배열 구현
		graph = new HashMap[N+1];
		
		for(int i =0; i < M; i++) {
			line = bf.readLine().split(" ");
			int V1 = Integer.parseInt(line[0]);
			int V2 = Integer.parseInt(line[1]);
			int W = Integer.parseInt(line[2]);
			
			// 인접리스트와 방문 배열을 만듦.
			if(graph[V1] == null) graph[V1] = new HashMap<>();
			if(graph[V2] == null) graph[V2] = new HashMap<>();
			
			graph[V1].put(V2, W);
			graph[V2].put(V1, W);
		}
		
		Prim(); 
		System.out.println(sum);
	}
	private static void Prim() {
		PriorityQueue<Edge> PQ = new PriorityQueue<>();
		PQ.offer(new Edge(1, 0));
		visited = new boolean[N+1];
		
		while(!PQ.isEmpty()) {
			// 최소 가중치의 간선을 꺼낸다.
			Edge edge = PQ.poll();
//			System.out.println(edge.toString());
			
			// 방문한적 있는 노드는 재껴
			if(visited[edge.node]) continue;
			visited[edge.node] = true;
			
			int node = edge.node;
			int weight = edge.weight;
			
			// 최소 가중치를 더한다.
			sum += weight;
			
			// 인접한 것들을 전부 확인한다.
			for(Integer key : graph[node].keySet()) {
				// 방문한적이 없는 노드면 PQ에 넣기
				if(!visited[key]) {
					// node에서 key로 가는 간선의 가중치를 넘겨줌
					PQ.offer(new Edge(key, graph[node].get(key)));
				}
			}
		}
	}
	


}
class Edge<T> implements Comparable<T>{
	int node;
	int weight;
	
	Edge(int node, int weight){
		this.node = node;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(T o) {
		if(this.weight > ((Edge)o).weight) return 1;
		if(this.weight < ((Edge)o).weight) return -1;
		return 0;
	}
	
	@Override
	public String toString() {
		return "node : "+node + ", weight : " + weight;
	}
}
