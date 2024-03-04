import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, eratos_N;
	static boolean[] eratos;
	static List<Integer> primes;
	static int count = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		eratos = new boolean[N+1];
		
		primes = makeEratos();
		eratos_N = primes.size();
		
		if(N == 1)
			count = 0;
		else 
			TwoPointer();
		System.out.println(count);
	}
	private static void TwoPointer() {
		// p1이상 p2 이하인 것의 점수의 함
		int p1 = 0, p2 = 0, cum_sum = primes.get(0);
		
		while(!(p2 >= eratos_N && p1 >= eratos_N)) {
//			System.out.println("p1 : " + p1 + ", p2  :" +p2 + ", cum : "+cum_sum);
			
			if(cum_sum < N) {
				if( ++p2 < eratos_N) {
					cum_sum += primes.get(p2);
				}
				else {
					if( ++p1 < eratos_N) {
						cum_sum -= primes.get(p1-1);
					}
				}
			}
			else if(cum_sum > N) {
				if( ++p1 < eratos_N) {
					cum_sum -= primes.get(p1-1);
				}
			}
			
			else if(cum_sum == N) {
				if( ++p2 < eratos_N) {
					cum_sum += primes.get(p2);
				}
				else {
					if( ++p1 < eratos_N) {
						cum_sum -= primes.get(p1-1);
					}
				}
				
				count++;
			}
		}
	}
	
	private static List<Integer> makeEratos(){
		List<Integer> primes = new ArrayList<>();
		eratos[0] = true; eratos[1] = true;
		for(int i = 2; i <= N; i++) {
			if(eratos[i]) continue;
			
			for(int j = i+i; j <= N; j += i) {
				eratos[j] = true;
			}
		}
		
		for(int i = 2; i <= N; i++) {
			if(!eratos[i]) primes.add(i);
		}
		return primes;
	}
	
}
