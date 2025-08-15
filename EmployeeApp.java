import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {
    // Update these with your DB details
    private static final String URL = "jdbc:mysql://localhost:3306/company";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password"; // Replace with your MySQL password

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void addEmployee(String name, String position, double salary) {
        String sql = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.executeUpdate();
            System.out.println("Employee added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewEmployees() {
        String sql = "SELECT * FROM employees";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ID | Name | Position | Salary");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateEmployee(int id, String name, String position, double salary) {
        String sql = "UPDATE employees SET name=?, position=?, salary=? WHERE id=?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.setInt(4, id);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("Employee updated.");
            else
                System.out.println("Employee not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("Employee deleted.");
            else
                System.out.println("Employee not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Position: ");
                    String position = sc.nextLine();
                    System.out.print("Salary: ");
                    double salary = sc.nextDouble();
                    addEmployee(name, position, salary);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    System.out.print("Employee ID to update: ");
                    int idU = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New Position: ");
                    String newPosition = sc.nextLine();
                    System.out.print("New Salary: ");
                    double newSalary = sc.nextDouble();
                    updateEmployee(idU, newName, newPosition, newSalary);
                    break;
                case 4:
                    System.out.print("Employee ID to delete: ");
                    int idD = sc.nextInt();
                    deleteEmployee(idD);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
