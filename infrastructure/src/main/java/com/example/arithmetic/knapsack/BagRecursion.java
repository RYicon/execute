package com.example.arithmetic.knapsack;

import com.google.common.collect.Lists;

import java.util.List;

//背包递归算法
/**
 * f[v]=max{f[v],f[v-c]+w}
 * https://blog.csdn.net/puffsun/article/details/84092054
 * https://blog.csdn.net/shenyu1997/article/details/83237957?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
 * https://blog.csdn.net/li1500742101/article/details/8875202
 */
public class BagRecursion {

    /**
     * @param args
     */
    public static void main(String[] args) {

        //石头数组，数字代表每个石头重量
        int[] stones = {  2, 5,3};

        //背包总重量
        int totalWeight = 5;

        int[] bags = new int[stones.length];


//        put(1, stones, bags);
//
        List<Integer> count = Lists.newArrayList();
        select(totalWeight, 0, stones, bags, count);
//        for (int bag : bags) {
//            if (bag != 0)
//                System.out.println(bag);
//        }
        System.out.println("count:" + count.size());

    }

    /**
     * 背包
     */
    public static void select(int remainderTotalWeight, int offset, int[] stones, int[] bag, List<Integer> count) {

        print(bag,offset);
        if (remainderTotalWeight==0){
            System.out.println("结束一次计算");
            return;
        }


        //直到在石头数组中找到无法放入背包中的石头，否则offset++
        while (offset < stones.length && remainderTotalWeight < stones[offset]) {
            offset++;

        }
        count.add(1);

        if (remainderTotalWeight <= 0 || offset > stones.length - 1)
            return;

        //不放入背包
        select(remainderTotalWeight, offset + 1, stones, bag.clone(), count);

        //放入背包
        select(remainderTotalWeight - stones[offset], offset + 1, stones, put(stones[offset], bag), count);

    }


    /**
     * 背包
     * @param stone
     * @param bag
     * @return
     */
    static int[] put(int stone, int[] bag) {

        int pwd = 0;

        while (bag[pwd] > 0) {
            pwd++;
        }

            bag[pwd] = stone;

        return bag;
    }

    private static void print(int[] bag,int offset) {	//打印背包中的数字
        System.out.println("offset:"+offset);
        System.out.print("bag: [");
        for(int n: bag) {
            if(n == 0) break;
            System.out.print(n + ",");
        }
        System.out.print("]");
        System.out.println();
    }


}
