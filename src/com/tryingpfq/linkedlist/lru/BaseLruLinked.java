package com.tryingpfq.linkedlist.lru;

import java.util.Scanner;

/**
 * @Author Tryingpfq
 * @Time 2019/4/17 23:46
 */
public class BaseLruLinked<T> {

    private final static int DEFAULT_CAPACITY = 8;

    private SNode headNode;

    private Integer length;

    private Integer capacity;

    public BaseLruLinked() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public BaseLruLinked(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data){
        SNode preNode = findPreNode(data);
        if(preNode == null){
            if(length >= capacity){
                deleteElementEnd();
            }
        }else{
            deleteElemOptim(preNode);
        }
        intsertElemAtBegin(data);
    }

    /**
     * 链表头部插入节点
     */
    private void intsertElemAtBegin(T data){
        SNode newNode = new SNode(data);
        newNode.setNext(headNode.getNext());
        headNode.setNext(newNode);
        length++;
    }

    /**
     * 删除指定节点的下一个节点
     */
    private void deleteElemOptim(SNode prNode){
        SNode temp = prNode.getNext();
        prNode.setNext(temp.getNext());
        temp = null;
        length --;
    }

    /**
     * 删除尾节点
     */
    private void deleteElementEnd(){
        SNode node = headNode;
        SNode preNode = null;
        if(headNode.next == null){
           return;
        }
        while(node.getNext() != null){
            preNode = node;
            node = node.next;
        }
        preNode.setNext(null);
        length--;
    }



    /**
     * 查找某元素的前一个节点
     * @param <T>
     */
    private SNode findPreNode(T data){
        SNode node = headNode;
        while(node.getNext() != null){
            if(data.equals(node.getNext().getElement())){
                return node;
            }
            node = node.getNext();
        }
        return null;
    }


    public class SNode<T>{
        private T element;

        private SNode next;

        public SNode(T element){
            this.element = element;
        }

        public SNode(T element,SNode next){
            this.element = element;
            this.next = next;
        }

        public SNode(){
            this.next = null;
        }

        public T getElement(){
            return element;
        }

        public void setElement(T element){
            this.element = element;
        }

        public SNode getNext(){
            return next;
        }

        public void setNext(SNode next){
            this.next = next;
        }
    }

    private void printAll() {
        SNode node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        BaseLruLinked list = new BaseLruLinked();
        Scanner sc = new Scanner(System.in);
       /* for(int i = 0 ; i< 10; i++){
            list.add(i);
            list.printAll();
        }*/
        while (true) {
            list.add(sc.nextInt());
            list.printAll();
        }
    }

}
