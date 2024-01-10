package edu.hw9;

import java.awt.Point;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
public class Test3 {

    @Test
    @DisplayName(
        "Тест генерирует случайный квадратный лабиринт и в случае отсутствия пути выводит лабиринт на экран, иначе проверяет путь")
    void checkMultiThreadBFS() throws InterruptedException {
        //Arrange
        int n = new Random().nextInt(2, 11);
        var toDo = new MazeGenerator().generate(n, n);

        //Act
        var thread = new Thread(() -> new Task3(0, 0, toDo, n - 1, n - 1).go());
        thread.start();
        thread.join();
        StringBuilder result = new StringBuilder();
        result.append("\n");
        for (int r = 0; r < toDo.grid.length; r++) {
            for (int i = 0; i < toDo.grid[0].length; i++) {
                if (toDo.grid[r][i].wallLeft) {
                    result.append("|");
                } else {
                    result.append(" ");
                }
                if (toDo.grid[r][i].wallBottom) {
                    result.append("_");
                } else {
                    result.append(" ");
                }
            }
            result.append("|");
            result.append("\n");
        }
        var toPrint = result.toString();
        var res = Task3.finalWay;

        //Assert
        if (res == null) {
            log.info(toPrint);
        } else {
            Point curr = (Point) res.getFirst();
            var to = res.iterator();
            while (true) {
                if (!to.hasNext()) {
                    break;
                } else {
                    var next = (Point) to.next();
                    assertThat(Math.abs(curr.x - next.x) + Math.abs(curr.y - next.y)).isEqualTo(1);
                    assertThat(next.x).isBetween(0, toDo.height);
                    assertThat(next.y).isBetween(0, toDo.width);
                    var i = next.x - curr.x;
                    var j = next.y - curr.y;
                    if (i < 0) {
                        assertFalse(toDo.grid[next.x][next.y].wallBottom);
                    }
                    if (i > 0) {
                        assertFalse(toDo.grid[curr.x][curr.y].wallBottom);
                    }
                    if (j > 0) {
                        assertFalse(toDo.grid[next.x][next.y].wallLeft);
                    } else if (j < 0) {
                        assertFalse(toDo.grid[curr.x][curr.y].wallLeft);
                    }
                    curr = next;
                }
            }
        }
    }
}
