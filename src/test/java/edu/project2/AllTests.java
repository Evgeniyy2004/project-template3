package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project2.Maze;
import project2.MazeGenerator;
import project2.Renderer;
import project2.Solver;
import java.awt.Point;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AllTests {

    private final static Logger LOGGER = LogManager.getLogger();
    @Test
    @DisplayName("Проверка корректности вывода созданного лабиринта - правильного количества cтрок и столбцов")
    void Test1(){
        var generator = new MazeGenerator();
        var first = generator.generate(15,15);
        var writer = new Renderer();
        var toPrint = writer.render(first);
        var delete = toPrint.replace("\n","").length();
        assertThat(toPrint.length() - delete).isEqualTo(first.grid.length + 1);
        var split = toPrint.split("\n");
        for(int i = 1; i < split.length; i++) {
            assertThat(split[i].length()).isEqualTo( 2 * first.grid[0].length + 1);
        }
        LOGGER.info("\n"+toPrint);
    }

    @Test
    @DisplayName("Проверка корректности вывода созданного лабиринта")
    void Test2(){
        var generator = new MazeGenerator();
        var first = generator.generate(15,15);
        var writer = new Renderer();
        var solver = new Solver();
        var path = solver.solveByBFS(first,new Point(1, 0), new Point(10, 13));
        var toPrint = writer.render(first, path);
        LOGGER.info("\n"+toPrint);
    }

}
