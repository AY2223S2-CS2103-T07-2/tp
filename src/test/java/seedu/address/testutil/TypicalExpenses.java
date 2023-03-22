package seedu.address.testutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ExpenseTracker;
import seedu.address.model.expense.Expense;

import static seedu.address.testutil.TypicalCategories.FOOD;
import static seedu.address.testutil.TypicalCategories.MISCCAT;
import static seedu.address.testutil.TypicalCategories.SCHOOL;
import static seedu.address.testutil.TypicalCategories.TECH;


/**
 * A utility class containing a list of {@code Expense} objects to be used in tests.
 */
public class TypicalExpenses {

    // For FastTrack
    public static final Expense APPLE =
            new Expense("Apple", 1.50, LocalDate.of(2023, 3, 1), FOOD);
    public static final Expense BANANA =
            new Expense("Banana", 1.00, LocalDate.of(2023, 3, 2), FOOD);
    public static final Expense CHERRY =
            new Expense("Cherry", 0.20, LocalDate.of(2023, 3, 1), TECH);
    public static final Expense DURIAN =
            new Expense("Durian", 15, LocalDate.of(2023, 3, 15), TECH);
    public static final Expense ELDERBERRY =
            new Expense("Elderberry", 4, LocalDate.of(2022, 1, 1), SCHOOL);
    public static final Expense FIG =
            new Expense("Fig", 1000, LocalDate.of(2023, 2, 15), MISCCAT);
    public static final Expense GRAPE =
            new Expense("Grape", 10, LocalDate.of(2023, 3, 17), MISCCAT);


    private TypicalExpenses() {} // prevents instantiation

    /**
     * Returns an {@code ExpenseTracker} with all the typical expenses.
     */
    public static ExpenseTracker getTypicalExpenseTracker() {
        ExpenseTracker et = new ExpenseTracker();
        for (Expense expense : getTypicalExpenses()) {
            et.addExpense(expense);
        }
        return et;
    }

    public static List<Expense> getTypicalExpenses() {
        return new ArrayList<>(Arrays.asList(APPLE, BANANA, CHERRY, DURIAN, ELDERBERRY, FIG, GRAPE));
    }
}
