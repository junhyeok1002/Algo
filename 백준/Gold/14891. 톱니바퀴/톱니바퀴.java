import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static boolean[] C1 = new boolean[8];
	static boolean[] C2 = new boolean[8];
	static boolean[] C3 = new boolean[8];
	static boolean[] C4 = new boolean[8];
	
	static boolean[][] Circles = {C1,C2,C3,C4};
	
	static int p1 = 0, p2 = 0, p3 = 0, p4 = 0;
	static int[] pointers  = {p1, p2, p3, p4};
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		for(int c = 0; c < 4; c++ ) {
			String line = bf.readLine();
			for(int i = 0 ; i < 8; i++) {
				if(line.charAt(i) == '1') Circles[c][i] = true;
			}
		}
		
		int num = Integer.parseInt(bf.readLine());
		
		for(int n = 0; n < num; n++) {
			String[] lines = bf.readLine().split(" ");
			int selectC = Integer.parseInt(lines[0])-1;
			int direction = Integer.parseInt(lines[1]);
			
			boolean C1_right = getRight(0);
			boolean C2_left = getLeft(1);
			
			boolean C2_right = getRight(1);
			boolean C3_left = getLeft(2);
			
			boolean C3_right = getRight(2);
			boolean C4_left = getLeft(3);
			
			if(selectC == 1) {
				if(C1_right != C2_left) {
					// 왼쪽을 반대로 이동
					pointers[0] = movePointer(pointers[0], direction*-1);
				}
				
				if(C2_right != C3_left) {
					if(C3_right != C4_left) {
						pointers[3] = movePointer(pointers[3], direction);
					}
					// 왼쪽을 반대로 이동
					pointers[2] = movePointer(pointers[2], direction*-1);
				}
				
				// 나도 이동
				pointers[selectC] = movePointer(pointers[selectC], direction);
			}
			
			else if(selectC == 2) {
				if(C3_right != C4_left) {
					// 오른쪽을 반대로 이동
					pointers[3] = movePointer(pointers[3], direction*-1);
				}
				
				if(C2_right != C3_left) {
					if(C1_right != C2_left) {
						pointers[0] = movePointer(pointers[0], direction);
					}
					// 왼쪽을 반대로 이동
					pointers[1] = movePointer(pointers[1], direction*-1);
				}
				
				// 나도 이동
				pointers[selectC] = movePointer(pointers[selectC], direction);
			}
			
			
			else if(selectC == 0) {
				if(C1_right != C2_left) {
					if(C2_right != C3_left) {
						if(C3_right != C4_left) {
							pointers[3] = movePointer(pointers[3], direction*-1);
						}
						pointers[2] = movePointer(pointers[2], direction);
					}
					pointers[1] = movePointer(pointers[1], direction*-1);
				}
				// 나도 이동
				pointers[selectC] = movePointer(pointers[selectC], direction);
			}
			
			else if(selectC == 3) {
				if(C3_right != C4_left) {
					if(C2_right != C3_left) {
						 if(C1_right != C2_left) {
							pointers[0] = movePointer(pointers[0], direction*-1);
						}
						pointers[1] = movePointer(pointers[1], direction);
					}
					pointers[2] = movePointer(pointers[2], direction*-1);
				}
				// 나도 이동
				pointers[selectC] = movePointer(pointers[selectC], direction);
			}
			
		}
		int sum = 0;
		if(Circles[0][pointers[0]]) sum += 1;
		if(Circles[1][pointers[1]]) sum += 2;
		if(Circles[2][pointers[2]]) sum += 4;
		if(Circles[3][pointers[3]]) sum += 8;
		
		System.out.println(sum);
	}
	
	private static boolean getLeft(int circle_num) {
		int p = ( pointers[circle_num] + 6 ) % 8;
		return Circles[circle_num][p];
	}
	
	private static boolean getRight(int circle_num) {
		int p = ( pointers[circle_num] + 2 ) % 8;
		return Circles[circle_num][p];
	}
	
	private static int movePointer(int p1, int direction) {
		// 시계방향 회전이면
		if( direction == 1) {
			p1--;
			if(p1 < 0) p1 = 7;
		}
		
		else if( direction == -1 ) {
			p1++;
			if(p1 > 7) p1 = 0;
		}
		
		return p1;
	}
}


