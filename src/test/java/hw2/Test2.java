package hw2;

import edu.hw2.Rectangle;
import edu.hw2.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test2 {

    @Test
    @MethodSource("Стороны квадрата не могут быть разной длины, поэтому меньшая из данных сторон должна быть автоматически дополнена до большей")
    void squareArea() {
        Square square1 = new Square();
        square1.setParams(2,3);
        assertThat(square1.area()).isEqualTo(9);
    }

    @Test
    @MethodSource("Прямоугольник не является квадратом")
    void RectangleIsNotSquare() {
        try {
            Rectangle rect1 = new Rectangle();
            var newSq = (Square) rect1;
        } catch(ClassCastException right) {
            assertThat(true).isTrue();
        }
    }

    @Test
    @MethodSource("Квадрат - это прямоугольник, upcast должен быть возможен")
    void SquareIsRectangle() {
        try {
            Square sq1 = new Square();
            var upcast = (Rectangle) sq1;
            assertThat(true).isTrue();
        } catch(ClassCastException right) {
            assertThat(false).isTrue();
        }
    }
}
