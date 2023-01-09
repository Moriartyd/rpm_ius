package ru.galeev.labone.util;

import java.util.Arrays;

public class ComplexVector {

    private ComplexNumber[] elementData;
    private int elementCount = 0;

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

    public ComplexNumber[] getElementData() {
        return elementData;
    }
    public int getLength() {
        return this.elementCount;
    }

    public void addToStart(ComplexNumber element) {
        addElement(0, element);
    }

    public void addToEnd(ComplexNumber element) {
        addElement(this.elementCount, element);
    }

    //Удаление, добавление элемента на место по индексу и в начало/конец
    public void addElement(int index, ComplexNumber element) {
        checkArg(element);
        checkArg(index);
        ComplexNumber[] newVector = new ComplexNumber[this.elementCount + 1];
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

    public void removeElement(ComplexNumber element) {
        removeElement(-1, element);
    }

    public void removeElement(int index, ComplexNumber element) {
        if (index == -1) {
            checkArg(element);
            index = this.getIndexOf(element);
            if (index < 0) {
                throw new IllegalArgumentException("Invalid element value: " + element);
            }
        } else {
            checkArg(index + 1);
        }

        ComplexNumber[] newVector = new ComplexNumber[this.elementCount - 1];
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
    public int getIndexOf(ComplexNumber complexNumber) {
        checkArg(complexNumber);
        int i = 0;
        while (i < this.elementCount) {
            if (this.elementData[i].toString().equals(complexNumber.toString())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    //Поиск элемента по индексу
    public ComplexNumber elementAt(int index) {
        checkArg(index);
        return this.elementData[index];
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

    private void checkArg(int index) {
        if (index < -1 || index > this.elementCount) {
            throw new IllegalArgumentException("Illegal arguments: index " + index + " is wrong!");
        }
    }

    private static void checkArg(ComplexNumber x) {
        if (x == null) {
            throw new IllegalArgumentException("Illegal argument: complex number is null");
        }
    }

    private static void checkArgs(ComplexVector x, ComplexVector y) {
        checkArg(x);
        checkArg(y);
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
