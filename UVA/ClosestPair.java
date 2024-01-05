import java.util.*;

public class ClosestPair {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner 開啟
        while (scanner.hasNext()) {
            // 讀取點的數量 
            int dataNumber = scanner.nextInt();
            if (dataNumber == 0)
                break;

            // 建立一個大小為 dataNumber 的二維陣列儲存座標點
            double[][] points = new double[dataNumber][2];

            // 輸入座標點的 x 和 y 座標
            for (int i = 0; i < dataNumber; i++) {
                double x = scanner.nextDouble(); // x座標
                double y = scanner.nextDouble(); // y座標
                points[i][0] = x;
                points[i][1] = y;
            }

            // 根據 x 座標排序座標點
            Arrays.sort(points, Comparator.comparingDouble(point -> point[0]));

            // 計算兩點之間的最小距離
            double distance = divide(points, 0, dataNumber - 1);
            if (distance >= 10000.0) { // 距離大於1萬就輸出INFINITY
                System.out.println("INFINITY");
            } else { // 小於10000就輸出兩點距離
                System.out.printf("%.4f\n", distance);
            }
        }
        scanner.close(); // 關閉 Scanner 物件
    }

    // 計算兩點之間的距離(兩點距離公式)
    public static double distance(double[] a, double[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    // 分治法找到最小距離
    public static double divide(double[][] points, int low, int high) {
        if (low >= high)
            return 99999; // 當只剩一個點時返回無窮大

        int mid = (low + high) / 2; // 區間的中間位置
        double minLeft = divide(points, low, mid); // 左邊區間的最小距離（遞迴）
        double minRight = divide(points, mid + 1, high); // 右邊區間的最小距離(遞迴）
        return combine(points, low, mid, high, minLeft, minRight); // 傳左邊和右邊最小距離然後統整
    }

    // 合併左右兩部分的最小距離
    public static double combine(double[][] points, int low, int mid, int high, double minLeft, double minRight) {
        double d = Math.min(minLeft, minRight); // 找到左右兩邊最小距離的最小值
        double line = (points[mid][0] + points[mid + 1][0]) * 0.5; // 計算中線的X座標值

        double minDistance = d;
        for (int i = mid + 1; i <= high && points[i][0] < line + d; i++) { // 搜尋右半部分與中線左側距離小於 d 的點
            for (int j = mid; j >= low && points[j][0] > line - d; j--) { // 搜尋左半部分與中線右側距離小於 d 的點
                double temp = distance(points[i], points[j]); // 計算左右兩邊點之間的距離
                if (temp < minDistance) { // 更新最小距離
                    minDistance = temp;
                }
            }
        }
        return minDistance;
    }
}
/*
 * 解題Tips
 * 這是很經典的演算法題目,解這題有使用掃描法(每個點都掃過一次找最小)或是分治法
 * 題目叫我們一定要用分治法,那分治法就是一定要把題目切成更小的問題然後再統合成一個答案
 * 這題的話我們根據Ｘ座標排列題目給的測資點,並使用divde方法找到左右邊最小
 * 找到最小之後,把跨過中線的點都給算過一次看有沒有比目前最段距離短,有的話再更新,沒有就代表找到解了
 */
