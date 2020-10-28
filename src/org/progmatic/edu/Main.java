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
        //9.
        System.out.println(mostPopulatedTribe(reservation));
        //10.
        System.out.println("Férfiak aránya a törzsekben");
        menRate(reservation);
        //11.
        System.out.println(mostTomahawk(reservation));
        //12.
        System.out.println(mostToolByKids(reservation));
        //13.
        toolsPerTribe(reservation);
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

    //9.

    public static String mostPopulatedTribe(HashMap<String, List<Indian>> indians) {
        Iterator<String> iterator = indians.keySet().iterator();
        int sum = 0;
        String tribe = "Csicska";
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (indians.get(s).size() > sum) {
                sum = indians.get(s).size();
                tribe = s;
            }
        }
        System.out.print(sum + " ");
        return tribe;
    }

    //10.

    public static void menRate(HashMap<String, List<Indian>> indians) {
        for (String tribeName : indians.keySet()) {
            System.out.println(tribeName + " " + countSexes(indians, "f", tribeName) + ":" + countSexes(indians, "n", tribeName));
        }
    }

    //11.

    public static String mostTomahawk(HashMap<String, List<Indian>> indians) {
        Iterator<String> iterator = indians.keySet().iterator();
        int sum = 0;
        String tribe = null;
        while (iterator.hasNext()) {
            String actualTribe = iterator.next();
            Iterator<Indian> member = indians.get(actualTribe).listIterator();
            int tribeMember = 0;
            while (member.hasNext()) {
                Indian actualIndian = member.next();
                if (actualIndian.getTools().contains("tomahawk") && actualIndian.getSex().equals("f")) {
                    tribeMember++;
                }
            }
            if (tribeMember > sum) {
                sum = tribeMember;
                tribe = actualTribe;
            }
        }
        return tribe;
    }

    //12.

    public static String mostToolByKids(HashMap<String, List<Indian>> indian) {
        int sum = 0;
        String tribe = null;
        for (String tribeName : indian.keySet()) {
            int toolCounter = 0;
            for (Indian member : indian.get(tribeName)) {
                if (member.getAge() < 18) {
                    toolCounter += member.getTools().size();
                }
            }
            if (toolCounter > sum) {
                sum = toolCounter;
                tribe = tribeName;
            }
        }
        return tribe;
    }

    //13.

    public static void toolsPerTribe(HashMap<String, List<Indian>> indians) {
        for (String tribeName : indians.keySet()) {
            int sumTools = 0;
            for (Indian indian : indians.get(tribeName)) {
                sumTools += indian.getTools().size();
            }
            System.out.println(tribeName + " " + sumTools);
        }
    }
}
