package org.progmatic.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        List<Indian> indians = new ArrayList<>(indians());
        HashMap<String, List<Indian>> reservation = new HashMap<>();
        for (Indian indian : indians) {
            reservation.putIfAbsent(indian.getTribe(), new ArrayList<>());
            reservation.get(indian.getTribe()).add(indian);
        }

        //1.
        System.out.println(indians.size() + " indián szerepel a leltárban");
        //2.
        System.out.println(numOfTools(indians) + " különböző eszköz van.");
        //3.
        System.out.println(numOfTribeMembers("Apache", indians));
        //4.
        System.out.println("Férfi nő arány: " + countSexes(reservation, "f", "Apache") + ":" + countSexes(reservation, "n", "Apache"));
        //5.
        System.out.println(oldStuff(reservation, "Apache"));
        //6.
        System.out.println(avgToolsPerPerson(indians));
        //7.
        System.out.println(indiansWithBow(indians));
        //8.
        System.out.println(Arrays.toString(bearFoot(indians)));
    }

    //Beolvasás
    public static List<Indian> indians() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("files/indianok.txt"));
        List<Indian> indians = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] splitit = sc.nextLine().split(",");
           // System.out.println(splitit[4]);
            int age = Integer.parseInt(splitit[3]);
            String[] tools = splitit[4].split("\\|");
            List<String> toollist = Arrays.asList(tools);
            Indian indian = new Indian(splitit[0], splitit[1], splitit[2], age, toollist);
          //  System.out.println(indian);//ez csak egy ellenörző
            indians.add(indian);
        }
        return indians;
    }

    //2. feladat

    public static int numOfTools(List<Indian> indians) {
        HashSet<String> set = new HashSet<>();
        for (Indian indian : indians) {
            set.addAll(indian.getTools());
        }
        return set.size();
    }

    //3. feladat

    public static int numOfTribeMembers(String tribe, List<Indian> indians) {
        int sum = 0;
        for (Indian indian : indians) {
            if (indian.getTribe().equals(tribe)) {
                sum++;
            }
        }
        return sum;
    }

    //4. feladat

    public static int countSexes(HashMap<String, List<Indian>> indians, String sex, String tribe) {
        int counter = 0;
        for (Indian indian : indians.get(tribe)) {
            if (indian.getSex().equals(sex)) {
                counter++;
            }
        }
        return counter;
    }

    //5. feladat

    public static ArrayList<String> oldStuff(HashMap<String, List<Indian>> indians, String tribe) {
        ArrayList<String> names = new ArrayList<>();
        for (Indian indian : indians.get(tribe)) {
            if (indian.getTools().contains("békepipa")) {
                names.add(indian.getName());
            }
        }
        return names;
    }

    //6. feladat

    public static float avgToolsPerPerson(List<Indian> indians) {
        float total = 0;
        for (Indian indian : indians) {
            total += indian.getTools().size();
        }
        return total/indians.size();
    }

    //7. feladat

    public static int indiansWithBow(List<Indian> indians) {
        int bows = 0;
        for (Indian indian : indians) {
            if (indian.getTools().contains("íj")) {
                bows++;
            }
        }
        return bows;
    }

    //8. feladat

    public static int[] bearFoot(List<Indian> indians) {
        int[] bearFoot = new int[2];
        int sumAge = 0;
        int sumBearFoot = 0;
        for (Indian indian : indians) {
            if (!indian.getTools().contains("mokaszin")) {
                sumAge += indian.getAge();
                sumBearFoot++;
            }
        }
        int avgAge = sumAge / sumBearFoot;
        bearFoot[0] = sumBearFoot;
        bearFoot[1] = avgAge;
        return bearFoot;
    }
}
