package com.dl.calculator;

import java.util.Stack;

public class stringParser {

    private static boolean isOperator(char c)
    {
        return (c == '*' || c == '/' || c == '+' || c == '-');
    }

    //For shunting yard
    private static int precedence(char c)
    {
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

    //SHUNTING YARD
    public static String postfix(String infix) {

        System.out.println("infix " + infix + " length: " + infix.length() + "\n");

        if (infix.isEmpty())
            return "";

        Stack<Character> stack = new Stack<>();

        StringBuilder output = new StringBuilder();
        char c;
        int i = 0;

        while (i < infix.length()) {
            c = infix.charAt(i);

            if (Character.isDigit(c)) {
                output.append("{"); //multi digit numbers
                while (Character.isDigit(infix.charAt(i))) {
                    output.append(infix.charAt(i));
                    i++;
                    if (i >= infix.length()) { break; }
                }
                output.append("}"); //Multiple digit number ended
            }

            else if (isOperator(c)) {
                while (!(stack.isEmpty()) && precedence(stack.peek()) >= precedence(c)) {
                    output.append(stack.pop());
                }
                stack.push(c);
                i++;
            }

        }

        //Loop ended, add all remaining operators from stack
        while (!stack.isEmpty())
            output.append(stack.pop());

        return output.toString();
    }

    //Evaluate a postfix expression given by shunting yard
    public static Integer evaluate(String infix)
    {
        //Perform syntax checking
        for (int i = 0; i < infix.length() - 1; i++) {
            char c = infix.charAt(i);
            char c1 = infix.charAt(i+1);
            if (isOperator(c) && isOperator(c1) && c == c1) { return -1; }
        }

        Stack<Integer> stack = new Stack<>();
        int op1;
        int op2;
        StringBuilder number = new StringBuilder();

        String postfix = postfix(infix); //Call shunting yard

        System.out.println("eval infix " + postfix + "\n"); //shunting yard diagnostic

        if (postfix.isEmpty())
            return 0;

        for (char c : postfix.toCharArray()) {

            if (c == '{') { continue; }

            //Stop building up number, push it onto stack
            if (c == '}') { stack.push(Integer.parseInt(number.toString())); number = new StringBuilder();}

            if (Character.isDigit(c)) { number.append(c); }

            if (isOperator(c)) {
                op1 = stack.pop();
                op2 = stack.pop();

                switch (c) {
                    case '+' -> stack.push(op2 + op1);
                    case '-' -> stack.push(op2 - op1);
                    case '*' -> stack.push(op2 * op1);
                    case '/' -> stack.push(op2 / op1);
                }

            }
        }

        //Should only be one item remaining
        return stack.pop();

    }
}
