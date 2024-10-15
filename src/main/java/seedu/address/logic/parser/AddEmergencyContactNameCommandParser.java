package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ECNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddEmergencyContactNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EmergencyContactName;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new {@code AddEmergencyContactNameCommand} object
 */
public class AddEmergencyContactNameCommandParser implements Parser<AddEmergencyContactNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddEmergencyContactNameCommand}
     * and returns a {@code AddEmergencyContactNameCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEmergencyContactNameCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ECNAME);
        if (!argMultimap.getValue(PREFIX_ECNAME).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddEmergencyContactNameCommand.MESSAGE_USAGE));
        }

        Index index;
        EmergencyContactName eCName;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            eCName = (EmergencyContactName) ParserUtil.parseEmergencyContactName(argMultimap.getValue(PREFIX_ECNAME).get());

        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddEmergencyContactNameCommand.MESSAGE_USAGE), ive);
        }

        return new AddEmergencyContactNameCommand(index, eCName);
    }

}
