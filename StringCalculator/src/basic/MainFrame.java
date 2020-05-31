package basic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EmptyStackException;

/** Объявляем элементы интерфейса */
class MainFrame extends JFrame {
    private JLabel label;
    private JTextField input;
    private JButton button;
    private JTextArea textArea;
    private JPanel inputPanel;
    private JPanel parentPanel;

    MainFrame() {
        /** Заголовок формы */
        super("Calculator");

        /** Создаем экземпляры классов интерфейса */
        label = new JLabel("Math expression:");
        input = new JTextField(30);
        button = new JButton("Calculate");
        textArea = new JTextArea();

        /** Добавляем элементы на форму */
        inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        inputPanel.add(label);
        inputPanel.add(input);
        inputPanel.add(button);

        parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(inputPanel, BorderLayout.NORTH);
        parentPanel.add(textArea, BorderLayout.CENTER);
        setContentPane(parentPanel);

        /** Добавляем обработчик нажатия кнопки */
        textArea.setEditable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        button.addActionListener(this::getResult);
    }


    private void getResult(ActionEvent event) {
        /** Получаем строку из поля ввода */
        String expression = input.getText();

        /** Если Введенная строка пустая показываем диагностическое сообщение и выходим из процедуры */
        if(expression.isEmpty()) {
            JOptionPane.showMessageDialog(parentPanel, "No math expression specified!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        /** Создаем новый экземпляр класса приоритетов и задаем значения приоритетов операций, кроме скобок (обрабатываются в классе Calculator) */
        AbstractOperatorsPriorities priorities = new OperatorsPriorities();
        priorities.addOperator("+", 1);
        priorities.addOperator("-", 1);
        priorities.addOperator("*", 2);
        priorities.addOperator("/", 2);

        /** Создаем экземпляр класса в котором переданная строка разделится на элементы */
        AbstractStringIterator iterator = new StringIterator(expression);

        /** Созадем экземпляр класса вычислений */
        Calculator calculator = new Calculator(priorities, iterator);


        try {
            /** вызываем метод вычисления */
            Double result = calculator.getResult();
            /** Полученное на прошлом шаге итоговое значение выводим на форму */
            textArea.setText(String.format("%.2f", result));
        }
        /** Если что-то пошло не так выводим сообщение об ошибке */
        catch(NullPointerException | EmptyStackException e) {
            JOptionPane.showMessageDialog(parentPanel, "Incorrect math expression!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
