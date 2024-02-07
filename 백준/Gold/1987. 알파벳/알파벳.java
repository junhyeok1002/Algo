import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	// 비트 마스킹 용 변수
	static HashMap<Character, Integer> dict = new HashMap<>();
	static char[][] map;
	
	static int N, M;
	
	static int[] dr = {0, 0, -1, +1};
	static int[] dc = {-1, +1, 0 ,0};
	
	static int max_deep = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		// 첫줄 입력
		String[] line = bf.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		map = new char[N][M];
		
		// 다음 입력
		for(int i=0; i < N ;i++) {
			String lines = bf.readLine();
			for(int j=0; j < M ;j++) {
				char temp = lines.charAt(j);
				map[i][j] = temp;
				
				if(!dict.containsKey(temp))
					dict.put(temp, dict.size());
			}
		}
		
		DFS(new Node(0, 0, 1, (1<<dict.get(map[0][0]))));
		System.out.println(max_deep);
		
	}
	private static void DFS(Node node) {
		
//		node.PrintNode();
		// 최대 깊이 갱신
		max_deep = Math.max(max_deep, node.order);
		
		// 사방에 인접한 것 탐색
		for(int k =0; k < 4 ; k ++) {
			int new_i = node.i + dr[k];
			int new_j = node.j + dc[k];
			
			// 비트 마스크와 새로운문자열 비트가 교집합이 없으면 DFS계속 간다
			if(0 <= new_i && new_i < N &&
			   0 <= new_j && new_j < M &&
			   (node.bitmask & (1<<(dict.get(map[new_i][new_j])))) == 0 ) {
				
				DFS(new Node(new_i, new_j, node.order +1, (node.bitmask | (1<<(dict.get(map[new_i][new_j]))))));
			}
		}
		
	}
}

class Node{
	int i;
	int j;
	int order;
	int bitmask;
	
	Node(){}
	Node(int i, int j, int order, int bitmask){
		this.i = i;
		this.j = j;
		this.order = order;
		this.bitmask = bitmask;
	}
	
	void PrintNode(){
		System.out.println("i= " + i + 
							", j= " + j + 
							", order= " + order +
							", bitmask= " + Integer.toBinaryString(bitmask) );
	}
}