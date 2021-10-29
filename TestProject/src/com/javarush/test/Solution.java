package com.javarush.test;

import java.util.Arrays;
import java.util.List;


public class Solution {
    public static void main(String[] args) {
        List<String> medicinesOnline = List.of("Askorbinka", "Nazonex", "Dekatylen", "Strepsils", "Zelenka");
        List<Pharmacy> pharmacies = List.of(new Pharmacy("3i", List.of("Nazonex", "Vitamin C", "Zn Supplements")),
                new Pharmacy("DS", List.of("Vitamin C", "Advil", "Doppel Herz")),
                new Pharmacy("Kopiiochka", List.of("Ibuprofen", "Vitamin C")));

        System.out.println(medicinesOnline);
        System.out.println(Arrays.toString(pharmacies.toArray()));
    }
}
