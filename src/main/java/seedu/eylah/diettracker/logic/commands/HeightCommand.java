package seedu.eylah.diettracker.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.eylah.commons.logic.command.Command;
import seedu.eylah.commons.logic.command.CommandResult;
import seedu.eylah.commons.logic.command.exception.CommandException;
import seedu.eylah.diettracker.model.DietModel;
import seedu.eylah.diettracker.model.self.Height;

/**
 * Adds the height of the user in centimeters.
 */
public class HeightCommand extends Command<DietModel> {

    public static final String COMMAND_WORD = "height";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": \n"
            + "Parameters: HEIGHT (must be in centimeters)\n"
            + "Example: " + COMMAND_WORD + " 170";

    public static final String MESSAGE_ADD_HEIGHT_SUCCESS = "Added Height: %1$s";

    private final Height height;

    /**
     * Constructor for Command that includes input Height.
     */
    public HeightCommand(Height height) {
        requireNonNull(height);
        this.height = height;
    }

    @Override
    public CommandResult execute(DietModel model) throws CommandException {
        requireNonNull(model);

        if (height.getHeightFloat() <= 0) {
            throw new CommandException("Please enter a height value >0 and <1000. Fun fact: The shortest person in the "
                    + "world is Chandra Bahadur Dangi at 54.6cm. However, we at EYLAH know you might want to calculate"
                    + " metrics for your baby too!");
        }

        if (height.getHeightFloat() > 1000) {
            throw new CommandException("Please enter a height value >0 and <1000. Fun fact: The tallest person in the "
                    + "world is Robert Wadlow at 2.72m. However, we at EYLAH believe people can grow till 10m!");
        }


        model.setHeight(height);

        return new CommandResult(String.format(MESSAGE_ADD_HEIGHT_SUCCESS, height.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HeightCommand // instanceof handles nulls
                && height.equals(((HeightCommand) other).height)); // state check
    }
}
