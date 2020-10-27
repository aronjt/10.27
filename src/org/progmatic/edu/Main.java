package org.progmatic.edu;

import com.sun.tools.attach.AgentInitializationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        indians();
    }

    public static List<Indian> indians() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("files/indianok.txt"));
        List<Indian> indians = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] splitit = sc.nextLine().split(",");
            System.out.println(splitit[4]);
            int age = Integer.valueOf(splitit[3]);
            String[] tools = splitit[4].split("\\|");
            List<String> toollist = Arrays.asList(tools);
            Indian indian = new Indian(splitit[0], splitit[1], splitit[2], age, toollist);
            System.out.println(indian);//ez csak egy ellenörző
            indians.add(indian);
        }
        return indians;
    }
}