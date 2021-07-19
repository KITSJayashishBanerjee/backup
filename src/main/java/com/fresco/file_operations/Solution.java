package com.fresco.file_operations;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

// Write your code below.
//

public class Solution {
    public static String filepath = System.getenv("OUTPUT_PATH").substring(0, System.getenv("OUTPUT_PATH").lastIndexOf("\\") + 1);

    private static String generateString()
    {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder generatedString = new StringBuilder(20);
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            char c = chars[random.nextInt(chars.length)];
            generatedString.append(c);
        }
        return generatedString.toString();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        try{
            EncryptDecryptFile f = new EncryptDecryptFile ();

            String encryptFilename = Solution.filepath + "EncryptionFile.txt";
            String generatedString = generateString();
            BufferedWriter writer = new BufferedWriter(new FileWriter(encryptFilename));
            writer.write(generatedString);
            writer.close();

            if(f.readEncryptionFile().equals(generatedString))
            {
                f.writeDecryptionFile(message);

                String decryptFilename = Solution.filepath + "DecryptionFile.txt";
                BufferedReader reader = new BufferedReader(new FileReader(decryptFilename));
                String messageFromFile = reader.readLine();
                reader.close();

                System.out.println(messageFromFile);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}

class EncryptDecryptFile {

    public String readEncryptionFile() throws IOException {
       // return Files.lines(Paths.get(Solution.filepath + "EncryptionFile.txt")).collect(Collectors.joining(System.lineSeparator()));;
        return Files.readAllLines(Paths.get(Solution.filepath + "EncryptionFile.txt")).stream().collect(
                Collectors.joining(System.lineSeparator()));
    }

    public void writeDecryptionFile(String message) throws IOException {
        Files.writeString(Paths.get(Solution.filepath + "DecryptionFile.txt"), message);
    }
}