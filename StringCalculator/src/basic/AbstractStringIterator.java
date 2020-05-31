package basic;

/** Абстрактное представление об итераторе для строки,с математическим выражением. */
public interface AbstractStringIterator {
    /** Проверка на наличеие следующего элемента*/
    boolean hasNext();

    /**Возвращает следующий элемент*/
    String next();
}
