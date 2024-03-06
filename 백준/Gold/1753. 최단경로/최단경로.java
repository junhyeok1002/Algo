import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
	static int V, E, start ;
	static int[] Vertexs;
	static HashMap<Integer, HashMap<Integer, Boolean>> visited = new HashMap<>();
	static HashMap<Integer, HashMap<Integer, Integer>> weights = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 첫줄 입력
		String[] line = bf.readLine().split(" ");
		V = Integer.parseInt(line[0]);
		E = Integer.parseInt(line[1]);
		 
		Vertexs = new int[V+1];
		
		// 둘째줄 입력
		start = Integer.parseInt(bf.readLine());
		 
		// 이후 입력
		for(int i =0; i < E; i++) {
			line = bf.readLine().split(" ");
			int E1 = Integer.parseInt(line[0]);
			int E2 = Integer.parseInt(line[1]);
			int W = Integer.parseInt(line[2]);
			
			// 첫 벌텍스에 대한 weight가 없으면?
			if(!weights.containsKey(E1)) {
				HashMap<Integer, Boolean> temp1 = new HashMap<>();
				temp1.put(E2, false);
				visited.put(E1, temp1);
				
				HashMap<Integer, Integer> temp2 = new HashMap<>();
				temp2.put(E2, W);
				weights.put(E1, temp2);
			}else { // 있으면
				visited.get(E1).put(E2, false);
				int min_w = W;
				if( weights.get(E1).get(E2) != null) {
					min_w = Math.min(min_w, weights.get(E1).get(E2));
				}
				weights.get(E1).put(E2, min_w);
			}
			

		}
		Dijkstra();
		
		for(int i = 1; i < Vertexs.length ; i++) {
			// start면 0이다.
			if(i == start) {
				sb.append( 0 + "\n");
				continue;
			}
			
			if(Vertexs[i] == 0) {
				sb.append("INF\n");
				continue;
			}
			else {
				sb.append(Vertexs[i] + "\n");
			}
		}
//		System.out.println(weights.toString());
		System.out.println(sb);
	}
	
	private static void Dijkstra() {
		PriorityQueue<Vertex> PQ = new PriorityQueue<>();
		PQ.offer(new Vertex(start, 0));
		
		// 우선순위 큐가 빌때까지
		while(!PQ.isEmpty()) {
			Vertex vertex = PQ.poll();
//			vertex.Print();
			
			// 기존보다 크면 가망이 없기 때무네~ 바로 카트
			if(vertex.cum > Vertexs[vertex.num]) continue;
			
			if(weights.get(vertex.num) == null) continue;
			
			for(Integer n: weights.get(vertex.num).keySet()) {
				if(!visited.get(vertex.num).get(n)) {
					
					visited.get(vertex.num).put(n, true);
					// 지금까지의 가중치 + 엣지 가중치
					int distance = Vertexs[vertex.num] + weights.get(vertex.num).get(n);
					if(Vertexs[n] == 0) Vertexs[n] = distance;
					
					// 거리가 원래보다 크면 바로 카트
					if(distance > Vertexs[n]) continue;
					
					// 그게 아니면 distance로 갱신
					Vertexs[n] = distance;
					PQ.offer(new Vertex(n, Vertexs[n]));
				}
			}
		}
		
	}
}
class Vertex<T> implements Comparable<T>{
	int num;
	int cum = 0;
	
	Vertex(){};

	Vertex(int num, int cum){
		this.num = num;
		this.cum = cum;
	}
	@Override
	public int compareTo(T o) {
		if(this.cum > ((Vertex)o).cum) return 1;
		if(this.cum < ((Vertex)o).cum) return -1;
		return 0;
	}
	
	void Print() {
		System.out.println("n : " + num + ", cum : "+ cum);
	}
	
}

