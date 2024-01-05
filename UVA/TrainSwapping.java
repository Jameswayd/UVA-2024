import java.util.Scanner;

public class TrainSwapping {
    // 主要功能是把輸入陣列的數字,使用泡沫排序法排列,並計算陣列內部的交換次數,並輸出次數
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in); // Scanner 開啟
        int testCaseNumber = sc.nextInt();
        while (--testCaseNumber >= 0) { // --testCaseNumber 用testCaseNumber-- 會多做一次(前綴運算)
            int swapping = 0; // 列車交換次數(每一次testCase都會歸零重新計算)
            int trainLength = sc.nextInt(); // 列車長度
            int trainLocation[] = new int[trainLength]; // 根據列車長度創建一個存放列車位置的陣列
            for (int i = 0; i < trainLength; i++) { // 輸入列車位置至陣列
                trainLocation[i] = sc.nextInt();
            }
            for (int j = 0; j < trainLength - 1; j++) {
                // 控制冒泡後的位置(trainLength - 1是因為最後一個位置數字一定是最大的不用再比對了)
                for (int i = 0; i < trainLength - j - 1; i++) {
                    // 抓起第i個位置的車廂跟下一個位置車廂的交換比對(這裡多減J是因為前面已經排列過的陣列就不用再排了,可以直接跳過)
                    if (trainLocation[i] > trainLocation[i + 1]) { // 如果第i個車廂位置比i+1位置後面
                        int temp = trainLocation[i]; // 把i的實際位置暫存起來
                        trainLocation[i] = trainLocation[i + 1]; // 把i+1的車廂位置丟到i
                        trainLocation[i + 1] = temp;
                        // 把我們剛剛存起來的位置放到i+1(就是i跟i+1在陣列裡swap的意思)
                        swapping++; // 交換次數+1,然後繼續跟下一個比對
                    }
                }
            }
            System.out.println("Optimal train swapping takes " + swapping + " swaps.");
        }
        sc.close();// Scanner 關閉
    }
}
/*解題Tips
 * 題目是說車站員工,發現可以使用將橋旋轉180度,來修改車廂的位置
 * 1 3 2 → 1 2 3 (類似這樣就是把 3 和 2 旋轉180度變成2 和 3 來排列整個陣列)
 * 這樣的交換方法就是泡沫排序法,透過一次比較兩個元素,來排列至不用進行交換了,就代表排列好了
 * 於是我們就輸入數字,並實作泡沫排序法,然後紀錄swap的次數,並根據格式輸出,就得到答案了
 */
