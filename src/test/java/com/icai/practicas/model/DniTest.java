package com.icai.practicas.model;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class DniTest {
    private static final String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
    private static final String[] INVALIDOS = new String[]{"00000000T", "00000001R", "99999999R"};

    char getLetra(int numero) {
        int resto = numero % 23;
        return DIGITO_CONTROL.charAt(resto);
    }

    @Test
    public void checkDniValidLetter() {
        for (int i = 0; i < 200; i++) {
            StringBuilder dni = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                // Generate random number
                int randomNum = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                // Add to DNI
                dni.append(String.valueOf(randomNum));
            }

            // Get letter
            char letra = this.getLetra(Integer.valueOf(dni.toString()));
            dni.append(String.valueOf(letra)); 

            // Ensure DNI is valid
            if (Arrays.binarySearch(INVALIDOS, dni.toString()) < 0) {
                // The letter is correct so DNI should be correct
                Assert.assertEquals(new DNI(dni.toString()).validar(), true);
            }

        }
    }

    @Test
    public void checkDniInalidLetter() {
        for (int i = 0; i < 200; i++) {
            StringBuilder dni = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                // Generate random number
                int randomNum = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                // Add to DNI
                dni.append(String.valueOf(randomNum));
            }
            int randomNum = ThreadLocalRandom.current().nextInt(0, DIGITO_CONTROL.length());
            // Get letter
            char randomLetter = DIGITO_CONTROL.charAt(randomNum);
            char letra = this.getLetra(Integer.valueOf(dni.toString()));

            // Ensure the random letter is invalid
            if (randomLetter != letra) {
                dni.append(String.valueOf(randomLetter)); 

                // The letter is correct so DNI should be correct
                Assert.assertEquals(new DNI(dni.toString()).validar(), false);
            }

        }
    }

    @Test
    public void checkInvalidDnis() {
        for (String invalid : INVALIDOS) {
            Assert.assertEquals(new DNI(invalid).validar(), false);
        }
    }


    @Test 
    public void checkLongerLengthInvalid() {
        StringBuilder dni = new StringBuilder();
        for (int j = 0; j < 9; j++) {
            // Generate random number
            int randomNum = ThreadLocalRandom.current().nextInt(0, 9 + 1);
            // Add to DNI
            dni.append(String.valueOf(randomNum));
        }
        int randomNum = ThreadLocalRandom.current().nextInt(0, DIGITO_CONTROL.length());
        // Get letter
        char randomLetter = DIGITO_CONTROL.charAt(randomNum);
        dni.append(String.valueOf(randomLetter)); 

        // The letter is correct so DNI should be correct
        Assert.assertEquals(new DNI(dni.toString()).validar(), false);
    }
}