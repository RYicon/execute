package com.example.arithmetic.跳阶梯;

//一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
public class Jump {

    public static void main(String[] args) {

        System.out.println(jump(new int[]{1,2},5));
    }

   static int jump(int[] jumpMethod, int totalCount) {


        if (totalCount < 0) {
            return -1;
        }

        //次数从1开始，所以dp数组也从1开始，方便理解
        int[] dp = new int[totalCount + 1];


        for (int currCount = 1; currCount <=totalCount; currCount++) {
            int minCount = Integer.MAX_VALUE;
            for (int method : jumpMethod) {
                if (currCount - method < 0) {
                    dp[currCount] = 0;
                    continue;
                }

                minCount = Math.min(dp[currCount - method] + 1, minCount);
            }
            dp[currCount] = minCount;

        }
        return dp[totalCount];
    }
}


