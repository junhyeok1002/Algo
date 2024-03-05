import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
	static char[][] map;
	static int N, M, max, stole_num, keys;
	
	static boolean[][] visited;
	
	static int[] dr = {0, 0, -1, +1};
	static int[] dc = {-1, +1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t <= T; t++) {
			String[] N_M = bf.readLine().split(" ");
			N = Integer.parseInt(N_M[0]);
			M = Integer.parseInt(N_M[1]);
			
			keys = 0;
			
			// 패딩을 해주기
			map = new char[N+2][M+2];
			
			for(int i = 1; i < N+1; i++) {
				String line = bf.readLine();
				for(int j = 1; j < M+1; j++) {
					map[i][j] = line.charAt(j-1);
				}
			}
			
			String key_str =  bf.readLine();
			if(!key_str.equals("0")) {
				for(int k =0; k < key_str.length(); k++) {
					int key_idx = key_str.charAt(k) - 96; // 아스키 코드 a가 97이므로 
					keys += (1 << key_idx);
				}
			}
			
			// 여기부터 
			max = 0; stole_num = 0;
			BFS(keys);
			sb.append(stole_num+"\n");
		}
		System.out.println(sb);
	}
	
	
	
	
	// 이 문제를 해결할 해결법에 대해 언급하고자 한다.
	// 대문자 알파벳 앞에 도착헀는데 열쇠가 없어 지나가지 못ㅎ는 경우 
	// 큐에 그 녀석들을 넣고 관리한다. 만약에 우리 본대 큐를 돌면서 그녀석들을 계속 확인해서 갈 수 있으면 간다.
	
	
	
	private static void BFS(int key) {
		Deque<Node> queue = new LinkedList<>();
		Deque<Node> blocked = new LinkedList<>();
		visited = new boolean[N+2][M+2];
		visited[0][0] = true;
		
		queue.offerFirst(new Node(0,0));
		
		while(!queue.isEmpty()) {
			Node node = queue.pollLast();
//			node.PrintNode(); System.out.println(" | " + map[node.i][node.j]);
			
			for(int k = 0; k < 4; k++) {
				int nr= node.i + dr[k];
				int nc= node.j + dc[k];
				
				// 갈수 있는 사방이고, 벽이 아니면
				if(0 <= nr && nr < N+2 && 0 <= nc && nc < M+2 &&
				   !visited[nr][nc] && map[nr][nc] != '*' ) {
					
					// 열쇠이고, 열쇠를 기존에 얻지 않았으면 키 얻어서 간다~
					if(Character.isLowerCase(map[nr][nc]) && (keys & (1 << (map[nr][nc] - 96))) != (1 << (map[nr][nc] - 96))) { 
						
						keys +=  (1 << (map[nr][nc] - 96));
						visited[nr][nc] = true;
						queue.offerFirst(new Node(nr, nc));
					}
					
					
					else if (Character.isUpperCase(map[nr][nc])) {
						int key_idx = Character.toLowerCase(map[nr][nc]) - 96;
						
						// 키가 있으면, 그냥 간다
						if((keys & (1 << key_idx)) == (1 << key_idx)) {
							visited[nr][nc] = true;
							queue.offerFirst(new Node(nr, nc));
						}
						
						// 키가 없으면 
						else {
							visited[nr][nc] = true;
							blocked.offer(new Node(nr, nc));
						}
					}
					
					// 훔쳐야할 물건 만나면 훔치고 증가
					else if(map[nr][nc] == '$') {
						stole_num++;
						
						visited[nr][nc] = true;
						queue.offerFirst(new Node(nr, nc));
					}

					// 그외 : .이거나 이미먹은 열쇠이거나 하면 그냥 간다
					else {
						visited[nr][nc] = true;
						queue.offerFirst(new Node(nr, nc));
					}
				}
				
			}
			
			int size = blocked.size();
			
			for(int i = 0; i < size ; i++) {
				Node n = blocked.pollLast();
				int key_idx = Character.toLowerCase(map[n.i][n.j]) - 96;
				// 키가 있으면, 그냥 간다
				if((keys & (1 << key_idx)) == (1 << key_idx)) {
					visited[n.i][n.j] = true;
					queue.offerFirst(new Node(n.i, n.j));
				}else {
					blocked.offerFirst(n);
				}
			}
		}
		
	}
}
class Node{
	int i;
	int j;
	
	Node(){}
	Node(int i, int j){
		this.i = i;
		this.j = j;
	}
	
	void PrintNode() {
		System.out.print("i : " + i + ", j : " + j );
	}
}

