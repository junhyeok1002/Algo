import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;
	static List<Integer[]>[] near_list;
	static boolean[] visited;
	static int max_long;
	static int lont_node;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		
		near_list = new ArrayList[N+1];
		
		for(int n = 0 ; n < N-1 ; n ++) {
			String[] inputs = bf.readLine().split(" ");
			
			
			int node1 = Integer.parseInt(inputs[0]);
			int node2 = Integer.parseInt(inputs[1]);
			int weight = Integer.parseInt(inputs[2]);
			
			if(near_list[node1] == null) {
				List<Integer[]> temp = new ArrayList<>();
				temp.add(new Integer[]{node2, weight});
				near_list[node1] = temp;
			}else {
				near_list[node1].add(new Integer[]{node2, weight});
			}
			
			if(near_list[node2] == null) {
				List<Integer[]> temp = new ArrayList<>();
				temp.add(new Integer[]{node1, weight});
				near_list[node2] = temp;
			}else {
				near_list[node2].add(new Integer[]{node1, weight});
			}
		}

		visited = new boolean[N+1];
		visited[1] = true;
		DFS(1, 0);
		
		visited = new boolean[N+1];
		visited[lont_node] = true;
		DFS(lont_node, 0);
	
		System.out.println(max_long);
		
	}
	// start_node를 
	private static void DFS(int now_node, int cumsum) {
		if(max_long < cumsum) {
			max_long = cumsum;
			lont_node = now_node;
		}
		
		// 가까운 노드들의 거리를 꺼내서 
		if(near_list[now_node] != null) {
			for(int i = 0; i < near_list[now_node].size() ; i++) {
					Integer[] node = near_list[now_node].get(i);
					
				if (!visited[node[0]]) { // 인접 거리가 0이 아닌 것들 중에서 
					visited[node[0]] = true;
					
//					System.out.println(cumsum +" " + node[0] + " " + now_node +" "+ node[1]);
					
					// 시작 노드에서 이 노드 까지의 거리는 누적
					DFS(node[0], cumsum + node[1] );
				}	
			}
		}
	}
}
