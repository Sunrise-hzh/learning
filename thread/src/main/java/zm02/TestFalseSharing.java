package zm02;

/**
 * @author huangzihua
 * @date 2021-04-02
 */
public class TestFalseSharing {
    static final int LINE_NUM = 5000;
    static final int COLUM_NUM = 5000;

    public static void main(String[] args) {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < LINE_NUM; j++) {
                array[i][j] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("cache time: " + (endTime - startTime));
    }

//    public static void main(String[] args) {
//        long[][] array = new long[LINE_NUM][COLUM_NUM];
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < LINE_NUM; i++) {
//            for (int j = 0; j < LINE_NUM; j++) {
//                array[j][i] = i * 2 + j;
//            }
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("cache time: " + (endTime - startTime));
//    }
}
