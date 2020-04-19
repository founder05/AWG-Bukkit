package com.marc_val_96.advancedworldgenerator.generator.biome;

import java.util.ArrayList;

public class ArraysCache {

    private final int[][] smallArrays = new int[128][];
    private final ArrayList<int[]> bigArrays = new ArrayList<int[]>();
    public boolean isFree = true;
    public OutputType outputType = OutputType.FULL;
    private int smallArraysNext = 0;
    private int bigArraysNext = 0;

    public ArraysCache() {

    }

    public void release() {
        smallArraysNext = 0;
        bigArraysNext = 0;
        isFree = true;
        outputType = OutputType.FULL;
    }

    public int[] getArray(int size) {

        if (size <= 256) {
            int[] array = smallArrays[smallArraysNext];
            if (array == null) {
                array = new int[256];
                smallArrays[smallArraysNext] = array;
            }
            smallArraysNext++;

            return array;
        }

        int[] array;
        if (bigArraysNext == bigArrays.size()) {
            array = new int[size];
            bigArrays.add(array);
        } else {
            array = bigArrays.get(bigArraysNext);
            if (array.length < size) {
                array = new int[size];
                bigArrays.set(bigArraysNext, array);
            }
        }

        bigArraysNext++;
        return array;

    }
}