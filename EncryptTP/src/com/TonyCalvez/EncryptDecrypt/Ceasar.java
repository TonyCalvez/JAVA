package com.TonyCalvez.EncryptDecrypt;

public class Ceasar implements Strategy {
    private int shift;

    public Ceasar(int shift) {
        this.shift = shift;
    }

    @Override
    public String doEncrypt(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            int charShift = 97;
            if (Character.isUpperCase(c))
                charShift = 65;
            char ch = (char) (((int) c + shift - charShift) % 26 + charShift);
            sb.append(ch);
        }
        return sb.toString();
    }

    @Override
    public String doDecrypt(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            int charShift = 97;
            if (Character.isUpperCase(c))
                charShift = 65;
            char ch = (char) (((int) c + (26 - shift) - charShift) % 26 + charShift);
            sb.append(ch);
        }
        return sb.toString();
    }

}