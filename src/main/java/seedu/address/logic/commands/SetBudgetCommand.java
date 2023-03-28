package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.address.model.Budget;
import seedu.address.model.Model;

/**
 * Sets the monthly budget for FastTrack.
 */
public class SetBudgetCommand extends Command {

    private final Budget budget;

    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets monthly budget.\n"
            + "Parameters: "
            + PREFIX_PRICE + "BUDGET_AMOUNT "
            + "Example: " + COMMAND_WORD + " " + PREFIX_PRICE + " 1000";

    public static final String MESSAGE_SUCCESS = "Monthly budget successfully set to ";

    public SetBudgetCommand(Budget budget) {
        this.budget = budget;
    }

    @Override
    public CommandResult execute(Model dataModel) {
        dataModel.setBudget(budget);
        return new CommandResult(MESSAGE_SUCCESS + this.budget, true);
    }
}
