package Model;

import java.util.ArrayList;
import java.util.Comparator;

public class Monomial {

    private double coefficient;
    private int exponent;

    public Monomial(){}

    public Monomial(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    //********************getters & setters********************//
    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }
    //*********************************************************//


    //builds a StringBuilder from an ArrayList of characters
    public StringBuilder builder(ArrayList<Character> arrayList){
        StringBuilder builder = new StringBuilder(arrayList.size());
        for(Character ch : arrayList)
            builder.append(ch);
        return builder;
    }

    public Monomial(String string){

        //declare two ArrayLists to store the coefficients and exponents character by character
        ArrayList<Character> coefficientArrayList = new ArrayList<>();
        ArrayList<Character> exponentArrayList = new ArrayList<>();

        int x=0; // variable used to traverse the string provided

        // this loop will fill the coefficient ArrayList with characters until the end of the string is reached
        // or until character 'x' is found
        while(x<string.length() && string.charAt(x) != 'x') {
            coefficientArrayList.add(string.charAt(x));
            x++;
        }

        // if no coefficient was found, it means it was 0
        if(coefficientArrayList.size()==0)
            this.coefficient=1;
        else
            if(coefficientArrayList.size()==1){
                switch (coefficientArrayList.get(0)) {
                    case '-':this.setCoefficient(-1); break;
                    case '+': this.setCoefficient(1); break;
                    default:this.setCoefficient(Double.parseDouble(builder(coefficientArrayList).toString()));
                }
                }
            if(coefficientArrayList.size()>1)
                // a couple of functions to convert from ArrayList to double type
                this.setCoefficient(Double.parseDouble(builder(coefficientArrayList).toString()));

        // if this moment, after reading the coefficient, if there is just one more character and that one is 'x'
        // it means that the exponent must be one (no "^y", y being an int, supplied)
        if(x+1==string.length())
            if(string.charAt(x)=='x')
                this.setExponent(1);

        // if there are more characters, and the next one after 'x' is '^', it means that there is an
        // exponent provided; same idea to extract the exponent as for the coefficient
        if(x+1<string.length() && string.charAt(x+1)=='^') {
            x += 2;
            while (x < string.length()){
                exponentArrayList.add(string.charAt(x));
                x++;
            }
            this.setExponent(Integer.parseInt(builder(exponentArrayList).toString()));
        }
    }

    //used to order the polynomials from high to low and vice versa
    public static Comparator<Monomial> highToLow = new Comparator<Monomial>() {
        @Override
        public int compare(Monomial o1, Monomial o2) {
            return o2.getExponent()- o1.getExponent();
        }
    };
    public static Comparator<Monomial> lowToHigh = new Comparator<Monomial>() {
        @Override
        public int compare(Monomial o1, Monomial o2) {
            return o1.getExponent()- o2.getExponent();
        }
    };

    //a method to divide two monomials
    public static Monomial divide (Monomial m1, Monomial m2){
        return new Monomial((m1.getCoefficient()/m2.getCoefficient()), (m1.getExponent()- m2.getExponent()));
    }

}
