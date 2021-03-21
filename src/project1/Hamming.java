package project1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Hamming {

    public Hamming (JPanel panel, String binaryNumber, String errorBitField, boolean pairParity){

        JPanel hammingPanel = new JPanel();
        panel.add(hammingPanel);

        hammingPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Table 3. Calculation of Hamming coded parity bits", TitledBorder.CENTER,
                TitledBorder.TOP));

        String[] row1 = getParityBit1(binaryNumber, pairParity);
        String[] row2 = getParityBit2(binaryNumber, pairParity);
        String[] row3 = getParityBit3(binaryNumber, pairParity);
        String[] row4 = getParityBit4(binaryNumber, pairParity);
        String[] row5 = getParityBit5(binaryNumber, pairParity);

        String[][] results = { row1, row2, row3, row4, row5};

        String[] headers = { "  ", "P1", "P2", "D1", "P3", "D2", "D3", "D4", "P4", "D5", "D6", "D7", "D8", "D9", "D10", "D11", "P5", "D12"};
        JTable table = new JTable(results, headers);
        hammingPanel.add(new JScrollPane(table), Component.CENTER_ALIGNMENT);
    }

    public Hamming(String word){
        String[] bits = getParityBit5(word, true);
        for (int i = 0; i < bits.length; i++) System.out.print(bits[i]);
    }

    private String[] getParityBit1(String binaryNumber, boolean pairParity){
        char[] bits = new char[17];
        bits[0] = '?';
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
     * @return     number of '1' char that word string contains
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

    public static void main(String[] args) {
        new Hamming("011011011100");
    }
}


