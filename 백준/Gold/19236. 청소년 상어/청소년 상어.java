import java.util.*;
import java.io.*;

public class Main {
	// 좌표 관련
	static final int[][] D_cordi = {{},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}}; 
	
	static int[][] map = new int[4][4]; 
	static Fish[] fish_arr = new Fish[17];
	static boolean[] isDead = new boolean[17];
	static int eat = 0;
	
	static Fish shark;
	static int min_eat = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 4; i++) {
			String[] line = bf.readLine().split(" ");
			for (int j = 0; j < line.length; j+=2) {
				int num = Integer.parseInt(line[j]);
				int direct = Integer.parseInt(line[j+1]);
				
				// 맵에는 숫자만 넣고
				map[i][j/2] = num;
				
				// 물고기 배열에는 Fish 객체를 넣기
				fish_arr[num] = new Fish(num, i, j/2, direct);
				
			}
		}
		
		
		// 첫물고기를 먹어버리기 
		// eat에 합치기, 죽음 true, 상어가 그 방향을 가짐
		Fish fish = fish_arr[map[0][0]];
		isDead[fish.num] = true;
		
		// 상어의 num은 먹은 양, 좌표, 방향
		shark = new Fish(fish.num, fish.i, fish.j, fish.direction);

		// 이후부터는 이동하고 먹기
		DFS();
		
		
//		fishMove();
//		
//		for(int i =0; i < 4; i++) {
//			for(int j =0; j < 4; j++) {
//				Fish temp_fish = fish_arr[map[i][j]];
//				System.out.print(temp_fish.toString() + "  ");
//			}
//			System.out.println();
//		}
		
		
		
		
//		System.out.println(Arrays.toString(fish_arr));
		
		System.out.println(min_eat);
	}
	
	// 이동하고 먹는다, 먹을게 없으면 다음으로 넘어가지 않는다
	private static void DFS() {
		
		// 현재 단계 물고기 이동하기
		fishMove();
		
		// 이동 전의 fish_arr을 기억하기
		Fish[] temp_fish_arr = new Fish[17];
		copyFish(temp_fish_arr, fish_arr);
				
		// 이동본을 저장해둠 -> DFS 이후에 복원하기 위함
		int[][] temp_map = new int[4][4];
		copy(temp_map, map);
		
		// 최댓값 조건
		min_eat = Math.max(min_eat, shark.num);
		
		// 상어가 모든 갈 수 있는 방향을 보면서 갈 수 있으면 간다.
		int nr = shark.i + D_cordi[shark.direction][0];
		int nc = shark.j + D_cordi[shark.direction][1];
		int nd = shark.direction;
		
		while(0 <= nr && nr < 4 && 0 <= nc && nc < 4) {
			// 먹을 물고기가 없는 경우 넘어감
			if(isDead[map[nr][nc]]) {
				nr += D_cordi[nd][0];
				nc += D_cordi[nd][1];
				continue;
			}
			
			// 먹을 물고기가 있다? 먹고 갔다가 와!!!!
			// 임시저장할 정보, 원래의 방향, 원래의 eat_num, 원래의 위치
			int temp_i = shark.i;
			int temp_j = shark.j;
			int temp_d = shark.direction;
			
			// 먹어!!!
			Fish fish = fish_arr[map[nr][nc]];
			shark.num += fish.num;
			shark.i = fish.i;
			shark.j = fish.j;
			shark.direction = fish.direction;
			isDead[fish.num] = true;
			
			// 갔다와!!
			DFS();
			
			
			// 다시 뱉어 이노마
			shark.i = temp_i;
			shark.j = temp_j;
			shark.direction = temp_d;
			shark.num -= fish.num;
			isDead[fish.num] = false;
			
			copy(map, temp_map);
			copyFish(fish_arr, temp_fish_arr);
			
			nr += D_cordi[nd][0];
			nc += D_cordi[nd][1];
		}
		
	}
	
	
	
	private static void fishMove() {
		for(int fishNum = 1; fishNum <= 16; fishNum++) {
			// 죽으면 이동 안함
			if(isDead[fishNum]) continue;
			
			// 물고기가 방향을 보고 이동하기
			Fish fish = fish_arr[fishNum];
			
			int nr = fish.i + D_cordi[fish.direction][0];
			int nc = fish.j + D_cordi[fish.direction][1];
			
			// 상어가 있거나 경계넘어야 while문 실행
			while( !(0 <= nr && nr < 4 && 0 <= nc && nc < 4) ||
					(nr == shark.i && nc == shark.j) ) {
				// 여기에 왔다는 건 갈 수 없다는 뜻이므로 방향이동
				
				fish.direction++;
				if(fish.direction == 9) fish.direction = 1;
				
				nr = fish.i + D_cordi[fish.direction][0];
				nc = fish.j + D_cordi[fish.direction][1];
			}
			
			// 여기에 나왔다는 것은 nr, nc이 그 물고기가 갈 곳이라는 뜻이다.
			int changeFishNum = map[nr][nc];
			
			// 그 다음 맵을 바꾸자
			map[nr][nc] = map[fish.i][fish.j];
			map[fish.i][fish.j] = changeFishNum;
			
			// 먼저 fish배열을 바꾸자
			int temp_i = fish_arr[fishNum].i;
			int temp_j = fish_arr[fishNum].j;
			
			fish_arr[fishNum].i = fish_arr[changeFishNum].i;
			fish_arr[fishNum].j = fish_arr[changeFishNum].j;
			
			fish_arr[changeFishNum].i = temp_i;
			fish_arr[changeFishNum].j = temp_j;
			
		}
		
	}
	
	private static void copy(int[][] arr1, int[][] arr2) {
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
	}
	
	private static void Print(int[][] arr1) {
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(i == shark.i && j == shark.j) {
					System.out.print("   s"+shark.direction + " ");
					continue;
				}
				
				if(isDead[map[i][j]]) {
					if(map[i][j] < 10) {
						System.out.print(" "+map[i][j]+"(-) ");
						continue;
					}
					System.out.print(map[i][j]+"(-) ");
					continue;
				}
				System.out.print(fish_arr[arr1[i][j]].toString() + " ");
			}
			System.out.println();
		}
	}
	
	private static void copyFish(Fish[] arr1, Fish[] arr2) {
		for(int i =1; i < 17; i++) {
			if(arr1[i] == null) arr1[i] = new Fish(0,0,0,0);
			arr1[i].num = arr2[i].num;
			arr1[i].i = arr2[i].i;
			arr1[i].j = arr2[i].j;
			arr1[i].direction = arr2[i].direction;
		}
	}
}


class Fish{
	int num;
	int i;
	int j;
	int direction;
	
	Fish(int num, int i, int j, int direction){
		this.num = num;
		this.i = i;
		this.j = j;
		this.direction = direction;
	}

	@Override
	public String toString() {
		if(num < 10) return " "+ num + "(" + direction + ")";
		return num + "(" + direction + ")";
	}

}

