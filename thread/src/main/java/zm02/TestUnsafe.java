package zm02;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author huangzihua
 * @date 2021-04-02
 */
public class TestUnsafe {
    // 获取Unsafe实例
//    static final Unsafe UNSAFE = Unsafe.getUnsafe();
    static final Unsafe UNSAFE;
    // 记录变量state在类TestUnsafe中的偏移值
    static final long stateOffset;
    // 变量
    private volatile long state = 0;

    // 下面的初始化块会报异常java.lang.ExceptionInInitializerError
//    static {
//        try {
//            // 获取state变量在类TestUnsafe中的偏移值
//            stateOffset = UNSAFE.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
//        } catch (Exception e) {
//            System.out.println(e.getLocalizedMessage());
//            throw new Error(e);
//        }
//    }

    // 使用反射
    static {
        try {
            // 通过反射获取Unsafe的成员变量theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 设置为可存取
            field.setAccessible(true);
            // 获取该变量的值
            UNSAFE = (Unsafe) field.get(null);
            // 获取state在TestUnsaf中的偏移量
            stateOffset = UNSAFE.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        // 创建实例，并设置state值为1
        TestUnsafe test = new TestUnsafe();
        Boolean success = UNSAFE.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(success);
    }
}
