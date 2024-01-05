import java.util.*;

public class NumberMaze {

    public static class Node implements Comparable<Node> { // 定義一個節點類別，實作 Comparable 介面
        int i; // 行
        int j; // 列
        int cost; // 走到這個點的成本(priority)

        public Node(int i, int j, int cost) { // 節點建構子
            this.i = i;
            this.j = j;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) { // 實作 compareTo 方法，用於 PriorityQueue 的排序
            return this.cost - o.cost; // 依照 cost 排序
        }
    }

    // 判斷座標是否超出地圖範圍
    public static boolean isValid(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    // Dijkstra 演算法
    public static int dijkstra(int[][] map, int n, int m) {
        int[][] distance = new int[1000][1000]; // 儲存最短路徑長度(這個圖會累積前面的cost)
        int[] dx = { 0, 0, 1, -1 }; // x 方向移動的增量
        int[] dy = { 1, -1, 0, 0 }; // y 方向移動的增量

        PriorityQueue<Node> Queue = new PriorityQueue<>(); // 優先級佇列，用於選擇最小的路徑
        Queue.add(new Node(0, 0, map[0][0])); // 將起點加入優先級佇列
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE); // 初始化距離為最大值(等等跑過會更新)
        }
        distance[0][0] = map[0][0]; // 起點的距離為起點的值

        while (!Queue.isEmpty()) {
            Node currence = Queue.poll(); // 取出當前路徑最短的節點
            if (currence.i == n - 1 && currence.j == m - 1) // 如果到達終點，返回最小路徑
                return currence.cost;

            if (currence.cost > distance[currence.i][currence.j]) // 如果當前路徑的 cost 大於已知最短路徑的 cost，跳過這個點
                continue;

            for (int k = 0; k < 4; k++) { // 嘗試四個方向的移動
                int x = currence.i + dx[k];
                int y = currence.j + dy[k];
                if (isValid(x, y, n, m) && (currence.cost + map[x][y]) < distance[x][y]) { // 如果移動合法且可以獲得更短的路徑，更新最短路徑
                    distance[x][y] = currence.cost + map[x][y];
                    Queue.add(new Node(x, y, distance[x][y])); // 將新節點加入優先級佇列
                }
            }
        }

        return -1; // 如果無法到達終點，返回 -1
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // 讀取測試案例數量

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt(); // 讀取行數
            int m = scanner.nextInt(); // 讀取列數
            int[][] map = new int[1000][1000]; // 創建地圖二維陣列(這個圖就單純計每個點的cost)
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    map[j][k] = scanner.nextInt(); // 讀取地圖資料
                }
            }

            System.out.println(dijkstra(map, n, m)); // 印出最短路徑
        }

        scanner.close(); // 關閉 Scanner
    }
}

/*
 * 解題Tips
 * 題目老師要求Dijkstra,所以BPS在這邊就不能用
 * 這邊我先實作了Node類別,讓結構簡單一點,當然用 PriorityQueue<int> Queue,並定義以cost來比較也可以
 * 再來進入Dijkstra的部分,跟走迷宮很像定義x,y的行走(上下左右),去掉超出地圖的點不斷尋找
 * 最小的cost加入Queue,在累加到終點,並且輸出答案
 */