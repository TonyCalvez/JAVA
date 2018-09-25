package com.TonyCalvez.Calculatrice;

public enum ArithmeticOperator {
    //
    PLUS("+",1),
    MINUS("-", 1),
    MULTIPLY ("x", 2),
    DIVIDE ("/", 2),
    MODULO ("%", 3);

    private String symbol;
    private int priority;

    ArithmeticOperator(String symbol, int priority){
        this.symbol = symbol;
        this.priority = priority;
    }

    public String getSymbol(){

        return this.symbol;
    }

    public int getPriority(){
        return this.priority;
    }

    public static ArithmeticOperator fromString(String s){
        String result;
        for (ArithmeticOperator i : ArithmeticOperator.values()){
            if (s.equals(i.getSymbol())) {
                return i;
            }
        }
        return null;
    }

}
