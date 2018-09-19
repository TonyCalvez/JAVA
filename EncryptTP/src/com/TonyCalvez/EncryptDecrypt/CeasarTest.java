package com.TonyCalvez.EncryptDecrypt;

import static org.junit.jupiter.api.Assertions.*;

class CeasarTest {

    Ceasar c;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.c = new Ceasar(2);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void doEncrypt() {

        assertEquals("cdefg", this.c.doEncrypt("abcde"));
    }

    @org.junit.jupiter.api.Test
    void doDecrypt() {
        assertEquals("abcde", this.c.doDecrypt("cdefg"));
    }

    @org.junit.jupiter.api.Test
    void doEncrypt1() {
    }

    @org.junit.jupiter.api.Test
    void doDecrypt1() {
    }
}