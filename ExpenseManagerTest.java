
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


public class ExpenseManagerTest {
    @Test
    void testUserCreation() {
        User user = new User(1, "Adam", "adam@email.com");
        assertEquals("Adam", user.getName());
    }

    @Test
    void testAddingExpense() {
        User user = new User(1, "Adam", "adam@email.com");
        Expense expense = new Expense(1, 100.0, "Groceries", user, List.of(user));
        user.addExpense(expense);
        assertEquals(1, user.getExpenses().size());
    }

    @Test
    void testExpenseSplitting() {
        User user1 = new User(1, "Adam", "adam@email.com");
        User user2 = new User(2, "Ewa", "ewa@email.com");
        Expense expense = new Expense(1, 200.0, "Rent", user1, List.of(user1, user2));
        Map<User, Double> splitCosts = expense.splitCost();
        assertEquals(100.0, splitCosts.get(user1));
        assertEquals(100.0, splitCosts.get(user2));
    }
    @Test
    void testReportGeneration() {
        User user = new User(1, "Adam", "adam@email.com");
        Expense expense = new Expense(1, 200.0, "Rent", user, List.of(user));
        user.addExpense(expense);

        Report report = new Report(1, user);

        // Test to see if the generateReport method works without errors
        assertDoesNotThrow(report::generateReport);
    }

}
