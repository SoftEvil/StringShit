package basic;

import java.util.EmptyStackException;
import java.util.Stack;

class Calculator {

    /** Добавляем объекты классов хранящих информацию об операторах и операндах */
    private AbstractOperatorsPriorities priorities;

    private AbstractStringIterator iterator;

    /** Добавляем стеки для произведения вычислений */
    private Stack<Double> numbers;

    private Stack<String> operators;

    /** Ассоциируем созданные выше объекты и классы */
    Calculator(AbstractOperatorsPriorities priorities, AbstractStringIterator iterator) {
        this.priorities = priorities;
        this.iterator = iterator;
        numbers = new Stack<>();
        operators = new Stack<>();
    }

    /** Метод перобразующий строку в надоры данных */
    /** Проверка на NullPointerException - пустую входную строку, EmptyStackException - пустое значение из стека */
    Double getResult() throws NullPointerException, EmptyStackException {
        /** Пока есть элементы */
        while(iterator.hasNext()) {
            /** Получаем элемент */
            String element = iterator.next();

            try {
                /** Преобразуем в число с запятой */
                double number = Double.parseDouble(element);
                /** И добавляем полученное число в стек операндов */
                numbers.push(number);
            }
            catch(NumberFormatException e) {
                /** Обработка скобок */
                /** Если встретили скобку открытия добавляем её в операторы */
                if(element.equals("(")) {
                    operators.push(element);
                }
                /** Если встретили скобку закрытия */
                else if(element.equals(")")) {
                    /** Производим вычисления пока не встретим знак открытия скобок */
                    while(!operators.peek().equals("("))
                        calculate();
                    /** В конце удаляем скобку  открытия*/
                    operators.pop();
                }
                else {
                    /** Если это первый оператор (стек операторов пуст) */
                    if(operators.empty()) {
                        /** Добавляем оператор в стек */
                        operators.push(element);
                    }
                    /** Если уже были операторы */
                    else {
                        /** Получаем приоритет текущего оператора*/
                        Integer priority = priorities.getPriority(element);
                        /** Пока есть операторы и не встречаются скобки и приоритет текущей операции ниже или равен последней добавленной */
                        while(!operators.empty() && !operators.peek().equals("(") && !operators.peek().equals(")") &&
                                priority <= priorities.getPriority(operators.peek())) {
                            /** Производим вычисление предшедствующей операции */
                            calculate();
                        }
                        /** После вычисления операции с большим приоритетом добавим наш оператор */
                        operators.push(element);
                    }
                }
            }
        }
        /** Когда элементы кончились доделываем оставшиеся операции */
        while(!operators.empty()) {
            calculate();
        }
        /** Возвращаем итоговое значение */
        return numbers.pop();
    }

    /** Производим вычисления */
    private void calculate() throws EmptyStackException {
        /** Достаем знак оператора из стека */
        String operator = operators.pop();
        /** Достаем операнды не путая их местами, начинаем с последнего */
        Double n2 = numbers.pop();
        Double n1 = numbers.pop();
        /** Очищаем результат */
        Double result = null;
        /** Производим операции в зависимости от знака */
        switch(operator) {
            case "+":
                result =  n1 + n2;
                break;
            case "-":
                result = n1 - n2;
                break;
            case "*":
                result = n1 * n2;
                break;
            case "/":
                result = n1 / n2;
        }
        /** Добавляем результат в стек операндов */
        numbers.push(result);
    }
}
