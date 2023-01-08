package ru.galeev.labone.util;

import java.util.Arrays;

public class ComplexVector {

    public ComplexNumber[] getElementData() {
        return elementData;
    }

    private final ComplexNumber[] elementData;

    int elementCount = 0;

    public ComplexVector(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        this.elementData = new ComplexNumber[initialCapacity];
    }

    public ComplexVector(ComplexNumber[] elementData) {
        if (elementData == null || elementData.length < 1) {
            throw new IllegalArgumentException("Illegal arguments");
        }
        this.elementData = elementData;
        this.elementCount = elementData.length;
    }

    public int getLength() {
        return this.elementCount;
    }

    public static ComplexVector sum(ComplexVector x, ComplexVector y) {
        checkArgs(x, y);
        int i = 0;
        ComplexNumber[] sum = new ComplexNumber[x.getLength()];
        while (i < x.getLength()) {
            sum[i] = ComplexNumber.sum(x.getElementData()[i], y.getElementData()[i]);
            i++;
        }
        return new ComplexVector(sum);
    }

    public static ComplexVector diff(ComplexVector x, ComplexVector y) {
        checkArgs(x, y);
        int i = 0;
        ComplexNumber[] diff = new ComplexNumber[x.getLength()];
        while (i < x.getLength()) {
            diff[i] = ComplexNumber.diff(x.getElementData()[i], y.getElementData()[i]);
            i++;
        }
        return new ComplexVector(diff);
    }

    public static ComplexVector mult(ComplexVector x, ComplexVector y) {
        checkArgs(x, y);
        int i = 0;
        ComplexNumber[] mult = new ComplexNumber[x.getLength()];
        while (i < x.getLength()) {
            mult[i] = ComplexNumber.mult(x.getElementData()[i], y.getElementData()[i]);
            i++;
        }
        return new ComplexVector(mult);
    }

//    public static ComplexVector mult(ComplexVector x, ComplexNumber y) {
//        checkArg(x);
//        int i = 0;
//        ComplexNumber[] mult = new ComplexNumber[x.length];
//        while (i < x.length) {
//            mult[i] = x[i] * y;
//            i++;
//        }
//        return mult;
//    }

    public static ComplexVector div(ComplexVector x, ComplexVector y) {
        checkArgs(x, y);
        int i = 0;
        ComplexNumber[] div = new ComplexNumber[x.getLength()];
        while (i < x.getLength()) {
            div[i] = ComplexNumber.div(x.getElementData()[i], y.getElementData()[i]);
            i++;
        }
        return new ComplexVector(div);
    }

//    public static ComplexVector div(ComplexVector x, ComplexNumber y) {
//        checkArg(x);
//        int i = 0;
//        ComplexNumber[] div = new ComplexNumber[x.getLength()];
//        while (i < x.getLength()) {
//            div[i] = x[i] / y;
//            i++;
//        }
//        return div;
//    }

    private static void checkArgs(ComplexVector x, ComplexVector y) {
        if (x == null || y == null || x.elementCount < 1 || y.elementCount < 1) {
            throw new IllegalArgumentException("Illegal arguments");
        }
        if (x.getLength() != y.getLength()) {
            throw new IllegalArgumentException(String.format("Vector's length doesn't match each other: %s and %s",
                    x.getLength(), y.getLength()));
        }
    }

    private static void checkArg(ComplexVector x) {
        if (x == null || x.getLength() < 1) {
            throw new IllegalArgumentException("Illegal arguments");
        }
    }

    //    @Override
    public String toString() {
        String[] str = new String[this.elementCount];
        int i = 0;
        while (i < this.elementCount) {
            str[i] = this.elementData[i].toString();
            i++;
        }
        return Arrays.toString(str)
                .replace("[", "")
                .replace("]", "");
    }
}
