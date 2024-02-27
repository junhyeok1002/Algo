import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	// Knapsack DP
	static int[][] dp;
	static int N, L;
	static List<Ingredient> refrigerator;
	static int max;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test_case = 1;
		for(int t = 0; t < test_case ; t++) {
			String[] line = bf.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			L = Integer.parseInt(line[1]);
			
			// 각 열은 재료의 종류, 각 행은 L칼로리까지 물건을 넣었을때 최대밸류 0행은 편의 상 비워둠
			dp = new int[L+1][N+1];
			
			max = 0;
			refrigerator = new ArrayList<>();
			refrigerator.add(new Ingredient()); // 빈거한개 넣기
			
			for(int i = 1 ; i < N+1; i++) {
				line = bf.readLine().split(" ");
				int taste = Integer.parseInt(line[1]);
				int kcal = Integer.parseInt(line[0]);
				
				refrigerator.add(new Ingredient(taste, kcal));
			}
			Collections.sort(refrigerator);
			
			fillDP();
			
//			int cnt = 0;
//			for(int i = 0; i < dp.length; i++) {
//				System.out.print((cnt++) + " ");
//				for(int j = 0; j < dp[0].length; j++) {
//					System.out.print(dp[i][j]+ " ");
//				}
//				System.out.println();
//			}
			
			sb.append( max );
		}
		System.out.println(sb);
	}
	private static void fillDP() {
		for(int j = 1; j < N+1; j++) { // 물건 j
			Ingredient ingredient = refrigerator.get(j);
			
			for(int i =1; i < L+1; i++) { // 무게 i
				if(ingredient.kcal <= i) {
					
					// 최대 칼로리리가 i인 햄버거에 최대 맛을 정하려면
					// 현재물건이 들어갔을때의 맛과 그렇지 않았을 때의 맛 중 최대 값이 되어야 한다
					dp[i][j] = Math.max(dp[i][j-1], // 직전의 것과
					ingredient.taste + dp[i-ingredient.kcal][j-1]); // 해당물건이 들어갔을때 최대를 비교 
				}
				// 들어갈 수 있는 것보다 현재 칼로리가 더 놓아버리면 이전것 대체
				else {
					dp[i][j] = dp[i][j-1];
				}
				// 최댓값 갱신
				max = Math.max(max, dp[i][j]);
			}
		}
	}

}
class Ingredient<T> implements Comparable<T>{
	int taste;
	int kcal;
	
	public Ingredient() {}
	public Ingredient(int taste, int kcal) {
		this.taste = taste;
		this.kcal = kcal;
	}
	
	@Override
	public int compareTo(T o) {
		Ingredient ingredient = (Ingredient) o;
		if(this.kcal > ingredient.kcal) return 1;
		if(this.kcal < ingredient.kcal) return -1;
			
		if(this.taste > ingredient.taste) return 1;
		if(this.taste < ingredient.taste) return -1;
		
		return 0;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "taste : " + taste + ", kcal : " + kcal+"\n";
	}
}