import fr.mythseur.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class GeneratorTest {

    private Game game;

    @Before
    public void prepareGame() {
        game = TestUtils.generateGame();
    }

    @Test
    @Ignore
    public void testActionGenerator() {
        game.trees.add(new Tree(22, 1, true, false));
//        game.trees.add(new Tree(19, 1, true, false));
        game.computeShadows();
        game.mySun = 2;
        List<Action> testValue = new ArrayList<>();
        testValue.add(new Action(EAction.SEED, 22, 9));
        testValue.add(new Action(EAction.SEED, 22, 21));
//        testValue.add(new Action(EAction.SEED, 19, 20));
//        testValue.add(new Action(EAction.SEED, 19, 7));
        testValue.add(new Action(EAction.SEED, 22, 23));
        testValue.add(new Action(EAction.GROW, null, 22));
//        testValue.add(new Action(EAction.SEED, 19, 36));
        List<Action> possibleMoves = ActionGenerator.getPossibleMoves(game);

        Assert.assertEquals(testValue.size(), possibleMoves.size());
        for (Action action : testValue) {
            Assert.assertTrue(possibleMoves.contains(action));
        }

    }
}
