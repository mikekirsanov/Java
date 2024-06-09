package Seminar2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerModel implements Repository {
    private List<String> chatLog = new ArrayList<>();
    private static final String LOG_FILE_NAME = "chatlog.txt";

    public ServerModel() {
        File logFile = new File(LOG_FILE_NAME);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Ошибка создания лог-файла: " + e.getMessage());
            }
        }
        this.chatLog = readMessagesFromFile();
    }

    @Override
    public void save(String message) {
        chatLog.add(message);
        saveMessageToFile(message);
    }

    @Override
    public List<String> read() {
        return new ArrayList<>(chatLog);
    }

    private void saveMessageToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_NAME, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл журнала: " + e.getMessage());
        }
    }

    private List<String> readMessagesFromFile() {
        List<String> messages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                messages.add(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения из файла журнала: " + e.getMessage());
        }
        return messages;
    }

    public List<String> getChatLog() {
        return new ArrayList<>(chatLog);
    }
}
