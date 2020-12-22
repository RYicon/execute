package com.example.arithmetic.knapsack;

import com.alibaba.fastjson.JSON;
import com.example.base.json.JsonUtil;

/**
 * 有N件物品和一个容量为V的背包。第i件物品的费用是c，价值是w。求解将哪些物品装入背包可 使这些物品的费用总和不超过背包容量，且价值总和最大。
 */
public class Knapsack_0_1_dynamic {

//    public static void main(String[] args) {
//
//        int[] stones = {1, 2, 3};
//        int[] values = new int[]{1, 3, 2};
//        int n = stones.length;
//        //
//        int[] dp = new int[n];
//        int totalWeight = 7;
//
//        int maxValueV1 = knapsack(totalWeight, stones, values);
//        int maxValueV2 = knapsackV2(stones, values, totalWeight);
//        System.out.println("总价值：" + maxValueV1);
//
//Integer
//    }
//
//
//    static int knapsackTest(int[] w, int[] v, int C) {
//
//        assert (w.length == v.length && C > 0);
//
//        int[] dp = new int[C + 1];
//        for (int i = 0; i <= C; i++) {
//
//        }
//    }
////    /**
////     * 对于每个石头，背包都尝试装一下，
////     *
////     * @param w
////     * @param v
////     * @param C
////     * @return
////     */
////    static int knapsackV2(int[] w, int[] v, int C) {
////        assert (w.length == v.length && C >= 0);
////        int n = w.length;
////        if (n == 0 || C == 0)
////            return 0;
////
////        int[] memo = new int[C + 1];
////
////        for (int i = 0; i < n; i++)
////            for (int j = C; j >= w[i]; j--) {
////                memo[j] = Math.max(memo[j], v[i] + memo[j - w[i]]);
////                System.out.println(String.format("i: %s,j:%s,memo[]:%s", i, j, JsonUtil.toString(memo)));
////            }
////
////
////        System.out.println(" 价值：" + JsonUtil.toString(memo));
////        return memo[C];
////    }
//
//    /**
//     * 对于当前totalWeight,挑选一个重量的石头，判断放入或者不放的最大价值，dp存储dp[currWeight-stoneWeight]的价值
//     *
//     * @param totalWeight
//     * @param stones
//     * @param values
//     * @return
//     */
//    public static int knapsack(int totalWeight, int[] stones, int[] values) {
//        int[][] dp = new int[totalWeight + 1][stones.length];
//
//        for (int i = 0; i < stones.length; i++) {
//            dp[0][i] = values[i] > dp[0][i] ? 0 : values[i];
//        }
//
//        for (int i = 1; i < totalWeight; i++) {
//            for (int j = 0; j < stones.length; j++) {
//                Math.max(dp[i - 1][j], values[j] + dp[i - j][j])
//            }
//        }
//        System.out.println(JsonUtil.toString(dp));
//        return 1;
////        for (int currTotalWeight = 1; currTotalWeight <= totalWeight; currTotalWeight++) {
////
////            int bestValue = dp[currTotalWeight-1];
////            int bestIdx = 0;
////
////            for (int idx = 0; idx < stones.length; idx++) {
////                int weight = stones[idx];
////                if (weight == 0) {
////                    continue;
////                }
////                int remainWeight = currTotalWeight - weight;
////                if (remainWeight < 0) {
////                    continue;
////                }
////                //可以选择石头时，表示出现当前可能的最优解，和记录的最优解比较，设置代驾小的值
////                int selectValue = dp[remainWeight] + values[idx];
////                if (selectValue-bestValue>0)
////
////
////                if (bestWeight == Integer.MAX_VALUE) {
////                    bestWeight = weight;
////                    bestIdx = idx;
//////                    stones[idx] = 0;
////                    continue;
////                }
////
////                if (selectValue <= values[bestIdx]) {
////                    continue;
////                }
////
////                bestWeight = weight;
////                bestIdx = idx;
//////                stones[idx] = 0;
////
////
////            }
////            //一轮重量中如果没有替换最优重量，表示可以使用上一个重量的dp
////            if (bestWeight == Integer.MAX_VALUE) {
////                dp[currTotalWeight] = dp[currTotalWeight - 1];
////            } else {
////                dp[currTotalWeight] = dp[currTotalWeight - 1] + values[bestIdx];
////                stones[bestIdx] = 0;
////            }
////        }
////
////        System.out.println(JsonUtil.toString(dp));
////
////        return dp[totalWeight];
//    }
}
