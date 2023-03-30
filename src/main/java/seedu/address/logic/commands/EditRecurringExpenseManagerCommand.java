package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESPAN;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.expense.RecurringExpenseManager;
import seedu.address.model.expense.RecurringExpenseType;

/**
 * Edits a RecurringExpenseManager in the ExpenseTracker
 */
public class EditRecurringExpenseManagerCommand extends Command {
    public static final String COMMAND_WORD = "edrec";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the recurring expense identified by the index number used in the displayed recurring"
            + " expenses list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_NAME + "EXPENSE NAME] "
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_PRICE + "AMOUNT] "
            + "[" + PREFIX_TIMESPAN + "FREQUENCY] \n"
            + "[" + PREFIX_END_DATE + "END-DATE] \n"
            + "Example: " + COMMAND_WORD + " 1 n/KFC c/food p/10 t/weekly ed/20/03/23 ";

    private final Index targetIndex;
    private final String newExpenseName;
    private final String newExpenseCategoryInString;
    private final Double newExpenseAmount;
    private final String newFrequencyInString;
    private final LocalDate newExpenseEndDate;

    /**
     * Creates an EditRecurringExpenseManagerCommand to edit the specified {@code RecurringExpenseManager}
     * @param targetIndex index of the recurringexpensemanager in the filtered recurringexpensemanager list to edit.
     * @param newExpenseName String representation of the new category name to be edited to, if applicable.
     * @param newExpenseAmount New expense price to be edited to, if applicable.
     * @param newExpenseCategoryInString String representation of the new category's name to be edited to,
     *                                  if applicable.
     * @param newFrequencyInString String representation of the frequency to be edited to, if applicable.
     * @param newExpenseEndDate New recurring expense end date to be edited to, if applicable.
     */
    public EditRecurringExpenseManagerCommand(Index targetIndex, String newExpenseName, Double newExpenseAmount,
                                              String newExpenseCategoryInString, String newFrequencyInString,
                                              LocalDate newExpenseEndDate) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.newExpenseName = newExpenseName;
        this.newExpenseAmount = newExpenseAmount;
        this.newExpenseCategoryInString = newExpenseCategoryInString;
        this.newFrequencyInString = newFrequencyInString;
        this.newExpenseEndDate = newExpenseEndDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Category> lastShownListOfCategories = model.getFilteredCategoryList();
        List<RecurringExpenseManager> lastShownListOfRecurringExpenseManagers = model.getFilteredRecurringGenerators();
        Category toBeAllocated = null;

        for (Category category : lastShownListOfCategories) {
            if (category.getCategoryName().equals(this.newExpenseCategoryInString)) {
                toBeAllocated = category;
                break;
            }
        }

        if (newExpenseName == null && newExpenseAmount == null
                && newFrequencyInString == null && newExpenseCategoryInString == null
                && this.newExpenseEndDate == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EDIT_FOR_EXPENSE);
        }

        //Check if index is valid
        if (targetIndex == null || targetIndex.getZeroBased() >= lastShownListOfRecurringExpenseManagers.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
        }

        RecurringExpenseManager generatorToEdit = lastShownListOfRecurringExpenseManagers
                .get(targetIndex.getZeroBased());

        if (toBeAllocated != null) {
            generatorToEdit.setExpenseCategory(toBeAllocated);
        } else if (this.newExpenseCategoryInString != null && toBeAllocated == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_CATEGORY);
        }

        if (newExpenseName != null) {
            generatorToEdit.setExpenseName(newExpenseName);
        }

        if (newExpenseAmount != null) {
            generatorToEdit.setExpenseAmount(newExpenseAmount);
        }

        if (newExpenseEndDate != null) {
            generatorToEdit.setEndDate(newExpenseEndDate);
        }

        if (newFrequencyInString != null) {
            //Check if it belongs in the enum
            try {
                RecurringExpenseType newTypeToSet = RecurringExpenseType.valueOf(newFrequencyInString);
                generatorToEdit.setRecurringExpenseType(newTypeToSet);
            } catch (IllegalArgumentException iae) {
                throw new CommandException(Messages.MESSAGE_INVALID_ENUM_FOR_FREQUENCY);
            }
        }
        return new CommandResult(String.format(Messages.MESSAGE_SUCCESSFULLY_EDITED_RECURRING, generatorToEdit), false);
    }
}
