package Seminar2;

public class ClientController {
    private ClientModel model;
    private ClientGUI view;
    private ServerController serverController;

    public ClientController(ClientModel model, ServerController serverController) {
        this.model = model;
        this.serverController = serverController;
    }

    public void setView(ClientGUI view) {
        this.view = view;
    }

    public void login(String ipAddress, int port, String username, String password) {
        view.removeLoginPanel();
        view.showMessage("Вы успешно подключились!");
        model.setUsername(username);
        serverController.addClient(this);
    }

    public void sendMessage(String message) {
        if (message != null && !message.isEmpty()) {
            serverController.broadcastMessage(model.getUsername() + ": " + message);
        }
    }

    public String getUsername() {
        return model.getUsername();
    }
    
    public void displayMessage(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    public void disconnect() {
        serverController.removeClient(this);
    }
}
