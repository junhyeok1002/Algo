import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String infix = bf.readLine();
		Deque<Character> stack = new LinkedList<>();
		
	//  infix -> postfix
        for(int i=0; i < infix.length() ; i++) {
            char element = infix.charAt(i);
            // 열린 괄호면 넣기
            if(element == '(') stack.offerFirst(element);
             
            // 닫힌 괄호면 ( 나올때까지 출력
            else if(element == ')') {
                char temp_str;
                while((temp_str = stack.pollFirst()) != '(') 
                    sb.append(temp_str);
            }
             
            // 곱하기면 들어갈때 우선순위 높으므로 그냥 푸시
            else if(element == '*' || element == '/') {
            	while(true) {
            		if(!stack.isEmpty()) {
                		char temp_char = stack.peekFirst();
                		if(temp_char == '*' || temp_char == '/') 
                        	sb.append(stack.pollFirst());
                        
                        else {
                            stack.offerFirst(element);
                            break;
                        }
                	}
            		else {
            			stack.offerFirst(element);
            			break;
            		}
            	}
            }
             
            // 더하기면 들어갈때 곱하기가 스택의 탑이면, 곱하기가 아닌거 나올때까지 pop후 넣기
            else if(element == '+' || element == '-' ) {

                while(true) {
     
                	if(!stack.isEmpty()) {
                		char temp_char = stack.peekFirst();
                        if((temp_char == '*' || temp_char == '/' || temp_char == '+'|| temp_char == '-')) 
                        	sb.append(stack.pollFirst());
                        
                        else {
                            stack.offerFirst(element);
                            break;
                        }
                	}
                	else {
                		stack.offerFirst(element);
                        break;
                	}
                }
            }
             
            // 피연산자의 경우 스트링 빌더에 출력
            else {
                sb.append(element);
            }
        }
        // 끝나면 남은거 전부 출력
        while(!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }
		System.out.println(sb);
	}
}
