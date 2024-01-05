import java.util.Scanner;

public class MergeSort {
    // 開啟暫存陣列,並把原始陣列傳給mergeSortSwaps
    static long mergeSort(int[] array) {
        int[] tempArray = new int[array.length]; // 暫存陣列跟n一樣大
        return mergeSortSwaps(array, tempArray, 0, array.length - 1);
    }

    // 計算交換次數和切開陣列,先切割陣列,從左邊,右邊,到對整個陣列對swap進行累加
    static long mergeSortSwaps(int[] array, int[] tempArray, int left, int right) {
        long swaps = 0; // 交換次數
        if (left < right) { // 右大於左(如果都已經切成單一個元素,就代表已經排好了(merge過了))
            int mid = (left + right) / 2; // 找中間
            swaps += mergeSortSwaps(array, tempArray, left, mid); // 遞迴對左邊進行排序
            swaps += mergeSortSwaps(array, tempArray, mid + 1, right); // 遞迴對右邊進行排序
            swaps += merge(array, tempArray, left, mid, right); //// 遞迴對陣列進行排序
        }
        return swaps; // 回傳交換次數
    }

    // 合併兩個已排序的子陣列，返回交換次數
    static long merge(int[] array, int[] temp, int left, int mid, int right) {
        long swaps = 0;
        int i = left; // 左子陣列的起始位置
        int j = mid + 1; // 右子陣列的起始位置
        int k = left; // 合併時元素放置位置

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) { // 若左邊小於等於右邊,不需要交換,將左邊元素放入 temp 陣列
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++]; // 若右邊小於左邊，需交換，將右邊元素放入 temp 陣列
                swaps += mid - i + 1; // 計算交換次數(看右邊子陣列剩餘多少元素再加上自己)
            }
        }

        while (i <= mid) { // 將剩餘的左邊元素放入 temp 陣列(把切開已經排序好的陣列拼起來)
            temp[k++] = array[i++];
        }
        while (j <= right) { // 將剩餘的右邊元素放入 temp 陣列(把切開已經排序好的陣列拼起來)
            temp[k++] = array[j++];
        }

        // 將合併後的結果放回原陣列
        for (i = left; i <= right; i++) {
            array[i] = temp[i];
        }
        return swaps;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner 開啟

        while (true) { // 進入無限迴圈
            int input = scanner.nextInt();
            if (input == 0) { // 0就跳出
                break;
            }

            int[] array = new int[input]; // 開啟一個input大小的陣列
            for (int i = 0; i < input; i++) {
                array[i] = scanner.nextInt(); // 輸入陣列數字
            }

            long swaps = mergeSort(array); // 排列計算
            System.out.println(swaps); // 輸出
        }
        scanner.close();
    }
}

/*
 * 解題Tips
 * 題目直觀就是要使用Merge sort並寫出Merge sort的運作,然後算出排列的時候Swap了幾次
 * 運作方式也如題目所說,將數字陣列切成二個子序列....切到變成一個一個的，然後進行數字交換，在使用遞迴合併子序列
 * 基本上我們這裡比較需要在意的是Swaps的計算是在切完變成一個一個然後各個合併那個時候進行的
 * 在這裡就是mergeSortSwaps這個函式來進行swap的計算,然後merge來進行合併和交換元素.
 */