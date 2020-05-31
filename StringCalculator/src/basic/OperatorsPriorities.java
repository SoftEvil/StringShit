package basic;

import java.util.HashMap;
import java.util.Map;

/** Класс, хранящий приоритеты операций. */
class OperatorsPriorities implements AbstractOperatorsPriorities {
    /** В ней хранится соответствие знака и приоритета операции */
    private Map<String, Integer> map;

    OperatorsPriorities(HashMap<String, Integer> map) {
        this.map = map;
    }

    OperatorsPriorities() {
        map = new HashMap<>();
    }

    /** Переопределяем методы абстактного класса */
    /** Возврат значения приоритета знака*/
    @Override
    public Integer getPriority(String operator) {
        return map.get(operator);
    }
    /**Добавление нового знака с заданным приоритетом*/
    @Override
    public void addOperator(String operator, int priority) {
        map.put(operator, priority);
    }

    /**Удаление знака*/
    @Override
    public void deleteOperator(String operator) {
        map.remove(operator);
    }
}
