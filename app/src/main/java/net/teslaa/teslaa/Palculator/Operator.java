package net.teslaa.teslaa.Palculator;

/**
 * This class is used to calculate prefix and postfix numbers
 * Operator class contains Operator default constructor to get limitation
 * of how many digit have to pop out from stack and get calculated by it's operator action performer. *
 * difference between Operator-extending classes are in doAction methods rely on wich type of operator they want to calculate because of
 * some math differences.
 * @author Teslaa
 */

public abstract class Operator {
    //String symbol;
    private int operandNumber;

    /**
     * Operator default constructor to get limitation
     * of how many digit have to pop out from stack and get calculated by it's operator action performer.
     *
     */
    Operator(int opNumber) {
        this.operandNumber = opNumber;
    }

    /**
     * doAction method care to calculate digits parsed into their specific class.
     * @param Arr
     * @return
     */
    public abstract int doAction(int Arr[]);

    /** method abstract getOperator declared to return the preferred symbol in every Operator-extending classes.
     *
     * @return pre-defined symbol
     */
    public abstract String getOperator();

    /**
     * Operator default constructor to get limitation
     * of how many digit have to pop out from stack and get calculated by it's operator action performer.
     * @return limitation
     */
    int getOperandNumber() {
        return operandNumber;
    }
}

class Plus extends Operator {

    Plus(int opNumber) {
        super(opNumber);
    }

    public int doAction(int Arr[]) {
        int result = 0;
        for (int aArr : Arr) {
            result += aArr;
        }
        return result;
    }

    public String getOperator() {
        return "+";
    }

}

class Minus extends Operator {

    Minus(int opNumber) {
        super(opNumber);
    }

    public int doAction(int Arr[]) {
        int result = 0;
        for (int aArr : Arr) {
            result = aArr - result;
        }
        return result;
    }

    public String getOperator() {
        return "-";
    }

}

class Mult extends Operator {

    Mult(int opNumber) {
        super(opNumber);
    }

    public int doAction(int Arr[]) {
        int result = 1;
        for (int aArr : Arr) {
            result *= aArr;
        }
        return result;
    }

    public String getOperator() {
        return "×";
    }

}

class Divide extends Operator {

    Divide(int opNumber) {
        super(opNumber);
    }

        public int doAction ( int Arr[]){
            try{
        int result = 1;
                for (int aArr : Arr) {
                    result = aArr / result;
                }
        return result;
            } catch (ArithmeticException e){return 0;}
    }


    public String getOperator() {
        return "/";
    }

}