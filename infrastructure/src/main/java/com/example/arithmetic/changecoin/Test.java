package com.example.arithmetic.changecoin;

import com.alibaba.fastjson.JSON;

/**
 * 要点：每次
 */
public class Test {
    public static void main(String[] args) {
        int amount = 11;
//        minCost = new int[amount];
        int i = coinChangeRecursion(new int[]{2, 3, 4}, 9);
        System.out.println("总：" + i + "步骤：" + JSON.toJSONString(i));

        int j = coinChangeDp(new int[]{2, 3, 4}, 9);
        System.out.println("总：" + j + "步骤：" + JSON.toJSONString(j));
    }
//    static int minCost;

    //recursion
    /*找零钱 总金额：amount
     * int[] coins 不同面额的硬币
     * int minCost最小次数
     *
     */
    public static int coinChangeRecursion(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount==0){
            return 0;
        }

        int minCost = 0;
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeRecursion(coins, amount - coin);
            //每次能继续找零时，记录当前amount时的最小次数
            if (res > -1) {
                minCost = Math.min(min, res + 1);
            }


        }

        return minCost == Integer.MAX_VALUE ? -1 : minCost;

    }

    /**
     * dp长度应该是amount+1，方便理解，相应的for循环应该从1开始到amount结束
     * 每个amount的子循环中，查找currAmount的最优解min，子循环结束后给dp[currAmount]赋值
     * dp[amount]
     *
     * @param coins
     * @param totalAmount
     * @return
     */
    public static int coinChangeDp(int[] coins, int totalAmount) {
        if (totalAmount < 0) {
            return -1;
        }

        int[] dp = new int[totalAmount + 1];

        for (int currAmount = 1; currAmount <= totalAmount; currAmount++) {

            //当前amount下，除了dp[0]=0,其余dp[amount]只会是-1或者>0，
            int min = Integer.MAX_VALUE;

            for (int coin : coins) {

                int remainAmount = currAmount - coin;
                if (remainAmount < 0) {
                    continue;
                }
                if (dp[remainAmount] >= 0) {
                    min = Math.min(dp[remainAmount] + 1, min);
                }
            }
            //当currAmount时，应该对count=Integer.Max_Value过滤
            dp[currAmount] = min == Integer.MAX_VALUE ? -1 : min;
        }
        System.out.println(JSON.toJSONString(dp));
        return dp[totalAmount] > 0 ? dp[totalAmount] : -1;
    }
}
