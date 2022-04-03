package com.icai.practicas.model;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.junit.Assert;



public class TelefonoTest {
    @Test
    public void checkNoSpaceNumber() {
        // Check number with no prefix
        for (int i = 0; i < 200; i++) {
            StringBuilder number = new StringBuilder();
            for (int j = 0; j < 9; j++) {
                // Generate random number
                int randomNum = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                // Add to DNI
                number.append(String.valueOf(randomNum));
            }

            Assert.assertEquals(new Telefono(number.toString()).validar(), true);
        }
    }

    @Test
    public void checkNoSpacePlusPrefix() {
        // Check number with two digit prefix
        for (int i = 0; i < 200; i++) {
            StringBuilder number = new StringBuilder("+");
            for (int j = 0; j < 12; j++) {
                // Generate random number
                int randomNum = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                // Add to DNI
                number.append(String.valueOf(randomNum));
            }

            Assert.assertEquals(new Telefono(number.toString()).validar(), true);
        }
    }

    @Test
    public void checkSpaceNumber() {
        // Check number with no prefix
        for (int i = 0; i < 200; i++) {
            StringBuilder number = new StringBuilder();
            for (int j = 0; j < 9; j++) {
                // Generate random number
                int randomNum = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                // Add to DNI
                number.append(String.valueOf(randomNum));
                if (j == 2 || j == 5) {
                    number.append(" ");
                }
            }

            Assert.assertEquals(new Telefono(number.toString()).validar(), true);
        }
    }

    @Test
    public void checkSpaceNumberPrefix() {
        // +123 345 234 123
        // Check number with no prefix
        for (int i = 0; i < 200; i++) {
            StringBuilder number = new StringBuilder("+");
            for (int j = 0; j < 12; j++) {
                // Generate random number
                int randomNum = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                // Add to DNI
                number.append(String.valueOf(randomNum));
                if (j == 2 || j == 5 || j == 8) {
                    number.append(" ");
                }
            }

            Assert.assertEquals(new Telefono(number.toString()).validar(), true);
        }
    }

    @Test
    public void checkExtraNumbersPhone() {
        Assert.assertEquals(new Telefono("123456789123").validar(), false);
    }

    @Test
    public void checkWrongNumbersPhone() {
        Assert.assertEquals(new Telefono("ABCDEFGHI").validar(), false);
    }
}
