package Seminar2;

public class ClientModel {
    private String username;
    private ClientGUI clientGUI;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setClientGUI(ClientGUI clientGUI) {
        this.clientGUI = clientGUI;
    }

    public ClientGUI getClientGUI() {
        return clientGUI;
    }
}
