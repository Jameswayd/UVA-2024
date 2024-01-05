import java.util.ArrayList;
import java.util.Scanner;

public class TheSkylineProblem {
    public static void main(String[] args) {
        // 主要功能是把輸入陣列的數字存入陣列,並更新區域內的最高高度,並輸出區域內的最高高度
        int left, height, right; // 左邊界(X軸),高度(Y軸),右邊界(X軸)
        ArrayList<Integer> highestPosition = new ArrayList<Integer>(); // 整數的動態陣列(存各個X軸的最高位置)
        Scanner sc = new Scanner(System.in); // Scanner 開啟
        int maxRight = 0; // 紀錄最大的右邊界

        while (sc.hasNext()) {// 只要有輸入數字就讀入
            left = sc.nextInt();
            height = sc.nextInt();
            right = sc.nextInt();
            /*
             * if (left == 0 && height == 0 && right == 0) {
             *  break; // 當輸入0 0 0的時候直接跳出輸入迴圈(測試用)
             * }
             */

            // 更新最大右邊界
            if (right > maxRight) {
                maxRight = right;
            }

            ensureSize(highestPosition, maxRight + 1); // 確認陣列大小

            for (int i = left; i < right; i++) { // 從輸入的X座標最左邊檢查到最右邊
                if (highestPosition.get(i) < height) { // 檢查這裡面目前的高度是否小於輸入的height
                    highestPosition.set(i, height); // 小於的話就更新最高高度
                }
            }
        }

        // 輸出
        for (int i = 1; i <= maxRight; i++) { // 從X=1檢查到x=maxRight
            // 如果相鄰的陣列數字不相等代表有建築有高度的變化,就輸出
            if (!highestPosition.get(i - 1).equals(highestPosition.get(i))) {
                System.out.print(i + " " + highestPosition.get(i));
                if (i < maxRight) { // 新增高度和X軸中間的空格
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
        sc.close();// Scanner 關閉
    }

    private static void ensureSize(ArrayList<Integer> list, int size) { // 要求陣列size = maxRight + 1
        while (list.size() < size) { // 小於就多填一個0到陣列尾巴,直到達到我們要求的陣列size
            list.add(0);
        }
    }
}

/*
 * 解題Tips
 * 題目是說輸入L, H, R,從L蓋到R,蓋一棟高度為H的大樓,然後輸出各個點的的最大高度
 * 那我們把它想的簡單一點,我們不就每輸入一筆數據就更新存放在陣列裡面的最大高度就好了
 * 所以我們就開個陣列,並檢查已經存進去的高度是否有比新進來的數據低,有的話我們在替換掉然後更新就好了
 * 最後如果相鄰的陣列數字相減不等於0,代表高度有變化,就輸出(不換行)
 * 這裡比較要注意空格的部分,不小心就會少個空格
 */