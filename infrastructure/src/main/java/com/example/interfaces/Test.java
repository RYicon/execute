package com.example.interfaces;

public class Test {
    public static void main(String[] args) throws Exception {
        String a = "aaa";

        String s1 = SoaWrap.callV2(a, s -> ToTest.Say(a));
        System.out.println(s1);


        String s2 = SoaWrap.callV2(a, ToTest::Say);
        System.out.println(s2);

        String s3 = SoaWrap.call(() -> ToTest.Say(a), a);
        System.out.println(s3);
    }
}
