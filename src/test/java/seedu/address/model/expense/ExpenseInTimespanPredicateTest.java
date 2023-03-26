package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCategories.MISCCAT;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.category.Category;
import seedu.address.model.category.MiscellaneousCategory;
import seedu.address.model.category.UserDefinedCategory;

public class ExpenseInTimespanPredicateTest {

    @Test
    public void equals() {

        LocalDate firstPredicateDate = LocalDate.of(2023, 1, 1);
        LocalDate secondPredicateDate = LocalDate.of(2023, 3, 15);

        ExpenseInTimespanPredicate firstPredicate = new ExpenseInTimespanPredicate(firstPredicateDate);
        ExpenseInTimespanPredicate secondPredicate = new ExpenseInTimespanPredicate(secondPredicateDate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExpenseInTimespanPredicate firstPredicateCopy = new ExpenseInTimespanPredicate(firstPredicateDate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_expenseInTimespan_returnsTrue() {
        LocalDate now = LocalDate.of(2023, 3, 23);

        ExpenseInTimespanPredicate predicate =
                new ExpenseInTimespanPredicate(LocalDate.of(2023, 3, 12));
        assertTrue(predicate.test(
                new Expense("Apple", 1.5, now, MISCCAT)));

        predicate = new ExpenseInTimespanPredicate(now);
        assertTrue(predicate.test(
                new Expense("Hello", 1.0, now, MISCCAT)
        ));
    }

    @Test
    public void test_expenseNotInCategory_returnsFalse() {
        Category category1 = new UserDefinedCategory("Category1", "Description1");
        Category category2 = new UserDefinedCategory("Category2", "Description2");
        Category miscCat = new MiscellaneousCategory();


        Expense expense = new Expense("test", 1, LocalDate.of(2023, 1, 1), category1);

        // Wrong Category
        ExpenseInTimespanPredicate predicate =
                new ExpenseInTimespanPredicate(LocalDate.of(2023, 2, 1));
        assertFalse(predicate.test(expense));

        expense = new Expense("test2", 2, LocalDate.of(2023, 1, 31), miscCat);
        assertFalse(predicate.test(expense));

        predicate = new ExpenseInTimespanPredicate(LocalDate.of(2024, 1, 1));
        expense = new Expense("test3", 3, LocalDate.now(), category1);
        assertFalse(predicate.test(expense));


    }
}
