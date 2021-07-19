package com.fresco.conditional;

import java.io.*;
import java.math.*;
import java.util.*;


class Result {
    Map<String, Double> itemToPrice = new HashMap<>(){
        {
            put("apple", 2.0);
            put("orange", 1.5);
            put("mango", 1.2);
            put("grapes", 1.0);
        }
    };

    /*
     * Complete the 'calculateGrade' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY students_marks as parameter.
     */

    public static String[] calculateGrade(int[][] students_marks) {
        String[] grades = new String[students_marks.length];
        for(int i = 0; i < students_marks.length; i++){
            int avg = Arrays.stream(students_marks[i]).sum()/students_marks[i].length;
            String grade;
            if(avg >=90){
                grade = "A+";
            }else if(avg <90 && avg >= 80){
                grade = "A";
            }else if(avg <80 && avg >= 70){
                grade = "B";
            }else if(avg <70 && avg >= 60){
                grade = "C";
            }else if(avg <60 && avg >= 50){
                grade = "D";
            } else{
                grade = "F";
            }
             grades[i] = grade;   
            
        }
        return grades;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int students_marksRows = Integer.parseInt(sc.next().trim());
        int students_marksColumns = Integer.parseInt(sc.next().trim());
		
		
		int[][] students_marks = new int[students_marksRows][students_marksColumns];
		for(int i = 0; i < students_marksRows; i++)
        {
			for(int j = 0; j < students_marksColumns; j++)
			{
				students_marks[i][j] = Integer.parseInt(sc.next().trim());				
			}
        }

        String[] result = Result.calculateGrade(students_marks);
		
		for(int i = 0; i < result.length; i++)
        {
			System.out.println(result[i]);
            bufferedWriter.write(result[i]+"\n");
        }       
        bufferedWriter.close();
    }
}
