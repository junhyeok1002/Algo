import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static Smell[][] smell; 
	static int[][] map;
	static Shark[] sharks;
	
	static boolean[] isDead;
	static int[][][] getD;
	static int[][] D = {{}, {-1,0}, {1,0}, {0,-1}, {0,+1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		// N, M, K 입력
		String[] line = bf.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);
		
		// map 배열 생성 및 smell 배열 생성
		map = new int[N][N];
		smell = new Smell[N][N];
		sharks = new Shark[M+1];
		isDead = new boolean[M+1];
		
		// 상어번호->현재방향->우선순위
		getD = new int[M+1][5][4];
		
		for(int i = 0; i < N; i++) {
			line = bf.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				// 맵을 채우기
				map[i][j] = Integer.parseInt(line[j]);
				
				// shark와 smell을 채우기
				if(map[i][j] != 0) {
					// 방향은 임시로 0으로 둔다.
					sharks[map[i][j]] = new Shark(i, j, map[i][j], 0);
					
					// 냄새 뿌리기
					smell[i][j] = new Smell(K, map[i][j]);
				}else {
					// 냄새 뿌리기
					smell[i][j] = new Smell(0, 0);
				}
			}
		}
		
		// 상어의 방향을 설정하기
		line = bf.readLine().split(" ");
		for(int i = 0; i < M; i++) {
			sharks[i+1].direction = Integer.parseInt(line[i]);
		}
		
		// 상어의 개수만큼 
		for(int m = 1; m <= M; m++) {
			for(int i = 1; i <= 4; i++) {
				line = bf.readLine().split(" ");
				for(int j = 0; j < 4; j++) {
					getD[m][i][j] = Integer.parseInt(line[j]);
				}
			}
		}

//		Debug();
//		for(int i = 0; i < 14; i++) {
//			sharkMove();
//			Debug();
//		}
		
		
		int second;
		for(second = 1; second <= 1000; second++) {
			if(sharkMove()) break;
		}
		
		if(second > 1000) second = -1;
		System.out.println(second);
	}
	
	// 1번~M번 움직인다(냄새 및 우선순위를 고려하여) => 겹치면 죽는 것도 고려하기
	// 움직인 후 냄새제거 한번하기 -> 그 후에 각 상어의 위치에 냄새남기기
	// 남아있는 상어의 수를 확인하기 1개면 종료 아니면 계속 감
	private static boolean sharkMove() {
		// 상어 별로!
		sharkLoop : 
		for(int m = 1 ; m <= M; m++) {
			if(isDead[m]) continue sharkLoop;
			
			// 살아있는 상어면 이동
			
			// 그 상어의 방향으로 살피기
			directionLoop :
			for(int direction : getD[m][sharks[m].direction]) {
				int nr = sharks[m].i + D[direction][0];
				int nc = sharks[m].j + D[direction][1];
				
				// 만약 경계조건 충족 안하면 넘기기
				if(!(0 <= nr && nr < N && 0 <= nc && nc < N))  continue directionLoop;
				
				// 갈곳이 냄새가 있는 곳이면 바로 커트, 냄새 없는 곳이면 바로감.
				if(smell[nr][nc].left != 0) continue directionLoop;
				
				// 냄새가 없는 곳이면 간다.
				// 근데 그곳에 이미 상어가 있다묜은? 
				// 원래 그 자리에 상어가 있을 확률은 없고, 내 앞번호가 이미 온것이므로 난 죽음...
				if(map[nr][nc] != 0) {
					isDead[sharks[m].num] = true;
					map[sharks[m].i][sharks[m].j] = 0;
					continue sharkLoop;
				}
				
				// 그것도 아니면 진짜 간다.
				map[sharks[m].i][sharks[m].j] = 0;
				sharks[m].i = nr;
				sharks[m].j = nc;
				sharks[m].direction = direction;
				map[nr][nc] = sharks[m].num;
				
				continue sharkLoop;
			}
			
			// 여기까지 온 것은 주변이 전부 냄새라는 것임
			// 주변이 전부 냄새가 있으면 나의 냄새라도 간다
			directionLoop :
			for(int direction : getD[m][sharks[m].direction]) {
				int nr = sharks[m].i + D[direction][0];
				int nc = sharks[m].j + D[direction][1];
				
				// 만약 경계조건 충족 안하면 넘기기
				if(!(0 <= nr && nr < N && 0 <= nc && nc < N))  continue directionLoop;
				
				// 내가 뿌린 냄새면 간다.
				if(smell[nr][nc].shark == m) {
					map[sharks[m].i][sharks[m].j] = 0;
					sharks[m].i = nr;
					sharks[m].j = nc;
					sharks[m].direction = direction;
					map[nr][nc] = sharks[m].num;
					
					continue sharkLoop;
				}
			}
		}
		
		// 다 움직인 후 냄새 제거하기
		SmellDown();
		
		// 상어들 냄새남기기
		int shart_cnt = 0;
		for(int m = 1; m <= M; m++) {
			if(isDead[m]) continue;
			
			smell[sharks[m].i][sharks[m].j].left = K;
			smell[sharks[m].i][sharks[m].j].shark = sharks[m].num;
			shart_cnt++;
		}
		
		// 상어가 1명 남아있냐?
		if(shart_cnt == 1) return true;
		return false; 
	}
	
	
	
	private static void SmellDown() {
		for(int i =0; i < smell.length; i++) {
			for(int j =0; j < smell[0].length; j++) {
				// 0이 아니면 감소시키기
				if(smell[i][j].left == 0) continue;
				smell[i][j].left--;
				
				if(smell[i][j].left == 0) smell[i][j].shark = 0;
			}
		}
	}
	
	private static void Print(int[][] arr) {
		for(int i =0; i < arr.length; i++) {
			for(int j =0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static <T> void Print(T[][] arr) {
		for(int i =0; i < arr.length; i++) {
			for(int j =0; j < arr[0].length; j++) {
				System.out.print(arr[i][j].toString() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void Debug() {
		System.out.println("살아있는 상어 정보");
		for(int m = 1 ; m <= M; m++) {
			if(isDead[m]) continue;
			System.out.print(sharks[m].toString() + "   ");
		}
		System.out.println("\n");
		System.out.println("맵의 정보");
		Print(map);
		System.out.println("냄새의 정보");
		Print(smell);
		System.out.println("=================================================");
	}
}

class Shark{
	int i;
	int j; 
	int num;
	int direction;
	
	public Shark(int i, int j, int num, int direction) {
		super();
		this.i = i;
		this.j = j;
		this.num = num;
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Shark [i=" + i + ", j=" + j + ", n=" + num + ", d=" + direction + "]";
	}
}

class Smell{
	int left;
	int shark;
	
	public Smell(int left, int shark) {
		super();
		this.left = left;
		this.shark = shark;
	}

	@Override
	public String toString() {
		return "["+left + "(" + shark + ")]";
	}
}