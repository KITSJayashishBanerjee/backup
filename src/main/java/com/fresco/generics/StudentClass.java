package com.fresco.generics;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class StudentClass {


    public String getQuery(String studentData, String query) {
        String[] studentDataSplit = studentData.split(" ");
        ScoreList scoreList = new ScoreList();
        StudentList studentList = new StudentList();
        Arrays.stream(studentDataSplit).forEach(studentList::addElement);
        String[] querySplit = query.split(",");
        String result = null;
        switch (querySplit[0]) {
            case "1":
                //find student with names starting with ..
                result = studentList.beginsWith(querySplit[1]);
                break;
            case "2":
                //find student with given blood group
                result = studentList.bloodGroupOf(querySplit[1].split(" "), querySplit[2]);
                break;
            case "3":
                //find student with marks > threshold
                Arrays.stream(studentDataSplit).mapToInt(Integer::parseInt).forEach(scoreList::addElement);
                result = String.format("%d students scored %s above", scoreList.thresholdScore(Integer.parseInt(querySplit[1])), querySplit[1]);
                break;
            case "4":
                //avg of student marks
                Arrays.stream(studentDataSplit).mapToInt(Integer::parseInt).forEach(scoreList::addElement);
                result = String.format("%.2f",scoreList.averageValues());
                break;
            case "5":
                //avg of student CGPA
                Arrays.stream(studentDataSplit).mapToDouble(Double::parseDouble).forEach(scoreList::addElement);
                DecimalFormat df = new DecimalFormat("#.##");
                NumberFormat formatter = new DecimalFormat("#0.00");
                result = String.format("%.2f",scoreList.averageValues());
                //result = formatter.format(scoreList.averageValues());
                break;
        }
        return result;

    }

}
