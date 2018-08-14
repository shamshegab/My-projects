/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemscompiler;

import java.util.Stack;

/**
 *
 * @author Shams Sherif
 */
public class Infix2Pstfix {

    static int Prec(char ch) {
        switch (ch) {
            case '+':

                return 1;

            case '*':

                return 2;

        }
        return -1;
    }

    static String infixToPostfix(String exp) {
        String result = new String("");

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result += c;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }

                if (!stack.isEmpty() && stack.peek() != '(') {
                    System.out.println("Invalid Expression");
                    return "";
                } else {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(c);
            }

        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}
