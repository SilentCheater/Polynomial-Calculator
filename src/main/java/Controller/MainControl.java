package Controller;

import Model.Polynomial;
import View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainControl implements ActionListener {
    MainView mainView = new MainView();
    public MainControl(){
        mainView.getAdd().addActionListener(this::actionPerformed);
        mainView.getSubtract().addActionListener(this::actionPerformed);
        mainView.getMultiply().addActionListener(this::actionPerformed);
        mainView.getDivide().addActionListener(this::actionPerformed);
        mainView.getDerivative().addActionListener(this::actionPerformed);
        mainView.getClear().addActionListener(this::actionPerformed);
        mainView.getIntegrate().addActionListener(this::actionPerformed);
    }

    private String check (String string){
        try{
            Polynomial.checkIsPolynomial(string);
            return string;
        }catch(IllegalArgumentException iea){
            JOptionPane.showMessageDialog(null, "Exception: "+iea.getMessage(), "FORMAT ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Polynomial polynomial1 = new Polynomial(check(mainView.getFirstTF().getText()));
        Polynomial polynomial2 = new Polynomial(check(mainView.getSecondTF().getText()));

        if(polynomial1.getPolynomial().size()!=0 && polynomial2.getPolynomial().size()!=0)
        {
            if (e.getSource() == mainView.getAdd())
                mainView.getResultTF().setText(polynomial1.add(polynomial2).toString());

            if (e.getSource() == mainView.getDivide()) {
                polynomial2.sort();
                System.out.println(mainView.getSecondTF().getText());
                if (mainView.getSecondTF().getText().equals("") || polynomial2.getPolynomial().get(0).getCoefficient() == 0)
                    JOptionPane.showMessageDialog(null, "Exception: can't divide by 0", "DIVISION BY ZERO", JOptionPane.ERROR_MESSAGE);
                else
                    mainView.getResultTF().setText(polynomial1.divide(polynomial2)[0].toString() + "; reminder: " + polynomial1.divide(polynomial2)[1].toString());
            }

            if (e.getSource() == mainView.getSubtract())
                mainView.getResultTF().setText(polynomial1.subtract(polynomial2).toString());

            if (e.getSource() == mainView.getMultiply())
                mainView.getResultTF().setText(polynomial1.multiply(polynomial2).toString());
        }
        else
            if(e.getSource()!=mainView.getDerivative() && e.getSource()!=mainView.getIntegrate() && e.getSource()!=mainView.getClear())
            JOptionPane.showMessageDialog(null, "You should introduce 2 polynomials for this operation",
                    "NOT ENOUGH OPERATORS", JOptionPane.ERROR_MESSAGE);


        if(e.getSource()==mainView.getDerivative())
            mainView.getResultTF().setText((polynomial1.derivative().toString()));

        if(e.getSource()==mainView.getIntegrate())
            mainView.getResultTF().setText((polynomial1.integrate().toString())+" + C");

        if(e.getSource()==mainView.getClear()) {
            mainView.getResultTF().setText("");
            mainView.getFirstTF().setText("");
            polynomial1.getPolynomial().clear();
            mainView.getSecondTF().setText("");
            polynomial2.getPolynomial().clear();
        }

    }
}
