package Lection1;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    private int gameSize = 3;
    private int chainSizeWin = 3;

    JButton btnStart = new JButton("Start new game");

    private JPanel gameModePanel() {
        Label label1 = new Label("Выберите режим игры.");
        JRadioButton button1 = new JRadioButton("Человек против компьютера.");
        JRadioButton button2 = new JRadioButton("Человек против человека.");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(button1);
        buttonGroup.add(button2);
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(label1);
        panel.add(button1);
        panel.add(button2);
        return panel;
    }

    private JPanel gameSizePanel() {
        Label label2 = new Label("Выберите размер поля.");
        Label label3 = new Label("Установленный размер поля: " + gameSize);
        JSlider slider = new JSlider(3, 10, 3);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gameSize = slider.getValue();
                label3.setText("Установленный размер поля: " + gameSize);
        }
    });
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(label2);
        panel.add(label3);
        panel.add(slider);
        return panel;
    }

    private JPanel ChainSizeWinPanel() {
        Label label4 = new Label("Выберите длину для победы.");
        Label label5 = new Label("Установленный размер выигрышного ряда: " + chainSizeWin);
        JSlider slider2 = new JSlider(3, 10, 3);
        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                chainSizeWin = slider2.getValue();
                label5.setText("Установленный размер выигрышного ряда: " + chainSizeWin);
            }
        });
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(label4);
        panel.add(label5);
        panel.add(slider2);
        return panel;
    }

    SettingsWindow(GameWindow gameWindow) {
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(gameModePanel());
        panel.add(gameSizePanel());
        panel.add(ChainSizeWinPanel());

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startNewGame(0, 3, 3, 3);
                setVisible(false);
            }
        });
        add(panel, BorderLayout.CENTER);
        add(btnStart, BorderLayout.SOUTH);
    }
}
