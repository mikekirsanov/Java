package Seminar2;

import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private View serverWindow;
    private boolean isRunning = false;
    private List<ClientController> connectedClients = new ArrayList<>();
    private ServerModel serverModel;

    public ServerController(ServerModel serverModel) {
        this.serverModel = serverModel;
    }

    public void startServer() {
        if (!isRunning) {
            isRunning = true;
            logMessage("Сервер запущен");
            serverWindow.connect();
            displayChatHistoryInServerWindow();
        }
    }

    public void stopServer() {
        if (isRunning) {
            isRunning = false;
            logMessage("Сервер остановлен");
            serverWindow.disconnect();
        }
    }

    public void setServerWindow(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
    }

    public void logMessage(String message) {
        if (serverWindow != null) {
            serverWindow.showMessage(message);
        }
        serverModel.save(message);
    }

    public void addClient(ClientController client) {
        connectedClients.add(client);
        logMessage("Клиент " + client.getUsername() + " подключен");
        sendChatHistoryToClient(client);
    }

    public void removeClient(ClientController client) {
        connectedClients.remove(client);
        String disconnectMessage = "Клиент " + client.getUsername() + " вышел из чата";
        broadcastMessage(disconnectMessage, false);
        logMessage(disconnectMessage);
    }

    public void broadcastMessage(String message) {
        broadcastMessage(message, true);
    }

    public void broadcastMessage(String message, boolean shouldLog) {
        for (ClientController client : connectedClients) {
            client.displayMessage(message);
        }
        if (shouldLog) {
            logMessage(message);
        }
    }

    public void sendPrivateMessage(String senderUsername, String recipientUsername, String message) {
        for (ClientController client : connectedClients) {
            if (client.getUsername().equals(recipientUsername)) {
                client.displayMessage(senderUsername + " (частное): " + message);
                break;
            }
        }
    }

    private void displayChatHistoryInServerWindow() {
        List<String> chatHistory = serverModel.read();
        for (String message : chatHistory) {
            serverWindow.showMessage(message);
        }
    }

    private void sendChatHistoryToClient(ClientController client) {
        List<String> chatHistory = serverModel.read();
        for (String message : chatHistory) {
            client.displayMessage(message);
        }
    }
}
