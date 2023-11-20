package hw2;
import edu.hw2.Task4;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class Test4 {

    @Test
    @DisplayName("Метод FindCallingInfo ищет в стеке вызовов предшествующий ему вызов")
    void checkCallingInfo(){

        var curr = Task4.findCallingInfo();
        assertThat(curr.className()).isEqualTo(new Test4().getClass().getName());
        assertThat(curr.methodName()).isEqualTo("checkCallingInfo");
    }

    @Test
    @DisplayName("Метод FindCallingInfo работает независимо от других методов в стеке")
    void moreThanTwoMethods(){
        checkCallingInfo();
    }
}
