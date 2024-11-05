package org.a3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private final CalculatorService service;
    private final JTextField display;

    private boolean isNewOperation = true;
    private boolean operatorEntered = false;

    public CalculatorGUI() {
        service = new CalculatorService();

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

        var panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        String[] buttons = {
            "7", "8", "9", "C",
            "4", "5", "6", "/",
            "1", "2", "3", "*",
            "0", "=", "+", "-"
        };

        for (String text : buttons) {
            var button = new JButton(text);
            button.addActionListener(this);

            panel.add(button);
        }

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setTitle("Calculator");
        setSize(300, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var command = e.getActionCommand();

        if (command.chars().allMatch(Character::isDigit) || command.equals(".")) {
            if (isNewOperation) {
                display.setText("");
                isNewOperation = false;
                operatorEntered = false;
            }

            display.setText(display.getText() + command);

            return;
        }

        if (command.equals("=")) {
            try {
                var tokens = display.getText().split(" ");
                if (tokens.length < 3) {
                    return;
                }

                var num1 = Double.parseDouble(tokens[0]);
                var num2 = Double.parseDouble(tokens[2]);

                var operator = tokens[1];

                var result = switch (operator) {
                    case "+" -> service.add(num1, num2);
                    case "-" -> service.subtract(num1, num2);
                    case "*" -> service.multiply(num1, num2);
                    case "/" -> service.divide(num1, num2);
                    default -> 0;
                };

                display.setText(String.valueOf(result));
                isNewOperation = true;
            } catch (Exception ignored) { }

            return;
        }

        if (command.equals("C")) {
            display.setText("");
            isNewOperation = true;
            operatorEntered = false;

            return;
        }

        if (!operatorEntered) {
            display.setText(display.getText() + " " + command + " ");
            operatorEntered = true;
        }
    }
}
