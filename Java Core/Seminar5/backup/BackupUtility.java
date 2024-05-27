import java.io.IOException;
import java.nio.file.*;

public class BackupUtility {
    public static void createBackup(String sourceDir) throws IOException {
        // Определяем исходную директорию и директорию для резервной копии
        Path sourceDirectory = Paths.get(sourceDir);
        Path backupDirectory = Paths.get(sourceDir, "backup");

        // Создаем директорию для резервной копии, если она не существует
        if (!Files.exists(backupDirectory)) {
            Files.createDirectory(backupDirectory);
        }

        // Получаем поток файлов в исходной директории (без поддиректорий)
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDirectory)) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) { // Проверяем, что это файл
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
