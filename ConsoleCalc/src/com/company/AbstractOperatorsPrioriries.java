package com.company;

public interface AbstractOperatorsPrioriries {
    Integer getPriority(String var1);

    void addOperator(String var1, int var2);

    void deleteOperator(String var1);
}
