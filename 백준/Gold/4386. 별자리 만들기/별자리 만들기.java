import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N, M;
	static double total = 0;
	
	static List<Star> stars = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		double n1, n2;
		
		// 첫줄 입력
		N = Integer.parseInt(bf.readLine());

		for(int i =0; i < N; i++) {
			String[] line = bf.readLine().split(" ");
			
			n1 = Double.parseDouble(line[0]);
			n2 = Double.parseDouble(line[1]);
			
			stars.add(new Star(i, n1, n2));
		}
		
		
		Prim();
		System.out.println(Math.round(total*100) / 100.0 );
		
		
	}
	private static void Prim() {
		PriorityQueue<Edge> PQ = new PriorityQueue<>();
		PQ.offer(new Edge(0, 0.0)); // 0번쨰 행성으로 먼저 들갑니다~
		boolean[] visited = new boolean[N];
		
		while(!PQ.isEmpty()) {
			Edge edge = PQ.poll();
			
			// 방문한적 있으면 pass
			if(visited[edge.toStarNum]) continue;
			visited[edge.toStarNum] = true;
			
			// 꺼내졌다면 최소 경로일 것.
			total += edge.weight;
			
			// 주변을 보고 넣기
			for(int i =0; i < N; i++) {
				Star to = stars.get(i);
				
				// 모든 별 중에 방문하지 않았은 것을 전부 PQ에 넣기
				if(!visited[to.id]) {
					Star my = stars.get(edge.toStarNum);
					PQ.offer(new Edge(to.id, getDistance(my.x, my.y, to.x, to.y)));
				}
			}
		}
		
	}
	private static double getDistance(double x1, double y1,double x2,double y2) {
		double dx = Math.abs(x1-x2);
		double dy = Math.abs(y1-y2);
		
		return Math.sqrt(dx*dx + dy*dy);
	}

}
class Star{
	int id;
	double x;
	double y;
	
	public Star(int id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
}

class Edge<T> implements Comparable<T>{
	int toStarNum;
	double weight;
	
	Edge(int toStarNum, double weight){
		this.toStarNum = toStarNum;
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return "toStarNum : " + toStarNum + ", weight : " + weight;
	}

	@Override
	public int compareTo(T o) {
		if( this.weight > ((Edge)o).weight) return 1;
		if( this.weight < ((Edge)o).weight) return -1;
		return 0;
	}
}
