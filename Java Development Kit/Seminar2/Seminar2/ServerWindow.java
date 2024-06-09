package Seminar2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame implements View {
    private JTextArea logArea = new JTextArea();
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    
    public ServerWindow(ServerController controller) {
        setTitle("Server Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Server Log"), BorderLayout.NORTH);
        topPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startServer();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stopServer();
            }
        });
    }

    public void logMessage(String message) {
        logArea.append(message + "\n");
    }

    @Override
    public void connect() {
        logMessage("Connected to server.");
    }

    @Override
    public void disconnect() {
        logMessage("Disconnected from server.");
    }

    @Override
    public void showMessage(String text) {
        logMessage(text);
    }
}
