package com.fresco;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class CipherDecipher{
    /**
     * This method is used to cipher the message (normal) by following steps.
     * Swap the cases of String - UpperCase letters to LowerCase letters and viceversa.
     * Reverse the String
     * Replace the spaces of string with a star character("*")
     * Replace the characters in the even positions of the string
     * Append any integer at the last in that String
     * 
     * @param normal
     * @return the ciphered message
     */
        public String ciphering(String normal){
       StringBuilder sb = IntStream.range(0, normal.length())
            .mapToObj(i -> normal.charAt(i))
            .map(ch -> Character.isUpperCase(ch)? Character.toLowerCase(ch): 
                Character.toUpperCase(ch))
            .map(ch -> Character.isWhitespace(ch)? '*': ch)
            .collect(
                    StringBuilder::new,
                    StringBuilder::append,
                    StringBuilder::append);
       
       sb.reverse();
       StringBuilder result = new StringBuilder();
       for (int i =0; i < sb.length(); ++i) {
           char c = sb.charAt(i);
           if(i%2 ==0){
               result.append(c);
           } else{
               int n = c;
               String a = String.valueOf(n);
               result.append(a);
           }
        }
        
        result.append(3);
                
        return result.toString();
    }
    /**
     * This method is used to get the normal text by the reverse process of ciphering.
     * 
     * @param ciphered
     * @return the deciphered message
     */
    public String deciphering(String ciphered){
        StringBuilder value = new StringBuilder();
        String number = "";
        for (int i =0; i < ciphered.length() - 1; ++i) {
             char c = ciphered.charAt(i);
             if(Character.isDigit(c)){
                number += c;
             }else{
                if(!number.isEmpty()){
                    char characterFromNum = (char)Integer.valueOf(number).intValue();
                    value.append(characterFromNum);
                    number = "";
                }
                value.append(c);
             }
             
        }   
        if(!number.isEmpty()){
            char characterFromNum = (char)Integer.valueOf(number).intValue();
            value.append(characterFromNum);
        }
        
        value.reverse();
        
        StringBuilder returnV = IntStream.range(0, value.length())
            .mapToObj(i -> value.charAt(i))
            .map(ch -> ch == '*'? ' ': ch)
            .map(ch -> Character.isUpperCase(ch)? Character.toLowerCase(ch): 
                Character.toUpperCase(ch))
            .collect(
                    StringBuilder::new,
                    StringBuilder::append,
                    StringBuilder::append);
                    
        return returnV.toString();               
    }
}

public class Solution {
    
    public static void main(String[] args){
        Scanner readInput = new Scanner(System.in);
        String normal=readInput.nextLine();
        String ciphered=readInput.nextLine();
        
        CipherDecipher cipherOrDecipher = new CipherDecipher();
        System.out.println(cipherOrDecipher.ciphering(normal));
        System.out.println(cipherOrDecipher.deciphering(ciphered));
        
        
        
    }
    
}
