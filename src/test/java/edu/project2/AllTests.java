package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import anotherproject.MazeGenerator;
import anotherproject.Renderer;
import anotherproject.Solver;
import java.awt.Point;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

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
    @DisplayName("Длина пути равна количеству меток 0 на карте лабиринта")
    void Test2(){
        var generator = new MazeGenerator();
        var first = generator.generate(8,5);
        var writer = new Renderer();
        var solver = new Solver();
        var path = solver.solveByDFS(first, new Point(0,0), new Point(4,4));
        var toPrint = writer.render(first, path);
        var delete = toPrint.replace("\n","").length();
        assertThat(toPrint.length() - delete).isEqualTo(first.grid.length + 1);
        var split = toPrint.split("\n");
        for(int i = 1; i < split.length; i++) {
            assertThat(split[i].length()).isEqualTo( 2 * first.grid[0].length + 1);
        }

        var deleteWay =  toPrint.replace("0","").length();
        assertThat(toPrint.length() - deleteWay).isEqualTo(path.toArray().length);
        LOGGER.info("\n"+toPrint);
    }

    @Test
    @DisplayName("Если поиск в ширину находит путь к конечной точке, то и поиск в глубину должен его найти")
    void Test3(){
        var generator = new MazeGenerator();
        var first = generator.generate(8,5);
        var solver = new Solver();
        var path = solver.solveByBFS(first, new Point(0,0), new Point(4,4));
        var anotherPath = solver.solveByDFS(first, new Point(0,0), new Point(4,4));
        if (path.size() != 0) {
           assertThat(anotherPath.size()).isNotEqualTo(0);
        } else {
           assertThat(anotherPath.size()).isEqualTo(0);
        }
    }

    @Test
    @DisplayName("Методы поиска пути в лабиринте должны возвращать исключение при неправильных значениях конца или начала пути")
    void Test4(){
        var generator = new MazeGenerator();
        var first = generator.generate(8,5);
        var solver = new Solver();
        try {
            solver.solveByBFS(first, new Point(0, 0), new Point(7, 5));
            fail("Должно быть возвращено исключение");
        } catch (IllegalArgumentException exception) {
        }

        try {
            solver.solveByDFS(first, new Point(0, 0), new Point(-1, 5));
            fail("Должно быть возвращено исключение");
        } catch (IllegalArgumentException exception) {
        }

        try {
            solver.solveByBFS(first, new Point(-1, 0), new Point(7, 3));
            fail("Должно быть возвращено исключение");
        } catch (IllegalArgumentException exception) {
        }

        try {
            solver.solveByDFS(first, new Point(3, 2), new Point(1, -1));
            fail("Должно быть возвращено исключение");
        } catch (IllegalArgumentException exception) {
        }


    }

    @Test
    @DisplayName("Методы создания лабиринта должны возвращать исключение при неправильных ширины и высоты")
    void Test5(){
        var generator = new MazeGenerator();
        try {
            generator.generate(0, 5);
            fail("Должно быть возвращено исключение");
        }  catch (IllegalArgumentException exception) {
        }
        try {
            generator.generate(4, -1);
            fail("Должно быть возвращено исключение");
        }  catch (IllegalArgumentException exception) {
        }

        try {
            generator.generate(0, 0);
            fail("Должно быть возвращено исключение");
        }  catch (IllegalArgumentException exception) {
        }

    }
}
