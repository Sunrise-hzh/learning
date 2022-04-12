package zm02;

/**
 * @author huangzihua
 * @date 2021-04-07
 */
public class TestExtend extends TestClass {
//    public int m;

    public void print() {
        System.out.println(m);
    }

    public static void main(String[] args) {
        TestExtend testExtend = new TestExtend();
        testExtend.print();
    }
}
