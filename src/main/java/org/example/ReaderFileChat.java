package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.exists;

public class ReaderFileChat {
    public static String read(String fileName) throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        exists(Path.of(fileName));

        try {

            BufferedReader in = new BufferedReader(new FileReader( fileName));
            try {

                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {

                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }


        return sb.toString();
    }

}
