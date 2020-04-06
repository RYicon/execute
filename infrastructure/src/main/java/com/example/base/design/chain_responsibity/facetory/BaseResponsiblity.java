package com.example.base.design.chain_responsibity.facetory;

@FunctionalInterface
public interface BaseResponsiblity<T,R> {
    R apply(T t);
}