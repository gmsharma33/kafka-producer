package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 50000; i++) {
            list.add("hello " + i);
        }

        Stream<String> s = list.stream().filter(item -> item.endsWith("0"));
        List<String> l = s.collect(Collectors.toList());
        System.out.println("size = " + l.size() + "\n");
    }

}
