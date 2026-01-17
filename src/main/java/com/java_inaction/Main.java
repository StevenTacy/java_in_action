package com.java_inaction;

import com.java_inaction.impl_stream.Dish;
import com.java_inaction.mapvsflatmap.StreamsToStream;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        System.out.println("Hello World");
        var nums1 = Arrays.asList(1, 2, 3);
        var nums2 = Arrays.asList(4, 5);

        System.out.println(StreamsToStream.getPairsByTwoLists(nums1, nums2));
    }
}
