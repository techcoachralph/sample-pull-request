package testcases;

public class practicing {
    static class A {
        int a = 10;
    }

    static class B extends A {
        int b = 10;
        int sum () {
            return b + a;
        }
    }

    public static void main(String args[]) {
        B sumOfAandB = new B();
        System.out.println(sumOfAandB.sum());
    }
}
