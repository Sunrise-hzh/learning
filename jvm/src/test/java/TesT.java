import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author huangzihua
 * @date 2021-05-17
 */
public class TesT {
    public static void main(String[] args) {
//        for (int i = 0; i < 20; i++) {
//            String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
//            System.out.println(uuid + "<<>>" + uuid.length());
//        }
//        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        long startTime = System.currentTimeMillis();
        Set<String> uuids = new HashSet<>();
        for (int i = 0; i < 20000000; i++) {
            String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
            if (!uuids.add(uuid)) {
                System.out.println(uuid);
            }
//            System.out.println(uuid + "<<>>" + uuid.length());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("结束，耗时" + ((endTime - startTime)/1000) + "秒，长度" + uuids.size());

    }
}
