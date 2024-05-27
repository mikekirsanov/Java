package BackupUtility;

import java.io.IOException;
import java.nio.file.*;

public class BackupUtility {
    public static void createBackup(String sourceDir) throws IOException {
        
        Path sourceDirectory = Paths.get(sourceDir);
        Path backupDirectory = Paths.get(sourceDir, "backup");

        if (!Files.exists(backupDirectory)) {
            Files.createDirectory(backupDirectory);
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDirectory)) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    Path destinationFile = backupDirectory.resolve(file.getFileName());
                    Files.copy(file, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error occurred while creating backup", e);
        }
    }

    public static void main(String[] args) {
        try {
            createBackup("C:\\Users\\mkirs\\Downloads\\Разработчик\\Java\\Java Core\\Seminar5");
            System.out.println("Backup completed successfully.");
        } catch (IOException e) {
            System.err.println("Failed to create backup: " + e.getMessage());
        }
    }
}
