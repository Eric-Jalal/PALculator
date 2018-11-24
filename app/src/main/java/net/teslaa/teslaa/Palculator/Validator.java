package net.teslaa.teslaa.Palculator;


import java.util.ArrayList;

public class Validator {
    private static boolean result;

    public static boolean validate(String exp) {
        String[] expArr = exp.split(" ");
        ArrayList<String> alInt = new ArrayList<>();
        ArrayList<String> alSymb = new ArrayList<>();
        for (String anExpArr : expArr) {
            if (anExpArr.matches("^\\d+$")) {
                alInt.add(anExpArr);
            }
        }
        for (String anExpArr : expArr) {
            if (anExpArr.matches("^[+\\-*]$")) {
                alSymb.add(anExpArr);
            }
        }
        for (int j = 0; j < alSymb.size(); j++) {
            if (Evaluate.hmp.containsKey(expArr[j])) {
                int limit =+ Evaluate.hmp.get(expArr[j]).getOperandNumber();
                if (limit == alInt.size()) {
                    result = true;
                } else {
                    result = false;
                }
            }
        }


        return result;
    }
}

