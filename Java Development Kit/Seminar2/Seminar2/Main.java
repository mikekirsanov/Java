package Seminar2;

public class Main {
    public static void main(String[] args) {
        ServerModel serverModel = new ServerModel();
        ServerController serverController = new ServerController(serverModel);
        ServerWindow serverWindow = new ServerWindow(serverController);
        serverController.setServerWindow(serverWindow);
        serverWindow.setVisible(true);

        int numberOfClients = 3;
        for (int i = 1; i <= numberOfClients; i++) {
            createAndShowClient(serverController, i);
        }
    }

    private static void createAndShowClient(ServerController serverController, int clientNumber) {
        ClientModel clientModel = new ClientModel();
        ClientController clientController = new ClientController(clientModel, serverController);
        ClientGUI clientGUI = new ClientGUI(clientController);
        clientController.setView(clientGUI);
        clientGUI.setTitle("Client " + clientNumber);
        clientGUI.setVisible(true);
    }
}