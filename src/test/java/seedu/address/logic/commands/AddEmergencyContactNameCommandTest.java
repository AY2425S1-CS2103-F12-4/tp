package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ECNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ECNAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.EmergencyContactName;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddEmergencyContactNameCommandTest {

    private static final String ECNAME_stub = "Jack Hardy";
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withEmergencyContactName(ECNAME_stub).build();
        AddEmergencyContactNameCommand ecNameCommand = new AddEmergencyContactNameCommand(INDEX_FIRST_PERSON,
                new EmergencyContactName(editedPerson.getEmergencyContactName().fullName));
        String expectedMessage = String.format(AddEmergencyContactNameCommand.MESSAGE_ADD_ECNAME_SUCCESS,
                editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        assertCommandSuccess(ecNameCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withEmergencyContactName("").build();
        AddEmergencyContactNameCommand ecNameCommand = new AddEmergencyContactNameCommand(INDEX_FIRST_PERSON,
                new EmergencyContactName(editedPerson.getEmergencyContactName().toString()));
        String expectedMessage = String.format(AddEmergencyContactNameCommand.MESSAGE_DELETE_ECNAME_SUCCESS,
                editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        assertCommandSuccess(ecNameCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased())).withEmergencyContactName(ECNAME_stub).build();
        AddEmergencyContactNameCommand ecNameCommand = new AddEmergencyContactNameCommand(INDEX_FIRST_PERSON,
                new EmergencyContactName(editedPerson.getEmergencyContactName().fullName));
        String expectedMessage = String.format(AddEmergencyContactNameCommand.MESSAGE_ADD_ECNAME_SUCCESS,
                editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        assertCommandSuccess(ecNameCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddEmergencyContactNameCommand ecNameCommand = new AddEmergencyContactNameCommand(outOfBoundIndex,
                new EmergencyContactName(VALID_ECNAME_BOB));
        assertCommandFailure(ecNameCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        AddEmergencyContactNameCommand ecNameCommand = new AddEmergencyContactNameCommand(outOfBoundIndex,
                new EmergencyContactName(VALID_ECNAME_BOB));

        assertCommandFailure(ecNameCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddEmergencyContactNameCommand standardCommand = new AddEmergencyContactNameCommand(
                INDEX_FIRST_PERSON, new EmergencyContactName(VALID_ECNAME_AMY));

        // same values -> returns true
        AddEmergencyContactNameCommand commandWithSameValues = new AddEmergencyContactNameCommand(
                INDEX_FIRST_PERSON, new EmergencyContactName(VALID_ECNAME_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different person name -> returns false
        assertFalse(standardCommand.equals(new AddEmergencyContactNameCommand(INDEX_SECOND_PERSON,
                new EmergencyContactName(VALID_ECNAME_AMY))));

        // different emergency contact name -> returns false
        assertFalse(standardCommand.equals(new AddEmergencyContactNameCommand(INDEX_FIRST_PERSON,
                new EmergencyContactName(VALID_ECNAME_BOB))));
    }
}

