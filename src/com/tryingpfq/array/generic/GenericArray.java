package com.tryingpfq.array.generic;

/**
 * @Author Tryingpfq
 * @Time 2019/4/7 12:26
 * 自定义一个通用的数组，可支持泛型
 */
public class GenericArray<T> {
    private T[] data;      //data
    private int size;      //cur count


    //initialize this array
    public GenericArray(int capacity){
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    public GenericArray(){
        this(10);       //default
    }

    public int getCapacity(){
        return data.length;
    }

    // get element count
    public int getCount(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void set(int index,T e){
        checkIndex(index);
        this.data[index] = e;
    }

    /**
     * get An element by index
     * @param index
     * @return
     */
    public T get(int index){
        checkIndex(index);
        return data[index];
    }

    /**
     * judege the container is contain one element
     * @param e
     * @return
     */
    public boolean isContain(T e){
        for(int i = 0; i< size; i++){
            if(data[i] == e){
                return true;
            }
        }
        return false;
    }

    /**
     * find one element`s index
     * if is contain will return the index >= 0
     * if failed will return -1;
     * @param e
     * @return
     */
    public int findTheIndex(T e){
        for(int i = 0; i < size ; i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * add an element in a appoint index
     * @param index
     * @param e
     */
    public void add(int index,T e){
        checkIndex(index);
        // is full must dilatation
        if(size == data.length){
            resize(2 *data.length);
        }
        for(int i = size -1 ; i >= index ;i --){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    /**
     * add a element in the head
     * @param e
     */
    public void addFirst(T e){
        add(0,e);
    }

    /**
     * add in the tail
     * @param e
     */
    public void addTail(T e){
        add(size,e);
    }

    /**
     * remove an element from appoint index
     * @param index
     * @return
     */
    public T remove(int index){
        checkIndexForRemove(index);
        T ret = data[index];

        for(int i = index + 1; i<index ; i++){
            data[i-1] = data[i];
        }
        size --;
        data[size] = null;

        if (size == data.length / 4 && data .length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;
    }


    /**
     * delete the head
     * @return
     */
    public T removeFirst() {
        return remove(0);
    }

    /**
     * delete the tail
     * @return
     */
    public T removeLast() {
        return remove(size - 1);
    }

    /**
     * delete the appoint
     * @param e
     */
    public void removeElement(T e) {
        int index = findTheIndex(e);
        if (index != -1) {
            remove(index);
        }
    }

    // check index is legal
    public void checkIndex(int index){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed ! the index must >= 0 And <= size");
        }
    }

    private void checkIndexForRemove(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed! Require index >=0 and index < size.");
        }
    }

    /**
     * make a big array
     * @param capacity
     */
    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];

        // data remove
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        sb.append('[');
        for(int i = 0; i < size ;i ++){
            sb.append(data[i]);
            if(i != size - 1){
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
