package seedu.eylah.addressbook.logic.commands;

import static seedu.eylah.addressbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.eylah.addressbook.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.eylah.addressbook.model.Model;
import seedu.eylah.addressbook.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}