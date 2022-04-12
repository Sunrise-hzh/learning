package exam07;

/**
 * @author huangzihua
 * @date 2021-04-08
 */
public class TestClinit {

    static class Parent {
        public static int A = 1;
        static {
            A = 2;
        }
    }

    static class Sub extends Parent {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
