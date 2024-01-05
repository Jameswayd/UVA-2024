import java.util.Scanner;

public class Coin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner 開啟
        String[] coinsInput = scanner.nextLine().split(" "); // 讀取第一列輸入，不同硬幣種類
        int[] coins = new int[coinsInput.length]; // 開啟儲存硬幣種類陣列
        for (int i = 0; i < coinsInput.length; i++) {
            coins[i] = Integer.parseInt(coinsInput[i]); // 字串陣列轉整數陣列
        }

        int amount = scanner.nextInt(); // 讀取第二列輸入，要組合的總金額
        int result = change(amount, coins); // 進入change方法計算組合數
        System.out.println(result); // 輸出答案
        scanner.close(); // 關閉 Scanner 物件
    }

    public static int change(int amount, int[] coins) {
        int[] dpArray = new int[amount + 1]; // 開啟儲存計算組合的陣列（DP)
        dpArray[0] = 1; // 0元只有一種方法（初始化）

        for (int coin : coins) { // 掃過所有硬幣種類
            for (int j = coin; j <= amount; j++) { // 從當前硬幣面額掃到要組合的總金額
                dpArray[j] += dpArray[j - coin]; // 更新組合方式數量(狀態轉移)
            }
        }
        return dpArray[amount];
    }
}

/*
 * 解題Tips
 * 題目老師有提示可以用背包問題解（實際上就是DP）
 * 那用DP的話就需要縮小問題,所以我們開啟dpArray陣列計算各個amount
 * 在各個coin內所累計的組合方法,在用前面算出來的去累加已得到後面amount較大的組合結果
 * 實際上就是一種遞迴的變形(會把前面算出來的結果存下來)
 * 注意這裡一定要從1開始算(開啟coins陣列,不然會重複計算）
 */