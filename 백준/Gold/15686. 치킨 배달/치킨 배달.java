import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
	static List<Node> chickens = new ArrayList<>();
	static HashSet<Node> homes = new HashSet<>();
	static List<Integer[]> combis = new ArrayList<>();
	
	static int N, M;
	
	 // 조합용 변수
	static Integer[] out;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = bf.readLine().split(" ");
		
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		
		
		for(int i=0; i < N; i++) {
			inputs = bf.readLine().split(" ");
			for(int j=0; j < N; j++) {
				int value = Integer.parseInt(inputs[j]);
				
				if(value == 1) homes.add(new Node(i,j,0));
				else if(value == 2) chickens.add(new Node(i,j,0));
				
			}
		}
		
		
		out = new Integer[M];
		boolean[] visited = new boolean[chickens.size()];
		Combination(0, visited);
		
		int total_min = N*N*N;
		for(Integer[] combination : combis) {
			int sum = 0;
			for(Node home : homes) {
				int min = N*N;
				for(Integer idx : combination) {
					int distance = Math.abs(chickens.get(idx).i - home.i) + 
								   Math.abs(chickens.get(idx).j - home.j);
					min = Math.min(distance, min);
				}
				sum += min;
			}
			total_min = Math.min(total_min, sum);
			
		}
		System.out.println(total_min);
	}
	
	private static void Combination(int depth, boolean[] visited) {
		if (depth == M) {
			combis.add(out.clone());
//			System.out.println(Arrays.toString(out));
			return;
		}
		
		for(int i=0; i < chickens.size() ; i++) {
			if(depth > 0 && out[depth -1] > i) continue;
			
			if(!visited[i]) {
				visited[i] = true;
				
				out[depth] = i;
				Combination(depth+1, visited);
				
				visited[i] = false;
			}
			
		}
	}
}

//치킨 집을 하나를 조헙하고
class Node{
	int i;
	int j;
	int order;
	
	Node(){}
	Node(int i, int j, int order){
		this.i = i;
		this.j = j;
		this.order = order;
	}
	
}