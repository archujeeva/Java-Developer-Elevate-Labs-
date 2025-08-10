import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nNotes App - Choose an option:");
            System.out.println("1. Add note");
            System.out.println("2. View notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter your note: ");
                    String note = scanner.nextLine();
                    appendNoteToFile(note);
                    System.out.println("Note saved.");
                    break;
                case "2":
                    System.out.println("\n--- Saved Notes ---");
                    displayNotes();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void appendNoteToFile(String note) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(note);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing note: " + e.getMessage());
        }
    }

    private static void displayNotes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("(No notes yet.)");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("• " + line);
            }
        } catch (IOException e) {
            System.err.println("Error reading notes: " + e.getMessage());
        }
    }
}


