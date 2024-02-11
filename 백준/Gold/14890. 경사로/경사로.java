import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, L;
	static int[][] map;
	static int[][] map_T;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = bf.readLine().split(" ");
		
		N = Integer.parseInt(inputs[0]);
		L = Integer.parseInt(inputs[1]);
		
		map = new int[N][N];
		map_T = new int[N][N];
		
		for(int i=0; i < N; i++) {
			inputs = bf.readLine().split(" ");
			for(int j=0; j < N; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
				map_T[j][i] = Integer.parseInt(inputs[j]);
			}
		}
		
		int cnt = 0;
		
		//행 체크
		LINE:
		for(int i=0; i < N; i++) {
			boolean pass = true;
			int stage_cnt = 1;
			 
			for(int j=0; j < N-1; j++) {
				try {
					// 다음길과 같으면 (다음 길로 갈 수 있으면 현재 높이에서 몇번 왔는지 체
					if(map[i][j] == map[i][j+1]) {
						stage_cnt++;
					}
					// 다음 길이 한칸 높으면, L과 비교하여 크면 경사 놓을 수 있
					else if(map[i][j] + 1 == map[i][j+1] && stage_cnt >=L) {
						stage_cnt = 1;
					}
					
					// 다음 길이 한칸 낮으면 , L과 비교하여 크면 경사 놓을 수 있
					else if(map[i][j] - 1 == map[i][j+1]) {
						for(int l = 1; l <= L; l++) {
							if(map[i][j] - 1 != map[i][j+l]) {
								pass = false;
								continue LINE;
							}
						}
						stage_cnt = 0;
						j += (L-1);
					}
					
					else {
						pass = false;
					}
					
				}catch (ArrayIndexOutOfBoundsException e) {
					pass = false;
				}
			}
			if(pass) {
				cnt++;
//				System.out.println("line(row) : " + i);
			}
		}
		
		
		//행 체크
		LINE:
		for(int i=0; i < N; i++) {
			boolean pass = true;
			int stage_cnt = 1;
			 
			for(int j=0; j < N-1; j++) {
				try {
					// 다음길과 같으면 (다음 길로 갈 수 있으면 현재 높이에서 몇번 왔는지 체
					if(map_T[i][j] == map_T[i][j+1]) {
						stage_cnt++;
					}
					// 다음 길이 한칸 높으면, L과 비교하여 크면 경사 놓을 수 있
					else if(map_T[i][j] + 1 == map_T[i][j+1] && stage_cnt >=L) {
						stage_cnt = 1;
					}
					
					// 다음 길이 한칸 낮으면 , L과 비교하여 크면 경사 놓을 수 있
					else if(map_T[i][j] - 1 == map_T[i][j+1]) {
						for(int l = 1; l <= L; l++) {
							if(map_T[i][j] - 1 != map_T[i][j+l]) {
								pass = false;
								continue LINE;
							}
						}
						stage_cnt = 0;
						j += (L-1);
					}
					
					else {
						pass = false;
					}
					
				}catch (ArrayIndexOutOfBoundsException e) {
					pass = false;
				}
			}
			if(pass) {
				cnt++;
//				System.out.println("line(col) : " + i);
			}
		}
		
		
		System.out.println(cnt);
	}
}
