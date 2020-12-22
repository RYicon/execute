package com.example.arithmetic.changecoin;

import com.alibaba.fastjson.JSON;

public class CC_Memory {
    public static int minCost[];

    public static void main(String[] args) {

//        /*
//         * int amount 当前剩余总金额
//         * int[] coins 不同面值硬币数组
//         * int count 当前次数
//         * 每种硬币的个数i < amount/coins[idx]
//         * 如果当前当前金额为0时，比较当前方式的次数和上一种方式的次数
//         */
//        int amount = 11;
//        minCost = new int[amount];
//        int i = changeCoinsV3(new int[]{1, 2, 5}, amount);
//        System.out.println("总：" + i + "步骤：" + JSON.toJSONString(minCost));
//
//        System.out.println(changeCoinsV4(new int[]{1, 2, 5}, amount, new int[amount]));

        int amount = 23;
        System.out.println("dp:" + changeCoinsDp(new int[]{3, 6}, amount));


    }


    /**
     * 记忆法找零钱,使用一个全局变量保存每个subAmount的需要的硬币
     *
     * @param coins
     * @param currAmount
     * @return
     */
    static int changeCoinsV3(int[] coins, int currAmount) {
        if (currAmount < 0)
            return -1;

        if (currAmount == 0) {
            return 0;
        }

        //上一步结果
        if (minCost[currAmount - 1] != 0) {
            return minCost[currAmount - 1];
        }

        int currMin = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {

            int res = changeCoinsV3(coins, currAmount - coins[i]);
            if (res >= 0 && res < currMin) {
                //此次结果=上次结果+1
                currMin = res + 1;
            }
        }

        minCost[currAmount - 1] = currMin == Integer.MAX_VALUE ? -1 : currMin;
        return minCost[currAmount - 1];

    }

    /**
     * 记忆法找零钱
     *
     * @param coins
     * @param currAmount
     * @return
     */
    static int changeCoinsV4(int[] coins, int currAmount, int[] memo) {
        if (currAmount < 0)
            return -1;

        if (currAmount == 0) {
            return 0;
        }
        //上一步结果
        if (memo[currAmount - 1] != 0) {
            return memo[currAmount - 1];
        }

        int currMin = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {

            int res = changeCoinsV3(coins, currAmount - coins[i]);
            if (res >= 0 && res < currMin) {
                //此次结果=上次结果+1
                currMin = res + 1;
            }
        }

        memo[currAmount - 1] = currMin == Integer.MAX_VALUE ? -1 : currMin;
        return memo[currAmount - 1];

    }


    /**
     * 记忆法找零钱,使用一个全局变量保存每个subAmount的需要的硬币
     * 对于一个子问题，在amount>coin时 min（amount）=dp[amount-coin]+1
     *
     * @param coins
     * @param amount
     * @return
     */
    static int changeCoinsDp(int[] coins, int amount) {
        if (amount < 0)
            return -1;

        int[] dp = new int[amount + 1];
        dp[0] = 0;
        //对于总金额，从1块钱开始算
        for (int currAmount = 1; currAmount <= amount; currAmount++) {

            int currCost = Integer.MAX_VALUE;
            //对于当前金额，只需要看看当前有没有满足
            for (int j = 0; j < coins.length && currAmount - coins[j] >= 0; j++) {
                int remainAmount = currAmount - coins[j];
                currCost =dp[remainAmount] + 1;
            }
            dp[currAmount] = currCost;
        }
        return dp[amount] == Integer.MIN_VALUE ? -1 : dp[amount];


    }
}
