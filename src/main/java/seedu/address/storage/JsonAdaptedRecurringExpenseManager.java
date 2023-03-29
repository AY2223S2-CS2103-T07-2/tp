package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.category.Category;
import seedu.address.model.expense.RecurringExpenseManager;
import seedu.address.model.expense.RecurringExpenseType;
import seedu.address.model.util.StorageUtility;

/**
 * Jackson-friendly version of {@link RecurringExpenseManager}.
 */
public class JsonAdaptedRecurringExpenseManager {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recurring Expense Manager's field is missing!";

    private final String expenseName;
    private final String expenseAmount;
    private final JsonAdaptedCategory expenseCategory;
    private final String nextExpenseDate;
    private final String startDate;
    private final String endDate;
    private final String recurringExpenseType;

    /**
     * Constructs a {@code JsonAdaptedRecurringExpenseManager} with the given category details.
     * @param expenseName Name of the expense.
     * @param expenseAmount Amount of the expense.
     * @param expenseCategory Category of the expense.
     * @param nextExpenseDate The next date at which the expense will be charged.
     * @param startDate The starting date at which the recurring expense was first added.
     * @param endDate The ending date at which the recurring expense will end.
     * @param recurringExpenseType Frequency-interval of which the expense will be added.
     */
    @JsonCreator
    public JsonAdaptedRecurringExpenseManager(@JsonProperty("expenseName") String expenseName,
                               @JsonProperty("expenseAmount") String expenseAmount,
                              @JsonProperty("expenseCategory") JsonAdaptedCategory expenseCategory,
                          @JsonProperty("nextExpenseDate") String nextExpenseDate,
                      @JsonProperty("startDate") String startDate,
                  @JsonProperty("endDate") String endDate,
              @JsonProperty("recurringExpenseType") String recurringExpenseType) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.expenseCategory = expenseCategory;
        this.nextExpenseDate = nextExpenseDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recurringExpenseType = recurringExpenseType;
    }

    /**
     * Converts a given {@code RecurringExpenseManager} into this class for Jackson use.
     * @param source future changes to this will not affect the created {@code JsonAdaptedRecurringExpenseManager}
     */
    public JsonAdaptedRecurringExpenseManager(RecurringExpenseManager source) {
        this.expenseName = source.getExpenseName();
        this.expenseAmount = Double.toString(source.getExpenseAmount());
        this.expenseCategory = new JsonAdaptedCategory(source.getExpenseCategory().getCategoryName(),
                source.getExpenseCategory().getSummary());
        this.nextExpenseDate = String.valueOf(source.getNextExpenseDate());
        this.startDate = String.valueOf(source.getExpenseStartDate());
        this.endDate = String.valueOf(source.getExpenseEndDate());
        this.recurringExpenseType = String.valueOf(source.getRecurringExpenseType());
    }

    /**
     * Converts this Jackson-friendly adapted RecurringExpenseManager object into the model's
     * {@code RecurringExpenseManager} object.
     * @throws IllegalValueException if there were any data constraints violated
     *     in the adapted category.
     */
    public RecurringExpenseManager toModelType() throws IllegalValueException {
        if (expenseName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        if (expenseAmount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        if (expenseCategory == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        if (recurringExpenseType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        Double modelAmount = Double.parseDouble(expenseAmount);

        LocalDate modelStartDate = StorageUtility.parseDateFromJson(startDate);

        LocalDate modelEndDate = StorageUtility.parseDateFromJson(endDate);

        RecurringExpenseType modelRecurringType = RecurringExpenseType.valueOf(recurringExpenseType);

        Category toBeUsed = expenseCategory.toModelType();

        return new RecurringExpenseManager(expenseName, modelAmount, toBeUsed, modelStartDate,
                modelEndDate, modelRecurringType);
    }
}