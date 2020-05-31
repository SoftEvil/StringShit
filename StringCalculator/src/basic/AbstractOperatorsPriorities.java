package basic;

/** Абстрактное представление о классе, хранящем приоритеты операций.*/
public interface AbstractOperatorsPriorities {

    /** Возвращает приоритет операции.*/
    Integer getPriority(String operator);

    /** Добавляет оператор c заданным приоритетом. */
    void addOperator(String operator, int priority);
    /** Удаляет оператор. */
    void deleteOperator(String operator);
}
