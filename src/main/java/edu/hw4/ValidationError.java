package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class ValidationError {


    public final String field;
    public final String message;

    public ValidationError(String futureMessage, String fieldName) {
            message = futureMessage;
            field = fieldName;
    }

    public static Set<ValidationError> checkAnimal(Animal toCheck) {
        HashSet<ValidationError> result = new HashSet<>();
        String out = "Value out of bounds.";
        String second = "Must be more than zero.";
        if (toCheck.weight() <= 0) {
            result.add(new ValidationError(out + second, "weight"));
        }
        if (toCheck.height() <= 0) {
            result.add(new ValidationError(out + second, "height"));
        }
        if (toCheck.age() < 0) {
            result.add(new ValidationError(out + second, "age"));
        }
        if (toCheck.name() == null || toCheck.name().equals("")) {
            result.add(new ValidationError("Mustn't be empty or null.", "name"));
        }
        return result;
    }
}
