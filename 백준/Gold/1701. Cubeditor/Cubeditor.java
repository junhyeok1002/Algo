import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static int max = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String pattern = bf.readLine();
		
		for(int i = 0; i < pattern.length(); i++) {
			int[] failure = computeFailure(pattern.substring(i));
		}
		System.out.println(max);
	}
	
	private static int[] computeFailure(String pattern) {
		// Failure funtion 
		int[] failure = new int[pattern.length()];
		int i = 1; 
		int j = 0; // j-1이 의미하는 바 : 매칭된 접두사부분의 인덱스
		failure[0] = 0; // failure(=j가 의미하는 바 : 매칭된 접두사부분의 하나 뒷 부분

		while(i < pattern.length()) {
				// 같으면 다음 단계로 넘어가고 failure 할당
		    if(pattern.charAt(j) == pattern.charAt(i)) {
				failure[i] = j+1;
				max = Math.max(max, failure[i]);
		        i++; j++;
				// 다르지만 직전이 이어지고 있으면 
				// 직전과 매칭된 문자 앞부분의 fail값을 j에 할당
		    }else if(j > 0) {
		        j = failure[j-1];
				// 다른데 직전이 이어지고 있지 않으면 그냥 0넣고, 증가
		    }else{ 
		        failure[i] = 0;
		        i++;
		    }
		}
		return failure;
	}
}
