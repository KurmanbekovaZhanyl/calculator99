package com.company;
import java.util.HashMap;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        String scanner = scr.nextLine();
        String arabicOrRoman = "";
        String  num1 = getNum1(scanner);
        String num2 = getNum2(scanner);
        String znak = getZnak(scanner);
        arabicOrRoman = arabOrRome(num1, num2);
        if(arabicOrRoman.equals("Arab")){
            System.out.println(arabicMath(num1, num2, znak));
        }
        else if(arabicOrRoman.equals("Rome")){
            System.out.println(arabicToRome(arabicMath(romeToArabic(num1), romeToArabic(num2), znak)));
        }

    }

    public static String getNum1(String scanner) {
        String num1 = "";
        for (int i = 0; i < scanner.length(); i++) {
            if (scanner.charAt(i) == '+' ||
                    scanner.charAt(i) == '-' ||
                    scanner.charAt(i) == '*' ||
                    scanner.charAt(i) == '/') {
                char operation = scanner.charAt(i);
                num1 = scanner.substring(0, scanner.indexOf(operation));
            }
        }
        return num1;
    }

    public static String getNum2(String scanner) {
        String num2 = "";
        for (int i = 0; i < scanner.length(); i++) {
            if (scanner.charAt(i) == '+' ||
                    scanner.charAt(i) == '-' ||
                    scanner.charAt(i) == '*' ||

                    scanner.charAt(i) == '/') {
                char operation = scanner.charAt(i);
                num2 = scanner.substring(scanner.indexOf(operation) + 1);
            }
        }
        return num2;
    }

    public static String getZnak(String scanner) {
        String znak = "";
        for (int i = 0; i < scanner.length(); i++) {
            if (scanner.charAt(i) == '+' ||
                    scanner.charAt(i) == '-' ||
                    scanner.charAt(i) == '*' ||
                    scanner.charAt(i) == '/') {
                znak = String.valueOf(scanner.charAt(i));
            }
        }
        return znak;
    }

    public static String arabicToRome(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLiterals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder rome = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                rome.append(romanLiterals[i]);
            }
        }
        return rome.toString();
    }

    public static String romeToArabic(String rome){
        HashMap<Character,Integer> hashMap = new HashMap<>();
        hashMap.put('I',1);
        hashMap.put('V',5);
        hashMap.put('X',10);
        hashMap.put('L',50);
        hashMap.put('C',100);
        hashMap.put('P',500);
        hashMap.put('M',1000);

        int result = 0;
        for (int i = 0; i < rome.length(); i++) {
            char ch = rome.charAt(i);
            if (i > 0 && hashMap.get(ch)>hashMap.get(rome.charAt(i-1))){
                result+=hashMap.get(ch) - 2 * hashMap.get(rome.charAt(i-1));
            }else {
                result+=hashMap.get(ch);
            }
        }
        return String.valueOf(result);
    }

    public static String arabOrRome(String num1, String num2){
        String araborRome ="";
        boolean valid1 = num1.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
        boolean valid2 = num2.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
        if (valid1 && valid2){
            araborRome = "Rome";
        }else {
            int number1 = Integer.parseInt(num1);
            int number2 = Integer.parseInt(num2);

            araborRome = "Arab";
        }
        return araborRome;
    }

    public static int arabicMath(String num1, String num2, String operation){
        int sum = 0;
        switch (operation){
            case "+" : sum = Integer.parseInt(num1)+Integer.parseInt(num2);break;
            case "-" : sum = Integer.parseInt(num1)-Integer.parseInt(num2);break;
            case "*" : sum = Integer.parseInt(num1)*Integer.parseInt(num2);break;
            case "/" : sum = Integer.parseInt(num1)/Integer.parseInt(num2);break;
        }
        return sum;
    }
}
