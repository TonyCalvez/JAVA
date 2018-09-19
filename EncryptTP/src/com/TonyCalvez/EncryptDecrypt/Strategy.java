package com.TonyCalvez.EncryptDecrypt;

public interface Strategy {
    public String doEncrypt(String word);
    public String doDecrypt(String word);
}