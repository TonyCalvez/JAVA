package com.TonyCalvez.EncryptDecrypt;

public class Main {
    public static void main(String[] args) {
        Context context = new Context(new Ceasar(13));
        System.out.println(context.executeStrategyEncrypt("MOT"));
        System.out.println(context.executeStrategyDecrypt(context.executeStrategyEncrypt("MOT")));

        context.setStrategy(new Substitution("PAS"));
        System.out.println(context.executeStrategyEncrypt("MOT"));
        System.out.println(context.executeStrategyDecrypt(context.executeStrategyEncrypt("MOT")));

        context.setStrategy(new Ceasar(21));
        context.executeStrategyEncrypt("MOT");
        System.out.println(context.executeStrategyEncrypt("MOT"));
        System.out.println(context.executeStrategyDecrypt(context.executeStrategyEncrypt("MOT")));
    }
}