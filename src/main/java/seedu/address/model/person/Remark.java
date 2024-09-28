package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents the Remark attribute of a Person in the address book.
 * Includes operations like printing in String format, and an equals method to test the equality
 * of two remarks
 */
public class Remark {

    public final String value;

    /**
     * Constructs an {@code Remark}.
     *
     * @param remark A valid remark.
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
        return other == this || (other instanceof Remark && value.equals(((Remark) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
