package com.example.arithmetic.knapsack;

public class Package01 {

    int number;// 表示物品的数量
    int maxWeight;// 表示背包的最大重量
    int[] weights; // 表示每一个物品的重量
    int[] values; // 表示每一个物品的价值
    int[][] func; // 用来表示状态转移方程

    public void sovle() {

        // 初始化各初始条件
        init();

        // 首先构造第一行上
        int i = 0, j = 0;
        for (j = 1; j <= maxWeight; j++) {
            if (j < weights[i]) {
                func[i][j] = 0;
            } else {
                func[i][j] = values[i];
            }
        }

        // 然后对剩下的n-1个物品填充
        for (i = 1; i < number; i++) {
            for (j = 1; j <= maxWeight; j++) {
                if (j < weights[i]) {
                    func[i][j] = func[i - 1][j];
                } else {
                    func[i][j] = Math.max(func[i - 1][j - weights[i]] + values[i], func[i - 1][j]);
                }
            }
        }

        // 输出打印地推二维表
        for (i = 0; i < number; i++) {
            for (j = 1; j <= maxWeight; j++) {
                System.out.print(func[i][j] + "\t");
            }
            System.out.println();
        }
    }

//    public void init() {
//        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();// 初始化物品的数量
//        m = sc.nextInt();// 初始化背包的最大重量
//        w = new int[n];
//        v = new int[n];
//        f = new int[n][m + 1]; // 初始化状态转移方程
//
//        // 初始化每个物品重量
//        for (int i = 0; i < n; i++) {
//            w[i] = sc.nextInt();
//        }
//        // 初始化每个物品的价值
//        for (int i = 0; i < n; i++) {
//            v[i] = sc.nextInt();
//        }
//        sc.close();
//    }

    public void init() {
        //物品重量
        weights = new int[]{1, 3, 4};
        //物品价格
        values = new int[]{3, 3, 3};
        //物品个数
        number = weights.length;// 初始化物品的数量
        maxWeight = 20;// 初始化背包的最大重量
//        w = new int[number];
//        v = new int[number];
        func = new int[number][maxWeight + 1]; // 初始化状态转移方程
    }

    public static void main(String[] args) {
        new Package01().sovle();
    }
}