import java.util.Scanner;

public class OilDeposits {
    /*
     * 主要功能是輸入數據後,先用grid讀每一行的地圖資訊(有沒有點是'@')
     * 有的話呼叫DFS,並根據DFS的方法跑過8個鄰近的點,在不斷遞迴直到沒有連接,然後把石油坑+1
     * 然後繼續尋找有'@'的格子直到所有格子都被找過
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner 開啟

        while (true) { // 進入無限迴圈
            int numRows = scanner.nextInt(); // 讀取地圖的行數
            int numColumns = scanner.nextInt(); // 讀取地圖的列數
            if (numRows == 0 && numColumns == 0) // 如果行數和列數都是零，則跳出迴圈
                break;

            String[] grid = new String[numRows]; // 創建一個字串陣列來存儲地圖的每一行
            boolean[][] visited = new boolean[numRows + 1][numColumns + 1]; // 創建一個布林陣列來記錄每個位置是否已經走過
            int componentCount = 0; // 石油坑紀錄

            for (int i = 0; i < numRows; i++) {
                grid[i] = scanner.next(); // 讀取每一行的地圖資訊
            }

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    if (!visited[i][j]) { // 如果該位置沒有被走過
                        visited[i][j] = true; // 標記該位置為已經走過了
                        if (grid[i].charAt(j) == '@') { // 如果該位置是 '@'
                            componentCount++; // 增加石油坑
                            dfs(i, j, numRows, numColumns, grid, visited); // 呼叫dfs函式來標記所有跟'@'相連的格子
                        }
                    }
                }
            }
            System.out.println(componentCount); // 輸出石油坑紀錄
        }
        scanner.close(); // 關閉 Scanner
    }

    /*
     * DFS(深度優先搜尋,帶入目前走到的格子(x,y)和地圖的行列數和地圖每一行的資訊和每一個點是否有被走過)
     * 我們帶入主函式最先走過的石油坑點,並把連接石油坑位置都走過一次,如果8個裡面有也是'@'的點,就從那個點繼續延伸,繼續呼叫DFS
     * 延伸到直到沒有連結的石油坑,在跳回主函式把componentCount + 1 ,繼續執行直到每個點都被走過
     */
    public static void dfs(int x, int y, int numRows, int numColumns, String[] grid, boolean[][] visited) {
        visited[x][y] = true; // 標記目前位置為已走過
        int[] dx = { 1, 1, 1, -1, -1, -1, 0, 0 }; // 定義八個可能的水平方向移動
        int[] dy = { 1, 0, -1, 1, 0, -1, 1, -1 }; // 定義八個可能的垂直方向移動
        int newX, newY;

        for (int i = 0; i < 8; i++) { // 8個連接石油坑位置都要走過一次
            newX = x + dx[i];
            newY = y + dy[i];
            if (newX >= 0 && newX < numRows && newY >= 0 && newY < numColumns && !visited[newX][newY]) { // 不超過地圖X和Y的上限和下限和還沒被走過
                visited[newX][newY] = true; // 標記新位置為已走過
                if (grid[newX].charAt(newY) == '@') { // 如果新位置是 '@'
                    dfs(newX, newY, numRows, numColumns, grid, visited); // 遞迴呼叫DFS搜索根據新點的位置標記相連的 '@' 格子
                }
            }
        }
    }
}
/*
 * 解題Tips
 * 題目是說鄰近的8個點都算是同一個石油坑,所以代表那8個石油坑再延伸的跟原本也算是同一個石油坑,所以我們就需要用遞迴來不斷跑過那鄰近的8個點
 * 直到沒有連結,所以在主函式把數據丟進去後,我們就用雙層迴圈跑過各個點(注意這裡是看行),遇到石油坑就進DFS函式,
 * 其實這裡 DFS or BFS 都可以,但題目比較小而且DFS比較好寫
 * 然後走過8個相連結的點在不斷遞迴,直到沒有連結,再回去主函式走過我們沒有走過的點,重覆到每個點都被走過然後輸出componentCount
 */