package fasttrack.logic.parser;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fasttrack.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static fasttrack.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.general.ExitCommand;
import fasttrack.logic.commands.general.FindCommand;
import fasttrack.logic.commands.general.HelpCommand;
import fasttrack.logic.commands.list.ListExpensesCommand;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.expense.ExpenseContainsKeywordsPredicate;
public class AddressBookParserTest {

    private final ExpenseTrackerParser parser = new ExpenseTrackerParser();

    @Test
    public void parseCommand_add() throws Exception {
        //Person person = new PersonBuilder().build();
        //AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        //assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        //assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        //assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        //DeleteCommand command = (DeleteCommand) parser.parseCommand(
        //DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        //assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");

        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new ExpenseContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListExpensesCommand.COMMAND_WORD) instanceof ListExpensesCommand);
        assertTrue(parser.parseCommand(ListExpensesCommand.COMMAND_WORD + " 3") instanceof ListExpensesCommand);
    }


    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}