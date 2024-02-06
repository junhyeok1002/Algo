import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	// 사방 탐색용 변수
	static int[] dr = {-1, +1, 0, 0};
	static int[] dc = {0, 0, -1, +1};
	static Queue<Node> queue = new LinkedList<>();
	
	// 반환할 변수, 포멧팅
	static int ret = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		// N과 M
		String[] inputs = bf.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		
		// char Map
		char[][] map = new char[N][M];
		
		// 초기 위치
		Node start_node = null;

		// 맵 입력 및 초기 위치 설졍
		for(int i = 0 ; i < N; i++) {
			String line = bf.readLine();
			for(int j = 0 ; j < M; j++) {
				map[i][j] = line.charAt(j);	
				
				// 시작점
				if (map[i][j] == '0') {
					start_node = new Node(i,j);
				}
			}
		}
		bf.close();
		
		
		// 인트로된 방문 배열과, 큐, 시작 노드 입력
		boolean[][][] visited = new boolean[N][M][64];
		queue.offer(start_node);
		visited[start_node.i][start_node.j][0] = true;
	
		while(!queue.isEmpty()) {
			Node node= queue.poll();

			if(ret == -1 && map[node.i][node.j] == '1') {
				ret = node.order;
				break;
			}
			
			for(int k = 0 ; k < 4 ; k ++) {
				int new_r = node.i + dr[k];
				int new_c = node.j + dc[k];

				// 경계 조건 만족, 방문한 적 없고, 0이어야 하고, 막힌 벽이 아니어야 함
				// 방문하지 않았거나, 방문했어도 이전의 키셋팅에 포함관계면가 아니면
				if(0 <= new_r && new_r < N && 
				   0 <= new_c && new_c < M && 
				   map[new_r][new_c] != '#' &&
				   !visited[new_r][new_c][node.keys]
				 ) {
					
					// 갈 수 있는 길이면!!
					if(map[new_r][new_c] == '.' || map[new_r][new_c] == '1' 
							|| map[new_r][new_c] == '0') {
						
						visited[new_r][new_c][node.keys] = true;
						queue.offer(new Node(new_r, new_c, node.order + 1,node.keys));
					}
					
					// 열쇠가 있는 곳이면 열쇠집기
					if(Character.isLowerCase(map[new_r][new_c])) {
						// 열쇠가 집지 않았던 것이면 집는다, 아니면 안집음
						int char_num = find_char_num(map[new_r][new_c]);
		
						// 열쇠가 이미 있으면, 키 변경 없이 가고
						if( (node.keys & (1<<char_num)) == (1<<char_num) ) {
							visited[new_r][new_c][node.keys] = true;
							queue.offer(new Node(new_r, new_c, node.order + 1, node.keys));
						}
						// 열쇠가 이미 없으면, 키 변경 있이 간다, 근데 키 변경했을때 포함관계여부 확인
						else {
							visited[new_r][new_c][node.keys + (1<<char_num)] = true;
							queue.offer(new Node(new_r, new_c, node.order + 1, node.keys + (1<<char_num)));
						}
					}
					
					// 자물쇠가 있는 곳이면
					else if(Character.isUpperCase(map[new_r][new_c])) {
						// 소문자로 바꾼 인덱스
						int char_num = find_char_num(Character.toLowerCase(map[new_r][new_c]));
						
						// 키가 있으면 간다
						if( (node.keys & (1<<char_num)) == (1<<char_num)) {
							visited[new_r][new_c][node.keys] = true;
							queue.offer(new Node(new_r, new_c, node.order + 1,node.keys));
						}
						// 키가 없으면 끝
					}
				}
			}
		}
		System.out.println(ret);
		
	}
	// A들어오는수, B는 맵의 수
	private static int find_char_num(char ch) {
		char[] keyPair = {'f','e','d','c','b','a'};
		
		for(int i=0; i < keyPair.length; i++) {
			if(keyPair[i] == ch) return i;
		}
		return -1;
	}
}
class Node{
	int i;
	int j;
	int order;
	int keys;

	
	Node(){};
	
	Node(int i, int j){
		this.i = i;
		this.j = j;	
		this.order = 0;
		this.keys = 0;
	}
	
	Node(int i, int j, int order, int keys){
		this.i = i;
		this.j = j;	
		this.order = order;
		this.keys = keys;
	}
	
	void PrintNode(){
		System.out.println("i : " + i + ", j : " + j + ", order : "+order + ", keys :"+  Integer.toBinaryString(keys));
	}
	
	public int getKeys() {
		if(keys == 0) return -1;
		return keys;
	}
	
}


// 현재 열쇠 보유 현황을 비트로 표현한다. 미로에서 이전에 방문했던 위치라도 현재 가지고 있는 열쇠 현황이 그때 당시와 다르면 다시 방문해도 된다.
