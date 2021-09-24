package com.javarush.task.pro.task02.task0214;

import java.util.Scanner;

/*
Чтение и преобразование строк
*/

public class Main {

        private void showFibonacci() {
            int num1 = 0;
            int num2 = 1;
            int result = num1 + num2;

            System.out.println(num1 + " " + num2 + " ");
            while (result < 150) {
                System.out.println(result + " ");
                num1 = num2;
                num2 = result;
                result = num1 + num2;
            }
        }

    }


