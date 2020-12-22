package com.example.arithmetic.changecoin;

import com.google.common.collect.ImmutableList;

public class CCRecursion {
    public static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) {

//        System.out.println(changeCoinsV2(0, new int[]{1, 2}, 3));
//        System.out.println(changeCoins(0, new int[]{1, 2}, 3));



        /*
         * int amount 当前剩余总金额
         * int[] coins 不同面值硬币数组
         * int count 当前次数
         * 每种硬币的个数i < amount/coins[idx]
         * 如果当前当前金额为0时，比较当前方式的次数和上一种方式的次数
         */
        changeCoinsV3(0, new int[]{3, 6}, 23);
        System.out.println(minCost);
        System.out.println(coinChange(0,new int[]{3, 6},10));;
    }

    static void changeCoinsV3(int currCount,  int[] coins, int currAmount) {
        if (currAmount < 0)
            return;
        if (currAmount == 0) {
            minCost = Math.min(currCount, minCost);
//            System.out.println(minCost);
        }

        for (int i = 0; i < coins.length; i++) {
            changeCoinsV3(currCount + 1, coins, currAmount - coins[i]);
        }


    }

    static int changeCoinsV2(int idxCoin, int[] coins, int amount) {
        if (amount == 0)
            return 0;
        //硬币面额没有用完或者剩余金额>0时处理递归
        if (idxCoin < coins.length && amount > 0) {
            //当前面值硬币最多个数
            int maxVal = amount / coins[idxCoin];
            //最小花费
            int minCost = Integer.MAX_VALUE;
            //当前面值硬币未到最大个数时
            for (int x = 0; x <= maxVal; x++) {
                //当前剩余金额大于x个当前面值硬币的总金额时，递归
                if (amount >= x * coins[idxCoin]) {
                    int res = changeCoinsV2(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1)
                        //当结束一次递归时设置最小次数
                        minCost = Math.min(minCost, res + x);
                }
            }
            return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
        }
        return -1;
    }




        private static int coinChange(int idxCoin, int[] coins, int amount) {
            if (amount == 0)
                return 0;
            if (idxCoin < coins.length && amount > 0) {
                int maxVal = amount / coins[idxCoin];
                int minCost = Integer.MAX_VALUE;
                for (int x = 0; x <= maxVal; x++) {
                    if (amount >= x * coins[idxCoin]) {
                        int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                        if (res != -1)
                            minCost = Math.min(minCost, res + x);
                    }
                }
                return (minCost == Integer.MAX_VALUE)? -1: minCost;
            }
            return -1;
        }



}
