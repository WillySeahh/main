package seedu.eylah.expensesplitter.model;

import seedu.eylah.addressbook.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * Adds an entry to the receipt.
     *
     * @param entry consists of an Item and ArrayList of Person sharing the cost of the item.
     */
    void addEntry(Entry entry);

    /**
     * Removes an entry from the receipt via the index.
     *
     * @param index Index of the entry to be removed.
     */
    void deleteEntry(int index);

    /**
     * Updates the amount the Person owes you. If amount is $0 then the Person is deleted.
     *
     * @param person Person who paid
     */
    void paidPerson(Person person);

    /**
     * Command for listing all person with their amount.
     */
    void listAmount();

    /**
     * Command for listing all items in that receipt.
     */
    void listReceipt();

    /**
     * Going back to EYLAH Main Menu.
     */
    void backToMainMenu();
}

