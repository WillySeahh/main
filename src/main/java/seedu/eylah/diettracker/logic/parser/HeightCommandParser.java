package seedu.eylah.diettracker.logic.parser;

import static seedu.eylah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.eylah.commons.logic.parser.Parser;
import seedu.eylah.commons.logic.parser.exception.ParseException;
import seedu.eylah.diettracker.logic.commands.HeightCommand;
import seedu.eylah.diettracker.model.self.Height;

/**
 * Parses input arguments and creates a new HeightCommand object
 */
public class HeightCommandParser implements Parser<HeightCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HeightCommand
     * and returns an HeightCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HeightCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HeightCommand.MESSAGE_USAGE));
        }

        Height height = ParserUtil.parseHeight(args);

        return new HeightCommand(height);
    }
}
