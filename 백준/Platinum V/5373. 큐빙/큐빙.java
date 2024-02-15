import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	
	static int small_edge = 1001;
	static int small_edge_i = -1;
	static int small_edge_j = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int test_case = Integer.parseInt(bf.readLine());
		for(int t = 0; t < test_case;t ++) {
			int command_N = Integer.parseInt(bf.readLine());
			String[] inputs = bf.readLine().split(" ");
			
			Cube cube = new Cube();
			
			for(int n=0; n < command_N ; n++) {
				char command1 = inputs[n].charAt(0);
				char command2 = inputs[n].charAt(1);
				cube.getCommand(command1, command2);
			}
			
			
			char[][] result = cube.getU();
			for(int i = 0 ; i < 3 ; i++){	
				for(int j = 0 ; j < 3 ; j++){	
					sb.append(result[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
class Cube{
	char[][] U = {{'w','w','w'},{'w','w','w'},{'w','w','w'}};
	char[][] D = {{'y','y','y'},{'y','y','y'},{'y','y','y'}};
	char[][] F = {{'r','r','r'},{'r','r','r'},{'r','r','r'}};
	char[][] B = {{'o','o','o'},{'o','o','o'},{'o','o','o'}};
	char[][] L = {{'g','g','g'},{'g','g','g'},{'g','g','g'}};
	char[][] R = {{'b','b','b'},{'b','b','b'},{'b','b','b'}};
	
	Cube(){};
	
	
	static void left_rotate(char[][] map) {
		char[] temp = new char[9];
		for(int i = 0 ; i < 3 ; i++){	
			for(int j = 0 ; j < 3 ; j++){	
				temp[i*3 + j] = map[i][j];
			}
		}
		
		int idx =0;
		for(int j = 0 ; j < 3 ; j++){
			for(int i = 2 ; i >= 0 ; i--){	
				map[i][j] = temp[idx++];
			}
		}
	}
	
	static void right_rotate(char[][] map) {
		char[] temp = new char[9];
		for(int i = 0 ; i < 3 ; i++){	
			for(int j = 0 ; j < 3 ; j++){	
				temp[i*3 + j] = map[i][j];
			}
		}
		
		int idx =0;
		for(int j = 2 ; j >= 0 ; j--){
			for(int i = 0 ; i < 3 ; i++){	
				map[i][j] = temp[idx++];
			}
		}
	}
	
	void getCommand(char command1, char command2) {
		if(command1 == 'F' && command2 == '+') F_plus();
		else if(command1 == 'B' && command2 == '+') B_plus();
		else if(command1 == 'U' && command2 == '+') U_plus();
		else if(command1 == 'D' && command2 == '+') D_plus();
		else if(command1 == 'L' && command2 == '+') L_plus();
		else if(command1 == 'R' && command2 == '+') R_plus();
		
		else if(command1 == 'F' && command2 == '-') F_minus();
		else if(command1 == 'B' && command2 == '-') B_minus();
		else if(command1 == 'U' && command2 == '-') U_minus();
		else if(command1 == 'D' && command2 == '-') D_minus();
		else if(command1 == 'L' && command2 == '-') L_minus();
		else if(command1 == 'R' && command2 == '-') R_minus();
		
//		PrintCube_U();
	}
	
	void R_plus() {
		right_rotate(R);
		char[] temp = new char[3]; 
		temp[0] = U[0][2]; temp[1] = U[1][2]; temp[2] = U[2][2]; 
		U[0][2] = F[0][2]; U[1][2] = F[1][2]; U[2][2] = F[2][2];// r -> w
		F[0][2] = D[2][2]; F[1][2] = D[1][2]; F[2][2] = D[0][2];// y - > r
		D[2][2] = B[2][0]; D[1][2] = B[1][0]; D[0][2] = B[0][0];// o > y
		B[2][0] = temp[0]; B[1][0] = temp[1]; B[0][0] = temp[2];// w -> o
	}
	
	void R_minus() {
		left_rotate(R);
		char[] temp = new char[3]; 
		temp[0] = U[0][2]; temp[1] = U[1][2]; temp[2] = U[2][2];
		
		U[0][2] = B[2][0]; U[1][2] = B[1][0]; U[2][2] = B[0][0];// o -> w
		B[2][0] = D[2][2]; B[1][0] = D[1][2]; B[0][0] = D[0][2];// y -> o
		D[2][2] = F[0][2]; D[1][2] = F[1][2]; D[0][2] = F[2][2];// r -> y
		F[0][2] = temp[0]; F[1][2] = temp[1]; F[2][2] = temp[2];
	}
	
	
	void L_plus() {
		right_rotate(L);
		char[] temp = new char[3]; 
		temp[0] = U[0][0]; temp[1] = U[1][0]; temp[2] = U[2][0]; 
		U[0][0] = B[2][2]; U[1][0] = B[1][2]; U[2][0] = B[0][2];// o -> w
		B[2][2] = D[2][0]; B[1][2] = D[1][0]; B[0][2] = D[0][0];// y -> o
		D[2][0] = F[0][0]; D[1][0] = F[1][0]; D[0][0] = F[2][0];// r -> y
		F[0][0] = temp[0]; F[1][0] = temp[1]; F[2][0] = temp[2];
	}
	
	void L_minus() {
		left_rotate(L);
		char[] temp = new char[3]; 
		temp[0] = U[0][0]; temp[1] = U[1][0]; temp[2] = U[2][0]; 
		U[0][0] = F[0][0]; U[1][0] = F[1][0]; U[2][0] = F[2][0];// r -> w
		F[0][0] = D[2][0]; F[1][0] = D[1][0]; F[2][0] = D[0][0];// y - > r
		D[2][0] = B[2][2]; D[1][0] = B[1][2]; D[0][0] = B[0][2];// o > y
		B[2][2] = temp[0]; B[1][2] = temp[1]; B[0][2] = temp[2];// w -> o
	}
	
	
	// 검증 끝
	void B_plus() {
		right_rotate(B);
		char[] temp = U[0].clone();
		U[0][0] = R[0][2]; U[0][1] = R[1][2]; U[0][2] = R[2][2]; // blue -> w
		R[0][2] = D[0][2]; R[1][2] = D[0][1]; R[2][2] = D[0][0]; // y => b
		D[0][2] = L[2][0]; D[0][1] = L[1][0]; D[0][0] = L[0][0]; // g -> y
		L[2][0] = temp[0]; L[1][0] = temp[1]; L[0][0] = temp[2]; // w - > g		
	}
	
	// 내가 보는 기준으로는 시계 방향임
	void B_minus() {
		left_rotate(B);
		char[] temp = U[0].clone();
		U[0][0] = L[2][0]; U[0][1] = L[1][0]; U[0][2] = L[0][0]; // g -> w
		L[2][0] = D[0][2]; L[1][0] = D[0][1]; L[0][0] = D[0][0]; // y -> g
		D[0][2] = R[0][2]; D[0][1] = R[1][2]; D[0][0] = R[2][2]; // blue -> y
		R[0][2] = temp[0]; R[1][2] = temp[1]; R[2][2] = temp[2]; // w - > g	
	}
		
	
	// F 검증 완료
	void F_plus() {
		right_rotate(F);
		char[] temp = U[2].clone();
		U[2][0] = L[2][2]; U[2][1] = L[1][2]; U[2][2] = L[0][2]; // g -> w
		L[2][2] = D[2][2]; L[1][2] = D[2][1]; L[0][2] = D[2][0]; // y -> g
		D[2][2] = R[0][0]; D[2][1] = R[1][0]; D[2][0] = R[2][0]; // blue -> y
		R[0][0] = temp[0]; R[1][0] = temp[1]; R[2][0] = temp[2]; // w - > g		
	}
	
	void F_minus() {
		left_rotate(F);
		char[] temp = U[2].clone();
		U[2][0] = R[0][0]; U[2][1] = R[1][0]; U[2][2] = R[2][0]; // blue -> w
		R[0][0] = D[2][2]; R[1][0] = D[2][1]; R[2][0] = D[2][0]; // y => b
		D[2][2] = L[2][2]; D[2][1] = L[1][2]; D[2][0] = L[0][2]; // g -> y
		L[2][2] = temp[0]; L[1][2] = temp[1]; L[0][2] = temp[2]; // w - > g		
	}
	
	// D검증 완료
	void D_plus(){
		
		left_rotate(D); // 얘만 방향이 다르게 설정함
		char[] temp = F[2].clone(); // red 자징
		F[2] = L[2]; // blue를 레드자리
		L[2] = B[2]; // 블루 자리에 오랜지
		B[2] = R[2];
		R[2] = temp;
	}
	
	void D_minus(){
		right_rotate(D); // 얘만 방향이 다르게 설정함
		char[] temp = F[2].clone(); // red 자징
		F[2] = R[2]; // blue를 레드자리
		R[2] = B[2]; // 블루 자리에 오랜지
		B[2] = L[2];
		L[2] = temp;
	}
	
	
	// U 검증 완료
	//  U+는 파랑 1행이 -> 빵강 1행 -> 초롱 1행 -> 주황 1행
	void U_plus(){
		right_rotate(U);
		char[] temp = F[0].clone(); // red 자징
		F[0] = R[0]; // blue를 레드자리
		R[0] = B[0]; // 블루 자리에 오랜지
		B[0] = L[0];
		L[0] = temp;
	}
	
	void U_minus(){
		left_rotate(U);
		char[] temp = F[0].clone(); // red 자징
		F[0] = L[0]; // blue를 레드자리
		L[0] = B[0]; // 블루 자리에 오랜지
		B[0] = R[0];
		R[0] = temp;
	}
	
	public char[][] getU() {
		return U;
	}

	void PrintCube_U(){
		for(int i = 0 ; i < 3 ; i++){	
			for(int j = 0 ; j < 3 ; j++){	
				System.out.print(U[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println("=============================");
	}
	
	void PrintCube_D(){
		for(int i = 0 ; i < 3 ; i++){	
			for(int j = 0 ; j < 3 ; j++){	
				System.out.print(D[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println("=============================");
	}

	void PrintCube_F(){
		for(int i = 0 ; i < 3 ; i++){	
			for(int j = 0 ; j < 3 ; j++){	
				System.out.print(F[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println("=============================");
	}
	
	void PrintCube_B(){
		for(int i = 0 ; i < 3 ; i++){	
			for(int j = 0 ; j < 3 ; j++){	
				System.out.print(B[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println("=============================");
	}
	
	void PrintCube_L(){
		for(int i = 0 ; i < 3 ; i++){	
			for(int j = 0 ; j < 3 ; j++){	
				System.out.print(L[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println("=============================");
	}
	
	void PrintCube_R(){
		for(int i = 0 ; i < 3 ; i++){	
			for(int j = 0 ; j < 3 ; j++){	
				System.out.print(R[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println("=============================");
	}
}