package Uppgift_03;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.swing.SwingConstants.CENTER;

public class Main extends JFrame implements ActionListener {
    List<JLabel> labelList = new ArrayList<>();
    List<JLabel> correctList = new ArrayList<>();
    JPanel gamePanel = new JPanel();
    JButton newGameButton = new JButton("Starta nytt spel");
    JPanel panel = new JPanel();
    JLabel winnerMess = new JLabel(" ");
    JLabel labelBackgroundImage = new JLabel(new ImageIcon("src/Uppgift_03/bild.jpg"));
    Border border = BorderFactory.createLineBorder(new Color(0), 1);

    public Main() {
        setTitle("Femtonspelet");
        gamePanel.setBackground(new Color(0,255,0));

        newGameButton.setBackground(new Color(0,255,0));
        newGameButton.setForeground(new Color(0));
        newGameButton.setPreferredSize(new Dimension(400, 40));
        newGameButton.setFont(new Font("Bold Serif", Font.BOLD, 18));
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(this);

        gamePanel.add(labelBackgroundImage);
        labelBackgroundImage.setLayout(new GridLayout(4, 4));
        labelBackgroundImage.setPreferredSize(new Dimension(400, 330));

        for (int i = 0; i < 16; i++) {
            labelList.add(new JLabel(String.valueOf(i + 1)));
            labelBackgroundImage.add(labelList.get(i));
            labelList.get(i).addMouseListener(mouseClickListener);
            correctList.add(labelList.get(i));

            labelList.get(i).setForeground(new Color(255,255,255));
            labelList.get(i).setFont(new Font("Bold Serif", Font.BOLD, 18));
            labelList.get(i).setHorizontalAlignment(CENTER);
            labelList.get(i).setBorder(border);
        }

        labelList.get(labelList.size() - 1).setText(" ");

        panel.setLayout(new BorderLayout());
        panel.add(newGameButton, BorderLayout.NORTH);
        panel.add(gamePanel, BorderLayout.CENTER);
        panel.setBackground(new Color(0,255,0));
        panel.add(winnerMess, BorderLayout.SOUTH);

        winnerMess.setFont(new Font("Bold Serif", Font.BOLD, 18));
        winnerMess.setForeground(new Color(0));
        winnerMess.setHorizontalAlignment(CENTER);
        winnerMess.setPreferredSize(new Dimension(400, 40));

        add(panel);
        panel.setPreferredSize(new Dimension(400, 420));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    MouseListener mouseClickListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int indexBlank = 0;
            int indexSiffra;

            if (labelList.contains((JLabel) e.getSource())) {
                indexSiffra = labelList.indexOf((JLabel) e.getSource());
                PlaceSwitching sp = new PlaceSwitching();
                sp.switchPlaces(labelBackgroundImage, labelList, correctList, indexBlank, indexSiffra, winnerMess);
                revalidate();
                repaint();
            }
        }
    };
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            labelBackgroundImage.removeAll();
            Collections.shuffle(labelList);
            for (JLabel jLabel : labelList) {
                labelBackgroundImage.add(jLabel);
            }
            winnerMess.setText(" ");
            revalidate();
            repaint();
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}