package com.TonyCalvez.EncryptDecrypt;

public class Substitution implements Strategy {
    private String keySeed;

    public Substitution(String keySeed) {
        this.keySeed = keySeed;
    }

    @Override
    public String doEncrypt(String word) {
        StringBuilder sb = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            char m = word.charAt(i);
            char k = keySeed.charAt(i % keySeed.length());;
            int mValue = (int) m - ((Character.isUpperCase(m)) ? 65 : 97);
            int kValue = (int) k - ((Character.isUpperCase(k)) ? 65 : 97);
            int cValue = ((mValue + kValue) % 26 + 26) % 26;;
            sb.append((char) (cValue + 97));
        }
        return sb.toString();
    }

    @Override
    public String doDecrypt(String word) {
        StringBuilder sb = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            char k = keySeed.charAt(i % keySeed.length());;;
            int cValue = (int) c - ((Character.isUpperCase(c)) ? 65 : 97);
            int kValue = (int) k - ((Character.isUpperCase(k)) ? 65 : 97);
            int mValue = ((cValue - kValue) % 26 + 26) % 26;;
            sb.append((char) (mValue + 97));
        }
        return sb.toString();
    }

}