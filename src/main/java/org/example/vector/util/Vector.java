package org.example.vector.util;

import java.util.Arrays;

public class Vector {
    private Double[] elementData;
    int elementCount = 0;

    public Vector(Double[] elementData) {
        if (elementData == null || elementData.length < 1) {
            throw new IllegalArgumentException("Illegal arguments");
        }
        this.elementData = elementData;
        this.elementCount = elementData.length;
    }

    public Double[] getElementData() {
        return elementData;
    }

    public void addToStart(Double element) {
        addElement(0, element);
    }

    public void addToEnd(Double element) {
        addElement(this.elementCount, element);
    }
    public void addElement(int index, Double element) {
        checkArg(element);
        checkArg(index);
        Double[] newVector = new Double[this.elementCount + 1];
        int i = 0;
        while (i < index) {
            newVector[i] = this.elementData[i];
            i++;
        }
        newVector[i++] = element;
        while (i < this.elementCount + 1) {
            newVector[i] = this.elementData[i - 1];
            i++;
        }
        this.elementData = newVector;
        this.elementCount += 1;
    }

    public void removeElement(Double element) {
        removeElement(-1, element);
    }

    public void removeElement(int index, Double element) {
        if (index == -1) {
            checkArg(element);
            index = this.getIndexOf(element);
            if (index < 0) {
                throw new IllegalArgumentException("Invalid element value: " + element);
            }
        } else {
            checkArg(index + 1);
        }

        Double[] newVector = new Double[this.elementCount - 1];
        int i = 0;
        while (i < index) {
            newVector[i] = this.elementData[i];
            i++;
        }
        while (i < this.elementCount - 1) {
            newVector[i] = this.elementData[i + 1];
            i++;
        }
        this.elementData = newVector;
        this.elementCount -= 1;
    }

    //Поиск индекса элемента
    public int getIndexOf(Double number) {
        checkArg(number);
        int i = 0;
        while (i < this.elementCount) {
            if (Double.compare(this.elementData[i], number) == 0) {
                return i;
            }
            i++;
        }
        return -1;
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

    private static void checkArgs(Vector x, Vector y) {
        checkArg(x);
        checkArg(y);
        if (x.getLength() != y.getLength()) {
            throw new IllegalArgumentException(String.format("Vector's length doesn't match each other: %s and %s",
                    x.getLength(), y.getLength()));
        }
    }

    private static void checkArg(Double x) {
        if (x == null) {
            throw new IllegalArgumentException("Illegal argument: complex number is null");
        }
    }

    private static void checkArg(Vector x) {
        if (x == null || x.getLength() < 1) {
            throw new IllegalArgumentException("Illegal arguments");
        }
    }

    private void checkArg(int index) {
        if (index < -1 || index > this.elementCount) {
            throw new IllegalArgumentException("Illegal arguments: index " + index + " is wrong!");
        }
    }

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
