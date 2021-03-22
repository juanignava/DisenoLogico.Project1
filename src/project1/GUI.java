package project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {

    // GUI components
    private JFrame mainFrame = new JFrame();
    private JPanel mainPanel;
    private JTextField binaryNumberField;
    private JTextField errorBitField;
    private JLabel binaryNumberLabel;
    private JLabel errorBitLabel;
    private JButton startButton;
    private JComboBox parityBox;
    private boolean isFirstNumber = true;

    public GUI(){

        // Panels characteristics
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 10,0, 10));
        mainPanel.setLayout(new GridLayout(0, 1));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        // Labels characteristics
        binaryNumberLabel = new JLabel("Enter a 12 bit binary number:");
        binaryNumberLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        errorBitLabel = new JLabel("Enter the error bit position:");
        errorBitLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        // Text fields characteristics
        binaryNumberField = new JTextField(10);
        binaryNumberField.setFont(new Font("Calibri", Font.PLAIN, 16));
        binaryNumberField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                binaryNumberLabel.setText("Enter a 12 bit binary number:");
                binaryNumberField.setText("");
                binaryNumberField.setBackground(Color.WHITE);
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        binaryNumberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int binaryBitsLeft = 12-binaryNumberField.getText().length();
                String bitsLeft = String.valueOf(binaryBitsLeft);
                if (binaryBitsLeft > 0){
                    binaryNumberLabel.setText("Amount of bits left: " + bitsLeft);
                }
                else if (binaryBitsLeft == 0){
                    binaryNumberLabel.setText("Done");
                }
                else {
                    binaryNumberLabel.setText("Your number is over 12 bits");
                }
            }
        });

        errorBitField = new JTextField(5);
        errorBitField.setFont(new Font("Calibri", Font.PLAIN, 16));
        errorBitField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                errorBitLabel.setText("Enter the error bit position:");
                errorBitField.setText("");
                errorBitField.setBackground(Color.WHITE);
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });

        // Combo box characteristics
        String options[] = {"Even Parity", "Odd Parity"};
        parityBox = new JComboBox(options);
        parityBox.setFont(new Font("Calibri", Font.PLAIN, 16));

        // Button characteristics
        startButton = new JButton("Start analysis");
        startButton.addActionListener(this);
        startButton.setFont(new Font("Calibri", Font.PLAIN, 16));
        startButton.setBackground(Color.LIGHT_GRAY);

        // Set up input panel
        inputPanel.add(binaryNumberLabel);
        inputPanel.add(binaryNumberField);
        inputPanel.add(errorBitLabel);
        inputPanel.add(errorBitField);
        inputPanel.add(parityBox);
        inputPanel.add(startButton);
        mainPanel.add(inputPanel);

        // Set up the frame and display it
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setSize(1200, 1000);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Binary Analysis");
        mainFrame.setVisible(true);
    }

    // Main function
    public static void main(String[] args) {
        new GUI();
    }

    // Start button action
    @Override
    public void actionPerformed(ActionEvent e) {
        String binaryNumberText = this.binaryNumberField.getText();
        String bitPositionText = this.errorBitField.getText();

        // Validates the information given
        if (checkBinaryNumber(binaryNumberText)
                && checkBitPosition(bitPositionText, binaryNumberText)){

            // Delete previous panel children
            if (!this.isFirstNumber){
                this.mainPanel.remove(3);
                this.mainPanel.remove(2);
                this.mainPanel.remove(1);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
            this.isFirstNumber = false;

            // Define parity boolean
            boolean parityBoolean = true;
            if (parityBox.getSelectedItem() == "Odd Parity"){
                parityBoolean = false;
            }

            // NRZI class displays the graphic in the panel
            new NRZI(this.mainPanel, this.binaryNumberField.getText());
            // Conversions class displays the binary number conversions in the panel
            new Conversions(this.mainPanel, this.binaryNumberField.getText());
            // Hamming class displays the tables in the panel
            new Hamming(this.mainPanel, this.binaryNumberField.getText(), this.errorBitField.getText(), parityBoolean);

            // Repaint the main panel to show the changes
            mainPanel.revalidate();
            mainPanel.repaint();

        }
    }

    /**
     * Name: Check Binary Number
     * @description Confirms if the given text corresponds to a valid binary number.
     * @param binaryNumberText: The text given by the user
     * @return
     */
    private boolean checkBinaryNumber(String binaryNumberText) {

        if (binaryNumberText.length() != 12) {
            binaryNumberLabel.setText("The number must have 12 bits");
            return false;
        }

        for (char bit: binaryNumberText.toCharArray()){
            String validNumbers = "01";
            if (bit != '1' && bit != '0'){
                binaryNumberLabel.setText("Your number is not binary");
                binaryNumberField.setBackground(Color.pink);
                return false;
            }
        }
        return true;
    }

    /**
     * @name Check Bit Position
     * @description Checks if the given position corresponds to a valid position of the given binary number
     * @param bitPosition position of the error bit given by the user.
     * @param binaryNumberText The binary number text given by the user.
     * @return
     */
    private boolean checkBitPosition (String bitPosition, String binaryNumberText){
        int binaryLength = binaryNumberText.length();

        try {
            int bitNumber = Integer.parseInt(bitPosition);
            if ( bitNumber >= binaryLength){
                errorBitLabel.setText("Your bit is not valid");
                errorBitField.setBackground(Color.pink);
                return false;
            }
            return true;
        }

        catch (Exception e){
            errorBitLabel.setText("The position must be a number");
            errorBitField.setBackground(Color.pink);
            return false;
        }

    }
}
