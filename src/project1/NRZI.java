package project1;

import javax.swing.*;
import java.awt.*;

public class NRZI extends JFrame{

    private int lineCounter = 0;
    private int actualHeight;

    /**
     * @name NRZI Constructor
     * @param panel the panel where the user will display the result.
     * @param binaryNumber the number written by the user.
     */
    public NRZI(JPanel panel, String binaryNumber){

        int panelWidth = 980;
        int panelHeight = 40;
        actualHeight = panelHeight;
        int binaryNumberLength = binaryNumber.length();
        int lineWidth = (int) panelWidth/binaryNumberLength;

        // Label that indicates the written number
        JLabel nrziLabel = new JLabel();
        nrziLabel.setText("NRZI of the message: " + binaryNumber);
        nrziLabel.setFont(new Font("Calibri", Font.BOLD, 16));

        // Panel where the the lines are drawn
        JPanel nrziPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Axis line
                g.drawLine(0, (int) actualHeight/2, panelWidth, (int) actualHeight/2);

                // Signal lines and divisions
                for (char bit: binaryNumber.toCharArray()) {
                    lineCounter++;

                    // Y position changes if the bit is a 1
                    if (bit == '1') {
                        if (actualHeight == 0) {
                            actualHeight = panelHeight;
                        } else {
                            actualHeight = 0;
                        }
                    }
                    g.setColor(Color.red);
                    g.drawLine(lineWidth*(lineCounter-1), actualHeight, lineWidth*lineCounter, actualHeight);
                    g.setColor(Color.gray);
                    g.drawLine(lineWidth*(lineCounter-1), 0, lineWidth*(lineCounter-1), panelHeight);

                }
            };

        };

        nrziPanel.add(nrziLabel);
        panel.add(nrziPanel);

    }

}
