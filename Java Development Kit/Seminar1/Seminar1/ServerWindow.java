package Seminar1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGTH = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private boolean isServerWorking;
    private List<ClientGUI> clients = new ArrayList<>();
    private BufferedWriter bw;

    public ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGTH);
        setResizable(false);
        setTitle("Chat Server");
        setAlwaysOnTop(true);
        setLayout(new BorderLayout());

        isServerWorking = false;

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(btnStart);
        panel.add(btnStop);
        add(panel, BorderLayout.SOUTH);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    log.append("Сервер уже запущен.\n");
                } else {
                    isServerWorking = true;
                    log.append("Сервер запущен.\n");
                    openLogFile();
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    log.append("Сервер не запущен.\n");
                } else {
                    isServerWorking = false;
                    log.append("Сервер остановлен.\n");
                    closeLogFile();
                }
            }
        });

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog, BorderLayout.CENTER);

        setVisible(true);
    }

    private void openLogFile() {
        try {
            bw = new BufferedWriter(new FileWriter("chatlog.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeLogFile() {
        try {
            if (bw != null) {
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logMessage(String message) {
        log.append(message + "\n");
        appendToLogFile(message);
    }

    private void appendToLogFile(String message) {
        if (bw != null) {
            try {
                bw.write(message + "\n");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addClient (ClientGUI client) {
        clients.add(client);
        String connectMessage = client.getUsername() + " подключился к беседе";
        logMessage(connectMessage);
        sendChatHistory(client);
    }

    private void sendChatHistory(ClientGUI client) {
        try (BufferedReader br = new BufferedReader(new FileReader("chatlog.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                client.showMessage(line, false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String message) {
        for (ClientGUI client : clients) {
            client.showMessage(message, true);
        }
        logMessage(message);
    }
}
