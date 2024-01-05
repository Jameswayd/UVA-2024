import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CharacterArrangementCatch {
    // 使用遞迴取字母,並把取過的字標記曾使用,防止重複選取
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner 開啟
        while (true) {
            String input = scanner.next(); // 輸入英文字串
            int number = scanner.nextInt(); // 輸入取幾個字母
            if (input.equals("")) { // 如果輸出是空白就跳出
                break;
            }
            Set<String> result = new TreeSet<>();// 組合Set
            boolean[] used = new boolean[input.length()]; // 重複紀錄
            generateEnglishstring(input, number, "", 0, used, result); // 生成字串(從index:0開始排)
            for (String combination : result) {
                System.out.println(combination); // 輸出生成的字串
            }
        }
        scanner.close();
    }

    /*
     * 從M中取N的子字串組合，使用遞迴組合生成
     * 讀入M和N,還有currentCombination(要生成的組合),還有index和數字有沒有用過,還有Set
     */
    public static void generateEnglishstring(String str, int N, String currentCombination, int currentIndex,
            boolean[] used, Set<String> result) {
        if (N == 0) {
            result.add(currentCombination); // 加入組合到Set
            return;
        }

        for (int i = currentIndex; i < str.length(); i++) {
            if (!used[i]) { // 如果字沒有被使用
                used[i] = true; // 將字標記為已使用
                generateEnglishstring(str, N - 1, currentCombination + str.charAt(i), i + 1, used, result); // 遞迴調用，選擇下一個字符
                used[i] = false; // 恢復字的使用標記，以便在不同的遞迴中選擇相同的字

                // 跳過相同的字，確保在同一層次不會重複選擇相同的字符 ex:ACCCCD已經選過一次ACC就不會再選到ACC
                while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                    i++;
                }
            }
        }
    }
}

/*
 * 解題Tips
 * 題目是說輸入英文字串,然後選幾個字母下來,看看能選擇多少組合
 * 一樣用遞迴,輸入資料後每一次遞迴就取一個字母取到N=0
 * 上禮拜比較不小心忘記有重複的要標記然後不輸出(用Set),這禮拜把她補回來,
 * 這裡在更新紀錄時也要小心currentIndex跟currentIndex+1不能是一樣的不然就會出現上禮拜連續出現兩個ACC的狀況
 * (其實用set就不會有這個狀況了)
 * 且題目說可以假設字串是由相異的字母所組成,所以也不會有重複
 */
