package com.fresco.generics;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudentList<T extends String> {

    private ArrayList<T> students = new ArrayList<>();

    public void addElement(T element) {
        students.add(element);
    }

    public void removeElement(T element) {
        students.remove(element);
    }

    public T getElement(int index) {
        return students.get(index);
    }

    public String beginsWith(String input) {
        return students.stream().filter(student -> student.toLowerCase().startsWith(input)).collect(Collectors.joining("\n")) + "\n";
    }

    public String bloodGroupOf(String[] bloodGroups, String input) {
        return IntStream.range(0, bloodGroups.length)
                .filter(i -> input.equals(bloodGroups[i]))
                .mapToObj(students::get).collect(Collectors.joining("\n")) + "\n";
    }

    public int thresholdScore(int input) {
        return 0;
    }

}
