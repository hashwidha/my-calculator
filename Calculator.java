import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    Frame frame;
    TextField textField;
    Panel panel;
    String firstNumber, secondNumber, operator;
    boolean isOperatorClicked;
    
    public Calculator() {
        frame = new Frame("Calculator");
        textField = new TextField();
        panel = new Panel();
        firstNumber = secondNumber = operator = "";
        isOperatorClicked = false;
        frame.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        frame.add(textField, BorderLayout.NORTH);
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };
        Button[] buttons = new Button[16];
        for (int i = 0; i < 16; i++) {
            buttons[i] = new Button(buttonLabels[i]);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        frame.add(panel, BorderLayout.CENTER);
        
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (isOperatorClicked) {
                secondNumber += command;
                textField.setText(secondNumber);
            } else {
                firstNumber += command;
                textField.setText(firstNumber);
            }
        } 
        else if (command.charAt(0) == '+' || command.charAt(0) == '-' || command.charAt(0) == '*' || command.charAt(0) == '/') {
            operator = command;
            isOperatorClicked = true;
        } 
        else if (command.equals("=")) {
            double num1 = Double.parseDouble(firstNumber);
            double num2 = Double.parseDouble(secondNumber);
            double result = 0;
            
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(result));
            firstNumber = secondNumber = operator = "";
            isOperatorClicked = false;
        } 
        else if (command.equals("C")) {
            textField.setText("");
            firstNumber = secondNumber = operator = "";
            isOperatorClicked = false;
        }
    }
    
    public static void main(String[] args) {
        new Calculator();
    }
}