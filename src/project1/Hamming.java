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
    }

    public Hamming(String word){
        String[] bits = getParityBit5(word, true);
        for (int i = 0; i < bits.length; i++) System.out.print(bits[i]);
    }

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


