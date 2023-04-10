package fasttrack.ui;

import static fasttrack.testutil.TypicalRecurringExpenses.GYM_MEMBERSHIP;
import static fasttrack.testutil.TypicalRecurringExpenses.NETFLIX_SUBSCRIPTION;
import static fasttrack.testutil.TypicalRecurringExpenses.RENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import fasttrack.model.expense.RecurringExpenseManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ListView;


class RecurringExpensePanelTest {

    private RecurringExpensePanel recurringExpensePanel;
    private ObservableList<RecurringExpenseManager> recurringExpenses;

    @BeforeEach
    public void setUp() {
        recurringExpenses = FXCollections.observableArrayList(GYM_MEMBERSHIP, NETFLIX_SUBSCRIPTION, RENT);
        // Initialise fake JavaFX environment
        new JFXPanel();
    }

    @Test
    public void recurringExpenseListView_validExpenses_countEqual() {
        recurringExpensePanel = new RecurringExpensePanel(recurringExpenses);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                // Test that the number of recurring expenses is correct
                ListView<?> recurringExpenseListView = (ListView<?>) recurringExpensePanel
                        .getRoot().lookup("#recurringExpenseListView");
                assertEquals(recurringExpenses.size(), recurringExpenseListView.getItems().size());
                future.complete(null);
            } catch (AssertionFailedError e) {
                future.completeExceptionally(e);
            }
        });
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            fail("Assertion error thrown in Platform.runLater thread: " + e.getMessage());
        }
    }

    @Test
    public void recurringExpenseListView_validExpenses_countZero() {
        recurringExpenses = FXCollections.observableArrayList();
        RecurringExpensePanel recurringExpensePanel = new RecurringExpensePanel(recurringExpenses);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                ListView<?> recurringExpenseListView = (ListView<?>) recurringExpensePanel
                        .getRoot().lookup("#recurringExpenseListView");
                assertEquals(0, recurringExpenseListView.getItems().size());
                future.complete(null);
            } catch (AssertionFailedError e) {
                future.completeExceptionally(e);
            }
        });
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            fail("Assertion error thrown in Platform.runLater thread: " + e.getMessage());
        }
    }

}