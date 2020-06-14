package com.company;

import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator {
    private AbstractOperatorsPrioriries priorities;
    private AbstractStringIterator iterator;
    private Stack<Double> numbers;
    private Stack<String> operators;

    Calculator(AbstractOperatorsPrioriries priorities, AbstractStringIterator iterator) {
        this.priorities = priorities;
        this.iterator = iterator;
        this.numbers = new Stack();
        this.operators = new Stack();
    }

    Double getResult() throws NullPointerException, EmptyStackException {
        while(this.iterator.hasNext()) {
            String element = this.iterator.next();

            try {
                double number = Double.parseDouble(element);
                this.numbers.push(number);
            } catch (NumberFormatException var4) {
                if (element.equals("(")) {
                    this.operators.push(element);
                } else if (element.equals(")")) {
                    while(!((String)this.operators.peek()).equals("(")) {
                        this.calculate();
                    }

                    this.operators.pop();
                } else if (this.operators.empty()) {
                    this.operators.push(element);
                } else {
                    Integer priority = this.priorities.getPriority(element);

                    while(!this.operators.empty() && !((String)this.operators.peek()).equals("(") && !((String)this.operators.peek()).equals(")") && priority <= this.priorities.getPriority((String)this.operators.peek())) {
                        this.calculate();
                    }

                    this.operators.push(element);
                }
            }
        }

        while(!this.operators.empty()) {
            this.calculate();
        }

        return (Double)this.numbers.pop();
    }

    private void calculate() throws EmptyStackException {
        String operator = (String)this.operators.pop();
        Double n2 = (Double)this.numbers.pop();
        Double n1 = (Double)this.numbers.pop();
        Double result = null;
        byte var6 = -1;
        switch(operator.hashCode()) {
            case 42:
                if (operator.equals("*")) {
                    var6 = 2;
                }
                break;
            case 43:
                if (operator.equals("+")) {
                    var6 = 0;
                }
            case 44:
            case 46:
            default:
                break;
            case 45:
                if (operator.equals("-")) {
                    var6 = 1;
                }
                break;
            case 47:
                if (operator.equals("/")) {
                    var6 = 3;
                }
        }

        switch(var6) {
            case 0:
                result = n1 + n2;
                break;
            case 1:
                result = n1 - n2;
                break;
            case 2:
                result = n1 * n2;
                break;
            case 3:
                result = n1 / n2;
        }

        this.numbers.push(result);
    }
}
