package project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private JFrame mainFrame = new JFrame();
    private JTextField binaryNumberField;
    private JTextField errorBitField;
    private JPanel mainPanel;
    private JPanel nrziPanel;
    private JPanel conversionsPanel;
    private JPanel hammingPanel;

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
        binaryNumberField = new JTextField(10);
        errorBitField = new JTextField(10);
        JButton startButton = new JButton("Start analysis");
        startButton.addActionListener(this);

        inputPanel.add(binaryNumberField);
        inputPanel.add(errorBitField);
        inputPanel.add(startButton);

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
        new NRZI(this.nrziPanel, this.binaryNumberField.getText());
        new Conversions(this.conversionsPanel, this.binaryNumberField.getText());
        new Hamming(this.hammingPanel, this.binaryNumberField.getText(), this.errorBitField.getText());
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
