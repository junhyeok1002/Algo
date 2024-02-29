import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	static int[] his_card = new int[9];
	static int[] my_card = new int[9];
	static List<Integer> cards;
	static boolean[] visited;
	
	static int total = 9*8*7*6*5*4*3*2*1 ; // 9!
	static int win_cnt, lose_cnt;
			
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int t =1; t <= T; t++) {
			visited = new boolean[9];
			
			cards = new ArrayList<>();
			for(Integer i = 1 ; i <= 18; i++) cards.add(i);
			
			String[] line = bf.readLine().split(" ");
			for(int i =0; i < 9; i++) {
				his_card[i] = Integer.parseInt(line[i]);
				cards.remove((Integer)his_card[i]);
			}
		
			// N 입력받기
			win_cnt = 0; // 놓을 수 있는 가짓수 카운트용도
			lose_cnt = 0;
			
			CardGame(0, 0, 0);
			
			// 결과 출력
			sb.append("#"+t+" " + lose_cnt+" "+ win_cnt+"\n");
		}
		System.out.println(sb);	
	}
	private static void CardGame(int depth, int my_score, int his_score) {
		// N개 다 놓았으면 카운트 증가후 return
		if(depth == 9) {
			if(my_score > his_score) win_cnt++;
			else if (my_score < his_score) lose_cnt++;
			return;
		}
		
		// 모든 열을 돌면서 그 열에 놓을 수 있으면 놓기
		for(int i = 0; i < 9 ; i++) {
			if(!visited[i]) {
				visited[i] = true;
				
				my_card[depth] = cards.get(i);
				
				if(his_card[depth] < cards.get(i)) {
					CardGame(depth + 1, my_score + his_card[depth] + my_card[depth], his_score );
				}else {
					CardGame(depth + 1, my_score, his_score + his_card[depth] + my_card[depth]);
				}
				
				visited[i] = false;
			}
		}
	}
}