package com.javarush.test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        List<String> medicinesOnline = List.of("Askorbinka", "Nazonex", "Dekatylen", "Strepsils", "Zelenka");
        List<Pharmacy> pharmacies = List.of(new Pharmacy("3i", List.of("Nazonex", "Vitamin C", "Zn Supplements")),
                new Pharmacy("DS", List.of("Vitamin C", "Advil", "Doppel Herz")),
                new Pharmacy("Kopiiochka", List.of("Ibuprofen", "Vitamin C")));

        System.out.println(medicinesOnline
        );
        System.out.println(Arrays.toString(pharmacies.toArray()));
    }
}
