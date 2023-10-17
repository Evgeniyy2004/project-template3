package edu.hw2;

public final class Task1 {
    private Task1() {
        //not used
    }

    public sealed interface Expr permits  Constant, Negate, Division, Exponent, Addition, Multiplication {

        double evaluate();
    }

    public record Constant(double value) implements Expr {
        public double evaluate() {
            return value;
        }
    }

    public record Negate(Expr valueToNegate) implements Expr {
        public double evaluate() {
            return -valueToNegate.evaluate();
        }
    }

    public record Exponent(Expr pow1, Expr pow2) implements Expr {

        public double evaluate() {
            if (pow1.evaluate() < 0 && pow2.evaluate() % 1 != 0) {
                throw new IllegalArgumentException("Нелья возвести отрицательное число в дробную степень");
            }
            return Math.pow(pow1.evaluate(), pow2.evaluate());
        }

        public Exponent(Expr pow1, double pow2) {
            this(pow1, new Constant(pow2));
        }
    }

    public record Addition(Expr one, Expr two) implements Expr {
        public double evaluate() {
            return one.evaluate() + two.evaluate();
        }
    }

    public record Division(Expr one, Expr two) implements Expr {
        public double evaluate() {
            if (two.evaluate() == 0)  {
                throw new IllegalArgumentException("Argument 'divisor' is 0");
            }
            return one.evaluate() / two.evaluate();
        }
    }

    public record Multiplication(Expr one, Expr two) implements Expr {
        public double evaluate() {
            return one.evaluate() * two.evaluate();
        }
    }
}
