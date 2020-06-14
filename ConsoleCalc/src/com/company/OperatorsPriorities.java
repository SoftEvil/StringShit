package com.company;

import java.util.HashMap;
import java.util.Map;

class OperatorsPriorities implements AbstractOperatorsPrioriries {
    private Map<String, Integer> map;

    OperatorsPriorities(HashMap<String, Integer> map) {
        this.map = map;
    }

    OperatorsPriorities() {
        this.map = new HashMap();
    }

    public Integer getPriority(String operator) {
        return (Integer)this.map.get(operator);
    }

    public void addOperator(String operator, int priority) {
        this.map.put(operator, priority);
    }

    public void deleteOperator(String operator) {
        this.map.remove(operator);
    }
}
