
package com.fresco.abstract_test;

import java.text.DecimalFormat;
import java.util.*;


public abstract class Student {

    public abstract String result(String marksOfStudent);
    DecimalFormat df = new DecimalFormat("####0.00");

    protected String calculateCgpa(List<String[]> gradePointToCreditPoint) {
        double numerator = gradePointToCreditPoint.stream()
                .peek(x->System.out.println("number: " + x[0] + ","+x[1]))
                .map(x -> gradePoint(Integer.parseInt(x[0])) * Integer.parseInt(x[1]))
                .peek(x->System.out.println("output: " + x))
                .mapToDouble(Double::valueOf).sum();
        int denominator = gradePointToCreditPoint.stream()
                .mapToInt(x -> 5).sum();
        System.out.println(numerator );
        System.out.println( denominator);
        System.out.println(numerator / denominator);
        return String.format("%.2f", numerator / denominator);
    }

    protected boolean shouldAddMarks(String marks) {
        return !marks.split(",")[0].trim().equals("0");
    }

    protected double gradePoint(Integer mark) {
        double gradePoint = 0;
        if (mark >= 75) {
            gradePoint = gradePoint(mark, 75, 100, 9.0);
        } else if (mark >= 60) {
            gradePoint = gradePoint(mark, 60, 75, 8.0);
        } else if (mark >= 50) {
            gradePoint = gradePoint(mark, 50, 60, 7.0);
        } else if (mark >= 40) {
            gradePoint = gradePoint(mark, 40, 50, 6.0);
        }
        return gradePoint;
    }

    private double gradePoint(Integer mark, int lowerLimit, int upperLimit, double lowerGrade) {
        return (lowerGrade * 10 + ((mark - lowerLimit)*10) / (upperLimit - lowerLimit))/10.0;
    }

}