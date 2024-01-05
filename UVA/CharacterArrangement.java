import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CharacterArrangement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner 開啟
        while (true) {
            String input = scanner.nextLine(); // 輸入英文字串
            if (input.equals("")) { // 如果輸出是空白就跳出
                break;
            }
            Set<String> result = new HashSet<>();
            generateEnglishstring(input, 0,result); // 排列(從str.length(0)開始排)
            for (String combination : result) {
                System.out.println(combination); // 輸出生成的字串
            }   
           
        }
        scanner.close();
    }

    /*
     * 讀入字串和目前index,如果已經排列到最後一位置就輸出排列,不然就交换i和currentIndex字母位置,然後持續做
     * 做到排列到最後一個位置,然後輸出,再還原成原本的排列方式,直到currentIndex到最後一個字母(沒得換了)
     */
    public static void generateEnglishstring(String str, int currentIndex,Set<String> result) {
        if (currentIndex == str.length() - 1) { // 如果已經排列到最後一位置
            result.add(str); // 輸出排列
        } else {
            for (int i = currentIndex; i < str.length(); i++) { // 如果目前排列位置小於字串長度
                str = swap(str, currentIndex, i); // 交换字母位置，輸出一個新的排列
                generateEnglishstring(str, currentIndex + 1, result); // 對下一個字母做一樣的交換動作(遞迴)
                str = swap(str, currentIndex, i); // 還原成原本的排列方式(不還原會有一大堆重複的)
            }
        }
    }

    /*
     * 讀入字串(轉成字元)和要交換字母的兩個位置,利用temp暫存i的數據,然後i代替j,再把temp填回j完成交換
     */
    public static String swap(String str, int i, int j) {
        char[] charEnglish = str.toCharArray(); // 轉成char型態(Java的string沒辦法動)
        char temp = charEnglish[i];
        charEnglish[i] = charEnglish[j];
        charEnglish[j] = temp;
        return new String(charEnglish); // 交換完回傳
    }
}

/*
 * 題目跟上禮拜一樣用遞迴解(有點類似DFS),利用for迴圈的i和currentIndex進行swap,換到currentIndex已經排列到最後一位置
 * 再換下一個i跟currentIndex交換,這樣會不停的嘗試每一種的排列方法在輸出
 * 這邊記得每輸出完一次要記得換回來不然會有重複的 (ex:ABC換成ACB如果沒重整可能會被換回ABC 這樣就會有兩個ABC)
 */
