package com.fresco.generics;


import java.util.ArrayList;


public class ScoreList<T extends Number> {

    private ArrayList<T> scores = new ArrayList<>();

    public void addElement(T element) {
        scores.add(element);
    }

    public int thresholdScore(int input) {
        return (int) scores.stream().mapToInt(Number::intValue).filter(i -> input <= i).count();
    }

    public void removeElement(T element) {
        scores.remove(element);
    }

    public T getElement(int element) {
        return scores.get(element);
    }

    public double averageValues() {
        double average = scores.stream().mapToDouble(Number::doubleValue).average().orElse(0);
        //return new BigDecimal(average).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return average;
    }
}

