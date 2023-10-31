package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class ValidationError {


    public final String field;
    public final String message;
    public ValidationError(String futureMessage, String fieldName){
            message = futureMessage;
            field = fieldName;
    }

    public static Set<ValidationError> checkAnimal(Animal toCheck) {
        HashSet<ValidationError> result = new HashSet<>();
        String out = "Value out of bounds.";
        if (toCheck.weight() <= 0) {
            result.add(new ValidationError(out+"Must be more than zero.","weight"));
        }
        if (toCheck.height() <= 0) {
            result.add(new ValidationError(out+"Must be more than zero.","height"));
        }
        if (toCheck.age() < 0) {
            result.add(new ValidationError(out+"Must more than zero.","age"));
        }
        if (toCheck.name() == null || toCheck.name().equals("")) {
            result.add(new ValidationError("Mustn't be empty or null.","name"));
        }
        return result;
    }
}
