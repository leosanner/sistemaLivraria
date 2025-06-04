package library.logs;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/* I chose this approach to learn more about file/dir use cases.
* But is very simple, any class can use IdChecker, just sent the class will be created
* a new dir/file.txt with the last id available. */

public class IdChecker {
    private String folderName = "id_list";
    private Path dir;
    private Path file;
    private String ENDPOINT = ".txt";

    public IdChecker () {}

    public void checkID(Class<?> clazz) {
        generateFile(clazz);
    }

    private void defineDir(Class<?> clazz) {
        this.dir = Paths.get(folderName+"/"+clazz.getSimpleName().toLowerCase());
    }

    // Receive User class and if necessary, create a file and directory to storage ids.
    private void generateFile(Class<?> clazz) {
       defineDir(clazz);
       // verifying the directory existence
        try {
            if (Files.notExists(dir)) {
                System.out.println("Current directory does not exists. Creating one...");
                System.out.println(clazz.getSimpleName() + "created id dir.");

                Files.createDirectories(dir);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Setting file path
        this.file = dir.resolve(clazz.getSimpleName() + ENDPOINT);
        // Verifying file existence
        try {
            if (Files.notExists(file)) {
                System.out.println("This file does not exists. Creating one...");
                System.out.println(clazz.getSimpleName() + "created id file.");

                Files.createFile(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Read txt file and return the last id used by some user
    private int readIdFile(){
        String line;
        int last = 0;

        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            while ((line = reader.readLine()) != null) {
                last = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return last;
    }

    // Update the txt file writing the last id used
    private void writeIdFile(){
        int value = readIdFile();
        value += 1;
        try (BufferedWriter writer = Files.newBufferedWriter(
                file,
                StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING
                //StandardOpenOption.APPEND
        )) {
            if (Files.size(file) > 0) {
                writer.newLine();
            }
            writer.write(String.valueOf(value));
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public int returnAvailableId() {
        int id = readIdFile();
        writeIdFile();
        return id;
    }

    public int returnAvailableId(Class<?> clazz){
        checkID(clazz);
        return returnAvailableId();
    }

    public void setFolderName(String name) {
        this.folderName = name;
    }

    public void setEndpoint(String endpoint){
        System.out.println("You should use .property, e.g .txt");
        this.ENDPOINT = endpoint;
    }
}