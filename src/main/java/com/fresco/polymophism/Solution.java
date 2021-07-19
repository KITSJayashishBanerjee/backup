package com.fresco.polymophism;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String args[] ) throws Exception {
        Scanner readInput = new Scanner(System.in);
        int startElement=readInput.nextInt();
        int endElement=readInput.nextInt();
         System.out.println("startElement: " + startElement);
          System.out.println("endElement: " + endElement);
        
        Parent prime = new ChildOne(startElement, endElement);
        Parent happy = new ChildTwo(startElement, endElement);
        
        System.out.println(prime.filter());
        System.out.println(happy.filter());
    }
}

class Parent {
    protected int startElement;
    protected int endElement;
    
    public Parent(int startElement, int endElement){
        this.startElement = startElement;
        this.endElement = endElement;
    }
    
    public String filter(){
        return null;
    }
    
    
}

class ChildOne extends Parent{
    
    public ChildOne(int startElement, int endElement){
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

class ChildTwo extends Parent{
    
    public ChildTwo(int startElement, int endElement){
        super(startElement, endElement);
    }
        
    public String filter(){
        return IntStream.range(startElement, endElement)
        .filter(this::isHappy).mapToObj(x -> String.valueOf(x)).collect(Collectors.joining(" "));
    }
    
    boolean isHappy(int number){
        int sum = number;
        while(sum != 1 && sum != 4){
            sum = sumOfSquaresOfDigits(sum);
        }
        return sum == 1;
    }
    
    int sumOfSquaresOfDigits(int number){
        int sum = 0;
        while(number > 0){
            int remainder = number % 10;
            sum += remainder * remainder;
            number = number / 10;
        }
        return sum;
    }
}