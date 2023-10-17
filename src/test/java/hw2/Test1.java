package hw2;
import edu.hw2.Task1.Addition;
import edu.hw2.Task1.Constant;
import edu.hw2.Task1.Division;
import edu.hw2.Task1.Exponent;
import edu.hw2.Task1.Expr;
import edu.hw2.Task1.Multiplication;
import edu.hw2.Task1.Negate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test1 {

    private final static Logger LOGGER = LogManager.getLogger();
    @Test
    @DisplayName("Возведение в дробную степень отрицательного числа должно выбрасывать исключение")
    void ExponentTest() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));
        assertThat(res.evaluate()).isEqualTo(37);

        var minusSqrt = new Constant(-Math.sqrt(8));
        var power = new Constant(-2);
        var mustBeZero = Math.abs(new Exponent(minusSqrt, power).evaluate() - 0.125);
        assertThat(mustBeZero).isLessThanOrEqualTo(1e-6);

        assertThat(new Exponent(new Constant(0), 0).evaluate()).isEqualTo(1);
        try{
            var minusSeven=new Constant(-7);
            var power1 = - 3.5;
            new Exponent(minusSeven, power1).evaluate();
            LOGGER.info("Дробная степень отрицательного числа должна вызвать исключение");
            assertThat(false).isTrue();
        } catch(IllegalArgumentException e){
            assertThat(true).isTrue();
        }
    }

    @Test
    @DisplayName("Деление на 0 также приводит к исключению")
    void DivisionTest() {
        var argOne = new Constant(-42);
        var argTwo = new Constant(-6);
        assertThat(new Division(argOne, argTwo).evaluate()).isEqualTo(7);

        try{
            new Division(argTwo, new Addition(argOne, new Constant(42))).evaluate();
            LOGGER.info("Деление на 0 должно вызвать исключение");
            assertThat(-6).isEqualTo(6);
        }catch (IllegalArgumentException e){
            assertThat(-6).isEqualTo(-6);
        }
    }
}
