package edu.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;


public class Homework4Functions {


    private Homework4Functions() {

    }

    public static List<Animal> sortByHeight(@NotNull Animal[] all) {
        ArrayList<Animal> curr = new ArrayList<Animal>(Arrays.stream(all).toList());
        curr.sort(Comparator.comparingInt(Animal::height));
        return  curr;
    }

    public static List<Animal> sortByWeight(@NotNull Animal[] all) {
        ArrayList<Animal> curr = new ArrayList<Animal>(Arrays.stream(all).toList());
        curr.sort((a1, a2) -> a2.weight() - a1.weight());
        return curr;
    }

    public static Map<Animal.Type, Integer> groupBySpecies(@NotNull Animal[] all) {
        ArrayList<Animal> curr = new ArrayList<Animal>(Arrays.stream(all).toList());
        var c = curr.stream().collect(Collectors.groupingBy(r -> r.type()));
        var result = new HashMap<Animal.Type, Integer>();
        for (Animal.Type type:c.keySet()) {
            result.put(type, c.get(type).size());
        }
        return result;
    }

    public static Animal longestName(@NotNull Animal[] all) {
        ArrayList<Animal> curr = new ArrayList<Animal>(Arrays.stream(all).toList());
        if (all.length == 0) {
            return null;
        }
        curr.sort((a1, a2) -> a2.name().length() - a1.name().length());
        return curr.get(0);
    }

    public static Animal.Sex maleOrFemale(@NotNull Animal[] all) {
        var one = Arrays.stream(all).filter(r -> r.sex() == Animal.Sex.F).count();
        var two = Arrays.stream(all).filter(r -> r.sex() == Animal.Sex.M).count();
        if (one > two) {
            return Animal.Sex.F;
        } else {
            return Animal.Sex.M;
        }
    }

    public static Map<Animal.Type, Animal> biggestOfEachSpecie(@NotNull Animal[] all) {
        var c = Arrays.stream(all).toList().stream().collect(Collectors.groupingBy(Animal::type));
        var result = new HashMap<Animal.Type, Animal>();
        for (Animal.Type type : c.keySet()) {
            var curr = c.get(type).stream().sorted((animal1, animal2) -> animal2.weight() - animal1.weight()).toList();
            result.put(type, curr.getFirst());
        }
        return  result;
    }

    public static Animal getOldest(@NotNull Animal[] all, int k) {
        if (k <= 0 || k > all.length)  {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(all).sorted((animal1, animal2) -> animal2.age() - animal1.age()).toList().get(k - 1);
    }

    public static Optional<Animal> lessThanK(@NotNull Animal[] all, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException();
        }
        var streamOf = Stream.of(all);
        var notfiltered = Arrays.stream(all).filter(animal -> animal.height() < k);
        var filtered = notfiltered.sorted((one, two) -> two.weight() - one.weight());
        if (filtered.toList().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(streamOf.toList().getFirst());
        }
    }

    public static Integer sumOfPaws(@NotNull Animal[] all) {
        return Arrays.stream(all).mapToInt(Animal::paws).sum();
    }

    public static List<Animal> pawsNotEqualToAge(@NotNull Animal[] all) {
        return Arrays.stream(all).filter(animal -> animal.paws() != animal.age()).toList();
    }

    public static List<Animal> whoCanBite(@NotNull Animal[] all) {
        var notRes = Arrays.stream(all).filter(animal -> animal.bites());
        notRes = notRes.filter(animal -> animal.height() > Math.pow(2 * 2 * 2 + 2, 2));
        return notRes.toList();
    }

    public static Integer moreThan(@NotNull Animal[] all) {
        return Math.toIntExact(Arrays.stream(all).filter(animal -> animal.weight() > animal.height()).count());
    }

    public static List<Animal> threeWordsInName(@NotNull Animal[] all) {
        return Arrays.stream(all).filter(animal -> animal.name().split(" ").length > 2).toList();
    }

    public static Boolean dogHigher(@NotNull Animal[] all, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException();
        }
        var res = Arrays.stream(all).filter(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
        return res.count() != 0;
    }

    public static Integer sumOfWeights(@NotNull Animal[] all,  int k, int i) {
        if (k <= 0 || i <= 0 || i < k) {
            throw new IllegalArgumentException();
        }
        var res = Arrays.stream(all).filter(animal -> animal.age() >= k && animal.age() <= i);
        return res.mapToInt(Animal::weight).sum();
    }

    public static List<Animal> sortBySpeciesThen(@NotNull Animal[] all) {
        return Arrays.stream(all).sorted((x, y) -> {
            if (x.type() != y.type()) {
                return x.type().compareTo(y.type());
            }
            if (x.sex() != y.sex()) {
                return x.sex().compareTo(y.sex());
            }

            return x.name().compareTo(y.name());
        }).toList();
    }

    public static Boolean spidersVersusDogs(@NotNull Animal[] allAnimals) {
        if (Arrays.stream(allAnimals).filter(animal -> animal.type() == Animal.Type.DOG).count() == 0) {
            return false;
        }
        if (Arrays.stream(allAnimals).filter(animal -> animal.type() == Animal.Type.SPIDER).count() == 0) {
            return false;
        }
        var newAll = Stream.of(allAnimals);
        var stream = Stream.of(allAnimals);
        var first = Arrays.stream(allAnimals).filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites());
        var second = Arrays.stream(allAnimals).filter(animal -> animal.type() == Animal.Type.DOG && animal.bites());
        var forSpiders = first.count();
        var forDogs = second.count();
        var spidersResult = (double) forSpiders / stream.filter(animal -> animal.type() == Animal.Type.SPIDER).count();
        var dogsResult = (double) forDogs / newAll.filter(animal -> animal.type() == Animal.Type.DOG).count();
        return  spidersResult > dogsResult;
    }

    public static Optional<Animal> biggestFish(@NotNull List<Animal>...lists) {
        Optional<Animal> result = Optional.empty();
        for (int j = 0; j < lists.length; j++) {
            var not = lists[j].stream().filter(x -> x.type() == Animal.Type.FISH);
            var curr = not.sorted((x, y) -> y.weight() - x.weight()).toList();
            if (curr.isEmpty()) {
                continue;
            } else if (result.isEmpty() || result.get().weight() < curr.getFirst().weight()) {
                result = Optional.of(curr.getFirst());
            }
        }
        return result;
    }

    public static Map<String, Set<ValidationError>> checkFields(@NotNull Animal[] all) {
        var reallyallNames = Arrays.stream(all).collect(Collectors.toMap(Animal::name, ValidationError::checkAnimal));
        var allNames = reallyallNames.entrySet().stream().filter(x -> !x.getValue().isEmpty());
        return  allNames.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, String> moreUnderstandable(@NotNull Animal[] all) {
        var curr = checkFields(all).entrySet().stream();
        //var notYet =
        var result = curr.collect(Collectors.toMap(r -> r.getKey(), r -> make(nothing(r.getValue().stream()))));
        return  result;
    }

    public static List<String> nothing(Stream<ValidationError> input) {
        return input.map(y -> y.field + ":" + y.message).toList();
    }

    public static String make(List<String> input) {
        return String.join(" ", input);
    }
}
