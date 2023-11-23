package org.example;

import javax.swing.*;
import java.io.*;

public class WriteFileChat {
    public static void writeFileChat(String fileName, String text) throws IOException {


//
//      try {
//          FileWriter fileWriter = new FileWriter(fileName);
//          fileWriter.write(String.valueOf(text));
//          fileWriter.close();
//      }
//     catch (IOException e) {
//        System.out.println("An error occurred while writing to the file.");
//        e.printStackTrace();
//    }



                File file = new File(fileName);
        PrintWriter writer = new PrintWriter((new FileWriter(file, true)));

               writer.println(text);
        writer.close();
    }
}


