package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A UI component that displays information of a {@code Expense} or {@code Category}
 * with a title and the category filter applied if any.
 */
public class ResultsHeader extends UiPart<Region> {

    private static final String FXML = "ResultsHeader.fxml";

    @FXML
    private Label resultType;
    @FXML
    private Label filterType;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ResultsHeader() {
        super(FXML);
        resultType.setText("Expenses");
        filterType.setText("All");
    }

    public void setHeader(ScreenType screenType, String filter) {
        if (screenType == ScreenType.EXPENSE_SCREEN) {
            resultType.setText("Expenses");
        } else if (screenType == ScreenType.CATEGORY_SCREEN) {
            resultType.setText("Category");
        } else if (screenType == ScreenType.RECURRING_EXPENSE_SCREEN) {
            resultType.setText("Recurring Expenses");
        }
        filterType.setText(filter);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ResultsHeader)) {
            return false;
        }

        // state check
        ResultsHeader header = (ResultsHeader) other;
        return resultType.getText().equals(header.resultType.getText())
                && filterType.getText().equals(header.filterType.getText());
    }
}
