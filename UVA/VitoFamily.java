import java.util.Arrays;
import java.util.Scanner;

public class VitoFamily { // 主要功能是找出一組陣列的中位數，並計算每個位置到中位數的距離總和(絕對值)
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in); // Scanner 開啟
        int testCaseNumber = sc.nextInt();
        while (--testCaseNumber >= 0) { // --testCaseNumber 用testCaseNumber-- 會多做一次(前綴運算)
            int totalDistance = 0; // 最短距離總和
            int relativeNumbers = sc.nextInt(); // 親戚數量
            int streetNumbers[] = new int[relativeNumbers]; // 根據親戚數量創建一個存放親戚家位置的陣列
            for (int i = 0; i < relativeNumbers; i++) {
                streetNumbers[i] = sc.nextInt();
            }
            Arrays.sort(streetNumbers); // 排序陣列以方便找到中位數

            if (relativeNumbers % 2 == 1) { // 奇數陣列
                int midNumber = streetNumbers[relativeNumbers / 2]; // 奇數陣列中位數(中間值)
                for (int i = 0; i < relativeNumbers; i++) {
                    totalDistance = totalDistance + Math.abs(streetNumbers[i] - midNumber); // 絕對值(Math.abs)函式,再用累加加出總和
                }
            } else { // 偶數陣列
                int midNumber = (streetNumbers[relativeNumbers / 2] + streetNumbers[(relativeNumbers - 1) / 2])
                        / 2; // 偶數陣列中位數(平均值)
                for (int i = 0; i < relativeNumbers; i++) {
                    totalDistance = totalDistance + Math.abs(streetNumbers[i] - midNumber); // 絕對值(Math.abs)函式,再用累加加出總和
                }
            }
            System.out.println(totalDistance);
        }
        sc.close(); // Scanner 關閉

    }
}

/*
 * 解題Tips
 * 這一題我們需要找出各個親戚家的距離相加之後總和為最小的距離總和
 * 所以我們先輸入各個親戚家的位置(街道號碼)到一個陣列,然後找出此陣列的中位數(不是平均數,因為平均數是最小化到每個親戚家位置的平均距離，而不是距離總和)
 * 再來我們判斷這個陣列是奇數陣列還是偶數陣列
 * - 如果陣列大小為奇數，則中位數就是排序後的中間值。
 * - 如果陣列大小為偶數，則中位數是中間兩個值的平均值。
 * 得知中位數數後計算中位數與各親戚家位置的絕對值差
 * 並把它們加總已獲得最短距離總和
 */
