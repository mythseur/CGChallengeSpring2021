import com.codingame.game.Board;
import com.codingame.game.BoardGenerator;
import com.codingame.game.CubeCoord;
import fr.mythseur.Cell;
import fr.mythseur.Game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TestUtils {
    static int[][] directions = new int[][]{{1, -1, 0}, {+1, 0, -1}, {0, +1, -1}, {-1, +1, 0}, {-1, 0, +1}, {0, -1, +1}};

    public static Game generateGame() {
        Game game = new Game();
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
        return game;
    }

    private static CubeCoord neighbor(CubeCoord coord, int orientation) {
        int nx = coord.getX() + directions[orientation][0];
        int ny = coord.getY() + directions[orientation][1];
        int nz = coord.getZ() + directions[orientation][2];
        return new CubeCoord(nx, ny, nz);
    }
}
