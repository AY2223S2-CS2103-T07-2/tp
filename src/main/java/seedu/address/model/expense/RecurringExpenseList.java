package seedu.address.model.expense;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Represents a List of Recurring Expenses in the Expense Tracker.
 */
public class RecurringExpenseList {

    private final ObservableList<RecurringExpenseManager> recurringExpenseList = FXCollections.observableArrayList();

    private final ObservableList<RecurringExpenseManager> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(recurringExpenseList);

    /**
     * Adds a recurring expense to the internal list of recurring expenses.
     * @param recurringExpense Recurring expense to add.
     */
    public void addRecurringExpense(RecurringExpenseManager recurringExpense) {
        recurringExpenseList.add(recurringExpense);

    }


    /**
     * Removes a recurring expense from the internal list of recurring expenses.
     * @param recurringExpense Recurring expense to remove.
     */
    public void removeRecurringExpense(RecurringExpenseManager recurringExpense) {
        recurringExpenseList.remove(recurringExpense);
    }


    public ObservableList<RecurringExpenseManager> getRecurringExpenseList() {
        return recurringExpenseList;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}
     * @return The unmodifiable list.
     */
    public ObservableList<RecurringExpenseManager> asUnmodifiableList() {
        return this.internalUnmodifiableList;
    }

    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        for (RecurringExpenseManager recurringExpense : recurringExpenseList) {
            expenses.addAll(recurringExpense.getExpenses());
        }
        return expenses;
    }

    public void setRecurringExpenseList(RecurringExpenseList replacementList) {
        recurringExpenseList.clear();
        recurringExpenseList.addAll(replacementList.getRecurringExpenseList());
    }

    public void setRecurringExpenseList(List<RecurringExpenseManager> replacementList) {
        requireAllNonNull(replacementList);
        recurringExpenseList.setAll(replacementList);
    }

    public int getSize() {
        return recurringExpenseList.size();
    }

    /**
     * Returns true if the list contains an equivalent recurring expense as the given argument.
     * @param recurringExpense Recurring expense to be compared to
     * @return Boolean depicting if the recurring expense is present in the list.
     */
    public boolean contains(RecurringExpenseManager recurringExpense) {
        return recurringExpenseList.contains(recurringExpense);
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (RecurringExpenseManager recurringExpense : recurringExpenseList) {
            totalAmount += recurringExpense.getTotalAmount();
        }
        return totalAmount;
    }

    /**
     * Delete all recurring expense.
     */
    public void clear() {
        recurringExpenseList.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (RecurringExpenseManager recurringExpense : recurringExpenseList) {
            sb.append(recurringExpense.toString());
        }
        return sb.toString();
    }
}
