package exam10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangzihua
 * @date 2021-04-15
 */
public class Test01 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "你好");
        map.put("how are you?", "吃了没？");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are you?"));
    }

//    public static void method(List<String> list) {
//        System.out.println("invoke method(List<String> list)");
//    }
//
//    public static void method(List<Integer> list) {
//        System.out.println("invoke method(List<Integer> list)");
//    }
}
