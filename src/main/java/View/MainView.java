package View;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    JLabel first = new JLabel("Enter the first polynomial:");
    JLabel second = new JLabel("Enter the second polynomial:");
    JLabel result = new JLabel("This is your result:");

    JTextField firstTF = new JTextField();
    JTextField secondTF = new JTextField();
    JTextField resultTF = new JTextField();

    JButton add = new JButton("ADD");
    JButton subtract = new JButton("SUBTRACT");
    JButton multiply = new JButton("MULTIPLY");
    JButton divide = new JButton("DIVIDE");
    JButton integrate = new JButton("INTEGRATE");
    JButton derivative = new JButton("DERIVATIVE");
    JButton clear = new JButton("CLEAR/RESET");

    public MainView(){
        //resultTF.setEditable(false);
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(6, 1, 50, 5));
        top.add(first);
        top.add(firstTF);
        top.add(second);
        top.add(secondTF);
        top.add(result);
        top.add(resultTF);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(3, 2, 5, 5));
        bottom.add(add);
        bottom.add(subtract);
        bottom.add(multiply);
        bottom.add(divide);
        bottom.add(integrate);
        bottom.add(derivative);

        JPanel bottomBottom = new JPanel();
        bottomBottom.setLayout(new GridLayout(1, 1, 5, 5));
        bottomBottom.add(clear);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(5, 5));
        this.setVisible(true);
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.CENTER);
        this.add(bottomBottom, BorderLayout.SOUTH);
        this.pack();
    }

    //************ getters and setters ************//


    public JButton getAdd() {
        return add;
    }

    public JTextField getFirstTF() {
        return firstTF;
    }

    public JTextField getResultTF() {
        return resultTF;
    }

    public JTextField getSecondTF() {
        return secondTF;
    }

    public JButton getClear() {
        return clear;
    }

    public JButton getDivide() {
        return divide;
    }

    public JButton getDerivative() {
        return derivative;
    }

    public JButton getMultiply() {
        return multiply;
    }

    public JButton getIntegrate() {
        return integrate;
    }

    public JButton getSubtract() {
        return subtract;
    }

}
