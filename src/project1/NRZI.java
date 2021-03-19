package project1;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

public class NRZI extends JFrame{

    private int lineCounter = 0;
    private int actualHeight;

    public NRZI(JPanel panel, String binaryNumber){
        int panelWidth = 980;
        int panelHeight = 40;
        //int panelWidth = panel.getWidth();
        //int panelHeight = panel.getHeight();
        actualHeight = panelHeight;
        int binaryNumberLength = binaryNumber.length();

        int lineWidth = (int) panelWidth/binaryNumberLength;


        System.out.println(panel.getWidth());
        System.out.println(panel.getHeight());
        System.out.println(panel.getX());
        System.out.println(panel.getY());

        JPanel nrziPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Axis line
                g.drawLine(0, (int) actualHeight/2, panelWidth, (int) actualHeight/2);
                //g.drawLine(0,0, panelWidth, 0);
                //g.drawLine(0,actualHeight, panelWidth, 900);
                g.setColor(Color.red);
                for (char bit: binaryNumber.toCharArray()) {
                    lineCounter++;
                    if (bit == '1') {
                        if (actualHeight == 0) {
                            actualHeight = panelHeight;
                        } else {
                            actualHeight = 0;
                        }
                    }
                    g.drawLine(lineWidth*(lineCounter-1), actualHeight, lineWidth*lineCounter, actualHeight);

                }
            };

        };

        panel.add(nrziPanel);

    }

}
