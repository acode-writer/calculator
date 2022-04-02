package sn.acodewriter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame {
    private JPanel container = new JPanel();
    private JLabel screen = new JLabel();
    private Dimension dimension = new Dimension(50,40);
    private Dimension operatorDimension = new Dimension(55,32);
    private String[] tabs = {"1","2","3","4","5","6","7","8","9","0",".","=","C","+","-","*","/"};
    private double number;
    private String operator = "";
    private boolean isAnOperatorClicked = false, isScreenRefreshed = false;

    public Window()  {
        this.setTitle("Calculator");
        this.setSize(250,290);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.paintCalculator();
        this.setContentPane(container);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void paintCalculator() {
        Font font = new Font("DS-digital",Font.BOLD, 20);
        screen = new JLabel("0");
        screen.setHorizontalAlignment(JLabel.RIGHT);
        screen.setFont(font);
        screen.setPreferredSize(new Dimension(220,30));
        JPanel operatorPanel = new JPanel();
        operatorPanel.setPreferredSize(new Dimension(70,260));
        JPanel numberPanel = new JPanel();
        numberPanel.setPreferredSize(new Dimension(165,260));
        JPanel screenPanel = new JPanel();

        for (int i = 0; i < tabs.length; i++)  {
            JButton button = new JButton(tabs[i]);;
            button.setPreferredSize(dimension);

            switch (i){
                case 11:
                    button.addActionListener(new EqualListener());
                    numberPanel.add(button);
                    break;
                case 12:
                    button.setForeground(Color.RED);
                    button.setPreferredSize(operatorDimension);
                    button.addActionListener(new ResetListener());
                    operatorPanel.add(button);
                    break;
                case 13:
                    button.setPreferredSize(operatorDimension);
                    button.addActionListener(new AdditionListener());
                    operatorPanel.add(button);
                    break;
                case 14:
                    button.setPreferredSize(operatorDimension);
                    button.addActionListener(new MinusListener());
                    operatorPanel.add(button);
                    break;
                case 15:
                    button.setPreferredSize(operatorDimension);
                    button.addActionListener(new MultiplicationListener());
                    operatorPanel.add(button);
                    break;
                case 16:
                    button.setPreferredSize(operatorDimension);
                    button.addActionListener(new DivisionListener());
                    operatorPanel.add(button);
                    break;
                default:
                    button.addActionListener(new NumberListener());
                    numberPanel.add(button);
                    break;
            }
            screenPanel.add(screen);
            screenPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            container.add(screenPanel,BorderLayout.NORTH);
            container.add(numberPanel,BorderLayout.CENTER);
            container.add(operatorPanel,BorderLayout.EAST);
        }
    }

    private void calcul(){
        if (operator.equals("+")){
            number += Double.valueOf(screen.getText()).doubleValue();
            screen.setText(String.valueOf(number));
        }
        if(operator.equals("-")){
            number -= Double.valueOf(screen.getText()).doubleValue();
            screen.setText(String.valueOf(number));
        }
        if (operator.equals("*")){
            number *= Double.valueOf(screen.getText()).doubleValue();
            screen.setText(String.valueOf(number));
        }
        if (operator.equals("/")){
            try {
                number /= Double.valueOf(screen.getText()).doubleValue();
                screen.setText(String.valueOf(number));
            }catch (ArithmeticException e){
                screen.setText("0");
            }
        }
    }

    class NumberListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String str = ((JButton)actionEvent.getSource()).getText();
            if (isScreenRefreshed){
                isScreenRefreshed = false;
            }else{
                if (!screen.getText().equals("0")){
                    str = screen.getText() + str;
                }
            }
            screen.setText(str);
        }
    }

    class ResetListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            isScreenRefreshed = true;
            isAnOperatorClicked = false;
            operator = "";
            number = 0;
            screen.setText("");
        }
    }

    class AdditionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (isAnOperatorClicked){
                calcul();
                screen.setText(String.valueOf(number));
            }else {
                isAnOperatorClicked = true;
                number = Double.valueOf(screen.getText()).doubleValue();
            }
            operator = "+";
            isScreenRefreshed = true;
        }
    }

    class MinusListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (isAnOperatorClicked){
                calcul();
                screen.setText(String.valueOf(number));
            }else {
                isAnOperatorClicked = true;
                number = Double.valueOf(screen.getText()).doubleValue();
            }
            operator = "-";
            isScreenRefreshed = true;
        }
    }

    class MultiplicationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (isAnOperatorClicked){
                calcul();
                screen.setText(String.valueOf(number));
            }else {
                isAnOperatorClicked = true;
                number = Double.valueOf(screen.getText()).doubleValue();
            }
            operator = "*";
            isScreenRefreshed = true;
        }
    }

    class DivisionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (isAnOperatorClicked){
                calcul();
                screen.setText(String.valueOf(number));
            }else {
                isAnOperatorClicked = true;
                number = Double.valueOf(screen.getText()).doubleValue();
            }
            operator = "/";
            isScreenRefreshed = true;
        }
    }

    class EqualListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            calcul();
            isAnOperatorClicked = false;
            isScreenRefreshed = true;
        }
    }


}
