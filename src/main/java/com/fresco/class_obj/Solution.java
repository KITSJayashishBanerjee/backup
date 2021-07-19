package com.fresco.class_obj;

import java.io.*;
import java.util.*;

class Register {
    
    private static Register register = null;
    Map<String, Double> itemToPrice = new HashMap<String, Double>(){
        {
            put("apple", 2.0);
            put("orange", 1.5);
            put("mango", 1.2);
            put("grape", 1.0);
        }
    };
    
    public static Register getInstance(){
        if(register == null){
            register = new Register();
        }
        return register;
    }
    /*
     * Complete the 'getTotalBill' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts MAP itemDetails as parameter.
     */

    public String getTotalBill(Map<String,Integer> itemDetails) {
       
        return String.valueOf(itemDetails.entrySet().stream()
        .map(x -> itemToPrice.get(x.getKey()) * x.getValue())
        .mapToDouble(Double::valueOf).sum());

    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        
        Scanner readInput = new Scanner(System.in);        
        String[] input=readInput.nextLine().split(" ");                
        Map<String,Integer> myItems=new HashMap<String,Integer>();
        for(int i=0;i<input.length;i+=2){
          myItems.put(input[i],Integer.parseInt(input[i+1]));	
        }
        Register regObj = Register.getInstance();        
        System.out.println(regObj.getTotalBill(myItems));
        readInput.close();
        
    }
}