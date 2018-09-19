package com.TonyCalvez.EncryptDecrypt;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public String executeStrategyEncrypt(String word){
        return strategy.doEncrypt(word);
    }

    public String executeStrategyDecrypt(String word){
        return strategy.doDecrypt(word);
    }

    public void setStrategy(Strategy s){
        this.strategy= s;
    }

}