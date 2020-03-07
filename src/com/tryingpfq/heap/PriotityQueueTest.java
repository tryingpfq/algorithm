package com.tryingpfq.heap;

import java.util.*;

/**
 * @Author tryingpfq
 * @Date 2020/3/7
 */
public class PriotityQueueTest {
    private static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(10, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    public static void main(String[] args) {
        int[] data = new int[]{3, 10, 2, 1, 60, 36};
        for (int i = 0; i < data.length; i++) {
            priorityQueue.offer(data[i]);
        }
        Object[] objects = priorityQueue.toArray();
        System.out.println(objects);
    }
}
