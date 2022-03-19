package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial{
    private ArrayList<Monomial> polynomial = new ArrayList<>();

    public Polynomial(){
    }

    public Polynomial(ArrayList<Monomial> polynomial) {
        this.polynomial = polynomial;
    }

    public ArrayList<Monomial> getPolynomial() {
        return this.polynomial;
    }

    public void sort(){
        Collections.sort(this.polynomial, Monomial.highToLow);
    }

    public void reverseSort(){
        Collections.sort(this.polynomial, Monomial.lowToHigh);
    }

    public static void checkIsPolynomial(String string){
        for(int i = 0;i<string.length();i++){
            if(!(Character.isDigit(string.charAt(i))) && !(string.charAt(i)=='x') && !(string.charAt(i)=='^') && !(string.charAt(i)=='+') && !(string.charAt(i)=='-') && !(string.charAt(i)==' '))
                throw new IllegalArgumentException("This is not a polynomial because of char: "+string.charAt(i));
            if(i+1<string.length() && (string.charAt(i)=='+' || string.charAt(i)=='-') &&(string.charAt(i+1)=='+' || string.charAt(i+1)=='-') )
                throw new IllegalArgumentException("This is not a polynomial because of char: "+string.charAt(i+1));
            if(i+1<string.length() && string.charAt(i)=='x'  && (Character.isDigit(string.charAt(i + 1)) || string.charAt(i+1)=='x'))
                throw new IllegalArgumentException("This is not a polynomial because of char: "+string.charAt(i+1));
        }
    }

    public Polynomial copy(Polynomial polynomial){
        Polynomial aux = new Polynomial();
        aux.polynomial.addAll(polynomial.polynomial);
        return aux;
    }

    public Polynomial (String poly)
    {
        //firstly check if it is a polynomial
        checkIsPolynomial(poly);
        //regex for finding groups of monomials
        Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x\\^\\d+)|(?:x)))");
        Matcher matcher = pattern.matcher(poly);
        while (matcher.find()){
            //make a new monomial out of the string
            Monomial monomial = new Monomial(matcher.group(1));
            //only if the coefficient is different form 0 add the monomial to the polynomial
            if(monomial.getCoefficient()!=0)
                this.polynomial.add(monomial);
        }
        //sort form the beginning to work easier
        sort();
    }

    public Polynomial add(Polynomial polynomial1){
        Polynomial result = new Polynomial();

        //checks each monomial from first polynomial with each monomial from second polynomial to see if they have common exponents
        for(Monomial monomial : this.polynomial){
            boolean found = false;//becomes true when two monomials with same exponent were found
            for(Monomial monomial1 : polynomial1.polynomial){
                if (monomial.getExponent() == monomial1.getExponent()) {
                    //perform the addition
                    Monomial res = new Monomial((monomial.getCoefficient() + monomial1.getCoefficient()), monomial.getExponent());

                    result.polynomial.add(res);
                    found=true;

                }
            }
            // if no common exponent monomial was found, add the monomial as it is
            if(!found)
                result.polynomial.add(monomial);
        }
        //now check if there are any monomials in the second polynomials that were not added in the result
        for(Monomial monomial : polynomial1.polynomial) {
            boolean found = false;
            for (Monomial monomial1 : result.polynomial)
                if (monomial.getExponent() == monomial1.getExponent()){
                    found=true;
                    break;
                }
            if(!found)
                result.polynomial.add(monomial);
        }
        return result;
    }

    public Polynomial subtract(Polynomial polynomial1){

        //copy the second polynomials but switch signs of coefficients
        Polynomial aux = new Polynomial();
        for(Monomial monomial : polynomial1.polynomial){
            aux.polynomial.add(new Monomial(-monomial.getCoefficient(), monomial.getExponent()));
        }
       return add(aux);
    }

    public Polynomial multiply(Polynomial rightPolynomial){
        Polynomial result= new Polynomial();

        //multiply each monomial from the first polynomial with each one from the other one
        for(Monomial mL : this.polynomial)
            for(Monomial mR : rightPolynomial.polynomial) {
                Polynomial temp = new Polynomial();
                temp.polynomial.add(new Monomial((mL.getCoefficient()* mR.getCoefficient()), (mL.getExponent()+ mR.getExponent())));
                result = result.add(temp);
            }
        return result;
    }

    //get the degree of the polynomial (the one with the highest exponent)
    public int degree(Polynomial polynomial){
        sort();
        if(polynomial.polynomial.size()!=0)
        return polynomial.getPolynomial().get(0).getExponent();
        else return -1;
    }

    public Polynomial[] divide(Polynomial rightPolynomial){
        Polynomial reminder = new Polynomial();
        Polynomial quotient = new Polynomial();

        sort();
        rightPolynomial.sort();

        Polynomial aux = new Polynomial();
        aux = copy(this);

        while (degree(aux)>=degree(rightPolynomial)){
            if(aux.polynomial!=null && rightPolynomial.polynomial!= null) {
                //divide the monomials with the greatest degree and store it in the quotient
                Monomial temp = Monomial.divide(aux.polynomial.get(0), rightPolynomial.polynomial.get(0));
                quotient.polynomial.add(temp);
                Polynomial step2 = new Polynomial();
                step2.polynomial.add(temp);
                //multiply the result with the rightPolynomial
                step2 = step2.multiply(rightPolynomial);
                //subtract the previous result from the first polynomial
                aux = aux.subtract(step2);
                aux.sort();
                //remove 0 coefficient term
                if (aux.polynomial.get(0).getCoefficient() != 0)
                    reminder.polynomial.add(aux.polynomial.get(0));
                aux.polynomial.remove(0);
            }
        }
        Polynomial[] result = {quotient, aux};
        return result;
    }

    public Polynomial derivative(){
        Polynomial result = new Polynomial();

        //simple calculus for derivative
        for(Monomial monomial : polynomial)
            if(monomial.getExponent()>0)
                result.polynomial.add(new Monomial((monomial.getCoefficient()* monomial.getExponent()), (monomial.getExponent()-1)));

        return result;
    }

    public Polynomial integrate(){
        Polynomial result = new Polynomial();

        //simple integration calculus
        for(Monomial monomial : polynomial)
                result.polynomial.add(new Monomial((monomial.getCoefficient()/(monomial.getExponent()+1)), (monomial.getExponent()+1)));

        return result;
    }

    //build the first (the highest degree) monomial of the polynomial
    public StringBuilder firstNr(Monomial monomial){
        StringBuilder string = new StringBuilder();
        if(monomial.getCoefficient()!=1)
            string.append(monomial.getCoefficient());
        if(monomial.getCoefficient()==1 && monomial.getExponent()==0)
            string.append(monomial.getCoefficient());
        if(monomial.getExponent()==0) {
        }
        else {
            string.append("x");
            if(monomial.getExponent()>1)
                string.append("^").append(monomial.getExponent());
        }
        return string;
    }

    //build each other monomials
    public StringBuilder otherNr(Monomial monomial){
        StringBuilder string = new StringBuilder();
        if(monomial.getCoefficient()==0)
            return string;
        if(monomial.getCoefficient()>=0)  string.append("+");
        if(monomial.getCoefficient()!=1)
            string.append(monomial.getCoefficient());
        if(monomial.getCoefficient()==1 && monomial.getExponent()==0)
            string.append(monomial.getCoefficient());
        if(monomial.getExponent()!=0) {
            string.append("x");
            if(monomial.getExponent()>1)
                string.append("^").append(monomial.getExponent());
        }
        return string;
    }

    //build the string out of the ArrayList
    public String toString(){
        sort();
        StringBuilder string = new StringBuilder();
        if(polynomial.size()==0)
            string.append(0);
        else
        for(Monomial monomial: polynomial)
            if(monomial == polynomial.get(0))
                string.append(firstNr(monomial));
            else
                string.append(otherNr(monomial));
         return string.toString();
    }
}
