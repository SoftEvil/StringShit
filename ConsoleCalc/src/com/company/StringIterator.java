package com.company;

class StringIterator implements AbstractStringIterator {
    private String string;
    private int currentIndex;

    StringIterator(String string) {
        this.string = string.replace(" ", "");
        this.currentIndex = 0;
    }

    public boolean hasNext() {
        return this.string != null && !this.string.isEmpty() && this.currentIndex < this.string.length();
    }

    public String next() {
        StringBuilder operand = new StringBuilder();

        char element;
        for(element = this.string.charAt(this.currentIndex); Character.isDigit(element) || element == '.' || this.currentIndex == 0 && element == '-' || this.currentIndex > 0 && this.string.charAt(this.currentIndex - 1) == '(' && element == '-'; element = this.string.charAt(this.currentIndex)) {
            operand.append(element);
            ++this.currentIndex;
            if (this.currentIndex == this.string.length()) {
                return operand.toString();
            }
        }

        if (operand.length() != 0) {
            return operand.toString();
        } else {
            ++this.currentIndex;
            return Character.toString(element);
        }
    }
}
