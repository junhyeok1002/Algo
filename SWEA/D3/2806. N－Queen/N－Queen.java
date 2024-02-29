import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int N;
	static int[] chess_map;
	static int count;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int t =1; t <= T; t++) {
			// N 입력받기
			N = Integer.parseInt(bf.readLine());
			count = 0; // 놓을 수 있는 가짓수 카운트용도
			
			// 체스 판 구현 : row 배열(index : 행번호, value : 열번호)
			chess_map = new int[N];
			
			// DFS 재귀 수행
			N_Queen(0, chess_map);
			
			// 결과 출력
			sb.append("#"+t+" "+count+"\n");
		}
		System.out.println(sb);	
	}
	private static void N_Queen(int row, int[] chess_map) {
		// N개 다 놓았으면 카운트 증가후 return
		if(row == N) {
			count++;
			return;
		}
		
		// 모든 열을 돌면서 그 열에 놓을 수 있으면 놓기
		for(int col = 0; col < N; col++ ) {
			if(compareALL(row, col)) {
				chess_map[row] = col; // 체스 판에 놓고
				N_Queen(row+1, chess_map); // 다음으로 넘기기
			}
		}
	}
	
	// 지금까지 놓은 돌을 전부 비교 : 놓을 수 있는지 체크하는 함수
	private static boolean compareALL(int row, int col){
		for(int i =0; i < row; i++) {	
			// 이전에 놓았던 것들을 확인하면서 놓을 수 있는지 체크하기
			if(!compareTwo(i, chess_map[i], row, col)) {
				return false;
			}
		}
		return true;
	}
	
	// 두개의 돌만 비교 : 놓을 수 있는지 체크하는 함수
	private static boolean compareTwo(int r1, int c1, int r2, int c2) {
		boolean check = true;
		
		// 대각선에 있으면(행 차이의 절댓값과 열 차이의 절댓값이 같으면) false
		if( Math.abs(r1-r2) == Math.abs(c1-c2)) {
			check = false;
		}
		
		// 행이나 열이 같으면 false
		if(c1 == c2 || r1 == r2) {
			check = false;
		}
		
		return check;
	}
}
