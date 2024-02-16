import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	static int[][] origin_matrix;
	static int[][] matrix;
	static int N;
	static long K;
	static Deque<Character> stack = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		String[] line = bf.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Long.parseLong(line[1]);
		
		matrix = new int[N][N];
		origin_matrix = new int[N][N];
		
		for(int i = 0 ; i < N; i ++) {
			line = bf.readLine().split(" ");
			for(int j = 0 ; j < N; j ++) {
				matrix[i][j] = Integer.parseInt(line[j]);
				origin_matrix[i][j] = Integer.parseInt(line[j]);
			}
		}
		if(K > 1) {
			Num_to_One(K);
			calculate();
		}
		else if(K == 1){
			self_Multiple();
		}
		PrintMatrix();
		System.out.println(sb);
	}
	private static void PrintMatrix() {
		for(int i = 0 ; i < N; i ++) {
			for(int j = 0 ; j < N; j ++) {
				sb.append(matrix[i][j] + " ");
			}
			sb.append("\n")
;		}
	}
	private static void calculate() {
		while(!stack.isEmpty()) {
			if( stack.pollFirst() == '+' ) {

				Multiple();
			}else {

				Square();
			}
			
		}
	}
	
	private static void Num_to_One(long num) {
		while(num != 1) {
//			System.out.println(num);
			if(num % 2 != 0) {
				stack.offerFirst('+');
				num -= 1;
			}else {
				stack.offerFirst('x');
				num /= 2;
			}
		}
		
	}
	private static void Square() {
		int[][] temp = new int[N][N];
		for(int i = 0 ; i < N; i ++) {
			for(int j = 0 ; j < N; j ++) {
				temp[i][j] =  matrix[i][j];
			}
		}
		
		for(int i = 0 ; i < N; i ++) {
			for(int j = 0 ; j < N; j ++) {
				
				int sum = 0;
				
				for(int k = 0 ; k < N; k ++) {
					sum += temp[i][k] * temp[k][j];
				}
						
				matrix[i][j] = sum%1000;
						
			}
		}
	}
	
	private static void Multiple() {
		int[][] temp = new int[N][N];
		for(int i = 0 ; i < N; i ++) {
			for(int j = 0 ; j < N; j ++) {
				temp[i][j] =  matrix[i][j];
			}
		}
		
		for(int i = 0 ; i < N; i ++) {
			for(int j = 0 ; j < N; j ++) {
				int sum = 0;
				
				for(int k = 0 ; k < N; k ++) {
					sum += temp[i][k] * origin_matrix[k][j];
				}
						
				matrix[i][j] = sum%1000;
			}
		}
	}
	
	private static void self_Multiple() {

		for(int i = 0 ; i < N; i ++) {
			for(int j = 0 ; j < N; j ++) {
				matrix[i][j] %= 1000;
			}
		}
	}

}