import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Solution
{
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 버퍼드 리더로 입력과 스트링 빌더로 출력
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(bf.readLine());
		
		// 테스트 케이스 만큼 돌면서
		for(int t = 0; t < test_case;t++) {
			// 스택 선언
			Stack<Integer> stack = new Stack<>();
		
			int num_count = Integer.parseInt(bf.readLine());
			for(int n = 0 ; n < num_count ; n++) {
				// 숫자를 개수만큼 입력 받고, 숫자가 0이면 pop아니면 push
				int num = Integer.parseInt(bf.readLine());
				
				if(num == 0) stack.pop();
				else stack.push(num);
			}
			
			int sum = 0;
			
			// 스택이 빌때까지 출력
			while(!stack.isEmpty()) {
				sum += stack.pop();
			}
			sb.append("#"+(t+1)+" "+sum+"\n");
		}
		System.out.println(sb);
	}
}