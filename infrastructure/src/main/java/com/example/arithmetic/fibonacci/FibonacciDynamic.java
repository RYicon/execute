package com.example.arithmetic.fibonacci;

/**
 * int fib(int N) {
 * vector<int> dp(N + 1, 0);
 * // base case
 * dp[1] = dp[2] = 1;
 * for (int i = 3; i <= N; i++)
 * dp[i] = dp[i - 1] + dp[i - 2];
 * return dp[N];
 * }
 * <p>
 * 作者：动态规划fibonacci 自底向上
 * 时间复杂度 O(N) 空间复杂度O（1）
 * 链接：https://leetcode-cn.com/problems/fibonacci-number/solution/dong-tai-gui-hua-tao-lu-xiang-jie-by-labuladong/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class FibonacciDynamic {

    public static void main(String[] args) {
        System.out.println(fib(3));
    }

    static int fib(int n) {
        if (n == 2 || n == 1)
            return 1;
        int prev = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }


}
