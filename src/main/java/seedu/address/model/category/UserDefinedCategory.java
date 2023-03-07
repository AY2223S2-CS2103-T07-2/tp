package seedu.address.model.category;

/**
 * User-defined category class which allows users to customize their own categories to use.
 */
public class UserDefinedCategory extends Category {

    UserDefinedCategory(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public void setCategoryName(String newName) {
        this.categoryName = newName;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
}
