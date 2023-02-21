package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Class representing a remark field of a person.
 */
public class Remark {

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param remark A remark by user.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
