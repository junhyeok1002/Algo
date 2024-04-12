import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<Integer> list = new ArrayList<>();;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String string = bf.readLine();
		String pattern = bf.readLine();
		
		kmpSearch(string, pattern);
		System.out.println(list.size());
		
		for(int num : list) sb.append(num+" ");
		System.out.println(sb);
		
		
	}
	public static void kmpSearch(String text, String pattern) {
	    int[] failure = computeFailure(pattern);
	    int i = 0; // text의 인덱스
	    int j = 0; // pattern의 인덱스

	    while (i < text.length()) {
	        if (pattern.charAt(j) == text.charAt(i)) {
							// 패턴 일치 매칭된 첫 인덱스 리턴하기
	            if (j == pattern.length() - 1) {
	            	list.add(i - j + 1);
	            	
	            	j = failure[j];
	            	i++;
	            	continue;
	            }
	            // 같으면 패턴, 문자열 둘다 한 칸이동
	            i++; j++;
	        } 
					// 일치하지 않고, 이전에 일치한 부분이 있으면
					else if (j > 0) j = failure[j - 1];
	        
					// 일치하지 않고, 이전에 일치한 부분도 없으면
	        else i++;
	    }
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
