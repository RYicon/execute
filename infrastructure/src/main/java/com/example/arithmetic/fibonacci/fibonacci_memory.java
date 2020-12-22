package com.example.arithmetic.fibonacci;

import java.util.Arrays;

/**
 * 递归斐波那契备忘录 自顶向下
 * 递归需要使用2叉树
 * 指数级别的时间复杂度o(2^n)
 * 时间复杂度 O(N) 空间复杂度O（1）
 */
public class fibonacci_memory {

    static int fibonacci(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }

        if (memo[n] == 0) {
            memo[n] = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
        }

        return memo[n];
    }

    public static void main(String[] args) {
        int counter = 10;
        int[] memo = new int[counter + 1];
        System.out.printf("Fibonacci of %d is: %d\n",
                counter, fibonacci(counter, memo));

    }
}