import java.util.*;

public class studentExchange {
    // 使用 Map 存學生的原始位置和目標位置，若能成功配對則移除對應的鍵
    public static String canExchangeStudents(int n, int[][] exchanges) {
        Map<Integer, Integer> exchangeMap = new HashMap<>();// 建立一個HashMap存學生起始位置和想要前往的位置

        // 對所有學生的交換資料進行檢查
        for (int[] exchange : exchanges) {
            int original = exchange[0];
            int destination = exchange[1];

            // 檢查是否有對應的交換，如果有則移除，否則加入Map中
            if (exchangeMap.containsKey(destination) && exchangeMap.get(destination) == original) {
                exchangeMap.remove(destination); // 交換配對成功，移除對應鍵值對
            } else {
                exchangeMap.put(original, destination); // 未找到對應交換，加入Map中
            }
        }

        return exchangeMap.isEmpty() ? "YES" : "NO"; // 如果Map為空，表示所有學生都有對應的交換
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner 開啟

        while (true) { // 進入無限迴圈
            int n = scanner.nextInt();
            if (n == 0) {
                break;
            }

            int students[][] = new int[n][2]; // 開啟每行有兩列的二維整數陣列
            for (int i = 0; i < n; i++) { // 輸入資料
                students[i][0] = scanner.nextInt(); // 學生的原始位置
                students[i][1] = scanner.nextInt(); // 學生想要前往的位置
            }

            // 檢查是否可以實現學生交換，並輸出結果
            String result = canExchangeStudents(n, students);
            System.out.println(result);
        }

        scanner.close();
    }
}


/*
 * 解題Tips
 * 題目老師其實有提示可以以implements Comparable 的方式來解
 * 但我自己是認為相較於比較物件的方法,我想到了一個我認為更簡單的解法
 * 雖然可能會比Comparable多浪費一點記憶體,就是我開啟兩個陣列
 * 一個儲存學生的原始位置,另外一個儲存學生想要前往的位置並以array[i]來分辨是哪個學生的資料
 * 然後再以HashMap鍵值對的概念來對應學生位置,如果可以互換就刪除對應鍵值對
 * 這樣如果HashMap裡面沒東西了,代表大家都有對應的交換,反之則有人沒辦法交換
 */