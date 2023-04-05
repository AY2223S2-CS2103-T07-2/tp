package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.add.AddCategoryCommand.MESSAGE_DUPLICATE_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUMMARY;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.ui.ScreenType;


/**
 * Edits a category in the ExpenseTracker
 */
public class EditCategoryCommand implements EditCommand {
    public static final String COMMAND_WORD = "edcat";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the category identified by the index number used in the displayed category list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_SUMMARY + "SUMMARY] "
            + "Example: " + COMMAND_WORD + " edcat 1 c/food s/for meals";

    private final Index targetIndex;

    private final String newCategoryName;

    private final String newSummary;

    /**
     * Creates an EditCategory to edit the specified {@code Category}
     * @param targetIndex index of the expense in the filtered category list to edit.
     * @param newCategoryName String representation of the new category name to be edited to, if applicable.
     * @param newSummary String representation of the new summary to be edited to, if applicable.
     */
    public EditCategoryCommand(Index targetIndex, String newCategoryName, String newSummary) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.newCategoryName = newCategoryName;
        this.newSummary = newSummary;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Category> lastShownList = model.getFilteredCategoryList();

        //Check whether targetIndex is a valid index.
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CATEGORY_DISPLAYED_INDEX);
        }

        UserDefinedCategory categoryToEdit = (UserDefinedCategory) lastShownList.get(targetIndex.getZeroBased());
        if (newCategoryName != null) {
            categoryToEdit.setCategoryName(newCategoryName.replaceAll("\\s+", " "));
            if (newCategoryName.strip().isEmpty()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CATEGORY_NAME);
            }
            if (model.hasCategory(categoryToEdit)) {
                throw new CommandException(MESSAGE_DUPLICATE_CATEGORY);
            }
        }
        if (newSummary != null) {
            categoryToEdit.setDescription(newSummary.replaceAll("\\s+", " "));
        }

        if (newCategoryName == null && newSummary == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EDIT_FOR_CATEGORIES);
        }

        return new CommandResult(
                    String.format(Messages.MESSAGE_SUCCESSFULLY_EDITED_CATEGORY,
                            categoryToEdit), ScreenType.CATEGORY_SCREEN);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCategoryCommand // instanceof handles nulls
                && targetIndex.equals(((EditCategoryCommand) other).targetIndex)); // state check
    }
}