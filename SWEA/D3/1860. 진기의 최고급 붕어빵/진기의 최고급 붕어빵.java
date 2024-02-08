import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution
{
	static int N, M, K;

	
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(bf.readLine());
			
		for(int t = 0 ; t < test_case ; t++) {
			// 첫줄은 NMK
			String[] line1 = bf.readLine().split(" ");
			N = Integer.parseInt(line1[0]);
			M = Integer.parseInt(line1[1]);
			K = Integer.parseInt(line1[2]);
			
			
			// 둘째줄은 큐에 넣기
			String[] line2 = bf.readLine().split(" ");
			Deque<Integer> queue = new LinkedList<>();
			List<Integer> list = new ArrayList<>();
			
			boolean possible = true;
			int fish_bread = 0;
			
			int max = -1;
			for(int i = 0 ; i < N; i ++) {
				int time = Integer.parseInt(line2[i]); 
				list.add(time);
				max = Math.max(max, time);
			}
			Collections.sort(list);
		
			
			for(Integer to_put : list) {
				queue.add(to_put);
			}
			
			int make_second = 1;
			for(int i = 1 ; i <= max; i++) {
				if(make_second == M) {
					make_second = 1;
					fish_bread += K;
				}else make_second++;
				
				if(queue.peekFirst() == 0) {
					possible = false;
					break;
				}
				// 사람 오면 붕어빨 불출
				if(queue.peekFirst() == i) {
					int delete = queue.pollFirst();
					// 붕어 빵이 없으면 break 
					if(fish_bread <= 0) {
						possible = false;
						break;
					}
					// 붕어빵 있으면 불출
					else fish_bread--;
				}
				
			}
			if(possible) sb.append("#"+(t+1)+" Possible\n");
			else sb.append("#"+(t+1)+" Impossible\n");
		}
		
		System.out.println(sb);
		
	}
}