import java.util.Scanner;

public class ConsoleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        System.out.println("Simple Java Console Calculator");

        do {
            System.out.print("\nEnter first number: ");
            double num1 = getValidDouble(scanner);

            System.out.print("Enter operator (+, -, *, /): ");
            char op = scanner.next().trim().charAt(0);

            System.out.print("Enter second number: ");
            double num2 = getValidDouble(scanner);

            double result;
            switch (op) {
                case '+': result = add(num1, num2); break;
                case '-': result = subtract(num1, num2); break;
                case '*': result = multiply(num1, num2); break;
                case '/':
                    result = divide(num1, num2);
                    break;
                default:
                    System.out.println("Invalid operator! Please use +, -, *, or /.");
                    continue;
            }

            System.out.printf("Result: %.4f%n", result);

            System.out.print("Do you want to perform another calculation? (yes/no): ");
            choice = scanner.next().trim().toLowerCase();
        } while (choice.equals("yes") || choice.equals("y"));

        System.out.println("Calculator exiting. Goodbye!");
        scanner.close();
    }

    public static double getValidDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public static double add(double a, double b) { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }
    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero. Returning Infinity.");
            return Double.POSITIVE_INFINITY;
        }
        return a / b;
    }
}
