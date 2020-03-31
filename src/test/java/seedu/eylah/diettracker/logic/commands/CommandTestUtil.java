package seedu.eylah.diettracker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.eylah.diettracker.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.eylah.diettracker.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.eylah.diettracker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.eylah.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.eylah.commons.core.index.Index;
import seedu.eylah.commons.logic.command.CommandResult;
import seedu.eylah.commons.logic.command.exception.CommandException;
import seedu.eylah.diettracker.model.DietModel;
import seedu.eylah.diettracker.model.FoodBook;
import seedu.eylah.diettracker.model.food.Food;
import seedu.eylah.diettracker.model.food.NameContainsKeywordsPredicate;
import seedu.eylah.diettracker.testutil.EditFoodDescriptorBuilder;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_PASTA = "Pasta Aglio Olio";
    public static final String VALID_NAME_PIZZA = "Pizza Pepperoni";
    public static final String VALID_CALORIES_PASTA = "1200";
    public static final String VALID_CALORIES_PIZZA = "560";
    public static final String VALID_TAG_FASTFOOD = "fastfood";
    public static final String VALID_TAG_FAVORITE = "favorite";

    public static final String NAME_DESC_PASTA = " " + PREFIX_NAME + " " + VALID_NAME_PASTA;
    public static final String NAME_DESC_PIZZA = " " + PREFIX_NAME + " " + VALID_NAME_PIZZA;

    public static final String CALORIES_DESC_PASTA = " " + PREFIX_CALORIES + " " + VALID_CALORIES_PASTA;
    public static final String CALORIES_DESC_PIZZA = " " + PREFIX_CALORIES + " " + VALID_CALORIES_PIZZA;

    public static final String TAG_DESC_FASTFOOD = " " + PREFIX_TAG + " " + VALID_TAG_FASTFOOD;
    public static final String TAG_DESC_FAVORITE = " " + PREFIX_TAG + " " + VALID_TAG_FAVORITE;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + " " + "James&"; // '&' not allowed in names

    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + " " + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditFoodDescriptor DESC_PASTA;
    public static final EditCommand.EditFoodDescriptor DESC_PIZZA;

    static {
        DESC_PASTA = new EditFoodDescriptorBuilder().withName(VALID_NAME_PASTA).build();
        DESC_PIZZA = new EditFoodDescriptorBuilder().withName(VALID_NAME_PIZZA)
                .withTags(VALID_TAG_FASTFOOD, VALID_TAG_FAVORITE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, DietModel actualModel, CommandResult expectedCommandResult,
                                            DietModel expectedModel) {
        System.out.println(expectedCommandResult);
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, DietModel, CommandResult, DietModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, DietModel actualModel, String expectedMessage,
                                            DietModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the food book, filtered food list and selected food in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, DietModel actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FoodBook expectedFoodBook = new FoodBook(actualModel.getFoodBook());
        List<Food> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFoodList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFoodBook, actualModel.getFoodBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredFoodList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the food at the given {@code targetIndex} in the
     * {@code model}'s food book.
     */
    public static void showFoodAtIndex(DietModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFoodList().size());

        Food food = model.getFilteredFoodList().get(targetIndex.getZeroBased());
        final String[] splitName = food.getName().name.split("\\s+");
        model.updateFilteredFoodList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFoodList().size());
    }

}
