import java.util.*;

// User class representing an individual user in the system
class User {
    private int userId;
    private String name;
    private String email;
    private List<Expense> expenses;

    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.expenses = new ArrayList<>();
    }

    // Method to add an expense to the user's expense list
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    // Method to retrieve the user's expenses
    public List<Expense> getExpenses() {
        return expenses;
    }

    public String getName() {
        return name;
    }

    // Override toString() method to return user’s name instead of memory reference
    @Override
    public String toString() {
        return name;
    }
}

// Expense class representing an individual expense
class Expense {
    private int expenseId;
    private double amount;
    private String category;
    private User payer;
    private List<User> splitBetween;

    public Expense(int expenseId, double amount, String category, User payer, List<User> splitBetween) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.category = category;
        this.payer = payer;
        this.splitBetween = splitBetween;
    }

    // Method to calculate and return the split cost among users
    public Map<User, Double> splitCost() {
        Map<User, Double> balances = new HashMap<>();
        double share = amount / splitBetween.size();
        for (User user : splitBetween) {
            balances.put(user, share);
        }
        return balances;
    }
}

// Payment class representing a financial transaction
class Payment {
    private int paymentId;
    private User fromUser;
    private User toUser;
    private double amount;
    private Date date;

    public Payment(int paymentId, User fromUser, User toUser, double amount) {
        this.paymentId = paymentId;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.date = new Date();
    }
}

// Report class generating expense reports for users
class Report {
    private int reportId;
    private User user;
    private List<Expense> expenses;

    public Report(int reportId, User user) {
        this.reportId = reportId;
        this.user = user;
        this.expenses = user.getExpenses();
    }

    // Method to generate an expense report for a user
    public void generateReport() {
        System.out.println("Expense report for: " + user.getName());
        for (Expense expense : expenses) {
            System.out.println("Amount split: " + expense.splitCost());
        }
    }
}

// Admin class for system administration functions
class Admin {
    public void deleteUser(User user) {
        System.out.println("User deleted: " + user.getName());
    }
}

// Main class to test the system functionality
public class ExpenseManager {
    public static void main(String[] args) {
        // Creating users
        User user1 = new User(1, "Adam", "adam@email.com");
        User user2 = new User(2, "Ewa", "ewa@email.com");

        // Creating an expense shared between users
        List<User> roommates = Arrays.asList(user1, user2);
        Expense rent = new Expense(1, 1000, "Rent", user1, roommates);
        user1.addExpense(rent);

        // Generating a report for a user
        Report report = new Report(1, user1);
        report.generateReport();
    }
}
