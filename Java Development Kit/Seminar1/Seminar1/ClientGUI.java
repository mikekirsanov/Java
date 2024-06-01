package Seminar1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGTH = 300;

    private final JTextPane log = new JTextPane();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField();
    private final JPasswordField tfPassword = new JPasswordField("123456");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private String username;
    private ServerWindow serverWindow;

    public ClientGUI(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGTH);
        setTitle("Chat Client");

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(new JPanel());
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = tfLogin.getText();
                if (!username.isEmpty()) {
                    showMessage("Вы успешно подключились!", false);
                    removeLoginPanel();
                    serverWindow.addClient(ClientGUI.this);
                }
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = tfMessage.getText();
                if (!message.isEmpty()) {
                    serverWindow.broadcastMessage(username + ": " + message);
                    tfMessage.setText("");
                }
            }
        });

        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });

        setVisible(true);
    }

    private void removeLoginPanel() {
        remove(panelTop);
        revalidate();
        repaint();
    }

    private void sendMessage() {
        String message = tfMessage.getText();
        if (!message.isEmpty()) {
            serverWindow.broadcastMessage(username + ": " + message);
            tfMessage.setText("");
        }
    }

    public String getUsername() {
        return username;
    }

    public void showMessage(String message, boolean isUserMessage) {
        StyledDocument doc = log.getStyledDocument();
        SimpleAttributeSet style = new SimpleAttributeSet();

        try {
            if (isUserMessage) {
                String[] parts = message.split(": ", 2);
                if (parts.length > 1) {
                    StyleConstants.setBold(style, true);
                    doc.insertString(doc.getLength(), parts[0] + ": ", style);
                    StyleConstants.setBold(style, false);
                    doc.insertString(doc.getLength(), parts[1] + "\n", style);
                } else {
                    doc.insertString(doc.getLength(), message + "\n", style);
                }
            } else {
                doc.insertString(doc.getLength(), message + "\n", style);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
