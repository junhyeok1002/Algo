import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static char[][] map;
	// 0 상, 1 하, 2 좌, 3 우
	static int[][] delta = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static char[] directions = {'U','D','L','R'};
	
	static Ball red, blue;
	static Track track;
	static Track ansTrack;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] firstLine = bf.readLine().split(" ");
		N = Integer.parseInt(firstLine[0]);
		M = Integer.parseInt(firstLine[1]);
		
		map = new char[N][M];
		track = new Track();
		ansTrack = new Track(); ansTrack.length = 11;
		
		
		String line;
		for(int i = 0; i < N; i++) {
			line = bf.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == 'R') {
					red  = new Ball(i,j,false);
					map[i][j] = '.';
				}
				if(map[i][j] == 'B') {
					blue = new Ball(i,j,false);
					map[i][j] = '.';
				}
			}
		}
		
		
		DFS(0);
		
		if(ansTrack.length == 11) {
			sb.append(0);
		}else {
			sb.append(1);
//			for(int i = 0; i < ansTrack.length; i++) {
//				sb.append(ansTrack.arr[i]);
//			}
		}
		
		
		System.out.println(sb);
	}
	
	 
	
	private static void DFS(int depth) {
		// 레드 죽고 블루는 살아있으면 끝
		if(red.isDead && !blue.isDead) {
			if(ansTrack.length > track.length) {
				ansTrack.arr = track.arr.clone();
				ansTrack.length = track.length;
			}
			
			return;
		}
		
		if(depth == 10) {
			return;
		}
		
		Ball temp_red = red.clone();
		Ball temp_blue = blue.clone();
		char temp_char = track.arr[depth];
		// 원래 위치 기억 및 복원하기
		for(int d = 0; d < 4; d++) {
			char direct = directions[d];
			if(depth > 0 && track.arr[depth-1] == direct) continue;
			
			// 그게 아니면 한번씩 다 가보기
			boolean alive = moveBalls(d);
			track.arr[depth] = direct;
			track.length++;
			
			// 블루가 살아있어야 간다.
			DFS(depth+1);
			
			// 가본 후면 복원
			track.arr[depth] = temp_char;
			track.length--;
			red.i = temp_red.i; red.j = temp_red.j; red.isDead = temp_red.isDead;
			blue.i = temp_blue.i; blue.j = temp_blue.j; blue.isDead = temp_blue.isDead;
		}
	}
	

	private static boolean moveBalls(int direction) {
		Ball first = red, last = blue;
		
		// 상의 경우
		if(direction == 0) {
			if(red.i < blue.i) {
				first = red; last = blue;
			}
			else if(red.i > blue.i) {
				first = blue; last = red;
			}
		}
		
		// 하의 경우
		else if(direction == 1) {
			if(red.i > blue.i) {
				first = red; last = blue;
			}
			else if(red.i < blue.i) {
				first = blue; last = red;
			}
		}
		
		// 좌의 경우
		else if(direction == 2) {
			if(red.j < blue.j) {
				first = red; last = blue;
			}
			else if(red.j > blue.j) {
				first = blue; last = red;
			}
		}
		
		// 우의 경우
		else if(direction == 3) {
			if(red.j > blue.j) {
				first = red; last = blue;
			}
			else if(red.j < blue.j) {
				first = blue; last = red;
			}
		}
		
		Ball temp_first = first.clone();
		Ball temp_last = last.clone();
		
		if(!first.isDead) move(first, direction);
		if(!last.isDead) move(last, direction);
		
		return blue.isDead;
	}
	
	
	//  공과 이동 방향
	private static void move(Ball ball, int direction) {

		int nr = ball.i + delta[direction][0];
		int nc = ball.j + delta[direction][1];
		
		// 범위 안에 있고 볼도 아니고, 벽이 아니면 간다.
		while( (0 <= nr && nr < N && 0 <= nc && nc < M) &&
			   (isBall(nr, nc) == 'F') && 
			   (map[nr][nc] != '#')) {
			
			if(map[nr][nc] == 'O') {
				ball.isDead = true;
				ball.i = -1;
				ball.j = -1;
				return;
			}
			
			nr += delta[direction][0];
			nc += delta[direction][1];
		}
		
		// 여기까지 온것은 넘어간 것이므로, 원복하기
		nr -= delta[direction][0];
		nc -= delta[direction][1];
		ball.i = nr;
		ball.j = nc;
	}
	
	private static void print(){
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				
				if(isBall(i,j) != 'F') {
					System.out.print(isBall(i,j) + " ");
				}
				else {
					System.out.print(map[i][j] + " ");					
				}
			}
			System.out.println();
		}
	}
	
	private static char isBall(int i, int j) {
		if(red.i == i && red.j == j) return 'R';
		if(blue.i == i && blue.j == j) return 'B';
		return 'F';
	}
}
class Ball{
	int i;
	int j;
	boolean isDead;
	
	public Ball(int i, int j, boolean isDead) {
		this.i = i;
		this.j = j;
		this.isDead = isDead;
	}
	
	public Ball clone() {
		return new Ball(this.i, this.j, this.isDead);
	}
}
class Track{
	int length;
	char[] arr;
	
	Track(){
		length = 0;
		arr = new char[10];
	}
}
