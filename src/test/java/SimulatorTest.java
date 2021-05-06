import com.codingame.game.Board;
import com.codingame.game.BoardGenerator;
import com.codingame.game.CubeCoord;
import fr.mythseur.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class SimulatorTest {

    private Game game;

    static int[][] directions = new int[][]{{1, -1, 0}, {+1, 0, -1}, {0, +1, -1}, {-1, +1, 0}, {-1, 0, +1}, {0, -1, +1}};

    @Before
    public void prepareGame() {
        game = new Game();
        Board generate = BoardGenerator.generate(new Random(7308340236785320085L));
        List<Cell> cells = new ArrayList<>();

        generate.coords.forEach(coord -> {
            List<Integer> orderedNeighborIds = new ArrayList<>(directions.length);
            int[] neighbors = new int[directions.length];
            com.codingame.game.Cell cell = generate.map.get(coord);
            for (int i = 0; i < directions.length; ++i) {
                orderedNeighborIds.add(
                        generate.map.getOrDefault(neighbor(coord, i), com.codingame.game.Cell.NO_CELL).getIndex()
                );

                for (int j = 0; j < orderedNeighborIds.size(); j++) {
                    neighbors[j] = orderedNeighborIds.get(j);
                }

            }
            cells.add(new Cell(cell.getIndex(), cell.getRichness(), neighbors));
        });

        cells.sort(Comparator.comparingInt(cell -> cell.index));

        game.day = 0;
        game.nutrients = 20;
        game.mySun = 10;
        game.myScore = 0;
        game.board.addAll(cells);
    }

    private CubeCoord neighbor(CubeCoord coord, int orientation) {
        int nx = coord.getX() + directions[orientation][0];
        int ny = coord.getY() + directions[orientation][1];
        int nz = coord.getZ() + directions[orientation][2];
        return new CubeCoord(nx, ny, nz);
    }

    @Test
    public void testComplete() {
        game.trees.add(new Tree(0, 3, true, false));
        Action action = new Action(EAction.COMPLETE, null, 0);
        Simulator.doAction(action, game);
        Assert.assertTrue(game.trees.isEmpty());
        Assert.assertEquals(6, game.mySun);
        Assert.assertEquals(24, game.myScore);
    }

    @Test
    public void testGrow() {
        game.trees.add(new Tree(0, 2, true, false));
        Action action = new Action(EAction.GROW, null, 0);
        Simulator.doAction(action, game);
        Assert.assertEquals(3, game.trees.get(0).size);
        Assert.assertTrue(game.trees.get(0).isDormant);
        Assert.assertEquals(6, game.mySun);
    }

    @Test
    public void testSeed() {
        game.trees.add(new Tree(0, 2, true, false));
        Action action = new Action(EAction.SEED, 0, 1);
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
