import java.util.*;

public class MutantFlatworld {
    /*
     * 主要功能是輸入數據後,使用HashMap對應的方位的數字和左轉右轉(數字的相加相減)來控制機器人的方向
     * 另外開啟布林的二維陣列紀錄機器人不見的點,這樣可以可以利用先走的機器人的紀錄確認不見的點以加速判斷
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in); // Scanner 開啟
        int maxX = sc.nextInt(); // 讀取地圖的最大 X 座標
        int maxY = sc.nextInt(); // 讀取地圖的最大 Y 座標
        boolean lostPoint[][] = new boolean[maxX + 1][maxY + 1]; // 二維陣列紀錄機器人不見的點

        // HashMap 用於將方向字母映射為數字
        HashMap<Character, Integer> directionMap = new HashMap<>();
        directionMap.put('N', 0);
        directionMap.put('W', 1);
        directionMap.put('S', 2);
        directionMap.put('E', 3);

        while (sc.hasNext()) { // 只要有輸入數字就讀入
            boolean lost = false; // 用於標記機器人是否不見
            int robotX = sc.nextInt(); // 讀取機器人的 x 座標
            int robotY = sc.nextInt(); // 讀取機器人的 y 座標
            char dirChar = sc.next().charAt(0); // 讀取機器人的預設方向字母
            int dir = directionMap.get(dirChar); // 使用HashMap取得方向對應的數字表示
            String instructions = sc.next(); // 讀取機器人的行走方式

            for (int i = 0; i < instructions.length() && !lost; i++) { // 跑過每個行走方式，並檢查是否有機器人不見
                switch (instructions.charAt(i)) {
                    case 'R':// 右轉
                        if (dir - 1 < 0) {
                            dir = 3; // 如果現在方向是北（0），則右轉後變為東（3）
                        } else {
                            dir = dir - 1; // 不然就右轉
                        }
                        dirChar = getDirectionChar(dir); // 根據新的方向數字獲取對應的方向字母
                        break;

                    case 'L': // 左轉
                        if (dir + 1 > 3) {
                            dir = 0; // 如果現在方向是東（3），則左轉後變為北（0）
                        } else {
                            dir = dir + 1; // 不然就左轉
                        }
                        dirChar = getDirectionChar(dir); // 根據新的方向數字獲取對應的方向字母
                        break;

                    case 'F': // 前進
                        if (dir == 0) { // 如果現在方向是北
                            if (robotY + 1 > maxY && !lostPoint[robotX][robotY]) { // 如果前進後機器人越出地圖範圍且該位置未曾有機器人不見
                                lostPoint[robotX][robotY] = true; // 標記機器人不見
                                System.out.println(robotX + " " + robotY + " " + dirChar + " LOST"); // 輸出機器人不見
                                lost = true; // 標記機器人不見
                            } else {
                                if (robotY != maxY) { // 如果沒超出Y邊界
                                    robotY = robotY + 1; // 否則，向北前進
                                }
                            }

                        } else if (dir == 1) { // 如果現在方向是西
                            if (robotX == 0 && !lostPoint[robotX][robotY]) { // 如果前進後機器人越出地圖範圍且該位置未曾有機器人不見
                                lostPoint[robotX][robotY] = true; // 標記機器人不見
                                System.out.println(robotX + " " + robotY + " " + dirChar + " LOST"); // 輸出機器人不見
                                lost = true; // 標記機器人不見
                            } else {
                                if (robotX != 0) { // 如果機器人X軸不是在0
                                    robotX = robotX - 1; // 否則，向西前進
                                }
                            }

                        } else if (dir == 2) { // 如果現在方向是南
                            if (robotY == 0 && !lostPoint[robotX][robotY]) { // 如果前進後機器人越出地圖範圍且該位置未曾有機器人不見
                                lostPoint[robotX][robotY] = true; // 標記機器人不見
                                System.out.println(robotX + " " + robotY + " " + dirChar + " LOST"); // 輸出機器人不見
                                lost = true; // 標記機器人不見
                            } else {
                                if (robotY != 0) { // 如果機器人Y軸不是在0
                                    robotY = robotY - 1; // 否則，向南前進
                                }
                            }

                        } else { // 如果現在方向是東
                            if (robotX + 1 > maxX && !lostPoint[robotX][robotY]) { // 如果前進後機器人越出地圖範圍且該位置未曾有機器人不見
                                lostPoint[robotX][robotY] = true; // 標記機器人不見
                                System.out.println(robotX + " " + robotY + " " + dirChar + " LOST"); // 輸出機器人不見
                                lost = true; // 標記機器人不見
                            } else {
                                if (robotX != maxX) { // 如果沒超出X邊界
                                    robotX = robotX + 1; // 否則，向東前進
                                }
                            }
                        }
                        break;
                }

                if (i == instructions.length() - 1) {
                    if (!lost) { // 機器人走過的路沒有使機器人不見
                        System.out.println(robotX + " " + robotY + " " + dirChar); // 如果已經處理完所有行走指令，且機器人未不見，則輸出當前位置和方向
                    }
                }
            }
        }
        sc.close();
    }

    // 根據方向索引取得方向字元的方法(輸入方向字母)
    private static char getDirectionChar(int dir) {
        char[] directionChars = { 'N', 'W', 'S', 'E' }; // 定義方向字母的陣列
        return directionChars[dir]; // 根據方向索引獲取對應的方向字母(輸出對照方向字母的索引)
    }
}

/*
 * 解題Tips
 * 題目是給我們地圖的右上X,Y軸以畫出不會不見的點,然後給予機器人的位置、方位、和接下來的行走路徑
 * 所以這題我們需要跑過機器人的方向和紀錄不見的點,紀錄不見的點我們上面解釋過使用二維的布林陣列來儲存
 * 而機器人轉彎方向的問題,這邊我想要減少SWITCH CASE的量,所以我選擇使用Hashmap的方式用數字對應方位
 * 31-36行就是一種使用數字加減的範例讓我們取得正確的轉彎方向
 * 最後前進的部分,因為我們已經確定方向了,所以我們只要確定現在走的點不是LOST點然後跟著繼續走就好了
 * 如果是LOST點那就標記機器人在哪個點不見,下個機器人走到同個點的時候就可以直接標記出來了
 */