package exam08;

/**
 * 方法静态调用演示
 * 1、使用javac编译源代码为字节码
 * 2、使用javap -verbose翻译字节码文件
 */
public class StaticResolution {
    public static void sayHello() {
        System.out.println("hello world!");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }
}
