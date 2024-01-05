import java.util.*;

public class SquareNumbers {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) { // 離開Try區塊自動關閉Scanner
            int startNumberA; // 起始數字
            int endNumberB; // 結尾數字
            int count = 0; // Square Number 數量
            while (true) {
                startNumberA = sc.nextInt();
                endNumberB = sc.nextInt();
                if (startNumberA == 0 && endNumberB == 0) // if 輸入數字為(0,0)直接跳出迴圈
                    break;
                count = (int) Math.sqrt(endNumberB) - (int) Math.ceil(Math.sqrt(startNumberA)) + 1;
                /*
                 * 使用Math.sqrt函數來獲得 startNumberA 和 endNumberB 的平方根並轉型成int格式
                 * 注意這裡的startNumberA 需要使用Math.ceil來向上取整
                 * 原因是如果平方根出來有小數的話會被int無條件捨去而沒有計算到
                 * 而也要記得如果startNumberA 向上取整可能會不包括 startNumberA 自己
                 * 相減時Square Number會少1個要加回去
                 */
                System.out.println(count);
            }
        }
    }
}
