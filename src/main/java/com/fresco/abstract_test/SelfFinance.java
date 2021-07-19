
package com.fresco.abstract_test;

import java.util.*;

public class SelfFinance extends Student {

    public String result(String marksOfStudent) {
        List<String[]> entries = new ArrayList<>();
        String[] marks = marksOfStudent.split("\\|");
        Arrays.stream(marks[0].trim().split(",")).map(x -> x.trim().split(" "))
                .forEach(mark -> entries.add(mark));

        if (shouldAddMarks(marks[1])) {
            String[] mark = marks[1].trim().split(",");
            entries.add(new String[]{mark[1], mark[2]});
        }

        return calculateCgpa(entries);
    }

}
