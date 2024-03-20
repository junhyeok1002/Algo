import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int N;
	static int[][] map;
	static int[] row_arr;
	static ArrayList<Integer>[] near;
	static boolean[] visited;
	
	static int max = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());

		String[] line;
		
		map = new int[2*N-1][2*N-1];
		row_arr= new int[2*N-1]; // 인덱스 행에 인덱스 자리
		near = new ArrayList[2*N-1];
		visited = new boolean[2*N];
		
		// 시작 좌표
		Cordi row = new Cordi(0, N-1);
		
		for(int i = 0; i < N; i++) {
			// 한줄 입력
			line = bf.readLine().split(" ");
			
			Cordi col = row.copy();
			for(int j = 0; j < N; j++) {
				// col 위치에 넣고
				map[col.i][col.j] = Integer.parseInt(line[j]);
				// col 이동
				col.i++; col.j++;
			}
			
			// 한 라인 끝나면 now이동
			row.i++; row.j--;
		}
		
		
		
		
		
		for(int i = 0; i < 2*N-1; i++) {
			
			if(near[i] == null) {
				near[i] = new ArrayList<>();
			}
			
			for(int j = 0; j < 2*N-1; j++) {
				if(map[i][j] == 1) {
					near[i].add(j);
				}
			}
		}
		
		DFS(0,0);
		System.out.println(max);
		
//		for(int i = 0; i < 2*N-1; i++) {
//			for(int j = 0; j < 2*N-1; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

	}
	private static void DFS(int row_depth, int put_num) { 
		// 남은 놓을 수 있는 수가 전체  full로 놓을 수 있는 수보다 가망이 없으면 커트
		if( max >= put_num + (2*N-1)-row_depth ) return;
		
		if(row_depth == 2*N-1) return;
		
		// 현재 행에서 놓을 수 있는 곳 다 놓아봐
		for(int col : near[row_depth]) {
			// 놓은적 없는 열이면
			if(!visited[col]) {
				visited[col] = true;
				
				row_arr[row_depth] = col;
				DFS(row_depth+1, put_num+1);
				max = Math.max(max, put_num+1); // 최댓값 초기화
				
				visited[col] = false;
			}
		}
		
		// 난 여기 행에 안놓을래~!
		DFS(row_depth+1, put_num);
	}
}
class Cordi{
	int i;
	int j;
	
	public Cordi(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public Cordi copy() {
		return new Cordi(i, j);
	}
}
