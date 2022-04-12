package exam08;

import java.io.Serializable;

/**
 * 重载方法匹配优先级按代码顺序由大到小排列
 */
public class Overload {
    // 正常输出 ==> hello char
    public static void sayHello(char arg) {
        System.out.println("hello char");
    }
    // 发生了一次自动类型转换，'a' => 97
    public static void sayHello(int arg) {
        System.out.println("hello int");
    }
    // 发生了两次自动类型转换，'a' => 97 => 97L
    // 自动转型还能继续发生多次，按照char->int->long->float->double的顺序转型进行匹配。
    // 但不会匹配到byte和short类型的重载，因为char到byte或short的转型是不安全的。
    public static void sayHello(long arg) {
        System.out.println("hello long");
    }
    // 发生了一次自动装箱，'a'被包装为char的封装类型java.lang.Character
    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }
    /*
        java.lang.Serializable是java.lang.Character类实现的一个接口，
        当自动装箱之后发现还是找不到装箱类，但是找到了装箱类实现了的接口类型，
        所以紧接着又发生一次自动转型。
        char可以转型成int，但是Character是绝对不会转型为Integer的，
        它只能安全地转型为它实现的接口或父类。
        Character还实现了另外一个接口java.lang.Comparable<Character>，
        如果同时出现两个参数分别为Serializable和Comparable<Character>的重载方法，
        那它们在此时的优先级是一样的。
        编译器无法确定要自动转型为哪种类型，会提示类型模糊，拒绝编译。
        程序必须在调用时显式地指定字面量的静态类型，如：sayHello((Comparable<Character>)'a')，才能编译通过。
     */
    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }
    // char装箱后，自动转型为父类
    // 如果有多个父类，那将在继承关系中从下往上开始搜索，越接近上层的优先级越低。
    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }
    // 变长参数的重载优先级是最低的，字符'a'被当做了一个数组元素
    // 注意：有一些在单个参数中能自动转型，如char转型为int，但在变长参数中是不成立的
    public static void sayHello(char... arg) {
        System.out.println("hello char......");
    }

    public static void main(String[] args) {
        sayHello('a');
    }
}
