package edu.hw4;

import edu.hw4.Animal;
import edu.hw4.Homework4Functions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TestsForAll {
    @Test
    @DisplayName("Упорядочивание животных происходит строго по росту")
    void Test1() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 16, false);
        Animal second = new Animal("second", Animal.Type.DOG, Animal.Sex.M, 1, 35, 10, true);
        Animal third = new Animal("killka", Animal.Type.FISH, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 5, 82, 14, true);
        Animal [] all = new Animal[]{first, second, third, last};
        assertThat(Homework4Functions.sortByHeight(all).toArray()).containsExactly(third, first, second, last);
    }

    @Test
    @DisplayName("Упорядочивание животных происходит строго по весу")
    void Test2() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 16, false);
        Animal second = new Animal("second", Animal.Type.DOG, Animal.Sex.M, 1, 35, 10, true);
        Animal third = new Animal("killka", Animal.Type.FISH, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 5, 82, 14, true);
        Animal [] all = new Animal[]{first, second, third, last};
        assertThat(Homework4Functions.sortByWeight(all).toArray()).containsExactly(first, last, second, third);
    }

    @Test
    @DisplayName("Словарь содержит в качестве ключей только те виды животных, которые были в массиве данных")
    void Test3() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 16, false);
        Animal second = new Animal("second", Animal.Type.DOG, Animal.Sex.M, 1, 35, 10, true);
        Animal third = new Animal("killka", Animal.Type.CAT, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 5, 82, 14, true);
        Animal [] all = new Animal[]{first, second, third, last};
        var result = Homework4Functions.groupBySpecies(all);
        assertFalse(result.containsKey(Animal.Type.SPIDER));
        assertFalse(result.containsKey(Animal.Type.FISH));
        assertThat(result.get(Animal.Type.DOG)).isEqualTo(1);
        assertThat(result.get(Animal.Type.CAT)).isEqualTo(2);
        assertThat(result.get(Animal.Type.BIRD)).isEqualTo(1);
    }

    @Test
    @DisplayName("Словарь содержит в качестве ключей только те виды животных, которые были в массиве данных")
    void Test4() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.M, 16, 32, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 5, 82, 14, true);
        Animal [] all = new Animal[]{first, second, third, last};
        assertThat(Homework4Functions.longestName(all).name()).isEqualTo("notfirst");

        assertThat(Homework4Functions.longestName(new Animal[0])).isNull();
    }

    @Test
    @DisplayName("Если количество самок и самцов одинаково - метод делает вывод, что самцов больше")
    void Test5() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 32, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 5, 74, 14, true);
        Animal [] all = new Animal[]{first, second, third, last};
        assertThat(Homework4Functions.maleOrFemale(all)).isEqualTo(Animal.Sex.M);

        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 82, 14, true);
        all = new Animal[] {first, second, third, last, anotherOne};
        assertThat(Homework4Functions.maleOrFemale(all)).isEqualTo(Animal.Sex.F);
    }

    @Test
    @DisplayName("Словарь содержит в качестве ключей только те виды животных, которые были в массиве данных")
    void Test6() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 32, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 5, 74, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 82, 18, true);
        Animal lastOne = new Animal("last", Animal.Type.CAT, Animal.Sex.M, 5, 45, 14, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};
        var result = Homework4Functions.biggestOfEachSpecie(all);

        assertFalse(result.containsKey(Animal.Type.FISH));
        assertThat(result.get(Animal.Type.SPIDER)).isEqualTo(third);
        assertThat(result.get(Animal.Type.DOG)).isEqualTo(second);
        assertThat(result.get(Animal.Type.CAT)).isEqualTo(anotherOne);
        assertThat(result.get(Animal.Type.BIRD)).isEqualTo(first);
    }

    @Test
    @DisplayName("Нельзя получить k-е самое старое животное, если длина массива меньше k или k меньше либо равно нулю")
    void Test7() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 32, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 74, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 82, 18, true);
        Animal lastOne = new Animal("last", Animal.Type.CAT, Animal.Sex.M, 5, 45, 14, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};
        try {
            Homework4Functions.getOldest(all,7);
            fail("k выходит за пределы массива");
        } catch (IllegalArgumentException exception) {
            //assertTrue(true);
        }

        try {
            Homework4Functions.getOldest(all,0);
            fail("k выходит за пределы массива");
        } catch (IllegalArgumentException exception) {
            //assertTrue(true);
        }

        var result = Homework4Functions.getOldest(all, 3);
        assertThat(result).isEqualTo(third);
    }

    @Test
    @DisplayName("Значение верхнего предела роста k должно быть положительным")
    void Test8() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 32, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 74, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 82, 18, true);
        Animal lastOne = new Animal("last", Animal.Type.CAT, Animal.Sex.M, 5, 45, 14, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};
        try {
            Homework4Functions.lessThanK(all,0);
            fail("Рост всегда положительный");
        } catch (IllegalArgumentException exception) {
        }

    }

    @Test
    @DisplayName("Значение верхнего предела роста k должно быть положительным")
    void Test8dot1() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 32, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 74, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 82, 18, true);
        Animal lastOne = new Animal("last", Animal.Type.CAT, Animal.Sex.M, 5, 45, 14, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};

        var result = Homework4Functions.lessThanK(all, 2);
        assertThat(result).isEqualTo(Optional.empty());

        result = Homework4Functions.lessThanK(all, 50);
        assertThat(result.get()).isEqualTo(first);
    }

    @Test
    @DisplayName("В случае пустого массива сумма количества лап равна нулю")
    void Test9() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 32, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 74, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 82, 18, true);
        Animal lastOne = new Animal("last", Animal.Type.CAT, Animal.Sex.M, 5, 45, 14, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};

        var result = Homework4Functions.sumOfPaws(all);
        assertThat(result).isEqualTo(26);

        result = Homework4Functions.sumOfPaws(new Animal[0]);
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Количество животных, возраст которых не равен количеству лап")
    void Test10() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 32, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 74, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 82, 18, true);
        Animal lastOne = new Animal("last", Animal.Type.CAT, Animal.Sex.M, 5, 45, 14, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};

        var result = Homework4Functions.pawsNotEqualToAge(all);
        assertFalse(result.contains(first));
        assertTrue(result.contains(second));
        assertTrue(result.contains(third));
        assertTrue(result.contains(last));
        assertTrue(result.contains(anotherOne));
        assertTrue(result.contains(lastOne));
    }

    @Test
    @DisplayName("Рост животного, прошедшего отбор, строго больше ста")
    void Test11() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 112, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 132, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 100, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 82, 18, true);
        Animal lastOne = new Animal("last", Animal.Type.CAT, Animal.Sex.M, 5, 215, 14, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};

        var result = Homework4Functions.whoCanBite(all);
        assertTrue(result.contains(second));
        assertTrue(result.contains(lastOne));
        assertFalse(result.contains(last));
    }

    @Test
    @DisplayName("Вес животного, прошедшего отбор, должен быть строго больше его роста")
    void Test12() {
        Animal first = new Animal("first", Animal.Type.BIRD, Animal.Sex.M, 2, 112, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 132, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 100, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 100, 18, true);
        Animal lastOne = new Animal("last", Animal.Type.CAT, Animal.Sex.M, 5, 215, 220, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};

        var result = Homework4Functions.moreThan(all);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Имя животного, прошедшего отбор, должно содержать минимум три слова")
    void Test13() {
        Animal first = new Animal("first animal", Animal.Type.BIRD, Animal.Sex.M, 2, 112, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 132, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 100, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 100, 18, true);
        Animal lastOne = new Animal("really last animal", Animal.Type.CAT, Animal.Sex.M, 5, 215, 220, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};

        var result = Homework4Functions.threeWordsInName(all);
        assertThat(result.stream().filter(r -> r.name().split(" ").length < 3).count()).isEqualTo(0);
        assertThat(result.toArray()).containsExactly(lastOne);
    }

    @Test
    @DisplayName("Нижняя граница роста собаки строго больше нуля")
    void Test14() {
        Animal first = new Animal("first animal", Animal.Type.BIRD, Animal.Sex.M, 2, 112, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 132, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 100, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 100, 18, true);
        Animal lastOne = new Animal("really last animal", Animal.Type.CAT, Animal.Sex.M, 5, 215, 220, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};

        try {
            Homework4Functions.dogHigher(all,0);
            fail("Рост всегда положительный");
        } catch (IllegalArgumentException exception) {
        }

        var result = Homework4Functions.dogHigher(all,45);
        assertTrue(result);
    }

    @Test
    @DisplayName("Нижняя граница возраста k меньше либо равна верхней границе возраста i. i и k строго больше нуля")
    void Test15() {
        Animal first = new Animal("first animal", Animal.Type.FISH, Animal.Sex.M, 2, 112, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 132, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 100, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 100, 18, true);
        Animal lastOne = new Animal("really last animal", Animal.Type.CAT, Animal.Sex.M, 5, 215, 220, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};

        try {
            Homework4Functions.sumOfWeights(all,-3,1);
            fail("Возраст всегда положительный");
        } catch (IllegalArgumentException exception) {
        }

        try {
            Homework4Functions.sumOfWeights(all,2,1);
            fail("Верхняя граница больше либо равна нижней");
        } catch (IllegalArgumentException exception) {
        }

        var result = Homework4Functions.sumOfWeights(all, 6, 10);
        assertThat(result).isEqualTo(19);
    }

    @Test
    @DisplayName("Если вид животных одинаков, сравнивается сначала их пол, если пол одинаков - сравниваются имена")
    void Test16() {
        Animal first = new Animal("first animal", Animal.Type.FISH, Animal.Sex.M, 2, 112, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 132, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, false);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 100, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 100, 18, true);
        Animal lastOne = new Animal("really last animal", Animal.Type.CAT, Animal.Sex.M, 5, 215, 220, true);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};

        var result = Homework4Functions.sortBySpeciesThen(all);
        assertThat(result.toArray()).containsExactly(last, lastOne, anotherOne, second, first, third);
    }

    @Test
    @DisplayName("Если в массиве нет пауков или собак - возвращается false. Иначе сравнивается доля кусающих собак и доля кусающих пауков.")
    void Test17() {
        Animal first = new Animal("first animal", Animal.Type.FISH, Animal.Sex.M, 2, 112, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 132, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, true);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 100, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 100, 18, true);
        Animal lastOne = new Animal("really last animal", Animal.Type.DOG, Animal.Sex.M, 5, 215, 220, false);
        //Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};

        var firstRes = Homework4Functions.spidersVersusDogs(new Animal[]{first, last, anotherOne});
        assertFalse(firstRes);
    }

    @Test
    @DisplayName("Если в массиве нет пауков или собак - возвращается false. Иначе сравнивается доля кусающих собак и доля кусающих пауков.")
    void Test17dot1() {
        Animal first = new Animal("first animal", Animal.Type.FISH, Animal.Sex.M, 2, 112, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 132, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, true);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 100, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.CAT, Animal.Sex.F, 5, 100, 18, true);
        Animal lastOne = new Animal("really last animal", Animal.Type.DOG, Animal.Sex.M, 5, 215, 220, false);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};
        var lastRes = Homework4Functions.spidersVersusDogs(all);
        assertTrue(lastRes);
    }

    @Test
    @DisplayName("Если ни в одном из списков нет рыб, возвращается Optional.Empty. Иначе возвращается самая тяжелая")
    void Test18() {
        Animal first = new Animal("first animal", Animal.Type.FISH, Animal.Sex.M, 2, 112, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 132, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, true);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, 8, 100, 14, true);
        Animal anotherOne = new Animal("anna", Animal.Type.FISH, Animal.Sex.F, 5, 100, 18, true);
        Animal lastOne = new Animal("really last animal", Animal.Type.FISH, Animal.Sex.M, 5, 215, 22, false);

        var firstRes = Homework4Functions.biggestFish(Arrays.stream(new Animal[]{second, third}).toList(), Arrays.stream(new Animal[]{last}).toList());
        assertThat(firstRes).isEmpty();

        Animal [] argOne = new Animal[]{second, third, lastOne};
        Animal [] argTwo = new Animal[]{last, anotherOne};
        Animal [] argThree = new Animal[]{first};
        var lastRes = Homework4Functions.biggestFish(Arrays.stream(argOne).toList(), Arrays.stream(argTwo).toList(), Arrays.stream(argThree).toList());
        assertThat(lastRes.get()).isEqualTo(lastOne);
    }

    @Test
    @DisplayName("Для каждого из имен животных составляется множество ошибок в его полях")
    void Test19() {
        Animal first = new Animal("first animal", Animal.Type.FISH, Animal.Sex.M, 2, 0, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 132, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, true);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, -8, 100, 14, true);
        Animal anotherOne = new Animal("", Animal.Type.FISH, Animal.Sex.F, 1, 100, 18, true);
        Animal lastOne = new Animal("really last animal", Animal.Type.FISH, Animal.Sex.M, 5, 215, 22, false);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};
        var allErrors = Homework4Functions.checkFields(all);

        assertTrue(allErrors.containsKey("first animal"));
        assertTrue(allErrors.containsKey(""));
        assertTrue(allErrors.containsKey("john"));
        assertFalse(allErrors.containsKey("notfirst"));
        assertFalse(allErrors.containsKey("noname"));
        assertFalse(allErrors.containsKey("really last animal"));

        assertThat(allErrors.get("first animal").stream().toList().getFirst().field).isEqualTo("height");
        assertThat(allErrors.get("first animal").size()).isEqualTo(1);
        assertThat(allErrors.get("john").stream().toList().getFirst().field).isEqualTo("age");
        assertThat(allErrors.get("john").size()).isEqualTo(1);
        assertThat(allErrors.get("").stream().toList().getFirst().field).isEqualTo("name");
        assertThat(allErrors.get("").size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Проверка, что в объединённых строках есть названия ошибочных полей")
    void Test20() {
        Animal first = new Animal("first animal", Animal.Type.FISH, Animal.Sex.M, 2, 0, 16, false);
        Animal second = new Animal("notfirst", Animal.Type.DOG, Animal.Sex.F, 16, 132, 10, true);
        Animal third = new Animal("noname", Animal.Type.SPIDER, Animal.Sex.F, 6, 9, 5, true);
        Animal last = new Animal("john", Animal.Type.CAT, Animal.Sex.M, -8, 100, 14, true);
        Animal anotherOne = new Animal("", Animal.Type.FISH, Animal.Sex.F, 1, 100, 18, true);
        Animal lastOne = new Animal("really last animal", Animal.Type.FISH, Animal.Sex.M, 5, 215, 22, false);
        Animal [] all = new Animal[] {first, second, anotherOne, third, last, lastOne};
        var allErrors = Homework4Functions.moreUnderstandable(all);

        assertTrue(allErrors.containsKey("first animal"));
        assertTrue(allErrors.containsKey(""));
        assertTrue(allErrors.containsKey("john"));
        assertFalse(allErrors.containsKey("notfirst"));
        assertFalse(allErrors.containsKey("noname"));
        assertFalse(allErrors.containsKey("really last animal"));

        assertTrue(allErrors.get("first animal").contains("height:"));
        assertTrue(allErrors.get("john").contains("age:"));
        assertTrue(allErrors.get("").contains("name:"));
    }
}
