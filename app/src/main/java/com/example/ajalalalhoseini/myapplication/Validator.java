package com.example.ajalalalhoseini.myapplication;


import java.util.ArrayList;

public class Validator {
    static boolean result;

    public static boolean validate(String exp) {
        String[] expArr = exp.split(" ");
        ArrayList<String> alInt = new ArrayList<>();
        ArrayList<String> alSymb = new ArrayList<>();
        for (int i = 0; i < expArr.length; i++) {
            if (expArr[i].matches("^\\d+$")) {
                alInt.add(expArr[i]);
            }
        }
        for (int k = 0; k < expArr.length; k++) {
            if (expArr[k].matches("^[\\+\\-\\*]$")){
                alSymb.add(expArr[k]);
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

