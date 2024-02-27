import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

// 9시 23분 시작
// 단축키 사용금지, 자동완성 금지
public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(bf.readLine());
		for(int t = 0; t < test_case ; t++) {
			String[] line = bf.readLine().split(" ");
			int N = Integer.parseInt(line[0]);
			int M = Integer.parseInt(line[1]);
			
			// 인덱스 번호가 : 사람의 번호, 원소가 리스트 : 아는 사람의 수 
			List<List<Integer>> personSet = new LinkedList<>();
			HashMap<Integer, Integer> person_Check = new HashMap<>();
			
			for(int i =0 ; i < M; i++) {
				line = bf.readLine().split(" ");
				
				if(line.length <= 1) {
					continue;
				}
				
				int num1 = Integer.parseInt(line[0]);
				int num2 = Integer.parseInt(line[1]);
				
				// 둘다 처음오는 사람이면
				if(!person_Check.containsKey(num1) && !person_Check.containsKey(num2)){
					// 각 사람을 각각 클래스를 부여해주고
					person_Check.put(num1, personSet.size());
					person_Check.put(num2, personSet.size());
					
					// 링크드 리스트를 만들어서 셋에 추가
					List<Integer> new_set = new LinkedList<>();
					new_set.add(num1);
					new_set.add(num2);
					personSet.add(new_set);
					
				}
				
				
				// 둘다 있는 경우 두개의 set이 다르면? 같으면 넘어감
				else if(person_Check.containsKey(num1) && person_Check.containsKey(num2) &&
						!(person_Check.get(num1).equals(person_Check.get(num2)))) {
					
					int idx = person_Check.get(num1);
					int delete_idx = person_Check.get(num2);
					
					for(Integer num : personSet.get(delete_idx)) {
						person_Check.put(num, idx); // 번호바꾸고
						personSet.get(idx).add(num); // 링크드 리스트 추가
					}
				}
				
				// num1만 있는 경우
				else if(person_Check.containsKey(num1) && !person_Check.containsKey(num2)){
					int idx = person_Check.get(num1);
					person_Check.put(num2, idx);
					personSet.get(idx).add(num2);
				}
				// num2만 있는 경우
				else if(!person_Check.containsKey(num1) && person_Check.containsKey(num2)){
					int idx = person_Check.get(num2);
					person_Check.put(num1, idx);
					personSet.get(idx).add(num1);
				}
				
				
			}
			
			int another = 0 ;
			for(int i = 1; i < N+1; i++) {
				if(!person_Check.containsKey(i)) another++;
			}
			
			
			int set_num = new HashSet<>(person_Check.values()).size();
			sb.append("#"+(t+1)+" "+(set_num + another)+"\n");
		}
		System.out.println(sb);
	}
}