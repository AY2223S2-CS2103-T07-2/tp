package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyExpenseTracker {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ObservableList<Category> getCategoryList();

    ObservableList<Expense> getExpenseList();

}
