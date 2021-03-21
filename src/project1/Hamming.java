package project1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Hamming {

    public Hamming (JPanel panel, String binaryNumber, String errorBitField, boolean pairParity){

        JPanel hammingPanel = new JPanel();
        panel.add(hammingPanel);

        hammingPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Table 2. Calculation of Hamming coded parity bits", TitledBorder.CENTER,
                TitledBorder.TOP));

        //rows for table 2 (Hamming codification)
        String[] row0 = this.getWord(binaryNumber);
        String[] row1 = this.getParityBit1(binaryNumber, pairParity);
        String[] row2 = this.getParityBit2(binaryNumber, pairParity);
        String[] row3 = this.getParityBit3(binaryNumber, pairParity);
        String[] row4 = this.getParityBit4(binaryNumber, pairParity);
        String[] row5 = this.getParityBit5(binaryNumber, pairParity);
        String[] word = this.getCodedWord(row1, row2, row3, row4, row5);

        String[][] results = { row0, row1, row2, row3, row4, row5, word};
        String[] headers = { "  ", "P1", "P2", "D1", "P3", "D2", "D3", "D4", "P4", "D5", "D6", "D7", "D8", "D9", "D10", "D11", "P5", "D12"};

        JTable table = new JTable(results, headers);
        table.getColumnModel().getColumn(0).setPreferredWidth(800);
        table.getColumnModel().getColumn(14).setPreferredWidth(150);
        table.getColumnModel().getColumn(15).setPreferredWidth(150);
        table.getColumnModel().getColumn(17).setPreferredWidth(150);
        hammingPanel.add(new JScrollPane(table), Component.CENTER_ALIGNMENT);

        String newBinaryNumber = modifiedBinaryNumber(binaryNumber, Integer.parseInt(errorBitField));

        JPanel decodingHammingPanel = new JPanel();
        panel.add(decodingHammingPanel);

        decodingHammingPanel.setBorder(BorderFactory.createTitledBorder(
                                       BorderFactory.createEtchedBorder(), "Table 3. Calculation of Hamming decoded parity bits",
                                       TitledBorder.CENTER, TitledBorder.TOP));

        //rows for table 3 (Hamming decodification)
        String[] decodedRow0 = this.getWord(newBinaryNumber);
        String[] decodedRow1 = this.getParityBit1(newBinaryNumber, pairParity);
        String[] decodedRow2 = this.getParityBit2(newBinaryNumber, pairParity);
        String[] decodedRow3 = this.getParityBit3(newBinaryNumber, pairParity);
        String[] decodedRow4 = this.getParityBit4(newBinaryNumber, pairParity);
        String[] decodedRow5 = this.getParityBit5(newBinaryNumber, pairParity);

        String[] codedParityBits = {row1[1], row2[2], row3[4], row4[8], row5[16]};
        String[] decodedParityBits = {decodedRow1[1], decodedRow2[2], decodedRow3[4], decodedRow4[8], decodedRow5[16]};

        String[] finalDecodedRow0 = this.addDecodedColumns(decodedRow0, " ", " ");
        String[] finalDecodedRow1 = this.addDecodedColumns(decodedRow1, decodedParityBits[0], codedParityBits[0]);
        String[] finalDecodedRow2 = this.addDecodedColumns(decodedRow2, decodedParityBits[1], codedParityBits[1]);
        String[] finalDecodedRow3 = this.addDecodedColumns(decodedRow3, decodedParityBits[2], codedParityBits[2]);
        String[] finalDecodedRow4 = this.addDecodedColumns(decodedRow4, decodedParityBits[3], codedParityBits[3]);
        String[] finalDecodedRow5 = this.addDecodedColumns(decodedRow5, decodedParityBits[4], codedParityBits[4]);

        String[] errorResults = { Character.toString(finalDecodedRow5[20].charAt(0)),
                                  Character.toString(finalDecodedRow4[20].charAt(0)),
                                  Character.toString(finalDecodedRow3[20].charAt(0)),
                                  Character.toString(finalDecodedRow2[20].charAt(0)),
                                  Character.toString(finalDecodedRow1[20].charAt(0))};

        String[][] decodingResults = { finalDecodedRow0, finalDecodedRow1, finalDecodedRow2, finalDecodedRow3, finalDecodedRow4,
                                       finalDecodedRow5};

        String[] decodingHeaders = { "  ", "P1", "P2", "D1", "P3", "D2", "D3", "D4", "P4", "D5", "D6", "D7", "D8", "D9", "D10", "D11", "P5", "D12",
                                     "Decoded Parity", "Coded Parity", "Error Calculation"};

        JTable decodingTable = new JTable(decodingResults, decodingHeaders);
        decodingTable.getColumnModel().getColumn(0).setPreferredWidth(300);
        decodingTable.getColumnModel().getColumn(14).setPreferredWidth(150);
        decodingTable.getColumnModel().getColumn(15).setPreferredWidth(150);
        decodingTable.getColumnModel().getColumn(17).setPreferredWidth(150);
        decodingTable.getColumnModel().getColumn(18).setPreferredWidth(150);
        decodingTable.getColumnModel().getColumn(19).setPreferredWidth(150);
        decodingTable.getColumnModel().getColumn(20).setPreferredWidth(150);
        decodingHammingPanel.add(new JScrollPane(decodingTable), Component.CENTER_ALIGNMENT);

        String errorResultStr = this.createNumber(errorResults);

        String errorText = "Parity error -> P5 P4 P3 P2 P1 -> " + errorResults[0] + " " + errorResults[1] + " " + errorResults[2] + " " + errorResults[3] +
                           " " + errorResults[4] + " = " + this.getDecimalValue(errorResultStr) + " -> bit error detected in position " + this.getDecimalValue(errorResultStr);

        JLabel errorLabel = new JLabel(errorText, SwingConstants.CENTER);
        errorLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
        panel.add(errorLabel);

    }

    /**
     * Sets the first row in table: the original row with every bit positioned in the corresponding column
     * @param binaryNumber  String with the 12 bit binary digit
     * @return word String array for the first row in table
     */
    private String[] getWord(String binaryNumber){
        String[] word = new String[18];
        word[0] = "Word";
        word[1] = " ";
        word[2] = " ";
        word[3] = String.valueOf(binaryNumber.charAt(0));
        word[4] = " ";
        word[5] = String.valueOf(binaryNumber.charAt(1));
        word[6] = String.valueOf(binaryNumber.charAt(2));
        word[7] = String.valueOf(binaryNumber.charAt(3));
        word[8] = " ";
        word[9] = String.valueOf(binaryNumber.charAt(4));
        word[10] = String.valueOf(binaryNumber.charAt(5));
        word[11] = String.valueOf(binaryNumber.charAt(6));
        word[12] = String.valueOf(binaryNumber.charAt(7));
        word[13] = String.valueOf(binaryNumber.charAt(8));
        word[14] = String.valueOf(binaryNumber.charAt(9));
        word[15] = String.valueOf(binaryNumber.charAt(10));
        word[16] = " ";
        word[17] = String.valueOf(binaryNumber.charAt(11));
        return word;
    }

    /**
     * Sets the last row: Hamming coded word
     * @param row1
     * @param row2
     * @param row3
     * @param row4
     * @param row5
     * @return word String array for the last row
     */
    private String[] getCodedWord(String[] row1, String[] row2, String[] row3, String[] row4, String[] row5){
        String[] word = new String[18];
        word[0] = "Coded Word";
        word[1] = row1[1];
        word[2] = row2[2];
        word[3] = row2[3];
        word[4] = row3[4];
        word[5] = row3[5];
        word[6] = row3[6];
        word[7] = row3[7];
        word[8] = row4[8];
        word[9] = row4[9];
        word[10] = row4[10];
        word[11] = row4[11];
        word[12] = row4[12];
        word[13] = row4[13];
        word[14] = row4[14];
        word[15] = row4[15];
        word[16] = row5[16];
        word[17] = row5[17];
        return word;
    }

    /**
     *Sets row for parity bit 1
     * @param binaryNumber  String with the 12 bit binary digit
     * @param pairParity    boolean (true -> even parity / false -> odd parity)
     * @return  word String array
     */
    private String[] getParityBit1(String binaryNumber, boolean pairParity){
        char[] bits = new char[17];
        bits[0] = '?'; //parity bit position
        bits[1] = ' ';
        bits[2] = binaryNumber.charAt(0);
        bits[3] = ' ';
        bits[4] = binaryNumber.charAt(1);
        bits[5] = ' ';
        bits[6] = binaryNumber.charAt(3);
        bits[7] = ' ';
        bits[8] = binaryNumber.charAt(4);
        bits[9] = ' ';
        bits[10] = binaryNumber.charAt(6);
        bits[11] = ' ';
        bits[12] = binaryNumber.charAt(8);
        bits[13] = ' ';
        bits[14] = binaryNumber.charAt(10);
        bits[15] = ' ';
        bits[16] = binaryNumber.charAt(11);

        String bitsStr = String.valueOf(bits);
        bits[0] = this.getParityBit(bitsStr, pairParity); //  set parity bit value

        String[] word = toStrArray(bits, "P1");
        return word;
    }

    /**
     *Sets row for parity bit 2
     * @param binaryNumber  String with the 12 bit binary digit
     * @param pairParity    boolean (true -> even parity / false -> odd parity)
     * @return  word String array
     */
    private String[] getParityBit2(String binaryNumber, boolean pairParity){
        char[] bits = new char[17];
        bits[0] = ' ';
        bits[1] = '?';
        bits[2] = binaryNumber.charAt(0);
        bits[3] = ' ';
        bits[4] = ' ';
        bits[5] = binaryNumber.charAt(2);
        bits[6] = binaryNumber.charAt(3);
        bits[7] = ' ';
        bits[8] = ' ';
        bits[9] = binaryNumber.charAt(5);
        bits[10] = binaryNumber.charAt(6);
        bits[11] = ' ';
        bits[12] = ' ';
        bits[13] = binaryNumber.charAt(9);
        bits[14] = binaryNumber.charAt(10);
        bits[15] = ' ';
        bits[16] = ' ';

        String bitsStr = String.valueOf(bits);
        bits[1] = this.getParityBit(bitsStr, pairParity); // set parity bit value

        String[] word = toStrArray(bits, "P2");
        return word;
    }

    /**
     *Sets row for parity bit 3
     * @param binaryNumber  String with the 12 bit binary digit
     * @param pairParity    boolean (true -> even parity / false -> odd parity)
     * @return  word String array
     */
    private String[] getParityBit3(String binaryNumber, boolean pairParity){
        char[] bits = new char[17];
        bits[0] = ' ';
        bits[1] = ' ';
        bits[2] = ' ';
        bits[3] = '?';
        bits[4] = binaryNumber.charAt(1);
        bits[5] = binaryNumber.charAt(2);
        bits[6] = binaryNumber.charAt(3);
        bits[7] = ' ';
        bits[8] = ' ';
        bits[9] = ' ';
        bits[10] = ' ';
        bits[11] = binaryNumber.charAt(7);
        bits[12] = binaryNumber.charAt(8);
        bits[13] = binaryNumber.charAt(9);
        bits[14] = binaryNumber.charAt(10);
        bits[15] = ' ';
        bits[16] = ' ';

        String bitsStr = String.valueOf(bits);
        bits[3] = this.getParityBit(bitsStr, pairParity); // set parity bit value

        String[] word = toStrArray(bits, "P3");
        return word;
    }

    /**
     *Sets row for parity bit 4
     * @param binaryNumber  String with the 12 bit binary digit
     * @param pairParity    boolean (true -> even parity / false -> odd parity)
     * @return  word String array
     */
    private String[] getParityBit4(String binaryNumber, boolean pairParity) {
        char[] bits = new char[17];
        bits[0] = ' ';
        bits[1] = ' ';
        bits[2] = ' ';
        bits[3] = ' ';
        bits[4] = ' ';
        bits[5] = ' ';
        bits[6] = ' ';
        bits[7] = '?';
        bits[8] = binaryNumber.charAt(4);
        bits[9] = binaryNumber.charAt(5);
        bits[10] = binaryNumber.charAt(6);
        bits[11] = binaryNumber.charAt(7);
        bits[12] = binaryNumber.charAt(8);
        bits[13] = binaryNumber.charAt(9);
        bits[14] = binaryNumber.charAt(10);
        bits[15] = ' ';
        bits[16] = ' ';

        String bitsStr = String.valueOf(bits);
        bits[7] = this.getParityBit(bitsStr, pairParity); // set parity bit value

        String[] word = toStrArray(bits, "P4");
        return word;
    }

    /**
     *Sets row for parity bit 5
     * @param binaryNumber  String with the 12 bit binary digit
     * @param pairParity    boolean (true -> even parity / false -> odd parity)
     * @return  word String array
     */
    private String[] getParityBit5(String binaryNumber, boolean pairParity) {
        char[] bits = new char[17];
        bits[0] = ' ';
        bits[1] = ' ';
        bits[2] = ' ';
        bits[3] = ' ';
        bits[4] = ' ';
        bits[5] = ' ';
        bits[6] = ' ';
        bits[7] = ' ';
        bits[8] = ' ';
        bits[9] = ' ';
        bits[10] = ' ';
        bits[11] = ' ';
        bits[12] = ' ';
        bits[13] = ' ';
        bits[14] = ' ';
        bits[15] = '?';
        bits[16] = binaryNumber.charAt(11);

        String bitsStr = String.valueOf(bits);
        bits[15] = this.getParityBit(bitsStr, pairParity); // set parity bit value

        String[] word = toStrArray(bits, "P5");
        return word;
    }

    /**
     * Returns the value of the parity bit. Counts the amount of ones and sets the value depending on the parity type (even or odd)
     * @param bits  String with the 12 bit binary digit
     * @param pairParity boolean (true -> even parity / false -> odd parity)
     * @return value    Char with value for parity bit ('1' or '0')
     */
    private char getParityBit(String bits, boolean pairParity){
        char value;
        if (pairParity){ //even parity
            if(this.countOnes(bits) % 2 == 0) value = '0';//even amount of ones in binaryNumber: Parity bit = 0
            else value = '1'; //odd amount of ones in binaryNumber: Parity bit = 1
        } else { //odd parity
            if(this.countOnes(bits) % 2 != 0) value = '0'; //odd amount of ones in binaryNumber: Parity bit = 0
            else value = '1'; //even amount of ones in binaryNumber: Parity bit = 1
        }

        return value;
    }

    /**
     * Parses char array to String array. Adds header to position 0 in the array
     * @param bits  char array
     * @param header    String
     * @return word String array : row ready for table
     */
    private String[] toStrArray(char[] bits, String header){
        int length = bits.length;
        String[] word = new String[length + 1];
        word[0] = header;
        for(int i = 0; i < length ; i++){
            word[i + 1] = Character.toString(bits[i]);
        }
        return word;
    }

    /**
     * Counts the number of '1' char in a string
     * @param word String
     * @return number of '1' char that word string contains
     */
    private int countOnes(String word){
        int count = 0;
        for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) == '1'){
                count++;
            }
        }
        return count;
    }

    /**
     * Changes the specified bit position to generate an error in the binaryNumber
     * @param oldBinaryNumber  String with the 12 bit binary digit
     * @param errorBitPosition
     * @return String the binary number with the eror bit.
     */
    private String modifiedBinaryNumber(String oldBinaryNumber, int errorBitPosition) {

        String newBinaryNumber = "";

        for (int i = 0; i < 12; i++) {

            if (i == errorBitPosition) {

                if (String.valueOf(oldBinaryNumber.charAt(i)).equals("0")) {

                    newBinaryNumber += "1";

                } else {

                    newBinaryNumber += "0";

                }

            } else {

                newBinaryNumber += String.valueOf(oldBinaryNumber.charAt(i));

            }

        }

        return newBinaryNumber;

    }

    /**
     * Adds the three missing error validation columns
     * @param oldRow
     * @param decodedBit
     * @param codedBit
     * @return resized received row
     */
    private String[] addDecodedColumns(String[] oldRow, String decodedBit, String codedBit) {

        String[] newRow = new String[21];

        for (int i = 0; i < 18; i++) {

            newRow[i] = oldRow[i];

        }

        newRow[18] = decodedBit;
        newRow[19] = codedBit;

        if (decodedBit.equals(" ") & codedBit.equals(" ")) {

            newRow[20] = " ";

        } else  if (decodedBit.equals(codedBit)) {

            newRow[20] = "0 OK";

        } else {

            newRow[20] = "1 Error";

        }

        return newRow;

    }

    /**
     * Convert all the array elements in one string
     * @param errorBits
     * @return string with all array elements
     */
    private String createNumber(String[] errorBits) {

        String number = "";

        for(int i = 0; i < errorBits.length; i++) {

            number += errorBits[i];

        }

        return number;

    }

    /**
     * Convert the received binaty number in a decimal number
     * @param binaryNumber
     * @return integer decimal number
     */
    private int getDecimalValue(String binaryNumber) {

        int bnLength = binaryNumber.length();
        int j = bnLength - 1;
        int decimal = 0;
        int bit;
        char singleBit;

        for(int i = 0; i < bnLength; i++){
            singleBit = binaryNumber.charAt(i);
            bit = Character.getNumericValue(singleBit);
            decimal = (int) (decimal + Math.pow(2, j)*bit);
            j = j - 1;
        }

        return decimal;

    }

}


