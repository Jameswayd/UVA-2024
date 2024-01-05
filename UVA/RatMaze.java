import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RatMaze {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner 開啟
        // System.out.print("請輸入迷宮的大小 N: ");
        // 我不太知道題目是要一次丟入(1)迷宮大小+迷宮數據,還是(2)迷宮大小 + Enter + 迷宮數據, 我猜是(2),所以先這樣寫
        int N = scanner.nextInt();// 輸出迷宮有幾列
        scanner.nextLine(); // 抓換行
        int[][] maze = new int[N][N]; // 開啟儲存N X N的迷宮的陣列
        // System.out.println("請輸入迷宮資料，以空格分隔數字，每行按 Enter 結束:");

        // 讀取迷宮數據
        for (int i = 0; i < N; i++) {
            String row = scanner.nextLine();
            String[] rowData = row.split(" "); // 以空白分開
            for (int j = 0; j < N; j++) {
                maze[i][j] = Integer.parseInt(rowData[j]); // 將數據存入迷宮陣列中
            }
        }

        ArrayList<String> path = solveMaze(maze, N); // 開啟ArrayList陣列儲存路徑點

        // 反轉路徑順序(因為我們是找到解才開始把點加入路徑中的)
        Collections.reverse(path);

        // 輸出路徑
        if (path.isEmpty()) { // 找不到路
            System.out.println("(0, 0)");
        } else {
            for (String step : path) {
                System.out.println(step);
            }
        }
        scanner.close();
    }

    // 找尋路徑,回傳路徑上的點
    public static ArrayList<String> solveMaze(int[][] maze, int N) {
        ArrayList<String> path = new ArrayList<>();
        boolean[][] visited = new boolean[N][N]; // 標記哪些點被走過

        // 從 (0,0) 開始尋找路徑 (陣列裡的(0,0))
        findPath(maze, 0, 0, visited, path);

        return path;
    }

    // 從指定位置 (i, j) 開始尋找迷宮中的路徑。
    public static boolean findPath(int[][] maze, int i, int j, boolean[][] visited, ArrayList<String> path) {
        // 如果超出迷宮邊界，表示找到一條路徑
        if (i < 0 || j < 0 || i >= maze.length || j >= maze[0].length) {
            return true;
        }

        // 如果當前位置是通行且未被走過
        if (maze[i][j] == 0 && !visited[i][j]) {
            visited[i][j] = true; // 標記當前位置為已訪問

            // 按照順序嘗試四個方向
            if (findPath(maze, i + 1, j, visited, path) || // 向下尋找路徑
                    findPath(maze, i - 1, j, visited, path) || // 向上尋找路徑
                    findPath(maze, i, j + 1, visited, path) || // 向右尋找路徑
                    findPath(maze, i, j - 1, visited, path)) { // 向左尋找路徑
                path.add("(" + (i + 1) + "," + (j + 1) + ")"); // 將當前位置加入路徑
                return true; // 找到一條路徑
            }
        }

        return false; // 當前位置無法走了或已被訪問過，返回 false
    }
}

/*
 * 解題Tips
 * 題目跟之前一樣用遞迴解(DFS),有點類似石油坑那題
 * 在 findPath 方法中，從指定的位置 (i, j) 開始探索迷宮。向四個方向進行移動，並以遞迴的方式繼續探索。
 * 當找到一條通往邊界的路徑時，它會將路徑上的座標點以 (x, y) 的格式加到 path 中,並反轉輸出。
 * 這題應該也可以透過stack來做可能會比較快。
 */