package com.example.mutli.thread.customerTTL;

import com.alibaba.ttl.TransmittableThreadLocal;

public class CustomerTTL <T> extends TransmittableThreadLocal<T> {

    @Override
    protected void afterExecute() {
        super.afterExecute();
    }
}
