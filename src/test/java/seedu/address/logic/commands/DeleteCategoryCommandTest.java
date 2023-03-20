package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.stubs.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.category.Category;
import seedu.address.model.category.UserDefinedCategory;

public class DeleteCategoryCommandTest {
    ModelStub model = new ModelStub();
    Category toDelete = new UserDefinedCategory("test", "test");
    Category toDelete2 = new UserDefinedCategory("test2", "test2");

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        model.addCategory(toDelete);
        model.addCategory(toDelete2);
        DeleteCategory deleteCategoryCommand = new DeleteCategory(Index.fromOneBased(1));
        deleteCategoryCommand.execute(model);
        ModelStub expectedModel = new ModelStub();
        expectedModel.addCategory(toDelete2);
        assertEquals(expectedModel.getCategories(), model.getCategories());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() throws CommandException {
        model.addCategory(toDelete);
        model.addCategory(toDelete2);
        DeleteCategory deleteCategoryCommand = new DeleteCategory(Index.fromOneBased(3));
        // command should throw an exception
        assertThrows(CommandException.class, () -> deleteCategoryCommand.execute(model));
    }

}
