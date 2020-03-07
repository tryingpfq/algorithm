package com.tryingpfq.heap;
/**
 * @Author Tryingpfq
 * @Time 2020/03/05 8:55
 *
 * 堆(这里基于大顶堆构建的)
 */
public class Heap {
    private int[] a;
    private int n;

    private int count;

    public Heap(int capacity) {
        a = new int[capacity - 1];
        n = capacity;
    }


    public void insert(int data) {
        if (count >= n) {
            return;
        }
        a[++count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            swap(a, i, i / 2);
            i = i / 2;
        }
    }

    public void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * 删除堆顶元素
     */
    public void removeMax() {
        if (count == 0) {
            return;
        }
        a[1] = a[count--];

    }

    private void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 < n && a[i] < a[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    /**
     * 构建堆
     */
    public void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    /**
     * 将堆进行排序
     */
    public void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            heapify(a, --k, 1);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{-1, 1, 53, 23, 6, 97, 67, 32, 12, 33};
        Heap heap = new Heap(30);
        heap.buildHeap(a, 9);
        heap.sort(a, 9);
        for (int i = 1; i <= 9; i++) {
            System.out.print(a[i] + ",");
        }
    }
}
