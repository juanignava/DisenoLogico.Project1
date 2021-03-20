package project1;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Conversions {

    private int getDecimalValue(String binaryNumber) {

        int bnLength = binaryNumber.length();
        int j = bnLength-1;
        int decimal = 0;
        int bit;
        char singleBit;

        for(int i = 0; i < bnLength; i++){
            singleBit = binaryNumber.charAt(i);
            bit = Character.getNumericValue(singleBit);
            decimal = (int) (decimal + Math.pow(2, j)*bit);
            j = j-1;
        }

        return decimal;
    }

    private String getOctalValueAux(int decimal, String octal){

        if(decimal < 8){
            return Integer.toString(decimal) + octal;
        }
        else{
            int rest = decimal%8;
            octal = Integer.toString(rest) + octal;
        }

        return getOctalValueAux((int)(decimal/8), octal);
    }

    private String getOctalValue(String binaryNumber){

        int decimal = getDecimalValue(binaryNumber);
        String octal = "";

        return getOctalValueAux(decimal, octal);
    }

    private String hexadecimalValue(int number){
        if(0 <= number & number < 10){
            return Integer.toString(number);
        }
        else if (number == 10){
            return "A";
        }
        else if (number == 11){
            return "B";
        }
        else if (number == 12){
            return "C";
        }
        else if (number == 13){
            return "D";
        }
        else if (number == 14){
            return "E";
        }
        else{
            return "F";
        }
    }

    private String getHexadecimalValueAux(int decimal, String hexadecimal){

        if(decimal < 16){
            return hexadecimalValue(decimal) + hexadecimal;
        }
        else{
            int rest = decimal%16;
            hexadecimal = hexadecimalValue(rest) + hexadecimal;
        }

        return getHexadecimalValueAux((int) decimal/16, hexadecimal);
    }

    private String getHexadecimalValue(String binaryNumber){
        int decimal = getDecimalValue(binaryNumber);
        String octal = "";

        return getHexadecimalValueAux(decimal, octal);
    }

    public Conversions (JPanel panel, String binaryNumber){

        JPanel conversionsPanel = new JPanel();
        // Create the labels with the results of the components...
        panel.add(conversionsPanel);

        conversionsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Table 1. Binary to Decimal, Octal and Hexadecimal bases.", TitledBorder.CENTER,
                TitledBorder.TOP));

        String[][] results = {
                { "Decimal", String.valueOf(getDecimalValue(binaryNumber))},
                { "Octal", getOctalValue(binaryNumber)},
                { "Hexadecimal", getHexadecimalValue(binaryNumber)},
        };

        String[] header = { "Base", "Resultado"};
        JTable table = new JTable(results, header);
        conversionsPanel.add(new JScrollPane(table), Component.CENTER_ALIGNMENT);
    }

}


