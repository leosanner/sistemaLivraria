package library.logs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Register {
    private final String folderName = "register_list";
    private final String ENDPOINT = ".csv";
    private Path dir;
    private Path file;
    private Path fileLog;

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
                System.out.println(clazz.getSimpleName() + "dir created.");

                Files.createDirectories(dir);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Setting file path
        this.file = dir.resolve(clazz.getSimpleName().toLowerCase() + ENDPOINT);
        this.fileLog = dir.resolve(clazz.getSimpleName().toLowerCase() + "_history" + ENDPOINT);
        // Verifying file existence
        try {
            if (Files.notExists(file)) {
                System.out.println("This file does not exists. Creating one...");
                System.out.println(clazz.getSimpleName() + "file created.");

                Files.createFile(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeElementsInfo(ArrayList<String> attributes) {
        String s;
        String timeRegistration = getTimeRegistration();

        try (BufferedWriter writer = Files.newBufferedWriter(
                file, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND
        )) {
            if (Files.size(file) > 0) {
                writer.newLine();
            }
            s = String.join(",", attributes);

            writer.write(s);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                fileLog, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND
        )) {
            if (Files.size(fileLog) > 0) {
                writer.newLine();
            }
            writer.write(attributes.getFirst() + "," + timeRegistration);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTimeRegistration() {
        LocalDateTime t = LocalDateTime.now();
        return String.format("%d-%d-%d %d:%d:%d",
                t.getYear(),
                t.getMonthValue(),
                t.getDayOfYear(),
                t.getHour(),
                t.getMinute(),
                t.getSecond());
    }

    public String getFolderName() {
        return this.folderName;
    }

    public String getEndpoint() {
        return this.ENDPOINT;
    }

    public void registerElements(ArrayList<String> attributes, Class<?> clazz){
        generateFile(clazz);
        writeElementsInfo(attributes);
    }

    public void readElements(Class<?> clazz) {
       ArrayList<String> data = getElements(clazz);
       System.out.println("Total of elements: " + data.size());

       for (String s: data) {
           System.out.println(s);
       }
    }

    private ArrayList<String> findElements(Class<?> clazz, String name) {
        ArrayList<String> data = getElements(clazz);
        ArrayList<String> results = new ArrayList<>();

        for (String line: data) {
            String[] attributes = line.split(",");
            for (String att: attributes) {
                if (att.equalsIgnoreCase(name)){
                    results.add(line);
                }
            }
        }
        return results;
    }

    public String searchResults(Class<?> clazz, String name) {
        ArrayList<String> results = findElements(clazz, name);
        if (results.isEmpty()) {
            System.out.println("Did not find anything related with the key: " + name);
            return "";
        }
        for (String s:results) {
            System.out.println(s);
        }
        Integer userInput = idCallUser(results);

        return returnElementByID(clazz, userInput);
    }

    private Integer idCallUser(ArrayList<String> results) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("Insert the number of the element: ");

        return Integer.parseInt(scanner.next());
    }

    private ArrayList<String> getElements(Class<?> clazz) {
        generateFile(clazz);
        ArrayList<String> elements = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                elements.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return elements;
    }

    public String returnElementByID(Class<?> clazz, int id) {
        ArrayList<String> results = getElements(clazz);
        for (String r: results) {
            String[] arr = r.split(",");
            if (arr[0].equals(String.valueOf(id))) {
                return r;
            }
        }
        System.out.println("Did not find the respective id, search again");
        return "";
    }

    public void changeElementInformation(Class<?> clazz, int id, int position, String value) {
        List<String> ls = new ArrayList<>();
        generateFile(clazz);

        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);) {
            String line;
            String[] split_element;

            while ((line = reader.readLine()) != null) {
                split_element = line.split(",");
                if (split_element[0].equals(String.valueOf(id))) {
                    split_element[position] = value;
                }
                ls.add(String.join(",", split_element));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(BufferedWriter writer =Files.newBufferedWriter(file, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);) {
            for (String l : ls) {
                writer.write(l);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
