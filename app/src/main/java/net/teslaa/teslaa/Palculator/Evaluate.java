package net.teslaa.teslaa.Palculator;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;

class Evaluate {
    private Status st = new Status();
    static HashMap<String, Operator> hmp = new HashMap<>();
    void addOperator(Operator op) {
        hmp.put(op.getOperator(), op);
    }

    boolean getSt(){
        return st.isStatus();
    }

    int expAnalyzer(String exp) {
        MainActivity m = new MainActivity();
        String[] expArr = exp.split(" ");
        String startString = expArr[0];
        String endString = expArr[expArr.length-1];
        String regex = "^[\\d\\+\\/\\×\\.\\- \\(\\)]*$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(exp).matches()) {
            //System.out.println("an appropriate character entered.");
            //status = false;
            return 0;
        } else if (hmp.containsKey(startString)) {
            //if(Validator.validate(exp)){
                //status = true;
                return prefixEvaluate(exp);
            //} else {
                //status = false;
            //    return 0;
            //}
        } else if (hmp.containsKey(endString)){
            //status = true;
            return postfixEvaluate(expArr);
        } else {
            //status = false;
            return 0;
        }
        //}
        //return 0;
    }

    private int prefixEvaluate(String expString) {

        Stack<Integer> stack = new Stack<>();
        String[] exp = expString.split(" ");
        try {
            for (int i = exp.length - 1; i >= 0; i--) {
                String s = exp[i];
                if (hmp.containsKey(s)) {
                    int limit = hmp.get(s).getOperandNumber();
                    int Arr[] = new int[limit];
                    for (int j = 0; j < limit; j++) {
                        Arr[j] = stack.pop();
                    }
                    stack.push(hmp.get(s).doAction(Arr));
                } else {
                    stack.push(Integer.parseInt(s));
                }
            }
        } catch (NullPointerException | EmptyStackException e){
            st.setStatus(false);
            return 0;
        }

        if (stack.size()-1 == 0) {
            st.setStatus(true);
            return stack.pop();
        } else {
            st.setStatus(false);
            return 0;
        }
    } // Prefix method end

    private int postfixEvaluate(String[] exp) {
        Stack<Integer> stack = new Stack<>();
        try {
            for (int i = 0; i <= exp.length - 1; i++) {
                String s = exp[i];
                if (hmp.containsKey(s)) {
                    int limit = hmp.get(s).getOperandNumber();
                    int Arr[] = new int[limit];
                    for (int j = 0; j < limit; j++) {
                        Arr[j] = stack.pop();
                    }
                    stack.push(hmp.get(s).doAction(Arr));
                } else {
                    stack.push(Integer.parseInt(s));
                }
            }
        } catch (NullPointerException | EmptyStackException e){
            st.setStatus(false);
            return 0;
        }
        if (stack.size()-1 == 0) {
            st.setStatus(true);
            return stack.pop();
        } else {
            st.setStatus(false);
            return 0;
        }

    } // Postfix method end

}