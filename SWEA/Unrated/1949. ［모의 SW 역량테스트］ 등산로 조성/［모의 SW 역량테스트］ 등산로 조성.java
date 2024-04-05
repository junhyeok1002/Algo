import java.io.*;
import java.util.*;

public class Solution {
	static int N, K;
	static int[][] map;
	static boolean[][] visited;
	static List<Node> top;
	static int[][] delta = {{1,0},{-1,0},{0,-1},{0,1}};
	
	static int highest, max;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		String[] line; 
		for(int t = 1; t <= T; t++) {
			sb.append("#"+ t + " ");
			
			line = bf.readLine().split(" ");
			// N과 K 그리고 맵 입력
			N = Integer.parseInt(line[0]);
			K = Integer.parseInt(line[1]);
			highest = 0; max = 0;
			top = new ArrayList<>();
			
			map = new int[N][N];
			for(int i = 0 ; i < N; i++) {
				line = bf.readLine().split(" ");
				for(int j = 0 ; j < N; j++) {
					map[i][j] = Integer.parseInt(line[j]);
					
					highest = Math.max(highest, map[i][j]);
				}
			}
			
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j < N; j++) {
					if(map[i][j] == highest)
						top.add(new Node(i,j,highest));
				}
			}
			
			for(Node node : top) {
				visited = new boolean[N][N];
				visited[node.i][node.j] = true;
				DFS(node, 1, false);
			}
			sb.append(max+"\n");
		}
		System.out.println(sb);
	}
	private static void DFS(Node now, int order, boolean use) {
		max = Math.max(max, order);
		// 갈 수 없을 때까지 가는 것으므로 멈춤 조건을 걸지 않는다.
		
//		System.out.println(now.toString() + " " + order);
		
		// 주변을 본다.
		for(int k = 0; k < 4; k++) {
			int nr = now.i + delta[k][0];
			int nc = now.j + delta[k][1];
			
			// 경계조건 안에 있고 방문한 적 없으면
			if(0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc]) {
				// 만약 자기보다 작은 경우면
				if(map[nr][nc] < now.num) {
					visited[nr][nc] = true;
					DFS(new Node(nr, nc, map[nr][nc]), order + 1, use);
					visited[nr][nc] = false;
				}
				
				// 나보다 작지 않고 기회를 안쓴 경우에, 기회를 쓰는 것이 효과가 있으면
				else if(!use && map[nr][nc] - K < now.num ){
					// 공사 진행
					int temp = map[nr][nc];
					map[nr][nc] = now.num-1;
					use = true;
					
					visited[nr][nc] = true;
					DFS(new Node(nr, nc, map[nr][nc]), order + 1, use);
					visited[nr][nc] = false;
					
					// 공사 복원
					use = false;
					map[nr][nc] = temp;
				}
			}
		}
	}
}
class Node{
	int i;
	int j;
	int num;
	
	public Node(int i, int j, int num) {
		this.i = i;
		this.j = j;
		this.num = num;
	}

	@Override
	public String toString() {
		return "Node [i=" + i + ", j=" + j + ", num=" + num + "]";
	}
}
