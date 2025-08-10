import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Sample data
        library.addUser(new User(1, "Alice"));
        library.addUser(new User(2, "Bob"));
        library.addBook(new Book("ISBN001", "Effective Java", "Joshua Bloch"));
        library.addBook(new Book("ISBN002", "Clean Code", "Robert C. Martin"));

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Display Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    library.displayBooks();
                    break;
                case "2":
                    System.out.print("ISBN to issue: ");
                    String issueIsbn = scanner.nextLine();
                    System.out.print("User ID: ");
                    int issueUid = Integer.parseInt(scanner.nextLine());
                    library.issueBook(issueIsbn, issueUid);
                    break;
                case "3":
                    System.out.print("ISBN to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnIsbn);
                    break;
                case "4":
                    System.out.println("Exiting. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice—try again.");
            }
        }
    }

    // -- Inner Classes --

    static class Book {
        private String isbn, title, author;
        private boolean isAvailable = true;

        public Book(String isbn, String title, String author) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
        }
        public String getIsbn() { return isbn; }
        public String getTitle() { return title; }
        public boolean isAvailable() { return isAvailable; }
        public void setAvailable(boolean v) { isAvailable = v; }

        @Override
        public String toString() {
            return String.format("[%s] \"%s\" by %s — %s", isbn, title, author,
                                 isAvailable ? "Available" : "Issued");
        }
    }

    static class User {
        private int userId;
        private String name;

        public User(int userId, String name) {
            this.userId = userId;
            this.name = name;
        }
        public int getUserId() { return userId; }
        public String getName() { return name; }

        @Override
        public String toString() {
            return String.format("User #%d: %s", userId, name);
        }
    }

    static class Library {
        private List<Book> books = new ArrayList<>();
        private List<User> users = new ArrayList<>();

        public void addBook(Book b) {
            books.add(b);
            System.out.println("Added book: " + b);
        }
        public void addUser(User u) {
            users.add(u);
            System.out.println("Added user: " + u);
        }

        public boolean issueBook(String isbn, int uid) {
            Book b = findBook(isbn);
            User u = findUser(uid);
            if (b == null || u == null) {
                System.out.println("Issue failed: invalid book or user.");
                return false;
            }
            if (!b.isAvailable()) {
                System.out.println("Issue failed: book already issued.");
                return false;
            }
            b.setAvailable(false);
            System.out.printf("Book \"%s\" issued to %s.\n", b.getTitle(), u.getName());
            return true;
        }

        public boolean returnBook(String isbn) {
            Book b = findBook(isbn);
            if (b == null || b.isAvailable()) {
                System.out.println("Return failed: invalid book or already available.");
                return false;
            }
            b.setAvailable(true);
            System.out.printf("Book \"%s\" has been returned.\n", b.getTitle());
            return true;
        }

        public void displayBooks() {
            System.out.println("Library Catalog:");
            books.forEach(System.out::println);
        }

        private Book findBook(String isbn) {
            for (Book b : books) if (b.getIsbn().equals(isbn)) return b;
            return null;
        }

        private User findUser(int uid) {
            for (User u : users) if (u.getUserId() == uid) return u;
            return null;
        }
    }
}
