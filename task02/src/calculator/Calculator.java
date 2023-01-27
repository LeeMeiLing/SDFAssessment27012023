package calculator;

public class Calculator {
    
    private double operand0 = 0d;
    private double operand1 = 0d;

    public Calculator(String operand0, String operand1) {

        this.operand0 = Double.parseDouble(operand0);
        this.operand1 = Double.parseDouble(operand1);

    }


    public double add() {
        return operand0 + operand1;
    }

    public double subtract() {
        return operand0 - operand1;
    }

    public double multiply() {
        return operand0 * operand1;
    }

    public double divide() {
        return operand0 / operand1; 
    }

    
}
