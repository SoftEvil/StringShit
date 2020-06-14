package com.company;
import java.util.EmptyStackException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        if (expression.isEmpty()) {
            System.out.print("No math expression specified!");
        } else {
            AbstractOperatorsPrioriries priorities = new OperatorsPriorities();
            priorities.addOperator("+", 1);
            priorities.addOperator("-", 1);
            priorities.addOperator("*", 2);
            priorities.addOperator("/", 2);
            AbstractStringIterator iterator = new StringIterator(expression);
            Calculator calculator = new Calculator(priorities, iterator);

            try {
                Double result = calculator.getResult();
                System.out.print(result);
            } catch (EmptyStackException | NullPointerException var7) {
                System.out.print("Incorrect math expression!");
            }

        }
    }
}
