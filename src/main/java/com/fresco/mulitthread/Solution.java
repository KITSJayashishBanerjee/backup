package com.fresco.mulitthread;

import java.util.Arrays;
import java.util.Scanner;

//Write your code here

public class Solution {
        public static final int[] threadArray = new int[300];
        public static volatile String i = 0+"";
    public boolean test() throws InterruptedException
    {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();
        Thread task2Thread = new Thread(task2);
        Thread task3Thread = new Thread(task3);
        task1.start();
        task2Thread.start();
        task1.join();
        task2Thread.join();
        task3Thread.start();
        int first = Task1.a+Task2.a; // i+j 80 +130
        int containsSecondThread = Task1.a; // i 80
        String oneAndTwo = "";
        String sizeOfTask1 = "";
        for(int i=0;i<first;i++)
        {
            oneAndTwo += threadArray[i]+" ";  // 1 to 210
        }
        for(int i=0;i<containsSecondThread;i++)
        {
            sizeOfTask1 += threadArray[i]+" "; // 1 to 80
        }
        int begOfTask3 = Task3.beg; // i+j 80+130=210
        String checkingString = "";
        for(int i=begOfTask3;i<threadArray.length;i++)
        {
            checkingString += i + " "; // 210 to 299
        }
        String task3String = "";
        for(int j = begOfTask3;j<threadArray.length;j++)
        {
            task3String += threadArray[j]+" ";// 210 to 299
        }
        if((!oneAndTwo.contains(begOfTask3+"") && sizeOfTask1.contains(Task2.beg+"")) && task3String.equals(checkingString))
        {
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws InterruptedException
    {
        Scanner sc= new Scanner(System.in);
            Solution solution = new Solution();
            int one = sc.nextInt();
            Task1.a = one;
            Task1.beg = 0;
            int two = sc.nextInt();
            Task2.a = two;
            Task2.beg = one;
            int three = sc.nextInt();
            Task3.a = three;
            Task3.beg = one+two;
            System.out.print(solution.test());
        Arrays.stream(solution.threadArray).forEach(System.out::println);
    }


}

class Task1 extends   Thread{

    public static int a;
    public static int beg;
    public static int count = 0;

    @Override
    public void run() {
        while(count < a){
            Solution.threadArray[beg+count] = beg+count;
            count++;
            //System.out.println("Task1: "+ beg );
        }
    }



}

class Task2 implements  Runnable{

    public static int a;
    public static int beg;
    public static int count = 0;

    @Override
    public void run() {
        while(count < a){
            Solution.threadArray[incrementIndex()] = beg+count;
            count++;
            //System.out.println("Task2: "+ beg );
        }
    }

    private int incrementIndex() {
        int index = Integer.parseInt(Solution.i);
        Solution.i = (index+1)+"";
        return index;
    }
}

class Task3 extends Thread{

    public static int a;
    public static int beg;
    public static int count = 0;

    @Override
    public void run() {
        while(count < 299-beg){
            Solution.threadArray[beg+count] = beg+count;
            count++;
           //System.out.println("Task3: "+ beg );
        }
    }

    private int incrementIndex() {
        int index = Integer.parseInt(Solution.i);
        Solution.i = (index+1)+"";
        return index;
    }
}
