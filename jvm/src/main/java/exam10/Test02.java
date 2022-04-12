package exam10;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author huangzihua
 * @date 2021-04-15
 */
public class Test02 {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 127;
        Integer f = 127;
        Long g = 3L;
        System.out.println(c == d);     // true
        System.out.println(e == f);     // false
        System.out.println(c == (a + b));       // true
        System.out.println(c.equals(a + b));    // true
        System.out.println(g == (a + b));       // false错，true
        System.out.println(g.equals(a + b));    // true错，false
    }

//    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1,2,3,4);
//        int sum = 0;
//        for (int i : list) {
//            sum += i;
//        }
//        System.out.println(sum);
//    }

//    public static void main(String[] args) {
//        List list = Arrays.asList(new Integer[] {
//                Integer.valueOf(1),
//                Integer.valueOf(2),
//                Integer.valueOf(3),
//                Integer.valueOf(4)
//        });
//        int sum = 0;
//        for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
//            int i = ((Integer) localIterator.next()).intValue();
//            sum += i;
//        }
//        System.out.println(sum);
//    }


}
