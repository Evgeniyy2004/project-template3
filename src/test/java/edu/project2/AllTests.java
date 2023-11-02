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

public class AllTests {

    private final static Logger LOGGER = LogManager.getLogger();
    @Test
    @DisplayName("Проверка корректности вывода созданного лабиринта")
    void Test1(){
        var generator = new MazeGenerator();
        var first = generator.generate(5,5);
        var writer = new Renderer();
        var solver = new Solver();
        var toPrint = writer.render(first/*,solver.solveByBFS(first, new Point(0, 0), new Point(0, 1))*/);
        LOGGER.info("\n"+toPrint);
    }

}
