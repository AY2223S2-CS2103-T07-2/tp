package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCategory;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUMMARY;

/**
 * Parses input arguments and creates a new EditCategory object
 */
public class EditCategoryParser implements Parser<EditCategory>{

    /**
     * Parses the given {@code String} of arguments in the context of the EditCategory
     * @param args Arguments provided by user in String form.
     * @return an instance of EditCategory with the necessary arguments extracted from user's arguments.
     * @throws ParseException if the user input does not conform to required format.
     */
    public EditCategory parse(String args) throws ParseException {
        //First check if the given index is valid.
        ArgumentMultimap argMultiMap
                = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_SUMMARY);
        Index index = ParserUtil.parseIndex(argMultiMap.getPreamble());

        //Get the new category name & summary if applicable
        if (isPrefixPresent(argMultiMap, PREFIX_CATEGORY) && isPrefixPresent(argMultiMap, PREFIX_SUMMARY)) {
            String newSummary = argMultiMap.getValue(PREFIX_SUMMARY).get();
            String newCategoryName = argMultiMap.getValue(PREFIX_CATEGORY).get();
            return new EditCategory(index, newCategoryName, newSummary);
        }

        if (isPrefixPresent(argMultiMap, PREFIX_CATEGORY)) {
            String newCategoryName = argMultiMap.getValue(PREFIX_CATEGORY).get();
            return new EditCategory(index, newCategoryName, null);
        }

        if (isPrefixPresent(argMultiMap, PREFIX_SUMMARY)) {
            String newSummary = argMultiMap.getValue(PREFIX_SUMMARY).get();
            return new EditCategory(index, null, newSummary);
        }

        return new EditCategory(index, null, null);
    }

    /**
     * Returns true if the given prefix does not contain {@code Optional} values in the given {@code ArgumentMultimap}
     * @param argMultiMap The argument multimap to check for.
     * @param toCheck The prefix to check for.
     * @return Boolean that indicates whether the given prefix is present or not.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argMultiMap, Prefix toCheck) {
        return argMultiMap.getValue(toCheck).isPresent();
    }
}
