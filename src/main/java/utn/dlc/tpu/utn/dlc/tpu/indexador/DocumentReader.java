package utn.dlc.tpu.utn.dlc.tpu.indexador;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DocumentReader {

    private Path file_location = Paths.get(System.getProperty("user.dir"), "DocumentosTP1");
    private String string_path =  System.getProperty("user.dir") + File.separator + "DocumentosTP1" + File.separator;
    private File f;
    private BufferedReader br;
    private Scanner sc;

    public DocumentReader(String name) {
        string_path+=name;
        f = new File(string_path);
    }

    public void buffered_reader() {
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(f), "ISO-8859-1"));
            do {
                line = br.readLine();
            }
            while( line != null);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void scanner_buffered_reader() {
        String line;
        try {
            sc = new Scanner(new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(f), "ISO-8859-1"
            )));
            while(sc.hasNextLine()) {
                line = sc.nextLine();
            }
            sc.close();
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void scanner_file() {
        String line;
        try {
            sc = new Scanner(f, "ISO-8859-1");
            while(sc.hasNextLine()) {
                line = sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void j8_lines() {

        try {
            List lines = Files.lines(Paths.get(string_path), Charset.forName("ISO-8859-1")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Test {
    public static void main(String args[]) {
        int iterations = 6000;
        DocumentReader dr = new DocumentReader("shndy10.txt");
        //dr.j8_lines();
        long total_duration = 0;
        for(int i = iterations; i > 0; i--) {
            long duration = time_this(dr::buffered_reader);
            total_duration += duration;
        }
        System.out.println("Average BufferedReader execution time for " + iterations + " executions: " + total_duration / iterations + " ms.");
        System.out.println("Total BufferedReader execution time for " + iterations + " executions: " + total_duration + " ms.");

        /*total_duration = 0;
        for(int i = iterations; i > 0; i--) {
            long duration = time_this(dr::scanner_buffered_reader);
            total_duration += duration;
        }
        System.out.println("Average Scanner(BufferedReader) execution time for " + iterations + " executions: " + total_duration / iterations + " ms.");
        System.out.println("Total Scanner(BufferedReader) execution time for " + iterations + " executions: " + total_duration + " ms.");

        total_duration = 0;
        for(int i = iterations; i > 0; i--) {
            long duration = time_this(dr::scanner_file);
            total_duration += duration;
        }
        System.out.println("Average Scanner(File) execution time for " + iterations + " executions: " + total_duration / iterations + " ms.");
        System.out.println("Total Scanner(File) execution time for " + iterations + " executions: " + total_duration + " ms.");*/

        total_duration = 0;
        for(int i = iterations; i > 0; i--) {
            long duration = time_this(dr::j8_lines);
            total_duration += duration;
        }
        System.out.println("Average File.lines() execution time for " + iterations + " executions: " + total_duration / iterations + " ms.");
        System.out.println("Total File.lines() execution time for " + iterations + " executions: " + total_duration + " ms.");
    }

    static long time_this(final Runnable method) {
        long start_time = 0;
        long end_time = 0;
        try {
            start_time = System.nanoTime();
            method.run();
            end_time = System.nanoTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (end_time - start_time) / 1000000;
    }
}
