package com.gmail.alexandrtalan;

public class TriangleUtils {

    /**
     * Задача о треугольнике
     * <p>
     * Вам даны длинны трех отрезков: a, b, c. Нужно вернуть true, если они
     * могут быть сторонами треугольника и false, если не могут.
     */

    public boolean isTriangle(int a, int b, int c) throws IllegalArgumentException {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("One or more of the parameters < 0.");
        } else if (a == 0 && b == 0 && c == 0) {
            throw new IllegalArgumentException("All parameters equals 0.");
        }

        return a < (c + b) && b < (a + c) && c < (a + b);
    }

    /**
     * Вам даны длинны трех сторон треугольника: a, b, c. Необходимо вычислить
     * площадь треугольника.
     */

    public double getTriangleArea(int a, int b, int c) throws IllegalArgumentException {
        if (isTriangle(a, b, c)) {
            double p = (a + b + c) / 2;
            return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }

        return -1;
    }
}
