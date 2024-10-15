package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ECNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.EmergencyPhoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EmergencyPhone;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new {@code AddEmergencyContactNumberCommand} object
 */
public class EmergencyPhoneCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddEmergencyContactNumberCommand}
     * and returns a {@code AddEmergencyContactCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EmergencyPhoneCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ECNUMBER);
        if (!argMultiMap.getValue(PREFIX_NAME).isPresent() || !argMultiMap.getValue(PREFIX_ECNUMBER).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EmergencyPhoneCommand.MESSAGE_USAGE));
        }

        Name name;
        EmergencyPhone emergencyPhone;

        try {
            name = ParserUtil.parseName(argMultiMap.getValue(PREFIX_NAME).get());
            emergencyPhone = ParserUtil.parseEmergencyPhone(argMultiMap.getValue(PREFIX_ECNUMBER).get());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EmergencyPhoneCommand.MESSAGE_USAGE), ive);
        }

        return new EmergencyPhoneCommand(name, emergencyPhone);
    }
}
