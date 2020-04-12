package com.example.lock.jol;

import ch.qos.logback.core.Layout;
import com.example.lock.jol.data.NullClass;
import org.openjdk.jol.info.ClassLayout;

public class HashCodeTest {
    public static void main(String[] args) {
        NullClass nullClass = new NullClass();

        int i = nullClass.hashCode();


        System.out.printf("16位："+Integer.toHexString(i));
        System.out.printf(ClassLayout.parseInstance(nullClass).toPrintable());;

    }
}
