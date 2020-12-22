package com.example.arithmetic.fibonacci;


/**
 * 至底向上的备忘录
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 */
public class Fibonacci_dpmemory {

    static int fibonacci(int counter) {
        int[] dp = new int[counter + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= counter; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[counter];

    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        ;
    }
}
