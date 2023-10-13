package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class Test3 {

    @Test
    @DisplayName("Работа с null-значениями")
    void nullTest() {
        try {
            boolean a=Task3.isNestable(null,null);
            assertThat(true).isEqualTo(false);
        } catch (IllegalArgumentException e) {
            assertThat(true).isEqualTo(true);
        }

        try {
            int[] a={0, 21450000};
            boolean res=Task3.isNestable(null,a);
            assertThat(false).isTrue();
        } catch (IllegalArgumentException e) {
            assertThat(true).isTrue();
        }

        try {
            int[] a={0, 222380};
            boolean res=Task3.isNestable(a,null);
            assertThat(false).isTrue();
        } catch (IllegalArgumentException e) {
            assertThat(true).isTrue();
        }
    }

    @Test
    @DisplayName("Работа с крайними случаями- пустыми массивами, равенствами минимумов и максимумов ")
    void edgeTest() {
        //given
        int[] zeroArray=new int[0];
        int[] one = {9, 8, -1, 36};
        int[] two = {9, 9, 8, 7, 4, 36, 5, 8,  8, 9, 3, -67686868};
        int[] three = {788999, 68,-1, 75, 78};
        //when
        boolean[] result = new boolean[4];
        result[0] = Task3.isNestable(zeroArray, one);
        result[1] = Task3.isNestable(three, zeroArray);
        result[2] = Task3.isNestable(one, two);
        result[3] = Task3.isNestable(one, three);
        //then
        assertThat(result).containsExactly(false, false, false, false);
    }

    @Test
    @DisplayName("Вложенность маленького отрезка чисел в большой и большого отрезка чисел в маленький")
    void simpleTest() {
        //given
        int[] one = {9, -1, 23, 16, 36};
        int[] two = {35, -67686868, 8 , 9, 34 , 8};
        int[] three = {-348, -340};
        //when
        boolean[] result = new boolean[3];
        result[0] = Task3.isNestable(one, two);
        result[1] = Task3.isNestable(two, three);
        result[2] = Task3.isNestable(three,two);
        //then
        assertThat(result).containsExactly(false, false,true);
    }
}
