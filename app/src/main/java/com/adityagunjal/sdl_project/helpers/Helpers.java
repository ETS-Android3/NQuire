package com.adityagunjal.sdl_project.helpers;

public class Helpers {

    public static  int nextEven(int n){
        return (n % 2 == 0) ? n + 2 : n + 1;
    }

    public static int nextOdd(int n){
        return (n % 2 == 0) ? n + 1 : n + 2;
    }

}
