package com.springboottutorial.spring_boot_tutorial.util;

public class ReflectionTest {
    private static String helloStatic(String s){
        return s;
    }
    private static void helloStatic(){
        System.out.println("Hello World");
    }
    private String helloNonStatic1(String s){
        return s;
    }
    private void helloNonStatic(){
        System.out.println("Hello World");
    }
}