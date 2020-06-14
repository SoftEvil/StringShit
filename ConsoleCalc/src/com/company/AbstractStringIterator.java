package com.company;

public interface AbstractStringIterator {
    /** Проверка на наличеие следующего элемента*/
    boolean hasNext();

    /**Возвращает следующий элемент*/
    String next();
}
