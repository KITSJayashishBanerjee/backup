package com.fresco.lamdafunc;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaFn {

    public List<Long> functionalProgramming(List<String> listOfIntegers) {
        List<Long> outputList = listOfIntegers.stream().map(Long::parseLong)
                .filter(this::isKaprekarNumber)
                .collect(Collectors.toList());
        return outputList;
    }

    private boolean isKaprekarNumber(long i) {
        if(i == 1)
            return true;
        boolean isKaprekarNumber = false;
        int n = numberOfDigits(i);
        long sq = i * i;
        int nOfSq = numberOfDigits(sq);

        for (int digits = n; digits < nOfSq && !isKaprekarNumber; digits++) {
            int pow = (int) Math.pow(10, digits);

            long sumOfToParts = (sq % pow + sq / pow);
            isKaprekarNumber = isKaprekarNumber || sumOfToParts == i;
        }

        return isKaprekarNumber;
    }

    int numberOfDigits(long i) {
        int count = 0;
        while (i != 0) {
            i = i / 10;
            count++;
        }
        return count;
    }

}