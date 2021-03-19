package project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GUI implements ActionListener {

    private JFrame mainFrame = new JFrame();
    private JPanel mainPanel;
    private JPanel nrziPanel;
    private JPanel conversionsPanel;
    private JPanel hammingPanel;
    private JTextField binaryNumberField;
    private JTextField errorBitField;
    private JLabel binaryNumberLabel;
    private JLabel errorBitLabel;

    public GUI(){

        // Panels
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30,10, 30));
        mainPanel.setLayout(new GridLayout(0, 1));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        nrziPanel = new JPanel();
        nrziPanel.setLayout(new FlowLayout());

        conversionsPanel = new JPanel();
        conversionsPanel.setLayout(new FlowLayout());
        hammingPanel = new JPanel();
        hammingPanel.setLayout(new FlowLayout());

        // Basic components
        binaryNumberLabel = new JLabel("Enter a binary number:");
        binaryNumberLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        binaryNumberField = new JTextField(10);
        binaryNumberField.setFont(new Font("Calibri", Font.PLAIN, 16));
        binaryNumberField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                binaryNumberLabel.setText("Enter a binary number:");
                binaryNumberField.setText("");
                binaryNumberField.setBackground(Color.WHITE);
                mainFrame.pack();
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });

        errorBitLabel = new JLabel("Enter the error bit position:");
        errorBitLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        errorBitField = new JTextField(5);
        errorBitField.setFont(new Font("Calibri", Font.PLAIN, 16));
        errorBitField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                errorBitLabel.setText("Enter a binary number:");
                errorBitField.setText("");
                errorBitField.setBackground(Color.WHITE);
                mainFrame.pack();
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });

        JButton startButton = new JButton("Start analysis");
        startButton.addActionListener(this);
        startButton.setFont(new Font("Calibri", Font.PLAIN, 16));
        startButton.setBackground(Color.LIGHT_GRAY);

        inputPanel.add(binaryNumberLabel);
        inputPanel.add(binaryNumberField);
        inputPanel.add(errorBitLabel);
        inputPanel.add(errorBitField);
        inputPanel.add(startButton);

        // Add components in the main panel
        mainPanel.add(inputPanel);
        mainPanel.add(nrziPanel);
        mainPanel.add(conversionsPanel);
        mainPanel.add(hammingPanel);

        // Set up the frame and display it
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Binary Analysis");
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    // Start button action
    @Override
    public void actionPerformed(ActionEvent e) {
        String binaryNumberText = this.binaryNumberField.getText();
        String bitPositionText = this.errorBitField.getText();

        // Validates the information
        if (checkBinaryNumber(binaryNumberText)
                && checkBitPosition(bitPositionText, binaryNumberText)){

            new NRZI(this.nrziPanel, this.binaryNumberField.getText());
            new Conversions(this.conversionsPanel, this.binaryNumberField.getText());
            new Hamming(this.hammingPanel, this.binaryNumberField.getText(), this.errorBitField.getText());
            mainPanel.revalidate();
            mainPanel.repaint();

        }
        mainFrame.pack();
    }

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
