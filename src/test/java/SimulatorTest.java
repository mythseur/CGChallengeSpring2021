import fr.mythseur.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimulatorTest {

    private Game game;

    @Before
    public void prepareGame() {
        game = TestUtils.generateGame();
    }

    @Test
    public void testComplete() {
        game.trees.add(new Tree(19, 3, true, false));
        Action action = new Action(EAction.COMPLETE, null, 19);
        Simulator.doAction(action, game);
        Assert.assertTrue(game.trees.isEmpty());
        Assert.assertEquals(6, game.mySun);
        Assert.assertEquals(20, game.myScore);
    }

    @Test
    public void testGrow() {
        game.trees.add(new Tree(19, 2, true, false));
        Action action = new Action(EAction.GROW, null, 19);
        Simulator.doAction(action, game);
        Assert.assertEquals(3, game.trees.get(0).size);
        Assert.assertTrue(game.trees.get(0).isDormant);
        Assert.assertEquals(6, game.mySun);
    }

    @Test
    public void testSeed() {
        game.trees.add(new Tree(19, 2, true, false));
        Action action = new Action(EAction.SEED, 19, 7);
        Simulator.doAction(action, game);
        Assert.assertEquals(2, game.trees.size());
        Assert.assertTrue(game.trees.get(0).isDormant);
        Assert.assertTrue(game.trees.get(1).isDormant);
        Assert.assertEquals(0, game.trees.get(1).size);
        Assert.assertEquals(10, game.mySun);
    }

    @Test
    public void testWait() {
        game.trees.add(new Tree(0, 2, true, true));
        game.computeShadows();
        Action action = new Action(EAction.WAIT, null, null);
        Simulator.doAction(action, game);
        Assert.assertEquals(1, game.day);
        Assert.assertFalse(game.trees.get(0).isDormant);
    }
}
