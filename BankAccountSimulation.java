import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private LocalDateTime timestamp;
    private double amount;
    private String type;

    public Transaction(double amount, String type) {
        this.timestamp = LocalDateTime.now();
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + type + ": " + amount;
    }
}

class Account {
    private double balance;
    private List<Transaction> history;

    public Account(double initialBalance) {
        this.balance = initialBalance;
        this.history = new ArrayList<>();
        history.add(new Transaction(initialBalance, "Initial Balance"));
    }

    public void deposit(double amt) {
        if (amt <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        balance += amt;
        history.add(new Transaction(amt, "Deposit"));
        System.out.printf("Deposited: %.2f. New balance: %.2f%n", amt, balance);
    }

    public void withdraw(double amt) {
        if (amt <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        if (amt > balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance -= amt;
        history.add(new Transaction(amt, "Withdrawal"));
        System.out.printf("Withdrew: %.2f. New balance: %.2f%n", amt, balance);
    }

    public void printBalance() {
        System.out.printf("Current Balance: %.2f%n", balance);
    }

    public void printHistory() {
        System.out.println("Transaction History:");
        for (Transaction t : history) {
            System.out.println(t);
        }
    }
}

public class BankAccountSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();  // consume newline

        Account account = new Account(initialBalance);

        while (true) {
            System.out.println("\nChoose an operation: ");
            System.out.println("1) Deposit");
            System.out.println("2) Withdraw");
            System.out.println("3) View Balance");
            System.out.println("4) View History");
            System.out.println("5) Exit");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter deposit amount: ");
                    double dep = Double.parseDouble(scanner.nextLine());
                    account.deposit(dep);
                    break;
                case "2":
                    System.out.print("Enter withdrawal amount: ");
                    double wit = Double.parseDouble(scanner.nextLine());
                    account.withdraw(wit);
                    break;
                case "3":
                    account.printBalance();
                    break;
                case "4":
                    account.printHistory();
                    break;
                case "5":
                    System.out.println("Thank you! Goodbye.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose 1‑5.");
            }
        }
    }
}
