package project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GUI implements ActionListener {

    // GUI components
    private JFrame mainFrame = new JFrame();
    private JPanel mainPanel;
    private JTextField binaryNumberField;
    private JTextField errorBitField;
    private JLabel binaryNumberLabel;
    private JLabel errorBitLabel;
    private JButton startButton;

    public GUI(){

        // Panels characteristics
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30,0, 30));
        mainPanel.setLayout(new GridLayout(0, 1));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        // Labels characteristics
        binaryNumberLabel = new JLabel("Enter a binary number:");
        binaryNumberLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        errorBitLabel = new JLabel("Enter the error bit position:");
        errorBitLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        // Text fields characteristics
        binaryNumberField = new JTextField(10);
        binaryNumberField.setFont(new Font("Calibri", Font.PLAIN, 16));
        binaryNumberField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                binaryNumberLabel.setText("Enter a binary number:");
                binaryNumberField.setText("");
                binaryNumberField.setBackground(Color.WHITE);
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });

        errorBitField = new JTextField(5);
        errorBitField.setFont(new Font("Calibri", Font.PLAIN, 16));
        errorBitField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                errorBitLabel.setText("Enter a binary number:");
                errorBitField.setText("");
                errorBitField.setBackground(Color.WHITE);
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });

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
        inputPanel.add(startButton);
        mainPanel.add(inputPanel);

        // Set up the frame and display it
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setSize(1000, 700);
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

            // NRZI class displays the graphic in the panel
            new NRZI(this.mainPanel, this.binaryNumberField.getText());
            // Conversions class displays the binary number conversions in the panel
            new Conversions(this.mainPanel, this.binaryNumberField.getText());
            // Hamming class displays the tables in the panel
            new Hamming(this.mainPanel, this.binaryNumberField.getText(), this.errorBitField.getText(), true);

            // Disable inputs
            startButton.setEnabled(false);
            errorBitField.setEnabled(false);
            binaryNumberField.setEnabled(false);

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
