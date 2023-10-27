package edu.hw3;

import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;



public class Task5 {

    private Task5() {

    }

    public static Contact[] parseContacts(String[] allNames, @NotNull String descendingOrNo) {
            if (allNames == null || allNames.length == 0) {
                return new Contact[0];
            }
            var descendingValue = "desc";
            if (!descendingOrNo.equalsIgnoreCase("asc") && !descendingOrNo.equalsIgnoreCase(descendingValue)) {
                throw new IllegalArgumentException();
            }
            List<String> curr  = Arrays.stream(allNames).sorted(new ContactsComparer()).toList();
            if (descendingOrNo.equalsIgnoreCase(descendingValue)) {
                curr = curr.reversed();
            }
            return  curr.stream().map(Contact::new).toArray(size -> new Contact[size]);
    }

    public static String[] toRightOrder(String[] fullName) {
        if (fullName.length == 1) {
            return fullName;
        } else {
            var temp = new String(fullName[1]);
            fullName[1] = fullName[0];
            fullName[0] = temp;
        }

        return fullName;
    }



}
