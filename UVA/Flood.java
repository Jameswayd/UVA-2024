import java.util.Arrays;
import java.util.Scanner;

public class Flood {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in); // Scanner 開啟
        int dataNumber = 1; // 測資比數
        while (true) { // 進入無限迴圈
            int columns = sc.nextInt(); // 讀取土地的列數
            int rows = sc.nextInt(); // 讀取土地的行數
            // 當列和行皆為零時，結束迴圈
            if (columns == 0 && rows == 0) {
                break;
            }

            int area = columns * rows; // 計算土地總數
            int[] array = new int[area]; // 儲存土地高度的陣列

            // 讀取每塊土地的高度
            for (int i = 0; i < area; i++) {
                array[i] = sc.nextInt();
            }

            int totalWater = sc.nextInt(); // 總水量

            //一塊土地
            if (area == 1) {
                double floodingHeight = array[0] + totalWater / 100.0;

                System.out.println("Region " + dataNumber);
                System.out.printf("Water level is: %.2f meters.\n", floodingHeight);
                System.out.printf("100.00 percent of the region is under water.\n\n");

                dataNumber++;
                continue;
            }
            Arrays.sort(array); // 將土地高度進行排序

            double floodingHeight = 0; // 最後的淹水高度
            int floodedArea = 0; // 被淹沒的土地數量

            for (int i = 0; i < area - 1; i++) {
                // 如果排序後相鄰的土地高度相同，跳過重複扣水的情況(同時淹水)
                if (array[i] == array[i + 1]) {
                    continue;
                }

                // 計算淹水所需的水量
                int waterNeeded = (array[i + 1] - array[i]) * (i + 1) * 100;

                // 若剩餘水量不足以填滿當前區域，計算淹水高度並跳出迴圈
                if (totalWater - waterNeeded < 0) {
                    floodingHeight = array[i] + totalWater / (100.0 * (i + 1));
                    floodedArea = i + 1;
                    break;
                }

                totalWater -= waterNeeded; //剩餘水量

                // 若水量足夠填滿所有區域，計算最後一個區域的淹水高度
                if (i == area - 2) {
                    floodingHeight = array[i + 1] + totalWater / (100.0 * (i + 2));
                    floodedArea = i + 2;
                }
            }

            double percentage = (floodedArea * 100.0) / area;

            // 輸出
            System.out.println("Region " + dataNumber);
            System.out.printf("Water level is: %.2f meters.\n", floodingHeight);
            System.out.printf("%.2f percent of the region is under water.\n\n", percentage);

            dataNumber++;
        }
        sc.close();
    }
}


/*
 * 解題Tips
 * 題目跟之前的題目比較不太一樣,之前滿多結構跟演算法的問題的,這題則是在考數學邏輯,有點小複雜,用紙算比較容易一點
 * 簡單來說我們只要把土地根據高度來排然後變得像梯田那樣,然後假設水會一層一層的淹上去,然後就可以推出每層
 * 淹水所需要的水量然後再用總水量去扣就好了,這裡比較需要要注意的是兩個例外(土地全淹滿跟相鄰土地同高度)
 * 相鄰土地同高度的話代表兩個都一起淹了,記得不要扣到兩次水量
 * 然後土地全淹滿的話在倒數第二層我們就要確認了,然後把剩下的水量全部灌在最後一層上(記得另外算)
 * 不然我們推出來的公式的話,淹水高度會錯(會少最後一層的那一段多出來的)
 */