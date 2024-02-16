import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	static long N;
	static Deque<Character> stack = new LinkedList<>();
	static long now_n = 1;
	static long now_n_1 = 1;
	static long div_num = 1000000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Long.parseLong(bf.readLine());
		root_to_One(N);
		calculation();
		
		System.out.println(now_n);
		
	}
	private static void calculation() {
		while(!stack.isEmpty()) {
			if( '+' == stack.pollFirst()) {
				plus_one();
			}else {
				multiple_two();
			}
		}
	}
	
	private static void root_to_One(long n) {
		while(n > 1) {
			if(n % 2 != 0) {
				n -= 1;
				// -1연산이므로 역으로 수행하기 위해 stack에 +1연산 넣기
				stack.offerFirst('+');
			}
			else {
				n /= 2;
				// /2 연산이므로 역으로 수행하기 위해 stack에 *2연산 넣기
				stack.offerFirst('x');
			}
		}
		
		// 첫 시작은 무조건 1더하기로 시작해야하므로 
	}
	private static void plus_one() {
		long temp2 = now_n_1;
		now_n_1 = (now_n + temp2) % div_num;
		now_n = temp2 % div_num;
	}
	private static void multiple_two() {
		long temp0 = ((now_n_1+div_num) - now_n) % div_num; // 나머지라서 뒷수가 더 작아 마이너스가 나오는 것 방지
		long temp1 = now_n;
		long temp2 = now_n_1;
		
		now_n = ((temp0 * temp1) + (temp1 * temp2)) % div_num;
		now_n_1 = ((temp1 * temp1) + (temp2 * temp2)) % div_num;
	}
}
