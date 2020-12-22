package com.example.arithmetic.fibonacci;

/**
 * 递归斐波那契 自顶向下
 * 递归需要使用2叉树
 * 指数级别的时间复杂度o(2^n)
 *  时间复杂度 O(N) 空间复杂度O（1）
 */
public class fibonacci {

    public static long fibonacci(long number) {


        long currentLong;
        long fibonacci2 = 0;
        long fibonacci1 = 0;
        if ((number == 0) || (number == 1))
            currentLong = number;
        else {
            fibonacci1 = fibonacci(number - 1);
            fibonacci2 = fibonacci(number - 2);
            currentLong = fibonacci1 + fibonacci2;
        }
        if (number ==0 ||number==1){
            System.out.println(number);
        }else
        System.out.println( "当前斐波那契值:" + currentLong+" " + "f("+number+"-1)：" + fibonacci1 +" "+ "f("+number+"-2):" + fibonacci2);
        System.out.println();
        return currentLong;
    }

    public static void main(String[] args) {
        int counter = 10;

        System.out.printf("Fibonacci of %d is: %d\n",
                counter, fibonacci(counter));

    }
}


