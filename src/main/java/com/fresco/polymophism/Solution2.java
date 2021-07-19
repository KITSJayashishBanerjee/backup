package com.fresco.polymophism;

import java.util.*;
import java.util.stream.*;

public class Solution2 {
    public static void main(String args[] ) throws Exception {
        Scanner readInput = new Scanner(System.in);
        int startElement=readInput.nextInt();
        int endElement=readInput.nextInt();
        
        Parent prime = new ChildOne(startElement, endElement);
        Parent happy = new ChildTwo(startElement, endElement);
        
        System.out.println(prime.filter());
        System.out.println("sadfasdfsdf"+happy.filter());
    }
}

class Parent2 {
    protected int startElement;
    protected int endElement;
    
    public Parent2(int startElement, int endElement){
        this.startElement = startElement;
        this.endElement = endElement;
    }
    
    public String filter(){
        return null;
    }
    
    
}

class ChildOne2 extends Parent2{
    
    public ChildOne2(int startElement, int endElement){
        super(startElement, endElement);
    }
        
    public String filter(){
        return IntStream.range(startElement, endElement)
        .filter(this::isPrime).mapToObj(x -> String.valueOf(x)).collect(Collectors.joining(" "));
        
       
    }
    
    boolean isPrime(int number){
        return number != 1 && !IntStream.rangeClosed(2, number/2).anyMatch(i -> number%i == 0);
    }
}

class ChildTwo2 extends Parent2{
    
    public ChildTwo2(int startElement, int endElement){
        super(startElement, endElement);
    }
        
    public String filter(){
        return IntStream.range(startElement, endElement)
        .filter(this::isHappy).mapToObj(x -> String.valueOf(x)).collect(Collectors.joining(" "));
    }
    
    boolean isHappy(int number){
        int sum = number;
        while(sum >= 10){
            sum = sumOfSquaresOfDigits(number);
        }
        return sum == 1;
    }
    
    int sumOfSquaresOfDigits(int number){
        return String.valueOf(number).chars().map(x -> x * x).sum();
    }
}