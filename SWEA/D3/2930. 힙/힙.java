import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(bf.readLine());
		
		// 테스트 케이스
		for (int t = 0 ; t < test_case ; t++) {
			sb.append("#"+(t+1) + " ");
			
			PriorityQueue<Integer> MaxHeap = new PriorityQueue<>(Collections.reverseOrder());
			int N = Integer.parseInt(bf.readLine());
			
			for(int n = 0 ; n < N; n++) {
				String[] line = bf.readLine().split(" ");
				
				// 1번 연산은 길이가 2니까 ㅎㅎ
				if(line.length == 2) {
					MaxHeap.offer(Integer.parseInt(line[1]));
				}
				// 2번 연산은 길이가 1이면
				else if(line.length == 1) {
					if(MaxHeap.isEmpty()) sb.append(-1 + " ");
					else sb.append(MaxHeap.poll() + " ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}