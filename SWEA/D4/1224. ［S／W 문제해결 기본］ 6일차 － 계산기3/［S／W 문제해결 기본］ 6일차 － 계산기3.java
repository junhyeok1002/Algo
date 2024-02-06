import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
 
class Solution
{
    public static void main(String args[]) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int test_num = 1;
        String temp;
         
        while((temp = bf.readLine()) != null) {
            StringBuilder sb = new StringBuilder();
            String infix =bf.readLine();
            Stack<String> stack = new Stack<>();
             
            // 길이 입력받기
            int len = Integer.parseInt(temp);
             
            //  infix -> postfix
            for(int i=0; i < len ; i++) {
                char element = infix.charAt(i);
                // 열린 괄호면 넣기
                if(element == '(') stack.push(element+"");
                 
                // 닫힌 괄호면 ( 나올때까지 출력
                else if(element == ')') {
                    String temp_str;
                    while(!((temp_str = stack.pop()).equals("("))) 
                        sb.append(temp_str);
                }
                 
                // 곱하기면 들어갈때 우선순위 높으므로 그냥 푸시
                else if(element == '*') stack.push(element+"");
                 
                // 더하기면 들어갈때 곱하기가 스택의 탑이면, 곱하기가 아닌거 나올때까지 pop후 넣기
                else if(element == '+') {
 
                    while(true) {
                        String temp_char = stack.peek();
                        if(temp_char.equals("*")) sb.append(stack.pop());
                        else {
                            stack.push(element+"");
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
                sb.append(stack.pop());
            }
             
            // StringBuilder -> String postfix
            String postfix = sb.toString();
             
            // postfix calculation
            for(int i=0; i < postfix.length(); i++) {
                char element = postfix.charAt(i);
                // 더하기 연산 2개 꺼내서 더하고 스택 넣기
                if(element == '+') {
                    int add_result = Integer.parseInt(stack.pop().toString()) + 
                                     Integer.parseInt(stack.pop().toString());
                    stack.push(Integer.toString(add_result));
                }
                // 곱하기 연산 2개 꺼내서 곱하고 스택 넣기
                else if(element == '*') {
                    int mul_result = Integer.parseInt(stack.pop().toString()) * 
                                     Integer.parseInt(stack.pop().toString());
                    stack.push(Integer.toString(mul_result));
                }
                // 그 외, 숫자면? 스택에 넣기
                else {
                    stack.push(element+"");
                }
            }
            // 끝나면 스택 빌때까지 스트링 빌더에 넣기 : 어처피 1개씩일 것임
            while(!stack.isEmpty()) {
                result.append("#"+(test_num++)+" "+ stack.pop()+"\n");
            }
        }
        // 최종출력
        System.out.println(result);
    }
}