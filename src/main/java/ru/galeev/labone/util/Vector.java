package ru.galeev.labone.util;

import java.util.Arrays;

public class Vector {

    public Double[] getElementData() {
        return elementData;
    }

    private Double[] elementData;

    int elementCount = 0;

    public Vector(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        this.elementData = new Double[initialCapacity];
    }

    public Vector(Double[] elementData) {
        if (elementData == null || elementData.length < 1) {
            throw new IllegalArgumentException("Illegal arguments");
        }
        this.elementData = elementData;
        this.elementCount = elementData.length;
    }

    /**
     * Добавляет в конец массива новый элемент
     * @param el - Новый элемент
     */
    public void addElement(Double el) {
        Double[] newArray = new Double[this.elementData.length + 1];
        int i = 0;
        while (i < this.elementCount) {
            newArray[i] = this.elementData[i];
        }
        newArray[i] = el;
        this.elementCount += 1;
        this.elementData = newArray;
    }

    public int getLength() {
        return this.elementCount;
    }

    public static Vector sum(Vector x, Vector y) {
        checkArgs(x, y);
        int i = 0;
        Double[] sum = new Double[x.getLength()];
        while (i < x.getLength()) {
            sum[i] = x.getElementData()[i] + y.getElementData()[i];
            i++;
        }
        return new Vector(sum);
    }

    public static Vector diff(Vector x, Vector y) {
        checkArgs(x, y);
        int i = 0;
        Double[] diff = new Double[x.getLength()];
        while (i < x.getLength()) {
            diff[i] = x.getElementData()[i] - y.getElementData()[i];
            i++;
        }
        return new Vector(diff);
    }

    public static Vector mult(Vector x, Vector y) {
        checkArgs(x, y);
        int i = 0;
        Double[] mult = new Double[x.getLength()];
        while (i < x.getLength()) {
            mult[i] = x.getElementData()[i] * y.getElementData()[i];
            i++;
        }
        return new Vector(mult);
    }

//    public static Vector mult(Vector x, Double y) {
//        checkArg(x);
//        int i = 0;
//        Double[] mult = new Double[x.length];
//        while (i < x.length) {
//            mult[i] = x[i] * y;
//            i++;
//        }
//        return mult;
//    }

    public static Vector div(Vector x, Vector y) {
        checkArgs(x, y);
        int i = 0;
        Double[] div = new Double[x.getLength()];
        while (i < x.getLength()) {
            div[i] = x.getElementData()[i] / y.getElementData()[i];
            i++;
        }
        return new Vector(div);
    }

//    public static Vector div(Vector x, Double y) {
//        checkArg(x);
//        int i = 0;
//        Double[] div = new Double[x.getLength()];
//        while (i < x.getLength()) {
//            div[i] = x[i] / y;
//            i++;
//        }
//        return div;
//    }

    private static void checkArgs(Vector x, Vector y) {
        if (x == null || y == null || x.getLength() < 1 || y.getLength() < 1) {
            throw new IllegalArgumentException("Illegal arguments");
        }
        if (x.getLength() != y.getLength()) {
            throw new IllegalArgumentException(String.format("Vector's length doesn't match each other: %s and %s",
                    x.getLength(), y.getLength()));
        }
    }

    private static void checkArg(Vector x) {
        if (x == null || x.getLength() < 1) {
            throw new IllegalArgumentException("Illegal arguments");
        }
    }

//    @Override
    public String toString() {
        String[] str = new String[this.elementCount];
        int i = 0;
        while (i < this.elementCount) {
            str[i] = String.valueOf(this.elementData[i]);
            i++;
        }
        return Arrays.toString(str)
                .replace("[", "")
                .replace("]", "");
    }
}
