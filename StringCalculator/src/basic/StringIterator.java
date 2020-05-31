package basic;


class StringIterator implements AbstractStringIterator {

    /** Входная строка */
    private String string;
    /** Индекс текущего символа строки */
    private int currentIndex;

    /** Убираем пробелы из выражения */
    StringIterator(String string) {
        this.string = string.replace(" ", "");
        /** Устанавливаем положение на нулевом эелменте */
        currentIndex = 0;
    }

    /** Переопределяем метод проверки на наличие следующего элемента */
    @Override
    public boolean hasNext() {
        /** Если входная строка не пустая и её ссылка не содержит null и текущий индекс не последний, значит не конец строки */
        return  string != null && !string.isEmpty() && currentIndex < string.length();
    }

    /** Переопределяем метод получения следующего элемента */
    @Override
    public String next() {
        /** Добавляем экземпляр класса работы со строками */
        StringBuilder operand = new StringBuilder();
        /** Получаем элемент по индексу */
        char element = string.charAt(currentIndex);

        /** Пока элемент это сивол или знак не являющийся операцией */
        while(Character.isDigit(element) || element == '.' || (currentIndex == 0 && element == '-') ||
                (currentIndex > 0 && string.charAt(currentIndex - 1) == '(' && element == '-')) {
            /** Добавляем символ в конец подстроки */
            operand.append(element);
            /** Переходим к следующему символу */
            currentIndex++;
            /** Если это последний элемент строки то позвращаем подстроку  и выходим из процедуры */
            if(currentIndex == string.length())
                return operand.toString();
            /** Если элемент не послдений в строке, то получаем значение следующего */
            element = string.charAt(currentIndex);
        }
         /**Если встретили элемент являющийся оператором и подстрока не пустая */
        if(operand.length() != 0)
            /** возвращаем подстроку */
            return operand.toString();
        /** переходим к следующему индексу*/
        currentIndex++;
        /** Возврат символа оператора */
        return Character.toString(element);
    }
}
