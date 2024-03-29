package exam08;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author huangzihua
 * @date 2021-04-14
 */
public class Test2 {
    class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }
    class Father extends GrandFather {
        void thinking() {
            System.out.println("i am father");
        }
    }
    class Son extends Father {
        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable ignored) {
            }
        }
    }

    public static void main(String[] args) {
        (new Test2().new Son()).thinking();
    }
}
