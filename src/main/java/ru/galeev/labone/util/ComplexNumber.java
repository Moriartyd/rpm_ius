package ru.galeev.labone.util;

public class ComplexNumber {

    private final int real;
    private final int imag;

    public ComplexNumber(int real, int imag) {
        this.real = real;
        this.imag = imag;
    }

    public static ComplexNumber sum(ComplexNumber x, ComplexNumber y) {
        return new ComplexNumber(
                x.real + y.real,
                x.imag + y.imag);
    }

    public static ComplexNumber diff(ComplexNumber x, ComplexNumber y) {
        return new ComplexNumber(
                x.real - y.real,
                x.imag - y.imag);
    }

    public static ComplexNumber mult(ComplexNumber x, ComplexNumber y) {
        return new ComplexNumber(
                (x.real * y.real) - (x.imag * y.imag),
                (x.imag * y.real) + (x.real + y.imag));
    }

    public static ComplexNumber div(ComplexNumber x, ComplexNumber y) {
        return new ComplexNumber(
                ((x.real * y.real) + (x.imag * y.imag)) / ((y.real * y.real) + (y.imag * y.imag)),
                ((x.imag * y.real) - (x.real + y.imag)) / ((y.real * y.real) + (y.imag * y.imag)));
    }

    public String toString() {
        return real + " + (" + imag + ")i";
    }
}
