package com.tryingpfq.linkedlist.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Tryingpfq
 * @Time 2019/4/16 22:39
 * 基于数组实现的LRU缓存
 */
public class BaseLruArray<T>{

    private static final int DEFAULT_CAPACITY = 1 << 3;

    private int capaticy;

    private int count;

    private T[] value;

    private Map<T, Integer> holder;

    public BaseLruArray(){
        this(DEFAULT_CAPACITY);
    }

    public BaseLruArray(int capaticy){
        this.capaticy = capaticy;
        value = (T[])new Object[capaticy];
        count = 0;
        holder = new HashMap<T, Integer>(capaticy);
    }

    /**
     * 访问某个元素
     */
    public void offer(T object){
        if (object == null) {
            throw new IllegalArgumentException("this container is not support null");
        }
        Integer index = holder.get(object);
        if (index == null) {
            if (isFull()) {
                removeAndCache(object);
            }else{
                cache(object,count);
            }
        }else{
            update(index);
        }
    }

    /**
     * 若缓存中有指定的值，则更新位置
     * @param end
     */
    public void update(int end) {
        T target = value[end];
        rightShift(end);
        value[0] = target;
        holder.put(target, 0);
    }

    /**
     * 缓存数据到头部，但要先右移
     * @param object
     * @param end 数组右移的边界
     */
    public void cache(T object, int end) {
        rightShift(end);
        value[0] = object;
        holder.put(object, 0);
        count++;
    }

    /**
     * 将end左边数据统一右移一个位置
     * @param end
     */
    public void rightShift(int end){
        for(int i = end ; i > 0 ; i--){
            value[i] = value[i-1];
            holder.put(value[i-1],i);
        }
    }
    /**
     * 数组满时 把末尾的剔除
     * @param object
     * @return
     */
    public void removeAndCache(T object){
        T key = value[--count];
        holder.remove(key);
        cache(object,count);
    }
    public boolean isContain(T object){
        return holder.containsKey(object);
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public boolean isFull(){
        return count == capaticy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(value[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void testDefaultConstructor() {
        System.out.println("======无参测试========");
        BaseLruArray<Integer> lru = new BaseLruArray<Integer>();
        lru.offer(1);
        lru.offer(2);
        lru.offer(3);
        lru.offer(4);
        lru.offer(5);
        System.out.println(lru);
        lru.offer(6);
        lru.offer(7);
        lru.offer(8);
        lru.offer(9);
        System.out.println(lru);
    }

    public static void main(String[] args) {
        testDefaultConstructor();
    }
}
