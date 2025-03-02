/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nerdywoof.calculatorchallenge;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Alex Taberner
 */
public class Calculator {

    public static double Calculate(String userInput) {
        userInput = userInput.replaceAll("\\s+","");
        Queue<String> postfixUserInput = parseToPostfix(userInput);
        return resolvePostfix(postfixUserInput);
    }

    private static Queue<String> parseToPostfix(String userInput) {
        Stack<Character> operations = new Stack<>();
        Queue<String> output = new LinkedList<>();
        StringBuilder numberBuffer = new StringBuilder();
        boolean operatorFlag = true;

        for (char character : userInput.toCharArray()) {
            if (Character.isDigit(character)
                    || (character == '.' && !numberBuffer.isEmpty())
                    || (operatorFlag && character == '-')) {
                numberBuffer.append(character);
                operatorFlag = false;
            } else if ("+-*/^".contains("" + character)) {
                if (operatorFlag) {
                    throw new ArithmeticException();
                }
                addNumberToQueue(numberBuffer, output);
                switch (character) {
                    case '^':
                        operations.push(character);
                        break;
                    case '*':
                    case '/':
                        while (!operations.isEmpty() && operations.peek() == '^') {
                            output.add(operations.pop().toString());
                        }
                        operations.push(character);
                        break;
                    case '+':
                    case '-':
                        while (!operations.isEmpty() && "*/^".contains(operations.peek().toString())) {
                            output.add(operations.pop().toString());
                        }
                        operations.push(character);
                        break;
                    default:
                        throw new ArithmeticException();
                }
                operatorFlag = true;
            } else if ("()".contains("" + character)) {
                addNumberToQueue(numberBuffer, output);
                if (character == '(') {
                    operations.push(character);
                    operatorFlag = true;
                } else {
                    while (!operations.isEmpty() && !"(".contains(operations.peek().toString())) {
                        output.add(operations.pop().toString());
                    }
                    operations.pop();
                    if (!operations.isEmpty()) {
                        output.add(operations.pop().toString());
                    }
                    operatorFlag = false;
                }
            } else {
                throw new ArithmeticException();
            }
        }

        addNumberToQueue(numberBuffer, output);

        while (!operations.isEmpty() && !operations.isEmpty()) {
            output.add(operations.pop().toString());
        }
        return output;
    }

    private static void addNumberToQueue(StringBuilder numberBuffer, Queue<String> output) {
        if (!numberBuffer.isEmpty()) {
            String number = numberBuffer.toString();
            if (number == "-") {
                throw new ArithmeticException("Command not valid math. Too many subsequent operators.");
            }
            output.add(number);
            numberBuffer.setLength(0);
        }
    }

    private static double resolvePostfix(Queue<String> postfix) {
        var calculation = new Stack<Double>();
        
        for(String operationNumber : postfix){
            try {
                double number = Double.parseDouble(operationNumber);
                calculation.push(number);
            } catch (NumberFormatException e) {
                var b = calculation.pop();
                var a = calculation.pop();

                var math = new MathFunction(a, b);

                switch (operationNumber)
                {
                    case "^":
                        calculation.push(math.exponential());
                        break;
                    case "*":
                        calculation.push(math.multiplication());
                        break;
                    case "/":
                        calculation.push(math.division());
                        break;
                    case "+":
                        calculation.push(math.addition());
                        break;
                    case "-":
                        calculation.push(math.subtraction());
                        break;
                }
            }
        }
        
        return calculation.pop();
    }
}
