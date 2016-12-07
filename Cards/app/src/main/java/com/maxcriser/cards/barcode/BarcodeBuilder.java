/*
 * (C) 2011 Slava Archibasov
 * http://plaincodesource.blogspot.com
 * slava.archibasov@gmail.com 
 * 
 * This code is licensed under The Code Project Open License (CPOL) 
 * http://www.codeproject.com/info/cpol10.aspx  
 */

package com.maxcriser.cards.barcode;


public class BarcodeBuilder {
    private String codeStringValue;
    private String generatedCode;

    public BarcodeBuilder(String codeString) {
        codeStringValue = codeString;
        parse();
    }

    public String getCode() {
        return generatedCode;
    }

    private String getFullCode() {

        int even = 0, odd = 0;
        String codeToParse = codeStringValue;

        for (int index = 0; index < 6; index++) {
            even += Integer.valueOf(codeToParse.substring(index * 2 + 1, index * 2 + 2));
            odd += Integer.valueOf(codeToParse.substring(index * 2, index * 2 + 1));
        }
        even *= 3;
        int controlNumber = 10 - (even + odd) % 10;
        if (controlNumber == 10) controlNumber = 0;

        codeToParse += String.valueOf(controlNumber);

        return codeToParse;

    }

    private String DigitToUpperCase(String digit) {
        String letters = "ABCDEFGHIJ";
        int position = Integer.valueOf(digit);

        return letters.substring(position, position + 1);

    }

    private String DigitToLowerCase(String digit) {
        String letters = "abcdefghij";
        int position = Integer.valueOf(digit);

        return letters.substring(position, position + 1);

    }

    private String createEAN13Code(String rawCode) {
        int firstFlag = Integer.valueOf(
                rawCode.substring(0, 1)
        );

        String leftString = rawCode.substring(1, 7);
        String rightString = rawCode.substring(7);

        String rightCode = "";
        String leftCode = "";

        for (int i = 0; i < 6; i++) {
            rightCode += DigitToLowerCase(rightString.substring(i, i + 1));
        }

        if (firstFlag == 0) {
            leftCode = "#!" + leftString.substring(0, 1)
                    + leftString.substring(1, 2)
                    + leftString.substring(2, 3)
                    + leftString.substring(3, 4)
                    + leftString.substring(4, 5)
                    + leftString.substring(5);
        }
        if (firstFlag == 1) {
            leftCode = "$!" + leftString.substring(0, 1)
                    + leftString.substring(1, 2)
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + leftString.substring(3, 4)
                    + DigitToUpperCase(leftString.substring(4, 5))
                    + DigitToUpperCase(leftString.substring(5));
        }
        if (firstFlag == 2) {
            leftCode = "%!" + leftString.substring(0, 1)
                    + leftString.substring(1, 2)
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + DigitToUpperCase(leftString.substring(3, 4))
                    + leftString.substring(4, 5)
                    + DigitToUpperCase(leftString.substring(5));
        }
        if (firstFlag == 3) {
            leftCode = "&!" + leftString.substring(0, 1)
                    + leftString.substring(1, 2)
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + DigitToUpperCase(leftString.substring(3, 4))
                    + DigitToUpperCase(leftString.substring(4, 5))
                    + leftString.substring(5);
        }
        if (firstFlag == 4) {
            leftCode = "'!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + leftString.substring(2, 3)
                    + leftString.substring(3, 4)
                    + DigitToUpperCase(leftString.substring(4, 5))
                    + DigitToUpperCase(leftString.substring(5));
        }
        if (firstFlag == 5) {
            leftCode = "(!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + leftString.substring(3, 4)
                    + leftString.substring(4, 5)
                    + DigitToUpperCase(leftString.substring(5));
        }
        if (firstFlag == 6) {
            leftCode = ")!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + DigitToUpperCase(leftString.substring(3, 4))
                    + leftString.substring(4, 5)
                    + leftString.substring(5);
        }
        if (firstFlag == 7) {
            leftCode = "*!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + leftString.substring(2, 3)
                    + DigitToUpperCase(leftString.substring(3, 4))
                    + leftString.substring(4, 5)
                    + DigitToUpperCase(leftString.substring(5));
        }
        if (firstFlag == 8) {
            leftCode = "+!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + leftString.substring(2, 3)
                    + DigitToUpperCase(leftString.substring(3, 4))
                    + DigitToUpperCase(leftString.substring(4, 5))
                    + leftString.substring(5);
        }
        if (firstFlag == 9) {
            leftCode = ",!" + leftString.substring(0, 1)
                    + DigitToUpperCase(leftString.substring(1, 2))
                    + DigitToUpperCase(leftString.substring(2, 3))
                    + leftString.substring(3, 4)
                    + DigitToUpperCase(leftString.substring(4, 5))
                    + leftString.substring(5);
        }


        return leftCode + "-" + rightCode + "!";
    }

    private void parse() {
        String fullString = getFullCode();
        generatedCode = createEAN13Code(fullString);
    }
}