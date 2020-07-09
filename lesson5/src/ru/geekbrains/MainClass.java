package ru.geekbrains;

import java.util.Arrays;

public class MainClass {
    static final int SIZE = 1_000_000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        doCalculationOne(creatArr());
        doCalculationTwo(creatArr());
    }

    private static float[] creatArr() {
        float arr[] = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        return arr;
    }

    public static void doCalculationOne(float[] arr) {
        System.out.println(Arrays.toString(arr));
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void doCalculationTwo(float[] arr) {
        System.out.println(Arrays.toString(arr));
        float[] arrOne = new float[HALF];
        float[] arrTwo = new float[HALF];
        long b = System.currentTimeMillis();
        System.arraycopy(arr, 0, arrOne, 0, HALF);
        System.arraycopy(arr, HALF, arrTwo, 0, HALF);
        System.out.println(Arrays.toString(arrOne));
        System.out.println(Arrays.toString(arrTwo));
        Runnable target;
        Thread calcOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arrOne.length; i++) {
                    arrOne[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        calcOne.start();

        Thread calcTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arrTwo.length; i++) {
                    arrTwo[i] = (float) (arr[i] * Math.sin(0.2f + (i + HALF) / 5) * Math.cos(0.2f + (i + HALF) / 5) * Math.cos(0.4f + (i + HALF) / 2));
                }
            }
        });
        calcTwo.start();

        try {
            calcOne.join();
            calcTwo.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.arraycopy(arrOne, 0, arr, 0, HALF);
        System.arraycopy(arrTwo, 0, arr, HALF, HALF);
        System.out.println(Arrays.toString(arr));
        System.out.println("The total time for two arrays is ");
        System.out.println(System.currentTimeMillis() - b);


    }
}
