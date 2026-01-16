package com.java_inaction.impl_stream;

import java.util.Comparator;

public interface AppleComparator extends Comparator<Apple> {
    public int compare(Apple a1, Apple a2);
}
